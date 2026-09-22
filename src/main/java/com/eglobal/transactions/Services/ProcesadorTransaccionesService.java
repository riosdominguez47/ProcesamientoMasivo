package com.eglobal.transactions.Services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eglobal.transactions.Entity.ControlLotes;
import com.eglobal.transactions.Entity.DetalleErrores;
import com.eglobal.transactions.Entity.Transacciones;
import com.eglobal.transactions.Repository.ControlLoteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ProcesadorTransaccionesService {

	@PersistenceContext
	private EntityManager entityManager;

	private final ControlLoteRepository loteRepository;

	public ProcesadorTransaccionesService(ControlLoteRepository loteRepository) {
		this.loteRepository = loteRepository;
	}

	@Transactional
	public void procesarArchivoMasiVo(InputStream archivoInputStream, String nombreArchivo) {
		// 1. Registrar auditoría inicial del Lote
		ControlLotes lote = new ControlLotes();
		lote.setNombreArchivo(nombreArchivo);
		lote.setEstado("PROCESANDO");
		lote = loteRepository.save(lote);

		int BATCH_SIZE = 1000;
		int numeroLinea = 0;

		List<Transacciones> loteTransacciones = new ArrayList<>();
		List<DetalleErrores> loteErrores = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(archivoInputStream))) {
			String linea;

			while ((linea = reader.readLine()) != null) {
				numeroLinea++;

				try {

					Transacciones transaccion = mapearLineaATransaccion(linea, lote);
					loteTransacciones.add(transaccion);
				} catch (Exception e) {

					DetalleErrores error = new DetalleErrores();
					error.setLote(lote);
					error.setNumeroLinea(numeroLinea);
					error.setRegistroFallo(linea);
					error.setMotivoError(e.getMessage() != null ? e.getMessage() : "Error de formato");
					loteErrores.add(error);
				}

				// Persistencia eficiente por bloques (Batching)
				if (numeroLinea % BATCH_SIZE == 0) {
					persistirEnLote(loteTransacciones, loteErrores);
				}
			}

			persistirEnLote(loteTransacciones, loteErrores);

			// 5. Actualizar estado final del lote
			lote.setEstado(loteErrores.isEmpty() ? "COMPLETADO" : "COMPLETADO_CON_ERRORES");
			loteRepository.save(lote);

		} catch (Exception e) {
			lote.setEstado("FALLIDO");
			loteRepository.save(lote);
			throw new RuntimeException("Error crítico procesando el archivo masivo", e);
		}
	}

	private void persistirEnLote(List<Transacciones> transacciones, List<DetalleErrores> errores) {
		// Guardar transacciones válidas
		for (Transacciones t : transacciones) {
			entityManager.persist(t);
		}
		// Guardar errores encontrados
		for (DetalleErrores e : errores) {
			entityManager.persist(e);
		}

		// Sincroniza con la base de datos enviando el Batch y limpia la memoria RAM
		entityManager.flush();
		entityManager.clear();

		// Limpiar las listas de la memoria intermedia
		transacciones.clear();
		errores.clear();
	}

	private Transacciones mapearLineaATransaccion(String linea, ControlLotes lote) {
		String[] columnas = linea.split(",");

		if (columnas.length < 5) {
			throw new IllegalArgumentException("Número de columnas inválido. Se esperaban mínimo 5.");
		}

		Transacciones t = new Transacciones();
		t.setLote(lote);
		t.setCuentaOrigen(columnas[1].trim());
		t.setCuentaDestino(columnas[2].trim());
		System.out.println(columnas[3].trim());
		// Validación de reglas de negocio (Tolerancia a fallos)
		BigDecimal monto = new BigDecimal(columnas[3].trim());
		if (monto.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("El monto debe ser mayor a cero.");
		}
		t.setMonto(monto);

		 String textoFecha = columnas[4].trim();
	        
	     // Define el patrón exacto que coincide con tu texto
	     DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	     
	    // Realiza el parseo a LocalDateTime
	    LocalDateTime fechaHora = LocalDateTime.parse(textoFecha, formateador);
		t.setFechaTransaccion(fechaHora); // ISO-8601 recomendado
		t.setTipoOperacion(columnas[4].trim());

		if (columnas.length > 5) {
			t.setReferencia(columnas[5].trim());
		}

		return t;
	}

}

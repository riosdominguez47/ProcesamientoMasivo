package com.eglobal.transactions.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eglobal.transactions.Services.ProcesadorTransaccionesService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

	private final ProcesadorTransaccionesService procesadorService;

	public TransactionsController(ProcesadorTransaccionesService procesadorService) {
		this.procesadorService = procesadorService;
	}

	@PostMapping("/procesarTransacciones")
	public ResponseEntity<String> cargarArchivo(@RequestParam("archivo") MultipartFile archivo) {
		if (archivo.isEmpty()) {
			return ResponseEntity.badRequest().body("El archivo está vacío.");
		}

		try {
			procesadorService.procesarArchivoMasiVo(archivo.getInputStream(), archivo.getOriginalFilename());
			return ResponseEntity.ok("Archivo recibido y procesado exitosamente.");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error al procesar el archivo: " + e.getMessage());
		}
	}
	
	
	
	
	
	

}

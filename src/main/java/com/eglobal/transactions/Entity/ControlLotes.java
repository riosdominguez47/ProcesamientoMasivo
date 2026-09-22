package com.eglobal.transactions.Entity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "control_lotes")

public class ControlLotes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lote_id")
	private Long loteId;

	@Column(name = "nombre_archivo", nullable = false, length = 255)
	private String nombreArchivo;

	@Column(name = "total_registros", nullable = false)
	private Integer totalRegistros = 0;

	@Column(name = "exitosos", nullable = false)
	private Integer exitosos = 0;

	@Column(name = "fallidos", nullable = false)
	private Integer fallidos = 0;

	@Column(name = "fecha_inicio")
	private LocalDateTime fechaInicio;

	@Column(name = "fecha_fin")
	private LocalDateTime fechaFin;

	@Column(name = "estado", nullable = false, length = 50)
	private String estado = "PROCESANDO";

	public Long getLoteId() {
		return loteId;
	}

	public void setLoteId(Long loteId) {
		this.loteId = loteId;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public Integer getExitosos() {
		return exitosos;
	}

	public void setExitosos(Integer exitosos) {
		this.exitosos = exitosos;
	}

	public Integer getFallidos() {
		return fallidos;
	}

	public void setFallidos(Integer fallidos) {
		this.fallidos = fallidos;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}

package com.eglobal.transactions.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_errores")

public class DetalleErrores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "error_id")
	private Long errorId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lote_id", nullable = false)
	private ControlLotes lote;

	@Column(name = "numero_linea", nullable = false)
	private Integer numeroLinea;

	@Column(name = "registro_fallo", nullable = false, columnDefinition = "TEXT")
	private String registroFallo;

	@Column(name = "motivo_error", nullable = false, columnDefinition = "TEXT")
	private String motivoError;

	public Long getErrorId() {
		return errorId;
	}

	public void setErrorId(Long errorId) {
		this.errorId = errorId;
	}

	public ControlLotes getLote() {
		return lote;
	}

	public void setLote(ControlLotes lote) {
		this.lote = lote;
	}

	public Integer getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public String getRegistroFallo() {
		return registroFallo;
	}

	public void setRegistroFallo(String registroFallo) {
		this.registroFallo = registroFallo;
	}

	public String getMotivoError() {
		return motivoError;
	}

	public void setMotivoError(String motivoError) {
		this.motivoError = motivoError;
	}

}

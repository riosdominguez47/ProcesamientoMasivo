package com.eglobal.transactions.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacciones")
public class Transacciones {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "transaccion_id")
	    private Long transaccionId;
	 
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "lote_id", nullable = false)
	    private ControlLotes lote;

	    @Column(name = "cuenta_origen", nullable = false, length = 50)
	    private String cuentaOrigen;

	    @Column(name = "cuenta_destino", nullable = false, length = 50)
	    private String cuentaDestino;
	    
	    @Column(name = "monto", nullable = false, precision = 15, scale = 2)
	    private BigDecimal monto;

	    @Column(name = "fecha_transaccion", nullable = false)
	    private LocalDateTime fechaTransaccion;

	    @Column(name = "tipo_operacion", nullable = false)
	    private String tipoOperacion;

	    @Column(name = "referencia", length = 100)
	    private String referencia;

		public Long getTransaccionId() {
			return transaccionId;
		}

		public void setTransaccionId(Long transaccionId) {
			this.transaccionId = transaccionId;
		}

		public ControlLotes getLote() {
			return lote;
		}

		public void setLote(ControlLotes lote) {
			this.lote = lote;
		}

		public String getCuentaOrigen() {
			return cuentaOrigen;
		}

		public void setCuentaOrigen(String cuentaOrigen) {
			this.cuentaOrigen = cuentaOrigen;
		}

		public String getCuentaDestino() {
			return cuentaDestino;
		}

		public void setCuentaDestino(String cuentaDestino) {
			this.cuentaDestino = cuentaDestino;
		}

		public BigDecimal getMonto() {
			return monto;
		}

		public void setMonto(BigDecimal monto) {
			this.monto = monto;
		}

		public LocalDateTime getFechaTransaccion() {
			return fechaTransaccion;
		}

		public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
			this.fechaTransaccion = fechaTransaccion;
		}

		public String getTipoOperacion() {
			return tipoOperacion;
		}

		public void setTipoOperacion(String tipoOperacion) {
			this.tipoOperacion = tipoOperacion;
		}

		public String getReferencia() {
			return referencia;
		}

		public void setReferencia(String referencia) {
			this.referencia = referencia;
		}

	    
	  
}

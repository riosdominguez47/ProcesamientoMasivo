
CREATE TABLE control_lotes (
    lote_id BIGSERIAL PRIMARY KEY,
    nombre_archivo VARCHAR(255) NOT NULL,
    total_registros INT NOT NULL DEFAULT 0,
    exitosos INT NOT NULL DEFAULT 0,
    fallidos INT NOT NULL DEFAULT 0,
    fecha_inicio TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fecha_fin TIMESTAMP WITH TIME ZONE,
    estado VARCHAR(50) NOT NULL DEFAULT 'PROCESANDO'
 
);

-- Índices estratégicos para reportes y búsquedas operacionales de control
CREATE INDEX idx_control_lotes_estado ON control_lotes(estado);
CREATE INDEX idx_control_lotes_fecha ON control_lotes(fecha_inicio DESC);


-- TABLA PRINCIPAL DE TRANSACCIONES
-- Diseñada para alta velocidad de inserción.
CREATE TABLE transacciones (
    transaccion_id BIGSERIAL PRIMARY KEY,
    lote_id BIGINT NOT NULL, -- Relación con el lote de origen
    cuenta_origen VARCHAR(50) NOT NULL,
    cuenta_destino VARCHAR(50) NOT NULL,
    monto NUMERIC(15, 2) NOT NULL,
    fecha_transaccion TIMESTAMP WITH TIME ZONE NOT NULL,
	tipo_operacion  varchar NOT NULL,
    referencia VARCHAR(100),
    -- Llave foránea que conecta al lote correspondiente
    CONSTRAINT fk_transacciones_lote FOREIGN KEY (lote_id) REFERENCES control_lotes(lote_id) ON DELETE CASCADE
);


CREATE INDEX idx_transacciones_lote_id ON transacciones(lote_id);
CREATE INDEX idx_transacciones_fecha ON transacciones(fecha_transaccion DESC);
CREATE INDEX idx_transacciones_cuentas ON transacciones(cuenta_origen, cuenta_destino);



CREATE TABLE detalle_errores (
    error_id BIGSERIAL PRIMARY KEY,
    lote_id BIGINT NOT NULL,
    numero_linea INT NOT NULL,
    registro_fallo TEXT NOT NULL,
    motivo_error TEXT NOT NULL,
    CONSTRAINT fk_errores_lote FOREIGN KEY (lote_id) REFERENCES control_lotes(lote_id) ON DELETE CASCADE
);

-- Índices estratégicos para análisis de errores de carga
CREATE INDEX idx_errores_lote_id ON detalle_errores(lote_id);






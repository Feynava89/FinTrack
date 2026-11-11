
package com.fintrack.dto;

import java.math.BigDecimal;

public record CuentaResponse(
    Long id,
    String nombre,
    String tipo,
    BigDecimal saldoInicial,
    Long usuarioId
) {
}

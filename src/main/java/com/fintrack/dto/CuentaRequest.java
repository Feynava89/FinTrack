
package com.fintrack.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CuentaRequest(

    @NotBlank(message = "El nombre de la cuenta es obligatorio")
    @Size(max = 80)
    String nombre,

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    String tipo,

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.00",
                message = "El saldo no puede ser negativo")
    BigDecimal saldoInicial

) {
}

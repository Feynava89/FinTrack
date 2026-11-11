
package com.fintrack.dto;

import java.time.LocalDateTime;

public record UsuarioResponse(
    Long id,
    String nombre,
    String email,
    LocalDateTime fechaRegistro
) {
}

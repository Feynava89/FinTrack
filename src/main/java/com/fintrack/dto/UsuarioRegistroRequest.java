//Los datos que recibiremos al registrar un usuario
package com.fintrack.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRegistroRequest(
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede superar 80 caracteres")
    String nombre,

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    @Size(max = 150)
    String email,

     @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 100,
          message = "La contraseña debe tener entre 8 y 100 caracteres")
    String password

) {
}

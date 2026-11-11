//USUARIO
package com.fintrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(
    name="usuarios",
    uniqueConstraints={
        @UniqueConstraint(
            name="uk_usuario_email",
            columnNames="email"
        )
    }
)

public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="El nombre es obligatorio")
    @Size(max=80)
    @Column(nullable = false, length = 80)
    private String nombre;

    @NotBlank(message="El correo es obligatorio")
    @Email(message="El correo es valido")
    @Column(nullable=false,length=150)
    private String email;

    @NotBlank(message="La contraseña debe de ser valida")
    @Column(nullable=false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist//inserta la fecha cuando guardamos
    protected void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }

    

    public Usuario(){

    }

    public Long getId(){
       return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

}

package com.fintrack.repository;

import com.fintrack.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    //Ver si ya existe un usuario c
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
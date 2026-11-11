
package com.fintrack.controller;

import com.fintrack.dto.UsuarioRegistroRequest;
import com.fintrack.dto.UsuarioResponse;
import com.fintrack.entity.Usuario;
import com.fintrack.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> registrarUsuario(
        @Valid @RequestBody UsuarioRegistroRequest request
    ) {
        Usuario usuario = new Usuario();

        usuario.setNombre(request.nombre());
        usuario.setEmail(request.email());
        usuario.setPassword(request.password());

        Usuario usuarioGuardado =
            usuarioService.registrarUsuario(usuario);

        UsuarioResponse response = convertirAResponse(usuarioGuardado);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerUsuarios() {

        List<UsuarioResponse> usuarios =
            usuarioService.obtenerUsuarios()
                .stream()
                .map(this::convertirAResponse)
                .toList();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuario(
        @PathVariable Long id
    ) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);

        return ResponseEntity.ok(convertirAResponse(usuario));
    }

    private UsuarioResponse convertirAResponse(Usuario usuario) {
        return new UsuarioResponse(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getFechaRegistro()
        );
    }
}

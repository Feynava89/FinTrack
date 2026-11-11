
package com.fintrack.controller;

import com.fintrack.dto.CuentaRequest;
import com.fintrack.dto.CuentaResponse;
import com.fintrack.entity.Cuenta;
import com.fintrack.service.CuentaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta(
        @PathVariable Long usuarioId,
        @Valid @RequestBody CuentaRequest request
    ) {
        Cuenta cuenta = new Cuenta();

        cuenta.setNombre(request.nombre());
        cuenta.setTipo(request.tipo());
        cuenta.setSaldoInicial(request.saldoInicial());

        Cuenta cuentaGuardada =
            cuentaService.crearCuenta(usuarioId, cuenta);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(convertirAResponse(cuentaGuardada));
    }

    @GetMapping
    public ResponseEntity<List<CuentaResponse>> obtenerCuentas(
        @PathVariable Long usuarioId
    ) {
        List<CuentaResponse> cuentas =
            cuentaService.obtenerCuentasPorUsuario(usuarioId)
                .stream()
                .map(this::convertirAResponse)
                .toList();

        return ResponseEntity.ok(cuentas);
    }

    private CuentaResponse convertirAResponse(Cuenta cuenta) {
        return new CuentaResponse(
            cuenta.getId(),
            cuenta.getNombre(),
            cuenta.getTipo(),
            cuenta.getSaldoInicial(),
            cuenta.getUsuario().getId()
        );
    }
}

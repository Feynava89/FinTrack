
package com.fintrack.service;

import com.fintrack.entity.Cuenta;
import com.fintrack.entity.Usuario;
import com.fintrack.repository.CuentaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import com.fintrack.repository.UsuarioRepository;

@Service
public class CuentaService{
    private final CuentaRepository cuentaRepository;
    private final UsuarioService usuarioService;

    public CuentaService(CuentaRepository cuentaRepository,UsuarioService usuarioService){
        this.cuentaRepository=cuentaRepository;
        this.usuarioService=usuarioService;
    }

    @Transactional
    public Cuenta crearCuenta(Long usuarioId,Cuenta cuenta){
         if (cuenta.getSaldoInicial() == null ||
        cuenta.getSaldoInicial().compareTo(BigDecimal.ZERO)<0){
             throw new IllegalArgumentException(
                "El saldo inicial no puede ser negativo"
            );   
        }
        Usuario usuario=usuarioService.obtenerUsuarioPorId(usuarioId);
        
        cuenta.setUsuario(usuario);

        return cuentaRepository.save(cuenta);
    }

     @Transactional(readOnly = true)
    public List<Cuenta> obtenerCuentasPorUsuario(Long usuarioId) {

        usuarioService.obtenerUsuarioPorId(usuarioId);

        return cuentaRepository.findByUsuarioId(usuarioId);
    }



}
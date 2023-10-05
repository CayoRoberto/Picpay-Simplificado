package com.picpaysimplificado.dto;

import com.picpaysimplificado.domain.TipoDeUsuario;

import java.math.BigDecimal;

public record UsuarioDTO(String nome, String sobrenome, String  documento, String email, String senha, BigDecimal saldo, TipoDeUsuario tipoDeUsuario) {
}

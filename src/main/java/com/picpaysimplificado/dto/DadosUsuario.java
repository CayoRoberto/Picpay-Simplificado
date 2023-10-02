package com.picpaysimplificado.dto;

import com.picpaysimplificado.domain.TipoDeUsuario;

public record DadosUsuario(String nome, String sobrenome, String  documento, String email, String senha, Double saldo, TipoDeUsuario tipoDeUsuario) {
}

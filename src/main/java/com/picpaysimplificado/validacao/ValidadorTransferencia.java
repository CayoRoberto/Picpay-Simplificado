package com.picpaysimplificado.validacao;

import com.picpaysimplificado.domain.Usuario;

import java.math.BigDecimal;

public interface ValidadorTransferencia {

    public void validarTransferencia(Usuario remetente, Usuario destinatario, BigDecimal valor);
}

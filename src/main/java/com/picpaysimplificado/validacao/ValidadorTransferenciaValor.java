package com.picpaysimplificado.validacao;

import com.picpaysimplificado.domain.Usuario;
import com.picpaysimplificado.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidadorTransferenciaValor implements ValidadorTransferencia{
    @Override
    public void validarTransferencia(Usuario remetente, Usuario destinatario, BigDecimal valor)  {
        if( remetente.getSaldo().compareTo(valor) < 0){
            throw  new ValidacaoException("Saldo insuficiente");
        }
    }
}

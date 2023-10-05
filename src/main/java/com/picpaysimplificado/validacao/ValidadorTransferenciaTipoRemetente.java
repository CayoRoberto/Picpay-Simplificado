package com.picpaysimplificado.validacao;

import com.picpaysimplificado.domain.Usuario;
import com.picpaysimplificado.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class ValidadorTransferenciaTipoRemetente implements ValidadorTransferencia{

    @Override
    public void validarTransferencia(Usuario remetente, Usuario destinatario, BigDecimal valor){
        if(Objects.equals(remetente.getTipoDeUsuario().toString(), "LOJISTA")){
            throw new ValidacaoException("Usuário do tipo lojista não está autorizado para fazer transferência");
        }


    }
}

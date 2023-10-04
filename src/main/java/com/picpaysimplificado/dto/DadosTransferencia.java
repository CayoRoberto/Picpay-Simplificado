package com.picpaysimplificado.dto;

import com.picpaysimplificado.domain.Usuario;

import java.math.BigDecimal;

public record DadosTransferencia(Long idRemetente, Long idDestinatario , BigDecimal valor) {
}

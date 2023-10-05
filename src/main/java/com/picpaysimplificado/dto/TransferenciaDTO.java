package com.picpaysimplificado.dto;

import java.math.BigDecimal;

public record TransferenciaDTO(Long idRemetente, Long idDestinatario , BigDecimal valor) {
}

package com.picpaysimplificado.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Transferencia")
@Table(name = "transferencias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Transferencia {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @ManyToOne //Um usuario pode ter varias trnasferencias, mas uma transferencai só pode ter um usuario
 @JoinColumn(name="idRemetente")
 private Usuario remetente;
 @ManyToOne
 @JoinColumn(name="idDestinatario")
 private Usuario destinatario;
 private BigDecimal valor;
 private LocalDateTime data;


}

package com.picpaysimplificado.domain;

import com.picpaysimplificado.dto.DadosUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private String sobrenome;
    @Column(unique = true)
    private String documento;
    @Column(unique = true)
    private String email;
    private String senha;
    private Double saldo;
    private TipoDeUsuario tipoDeUsuario;

    public Usuario(DadosUsuario dados){
        this.nome = dados.nome();
        this.sobrenome = dados.sobrenome();
        this.documento = dados.documento();
        this.email = dados.email();
        this.senha = dados.senha();
        this.saldo = dados.saldo();
        this.tipoDeUsuario = dados.tipoDeUsuario();
    }
}

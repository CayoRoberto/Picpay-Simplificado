package com.picpaysimplificado.repository;

import com.picpaysimplificado.domain.Transferencia;
import com.picpaysimplificado.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}

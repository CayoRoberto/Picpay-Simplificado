package com.picpaysimplificado.service;

import com.picpaysimplificado.domain.Usuario;
import com.picpaysimplificado.dto.DadosUsuario;
import com.picpaysimplificado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    public Usuario cadastrar(DadosUsuario dados){
        Usuario usuario = new Usuario(dados);
        return repository.save(usuario);
    }
}

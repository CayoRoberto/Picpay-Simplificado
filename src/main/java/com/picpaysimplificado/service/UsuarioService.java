package com.picpaysimplificado.service;

import com.picpaysimplificado.domain.Usuario;

import com.picpaysimplificado.dto.UsuarioDTO;
import com.picpaysimplificado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    public Usuario cadastrar(UsuarioDTO dados){
        Usuario usuario = new Usuario(dados);
        return this.repository.save(usuario);
    }

    public Usuario encontrarUsuarioPorId(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(()->new Exception("Usuário não encontrado"));
    }


    public void salvar(Usuario usuario) {
        this.repository.save(usuario);
    }

    public List<Usuario> pegarTodosUsuarios() {
        return this.repository.findAll();
    }
}

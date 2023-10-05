package com.picpaysimplificado.controller;


import com.picpaysimplificado.domain.Usuario;
import com.picpaysimplificado.dto.UsuarioDTO;
import com.picpaysimplificado.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioDTO dados){
        Usuario novoUsuario = usuarioService.cadastrar(dados);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios(){
        List<Usuario> usuarios = this.usuarioService.pegarTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);

    }
}

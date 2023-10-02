package com.picpaysimplificado.controller;


import com.picpaysimplificado.domain.Usuario;
import com.picpaysimplificado.dto.DadosUsuario;
import com.picpaysimplificado.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody DadosUsuario dados){
        Usuario novoUsuario = usuarioService.cadastrar(dados);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }
}

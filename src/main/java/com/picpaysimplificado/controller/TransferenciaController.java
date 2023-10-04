package com.picpaysimplificado.controller;


import com.picpaysimplificado.domain.Transferencia;
import com.picpaysimplificado.dto.DadosTransferencia;
import com.picpaysimplificado.service.TransferenciaService;
import com.picpaysimplificado.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<Transferencia> realizarTransferencia(@RequestBody DadosTransferencia dados) throws Exception {
        transferenciaService.criarTransferencia(dados);
        return null;
    }
}

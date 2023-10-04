package com.picpaysimplificado.service;

import com.picpaysimplificado.domain.Usuario;
import com.picpaysimplificado.dto.DadosTransferencia;
import com.picpaysimplificado.repository.TransferenciaRepository;
import com.picpaysimplificado.validacao.ValidadorTransferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.client.WebClient;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private List<ValidadorTransferencia> validadores;

    @Autowired
    private RestTemplate restTamplete;



    public void criarTransferencia(DadosTransferencia dados) throws Exception {

        Usuario remetente = this.usuarioService.encontrarUsuarioPorId(dados.idRemetente());
        Usuario destinatario = this.usuarioService.encontrarUsuarioPorId(dados.idDestinatario());

        validadores.forEach(v ->v.validarTransferencia(remetente, destinatario, dados.valor()));



        if(!this.autorizarTransferencia()){
            throw new Exception("Transferencia não autorizada");
        }

        remetente.setSaldo(remetente.getSaldo().subtract(dados.valor()));
        destinatario.setSaldo(destinatario.getSaldo().add(dados.valor()));
    }

    private Boolean autorizarTransferencia() {

        //Infelizmente a url para autorizar a transferência não está mais funcionando
        //Esse código ficará apenas para aprendizagem

        // recuperar a representacao fazendo um Get na URL
//        ResponseEntity<Map> autorizacaoResponse = restTamplete.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
//
//        if(autorizacaoResponse.getStatusCode() == HttpStatus.OK && autorizacaoResponse.getBody().get("message") == "Autorizado"){
//            return true;
//        }
//        return false;


        // Código para gerar randomicamente se a transferencia será ou não autorizada
        String autorizar[] = {"Autorizado", "Não autorizado"};

        Random gerador = new Random();
        var index = gerador.nextInt(autorizar.length);

        if(autorizar[index] == "Autorizado"){
            return true;
        }else{
            return false;
        }

    }
}

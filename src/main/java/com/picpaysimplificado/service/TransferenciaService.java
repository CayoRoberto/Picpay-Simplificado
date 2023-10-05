package com.picpaysimplificado.service;

import com.picpaysimplificado.domain.Transferencia;
import com.picpaysimplificado.domain.Usuario;
import com.picpaysimplificado.dto.TransferenciaDTO;
import com.picpaysimplificado.repository.TransferenciaRepository;
import com.picpaysimplificado.validacao.ValidadorTransferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
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

    @Autowired
    private NotificacaoService noticacao;



    public Transferencia criarTransferencia(TransferenciaDTO dados) throws Exception {

        Usuario remetente = this.usuarioService.encontrarUsuarioPorId(dados.idRemetente());
        Usuario destinatario = this.usuarioService.encontrarUsuarioPorId(dados.idDestinatario());

        validadores.forEach(v ->v.validarTransferencia(remetente, destinatario, dados.valor()));



        if(!this.autorizarTransferencia()){
            throw new Exception("Transferência não autorizada!");
        }

        Transferencia transferencia = new Transferencia();
        transferencia.setRemetente(remetente);
        transferencia.setDestinatario(destinatario);
        transferencia.setValor(dados.valor());
        transferencia.setData(LocalDateTime.now());


        remetente.setSaldo(remetente.getSaldo().subtract(dados.valor()));
        destinatario.setSaldo(destinatario.getSaldo().add(dados.valor()));


        this.repository.save(transferencia);
        this.usuarioService.salvar(remetente);
        this.usuarioService.salvar(destinatario);

        noticacao.notificacaoTransferencia(remetente, "Transferência realizada com sucesso");
        noticacao.notificacaoTransferencia(destinatario, "Transferência recebida com sucesso");
        return transferencia;
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

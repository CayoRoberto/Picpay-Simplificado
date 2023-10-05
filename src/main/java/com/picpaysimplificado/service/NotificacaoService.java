package com.picpaysimplificado.service;

import com.picpaysimplificado.domain.Usuario;
import com.picpaysimplificado.dto.NotificacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacaoService {

    @Autowired
    private RestTemplate restTemplate;

    public void notificacaoTransferencia(Usuario usuario, String mensagem) throws Exception {
        String email = usuario.getEmail();
        NotificacaoDTO notificacaoRequest = new NotificacaoDTO(email, mensagem);

        //Infelizmente a url proposta para uso no desafio não está mais funcionando
        //Esse código ficará apenas para aprendizagem
//        ResponseEntity<String> notificacaoResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificacaoRequest, String.class);
//
//        if(!(notificacaoResponse.getStatusCode() == HttpStatus.OK)){
//            System.out.println("Erro ao enviar notificação");
//            throw new Exception("Serviço de notificação está fora do ar");
//        }

        System.out.println("Notificação enviada para o usuário");
    }
}

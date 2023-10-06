package com.picpaysimplificado.controller;

import com.picpaysimplificado.domain.TipoDeUsuario;
import com.picpaysimplificado.domain.Transferencia;
import com.picpaysimplificado.domain.Usuario;
import com.picpaysimplificado.dto.TransferenciaDTO;
import com.picpaysimplificado.dto.UsuarioDTO;
import com.picpaysimplificado.service.TransferenciaService;
import com.picpaysimplificado.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TransferenciaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<TransferenciaDTO> transferenciaDTOJson;

    @Autowired
    private JacksonTester<Transferencia> transferenciaJson;

    @MockBean
    private TransferenciaService transferenciaService;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deveria devolver http 200 quando a transferencia é feito por dois usuários do tipo comum")
    void realizarTransferenciacenario1() throws Exception {
        var valor = BigDecimal.valueOf(10);
        var data = LocalDateTime.now();
        var valorUsuarioteste1 = BigDecimal.valueOf(100);
        var usuarioteste1 = new UsuarioDTO(
                "Peter",
                "Parker",
                "000022000000",
                "ppark@email.com",
                "123456",
                valorUsuarioteste1,
                TipoDeUsuario.COMUM
        );
        var valorUsuarioteste2 = BigDecimal.valueOf(100);
        var usuarioteste2 = new UsuarioDTO(
                "Mary",
                "Jane",
                "000023000000",
                "mjwatson@email.com",
                "123457",
                valorUsuarioteste2,
                TipoDeUsuario.COMUM
        );
        Usuario remetente = usuarioService.cadastrar(usuarioteste1);
        Usuario destinatario = usuarioService.cadastrar(usuarioteste2);

        // Fazer um mockito para que o teste utilize o mock no lugar  consultar o banco de dados para passar
        //para o método criarTransferencia da classe TransferenciaService
        var transferenciaRetorno = new Transferencia(null, remetente,destinatario, valor, data);

        when(transferenciaService.criarTransferencia(any())).thenReturn(transferenciaRetorno);

        // enviar um json mock para a API realizar o teste
        var response = mvc
                .perform(post("/transferencia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transferenciaDTOJson.write(
                                new TransferenciaDTO(1l,2l, valor)).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        //Verificar o json que a API esta devolvendo
        var jsonEsperado = transferenciaJson.write(
                transferenciaRetorno).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}
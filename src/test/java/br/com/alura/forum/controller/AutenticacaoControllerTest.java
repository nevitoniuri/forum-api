package br.com.alura.forum.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutenticacaoControllerTest {

    URI uri = URI.create("/auth");
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornar401AoRealizarLoginDeUsuarioInvalido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\")\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginDeveRetornarStatus400() throws Exception {

        String json = "{\"email\":\"invalido@email.com\", \"senha\":\"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void deveRetornar403aoTentarDeletarTopico() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(new URI("/topicos/1")))
                .andExpect(status().isForbidden());
    }


}
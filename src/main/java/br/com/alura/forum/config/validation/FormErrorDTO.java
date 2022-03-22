package br.com.alura.forum.config.validation;

import lombok.Getter;

@Getter
public class FormErrorDTO {

    private String campo;
    private String mensagem;

    public FormErrorDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }
}

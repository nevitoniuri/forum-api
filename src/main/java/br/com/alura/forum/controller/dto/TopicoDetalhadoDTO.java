package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class TopicoDetalhadoDTO {

    private final Long id;
    private final String titulo;
    private final String mensagem;
    private final LocalDateTime dataCriacao;
    private final StatusTopico status;
    private final String nomeAutor;
    private final List<RespostaDTO> respostas;


    public TopicoDetalhadoDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus();

        if (Objects.isNull(topico.getAutor())) {
            this.nomeAutor = "Aluno";
        } else {
            this.nomeAutor = topico.getAutor().getNome();
        }

        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
    }

}

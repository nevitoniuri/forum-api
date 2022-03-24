package br.com.alura.forum.service;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.TopicoFormCadastrar;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Page<TopicoDTO> listarTopicos(String nomeCurso, Pageable paginacao) {

        Page<Topico> topicos;

        if (nomeCurso == null) {
            topicos = topicoRepository.findAll(paginacao);
        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
        }
        return TopicoDTO.toDTO(topicos);
    }

    public Topico cadastrarTopico(TopicoFormCadastrar topicoFormCadastrar) {
        Topico topico = topicoFormCadastrar.toTopico(cursoRepository);
        topicoRepository.save(topico);
        return topico;
    }

    public Optional<Topico> detalharTopico(Long id) {

        return topicoRepository.findById(id);
    }
}

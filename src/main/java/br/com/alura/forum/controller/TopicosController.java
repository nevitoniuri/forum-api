package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDetalhadoDTO;
import br.com.alura.forum.controller.form.TopicoFormAtualizar;
import br.com.alura.forum.controller.form.TopicoFormCadastrar;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> listar(String nomeCurso) {

        List<Topico> topicos;
        if (nomeCurso == null) {
            topicos = topicoRepository.findAll();
        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso);
        }

        return ResponseEntity.ok(TopicoDTO.toDTO(topicos));
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoFormCadastrar topicoFormCadastrar,
                                               UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = topicoFormCadastrar.toTopico(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalhadoDTO> detalhar(@PathVariable Long id) {
        Topico topico = topicoRepository.getById(id);

        return ResponseEntity.ok(new TopicoDetalhadoDTO(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id,
                                               @RequestBody @Valid TopicoFormAtualizar topicoFormAtualizar) {

        Topico topico = topicoRepository.getById(id);
        topico.setTitulo(topicoFormAtualizar.getTitulo());
        topico.setMensagem(topicoFormAtualizar.getMensagem());

        Topico topicoAtualizado = topicoRepository.saveAndFlush(topico);

        return ResponseEntity.ok(new TopicoDTO(topicoAtualizado));
    }


}

package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDetalhadoDTO;
import br.com.alura.forum.controller.form.TopicoFormAtualizar;
import br.com.alura.forum.controller.form.TopicoFormCadastrar;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import br.com.alura.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    @Cacheable(value = "listaTopicos")
    public ResponseEntity<Page<TopicoDTO>> listarTopicos(@RequestParam(required = false) String nomeCurso,
                                                         @PageableDefault(page = 0, size = 10, sort = "id",
                                                                 direction = Sort.Direction.DESC)
                                                                 Pageable paginacao) {
        return ResponseEntity.ok(topicoService.listarTopicos(nomeCurso, paginacao));
    }

    @PostMapping
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> cadastrarTopico(@RequestBody @Valid TopicoFormCadastrar topicoFormCadastrar,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoService.cadastrarTopico(topicoFormCadastrar);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalhadoDTO> detalharTopico(@PathVariable Long id) {

        Optional<Topico> topicoBuscado = topicoService.detalharTopico(id);

        if (topicoBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new TopicoDetalhadoDTO(topicoBuscado.get()));
        }
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> atualizarTopico(@PathVariable Long id,
                                                     @RequestBody @Valid TopicoFormAtualizar topicoFormAtualizar) {

        Optional<Topico> topicoBuscado = topicoRepository.findById(id);

        if (topicoBuscado.isPresent()) {
            topicoBuscado.get().setTitulo(topicoFormAtualizar.getTitulo());
            topicoBuscado.get().setMensagem(topicoFormAtualizar.getMensagem());

            Topico topicoAtualizado = topicoRepository.saveAndFlush(topicoBuscado.get());
            return ResponseEntity.ok(new TopicoDTO(topicoAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<?> removerTopico(@PathVariable Long id) {

        Optional<Topico> topicoBuscado = topicoRepository.findById(id);

        if (topicoBuscado.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}

package br.com.alura.forum.repository;

import br.com.alura.forum.model.Curso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void deveCarregarUmCursoAoBuscarPeloNome() {
        String nomeCurso = "Spring Boot";

        Curso curso = new Curso();
        curso.setNome(nomeCurso);
        curso.setCategoria("Programação");

        testEntityManager.persist(curso);

        Curso cursoBuscado = cursoRepository.findByNome(nomeCurso);
        Assert.assertNotNull(cursoBuscado);
        Assert.assertEquals(cursoBuscado.getNome(), nomeCurso);
    }

    @Test
    public void deveRetornarNullQuandoCursoNaoExiste() {
        String nomeCurso = "Jpa";
        Curso curso = cursoRepository.findByNome(nomeCurso);

        Assert.assertNull(curso);
    }
}

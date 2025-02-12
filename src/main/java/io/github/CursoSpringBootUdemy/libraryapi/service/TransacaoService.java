package io.github.CursoSpringBootUdemy.libraryapi.service;

import io.github.CursoSpringBootUdemy.libraryapi.model.Autor;
import io.github.CursoSpringBootUdemy.libraryapi.model.GeneroLivro;
import io.github.CursoSpringBootUdemy.libraryapi.model.Livro;
import io.github.CursoSpringBootUdemy.libraryapi.repository.AutorRepository;
import io.github.CursoSpringBootUdemy.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository.findById(UUID.fromString("1535ba05-12c0-4bb9-8633-9e95c748dfa2")).orElse(null);
        livro.setDataPublicacao(LocalDate.of(2023, 1, 2));

    }

    @Transactional
    public void executar() {
        Autor autor = new Autor();
        autor.setNome("Jeffery");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(2005, 6, 24));

        Livro livro = new Livro();
        livro.setIsbn("900900");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Thugger");
        livro.setDataPublicacao(LocalDate.of(2016, 6, 24));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Jeffery")) {
            throw new RuntimeException("Rollback!");
        }
    }
}

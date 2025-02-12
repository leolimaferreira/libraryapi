package io.github.CursoSpringBootUdemy.libraryapi.repository;

import io.github.CursoSpringBootUdemy.libraryapi.model.Autor;
import io.github.CursoSpringBootUdemy.libraryapi.model.GeneroLivro;
import io.github.CursoSpringBootUdemy.libraryapi.model.Livro;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Leonardo");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2005, 6, 24));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id =UUID.fromString("e3667ddc-a217-4409-b6c6-a0933fa9e905");

        Optional<Autor> possivelAutor= autorRepository.findById(id);

        if(possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(2006, 6, 5));

            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void deleteAllTeste() {
        autorRepository.deleteAll();
    }

    @Test
    public void listarTeste() {
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("28d1cd24-c7d2-49c8-8e29-2c927c5b3138");

        Optional<Autor> possivelAutor= autorRepository.findById(id);

        if(possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            autorRepository.deleteById(autorEncontrado.getId());
        }
    }

    public void deleteObjectTest() {
        var id = UUID.fromString("f354d702-2d82-4a17-b3ff-0bae14377d78");

        Optional<Autor> possivelAutor= autorRepository.findById(id);

        if(possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            autorRepository.delete(autorEncontrado);
        }
    }

    @Test
    public void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Kenny");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(2005, 6, 24));

        Livro livro = new Livro();
        livro.setIsbn("900900");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("League of Legends");
        livro.setDataPublicacao(LocalDate.of(2016, 6, 24));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("900900");
        livro2.setPreco(BigDecimal.valueOf(100));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("Fortnite");
        livro2.setDataPublicacao(LocalDate.of(2016, 6, 24));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);


        autorRepository.save(autor);
    }

    @Test
    public void listarLivrosAutor() {
        UUID idAutor = UUID.fromString("3eed50bb-6b35-463d-8c5d-030bf71dec6e");
        var autor = autorRepository.findById(idAutor).get();
        List<Livro> listaLivros = livroRepository.findByAutor(autor);
        autor.setLivros(listaLivros);
        autor.getLivros().forEach(System.out::println);
    }

}

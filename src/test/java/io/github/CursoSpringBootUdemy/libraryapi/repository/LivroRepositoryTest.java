package io.github.CursoSpringBootUdemy.libraryapi.repository;

import io.github.CursoSpringBootUdemy.libraryapi.model.Autor;
import io.github.CursoSpringBootUdemy.libraryapi.model.GeneroLivro;
import io.github.CursoSpringBootUdemy.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("900900");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Ciencia");
        livro.setDataPublicacao(LocalDate.of(2016, 6, 24));
        Autor autor = autorRepository.findById(UUID.fromString("19c958d1-bd6a-43a0-8c40-94a3ea22dfd0")).orElse(null);
        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void salvarTestCascade() {
        Livro livro = new Livro();
        livro.setIsbn("900900");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(2016, 6, 24));

        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2005, 6, 24));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("900900");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Terceiro Livro");
        livro.setDataPublicacao(LocalDate.of(2016, 6, 24));

        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2005, 6, 24));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutorLivroTest() {
        UUID idLivro = UUID.fromString("c6145bbb-64cd-43e5-a65b-b2041bcfb9ec");
        var livroParaAtualizar = livroRepository.findById(idLivro).orElse(null);
        UUID idAutor = UUID.fromString("f354d702-2d82-4a17-b3ff-0bae14377d78");
        Autor autorParaAtualizar = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autorParaAtualizar);

        livroRepository.save(livroParaAtualizar);

    }

    @Test
    public void deletarLivroTest() {
        UUID idLivro = UUID.fromString("7ee04ad4-a339-4ef5-94f4-62d0a2a09809");
        livroRepository.deleteById(idLivro);
    }

    @Test
    @Transactional
    public void buscarLivroTest() {
        UUID idLivro = UUID.fromString("1535ba05-12c0-4bb9-8633-9e95c748dfa2");
        Livro livro = livroRepository.findById(idLivro).orElse(null);
        System.out.print("Livro: ");
        System.out.println(livro.getTitulo());

        System.out.print("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    public void pesquisaPorTituloTest() {
        List<Livro> lista = livroRepository.findByTitulo("League of Legends");
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisarPorIsbnTest() {
        Optional<Livro> livro = livroRepository.findByIsbn("900900");
        livro.ifPresent(System.out::println);
    }

    @Test
    public void pesquisarPorTituloEPrecoTest() {
        List<Livro> lista = livroRepository.findByTituloAndPreco("League of Legends", BigDecimal.valueOf(100));
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisarPorTituloOuPrecoTest() {
        List<Livro> lista = livroRepository.findByTituloOrPreco("", BigDecimal.valueOf(100));
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisarDistintoPorPrecoTest() {
        List<Livro> lista = livroRepository.findDistinctByPreco(BigDecimal.valueOf(100));
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisarPorTituloIncertoTest() {
        List<Livro> lista = livroRepository.findByTituloContaining("of");
        lista.forEach(System.out::println);
    }

    @Test
    public void listarTodosComJPQL() {
        List<Livro> livros = livroRepository.listarTodosOrdenadoPorTituloEPreco();
        livros.forEach(System.out::println);
    }

    @Test
    public void listarAutoresDosLivrosJPQL() {
        List<Autor> autoresDosLivros = livroRepository.listarAutoresDosLivros();
        autoresDosLivros.forEach(System.out::println);
    }

    @Test
    public void listarNomesLivrosDistintosJPQL() {
        List<String> nomesDosLivros = livroRepository.listarNomeDiferentesLivros();
        nomesDosLivros.forEach(System.out::println);
    }

    @Test
    public void listarGenerosBrasileirosJPQL() {
        List<String> generoDosLivros = livroRepository.listarGenerosAutoresBrasileiros();
        generoDosLivros.forEach(System.out::println);
    }

    @Test
    public void listarLivrosPorGeneroEComBasePropriedadeJPQL() {
        List<Livro> livros = livroRepository.findByGenero(GeneroLivro.FICCAO, "titulo");
        livros.forEach(System.out::println);
    }
    @Test
    public void listarLivrosPositionalParametersJPQL() {
        List<Livro> livros = livroRepository.findByGeneroPositionalParameters(GeneroLivro.FICCAO, "titulo");
        livros.forEach(System.out::println);
    }

    @Test
    public void deletePorGeneroTest() {
        livroRepository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    public void updateDataPublicacaoTest() {
        livroRepository.updateDataPublicacao(LocalDate.of(2025, 2, 3));
    }
}
package io.github.CursoSpringBootUdemy.libraryapi.repository;

import io.github.CursoSpringBootUdemy.libraryapi.model.Autor;
import io.github.CursoSpringBootUdemy.libraryapi.model.GeneroLivro;
import io.github.CursoSpringBootUdemy.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 *
 */

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    Optional<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    List<Livro> findByTituloOrPreco(String titulo, BigDecimal preco);

    List<Livro> findDistinctByPreco(BigDecimal preco);

    List<Livro> findByTituloContaining(String titulo);

    @Query("select l from Livro as l order by l.titulo, l.preco")
    List<Livro> listarTodosOrdenadoPorTituloEPreco();

    @Query("select a from Livro as l join l.autor a")
    List<Autor> listarAutoresDosLivros();

    @Query("select distinct l.titulo from Livro as l")
    List<String> listarNomeDiferentesLivros();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileiro'
            order by l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();

    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, @Param("paramOrdenacao") String nomeDaPropriedade);

    @Query("select l from Livro l where l.genero = ?1 order by ?2 ")
    List<Livro> findByGeneroPositionalParameters(GeneroLivro generoLivro, String nomeDaPropriedade);

    @Transactional
    @Modifying
    @Query("delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro generoLivro);

    @Transactional
    @Modifying
    @Query("update Livro set dataPublicacao = ?1")
    void updateDataPublicacao(LocalDate dataPublicacao);

    boolean existsByAutor(Autor autor);

}

package io.github.CursoSpringBootUdemy.libraryapi.service;

import io.github.CursoSpringBootUdemy.libraryapi.model.GeneroLivro;
import io.github.CursoSpringBootUdemy.libraryapi.model.Livro;
import io.github.CursoSpringBootUdemy.libraryapi.model.Usuario;
import io.github.CursoSpringBootUdemy.libraryapi.repository.LivroRepository;
import io.github.CursoSpringBootUdemy.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static io.github.CursoSpringBootUdemy.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroValidator validator;
    private final SecurityService securityService;

    public Livro salvar(Livro livro) {
         validator.validar(livro);
         Usuario usuario = securityService.obterUsuarioLogado();
         livro.setUsuario(usuario);
         return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return livroRepository.findById(id);
    }

    public void excluir(Livro livro) {
        livroRepository.delete(livro);
    }

    public Page<Livro> pesquisa(
            String isbn,
            String titulo,
            String nomeAutor,
            GeneroLivro genero,
            Integer anoPublicacao,
            Integer pagina,
            Integer tamanhoPagina) {
        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(isbn != null) {
            specs = specs.and(isbnEqual(isbn));
        }

        if(titulo != null) {
            specs = specs.and(tituloLike(titulo));
        }

        if(genero != null) {
            specs = specs.and(generoEqual(genero));
        }

        if(anoPublicacao != null) {
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if(nomeAutor != null) {
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return livroRepository.findAll(specs, pageRequest);
    }

    public void atualizar(Livro livro) {
        validator.validar(livro);
        if(livro.getId() == null) {
            throw new IllegalArgumentException("Livro não cadastrado!");
        }

        livroRepository.save(livro);
    }
}

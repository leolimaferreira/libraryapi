package io.github.CursoSpringBootUdemy.libraryapi.repository;

import io.github.CursoSpringBootUdemy.libraryapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByLogin(String login);

    Usuario findByEmail(String email);
}

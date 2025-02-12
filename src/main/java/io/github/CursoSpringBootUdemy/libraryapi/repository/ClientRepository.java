package io.github.CursoSpringBootUdemy.libraryapi.repository;

import io.github.CursoSpringBootUdemy.libraryapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByClientId(String clientId);
}

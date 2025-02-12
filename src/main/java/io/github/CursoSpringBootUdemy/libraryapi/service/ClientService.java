package io.github.CursoSpringBootUdemy.libraryapi.service;

import io.github.CursoSpringBootUdemy.libraryapi.model.Client;
import io.github.CursoSpringBootUdemy.libraryapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    public void salvar(Client client) {
        var senhaCriptografada = encoder.encode(client.getClientSecret());
        client.setClientSecret(senhaCriptografada);
        clientRepository.save(client);
    }

    public Client obterPorClientID(String clientId) {
        return clientRepository.findByClientId(clientId);
    }
}

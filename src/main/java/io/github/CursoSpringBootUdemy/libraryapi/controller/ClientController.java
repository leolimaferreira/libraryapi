package io.github.CursoSpringBootUdemy.libraryapi.controller;

import io.github.CursoSpringBootUdemy.libraryapi.model.Client;
import io.github.CursoSpringBootUdemy.libraryapi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Salvar", description = "Cadastrar novo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "202", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Cliente já cadastrado.")
    })
    public void salvar(@RequestBody Client client) {
        log.info("Registrando novo Client: {} com scope: {} ", client.getClientId(), client.getScope());
        clientService.salvar(client);
    }
}

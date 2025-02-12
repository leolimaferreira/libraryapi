package io.github.CursoSpringBootUdemy.libraryapi.controller;

import io.github.CursoSpringBootUdemy.libraryapi.controller.dto.UsuarioDTO;
import io.github.CursoSpringBootUdemy.libraryapi.controller.mappers.UsuarioMapper;
import io.github.CursoSpringBootUdemy.libraryapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar", description = "Cadastrar novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "202", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado.")
    })
    public void salvar(@RequestBody @Valid UsuarioDTO dto) {
        var usuario = usuarioMapper.toEntity(dto);
        usuarioService.salvar(usuario);
    }
}

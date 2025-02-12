package io.github.CursoSpringBootUdemy.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.util.List;

@Schema(name = "Erro Resposta")
public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrao(String mensagem) {
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(),mensagem, List.of());
    }

    public static  ErroResposta conflito(String mensagem) {
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }

}

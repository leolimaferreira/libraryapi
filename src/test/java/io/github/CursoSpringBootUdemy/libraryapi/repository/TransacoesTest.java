package io.github.CursoSpringBootUdemy.libraryapi.repository;

import io.github.CursoSpringBootUdemy.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    @Test
    void transacaoSimples() {
        transacaoService.executar();
    }

    @Test
    void transacaoExecutarSemExecutar() {
        transacaoService.atualizacaoSemAtualizar();
    }
}

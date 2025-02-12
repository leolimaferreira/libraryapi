package io.github.CursoSpringBootUdemy.libraryapi.exceptions;

public class RegistroDuplicadoException extends RuntimeException {
    public RegistroDuplicadoException(String message) {
        super(message);
    }

}

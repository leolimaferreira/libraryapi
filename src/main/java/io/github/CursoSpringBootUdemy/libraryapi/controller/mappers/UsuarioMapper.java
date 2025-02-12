package io.github.CursoSpringBootUdemy.libraryapi.controller.mappers;

import io.github.CursoSpringBootUdemy.libraryapi.controller.dto.UsuarioDTO;
import io.github.CursoSpringBootUdemy.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}

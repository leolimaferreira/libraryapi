package io.github.CursoSpringBootUdemy.libraryapi.controller.mappers;

import io.github.CursoSpringBootUdemy.libraryapi.controller.dto.AutorDTO;
import io.github.CursoSpringBootUdemy.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}

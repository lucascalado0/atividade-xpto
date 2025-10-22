package com.example.atividade_xpto.core.mappers.endereco;

import com.example.atividade_xpto.core.dtos.endereco.EnderecoDTO;
import com.example.atividade_xpto.core.models.endereco.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoDTO toDTO(Endereco endereco);

    @Mapping(target = "cliente", ignore = true)
    Endereco toEntity(EnderecoDTO enderecoDTO);

    List<EnderecoDTO> toDTOList(List<Endereco> enderecos);
}

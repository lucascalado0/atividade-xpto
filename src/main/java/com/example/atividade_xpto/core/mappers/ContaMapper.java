package com.example.atividade_xpto.core.mappers;

import com.example.atividade_xpto.core.dtos.contas.ContaDTO;
import com.example.atividade_xpto.core.models.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContaMapper {
    @Mapping(target = "cliente", ignore = true)
    Conta toEntity(ContaDTO dto);

    ContaDTO toDTO(Conta entity);

    List<ContaDTO> toDTOList(List<Conta> entities);
}

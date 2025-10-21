package com.example.atividade_xpto.core.mappers;

import com.example.atividade_xpto.core.dtos.contas.ContaDTO;
import com.example.atividade_xpto.core.models.Conta;

import java.util.List;

public interface ContaMapper {
    Conta toEntity(ContaDTO dto);
    ContaDTO toDTO(Conta entity);
    List<ContaDTO> toDTOList(List<Conta> entities);
}

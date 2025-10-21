package com.example.atividade_xpto.core.mappers;

import com.example.atividade_xpto.core.dtos.clientes.ClientePFDTO;
import com.example.atividade_xpto.core.models.ClientePF;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientePFMapper {
    ClientePFDTO toDTO(ClientePF clientePF);

    ClientePF toEntity(ClientePFDTO clientePFDTO);

    List<ClientePFDTO> toDTOList(List<ClientePF> clientePFList);
}

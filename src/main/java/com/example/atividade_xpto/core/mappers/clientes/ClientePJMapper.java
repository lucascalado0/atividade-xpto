package com.example.atividade_xpto.core.mappers.clientes;

import com.example.atividade_xpto.core.dtos.clientes.ClientePJDTO;
import com.example.atividade_xpto.core.models.clientes.ClientePJ;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientePJMapper {
    ClientePJDTO toDTO(ClientePJ clientePJ);

    ClientePJ toEntity(ClientePJDTO clientePJDTO);

    List<ClientePJDTO> toDTOList(List<ClientePJ> clientePJList);

}

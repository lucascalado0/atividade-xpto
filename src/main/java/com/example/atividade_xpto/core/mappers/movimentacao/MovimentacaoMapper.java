package com.example.atividade_xpto.core.mappers.movimentacao;

import com.example.atividade_xpto.core.dtos.movimentacao.MovimentacaoDTO;
import com.example.atividade_xpto.core.models.movimentacao.Movimentacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimentacaoMapper {
    @Mapping(target = "conta.id", source = "contaId")
    Movimentacao toEntity(MovimentacaoDTO dto);

    @Mapping(target = "contaId", source = "conta.id")
    MovimentacaoDTO toDTO(Movimentacao movimentacao);

    List<MovimentacaoDTO> toDTOList(List<Movimentacao> movimentacaoLista);
}

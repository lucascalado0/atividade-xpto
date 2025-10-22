package com.example.atividade_xpto.core.dtos.movimentacao;

import com.example.atividade_xpto.core.models.enums.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovimentacaoDTO {

    private Long id;

    @NotNull
    private LocalDateTime dataMovimentacao;

    @NotNull
    private TipoMovimentacao tipoMovimentacao;

    private String descricao;

    @JsonProperty("conta_id")
    private Long contaId;

    @NotNull
    private BigDecimal valor;
}

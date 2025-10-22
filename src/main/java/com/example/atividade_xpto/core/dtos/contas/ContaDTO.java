package com.example.atividade_xpto.core.dtos.contas;

import com.example.atividade_xpto.core.models.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaDTO {
    private Long id;

    @NotBlank
    private String agencia;

    @NotBlank
    private String numeroConta;

    @NotNull
    private TipoConta tipoConta;

    @Min(value = 0, message = "O saldo não pode ser negativo")
    private BigDecimal saldo;

    @JsonProperty("cliente_id")
    private Long clienteId;
}

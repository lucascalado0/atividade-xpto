package com.example.atividade_xpto.core.dtos.enderecos;

import com.example.atividade_xpto.core.dtos.clientes.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;
    @NotBlank
    private String cep;
    @NotBlank
    private String rua;
    @NotBlank
    private String numero;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;
    @JsonProperty("cliente_id")
    private Long clienteId;
}

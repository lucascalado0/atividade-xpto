package com.example.atividade_xpto.core.dtos.clientes;

import com.example.atividade_xpto.core.dtos.contas.ContaDTO;
import com.example.atividade_xpto.core.dtos.enderecos.EnderecoDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @NotBlank
    private String email;

    @NotBlank
    private List<EnderecoDTO> enderecos;

    @NotBlank
    private List<ContaDTO> contas;
}

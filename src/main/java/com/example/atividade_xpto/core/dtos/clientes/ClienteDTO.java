package com.example.atividade_xpto.core.dtos.clientes;

import com.example.atividade_xpto.core.dtos.conta.ContaDTO;
import com.example.atividade_xpto.core.dtos.endereco.EnderecoDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
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

    private LocalDateTime dataCadastro = LocalDateTime.now();

    private List<EnderecoDTO> enderecos;

    private List<ContaDTO> contas;
}

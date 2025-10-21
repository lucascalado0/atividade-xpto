package com.example.atividade_xpto.core.dtos.clientes;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ClientePFDTO extends ClienteDTO {

    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;
}

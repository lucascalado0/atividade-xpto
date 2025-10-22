package com.example.atividade_xpto.core.models.clientes;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@ToString(callSuper = true)
@Table(name = "tb_clientes_pf")
@DiscriminatorValue("PF")
public class ClientePF extends Cliente {

    @Column(nullable = false, unique = true, length = 20)
    private String cpf;

    @Column(nullable = false, unique = true, length = 20)
    private String rg;

}

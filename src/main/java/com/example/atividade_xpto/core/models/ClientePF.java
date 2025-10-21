package com.example.atividade_xpto.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "tb_clientes_pf")
@DiscriminatorValue("PF")
public class ClientePF extends Cliente {

    @Column(nullable = false, unique = true, length = 20)
    private String cpf;

    @Column(nullable = false, unique = true, length = 20)
    private String rg;
}

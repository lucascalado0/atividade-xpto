package com.example.atividade_xpto.core.models.clientes;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_clientes_pj")
@DiscriminatorValue("PJ")
public class ClientePJ extends Cliente {

    @Column(nullable = false, unique = true, length = 20)
    private String cnpj;
}

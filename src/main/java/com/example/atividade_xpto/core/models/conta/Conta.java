package com.example.atividade_xpto.core.models.conta;


import com.example.atividade_xpto.core.models.movimentacao.Movimentacao;
import com.example.atividade_xpto.core.models.clientes.Cliente;
import com.example.atividade_xpto.core.models.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tb_contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8)
    private String agencia;

    @Column(nullable = false, length = 10)
    private String numeroConta;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @ToString.Exclude
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimentacao> movimentacoes;

    public Conta(){
        this.saldo = BigDecimal.ZERO;
        this.dataCriacao = LocalDateTime.now();
    }



}

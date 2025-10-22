package com.example.atividade_xpto.core.models.clientes;


import com.example.atividade_xpto.core.models.conta.Conta;
import com.example.atividade_xpto.core.models.endereco.Endereco;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_clientes")
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 50)
    private String email;

    @Column
    private LocalDateTime dataCadastro;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<Conta> contas = new ArrayList<>();

    public Cliente(){
        this.dataCadastro = LocalDateTime.now();
    }

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", enderecos=" + enderecos +
                ", contas=" + contas +
                '}';
    }
}

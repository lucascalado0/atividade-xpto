package com.example.atividade_xpto.core.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tb_enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12)
    private String cep;

    @Column(nullable = false, length = 30)
    private String rua;

    @Column(nullable = false, length = 4)
    private String numero;

    @Column(nullable = false, length = 30)
    private String bairro;

    @Column(nullable = false, length = 30)
    private String cidade;

    @Column(nullable = false, length = 30)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @ToString.Exclude
    @JsonBackReference
    private Cliente cliente;


}

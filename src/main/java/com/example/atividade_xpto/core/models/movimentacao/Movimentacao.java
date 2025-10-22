package com.example.atividade_xpto.core.models.movimentacao;

import com.example.atividade_xpto.core.models.conta.Conta;
import com.example.atividade_xpto.core.models.enums.TipoMovimentacao;
import com.example.atividade_xpto.exception.movimentacoes.MovimentacaoArgumentException;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_movimentacoes")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    public Movimentacao(){
        this.dataMovimentacao = LocalDateTime.now();
    }

    public Movimentacao(String number, String credito, BigDecimal bigDecimal) {
    }

    public void depositar(BigDecimal valor) {
        if (this.conta == null) {
            throw new MovimentacaoArgumentException("Conta não pode ser nula");
        }
        if(this.valor == null || this.valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MovimentacaoArgumentException("Valor de depósito deve ser maior que 0.");
        }
        this.conta.setSaldo(this.conta.getSaldo().add(valor));
    }

    public void sacar(BigDecimal valor) {
        if (this.conta == null){
            throw new MovimentacaoArgumentException("Conta não pode ser nula");
        }

        if (this.conta.getSaldo().compareTo(valor) < 0) {
            throw new MovimentacaoArgumentException("Saldo insuficiente para saque.");
        }

        if (this.valor == null || this.valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MovimentacaoArgumentException("Valor de saque deve ser maior que 0.");
        }

        this.conta.setSaldo(this.conta.getSaldo().subtract(valor));
    }
}

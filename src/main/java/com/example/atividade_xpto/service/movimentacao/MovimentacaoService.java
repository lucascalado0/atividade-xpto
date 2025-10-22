package com.example.atividade_xpto.service.movimentacao;

import com.example.atividade_xpto.core.models.conta.Conta;
import com.example.atividade_xpto.core.models.movimentacao.Movimentacao;
import com.example.atividade_xpto.core.models.enums.TipoMovimentacao;
import com.example.atividade_xpto.exception.contas.ContaArgumentException;
import com.example.atividade_xpto.exception.contas.ContaNotFoundException;
import com.example.atividade_xpto.exception.movimentacoes.MovimentacaoNotFoundException;
import com.example.atividade_xpto.repository.conta.ContaRepository;
import com.example.atividade_xpto.repository.movimentacao.MovimentacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ContaRepository contaRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Transactional
    public Movimentacao registrarMovimentacao(Movimentacao movimentacao){
        if (movimentacao.getConta() == null || movimentacao.getConta().getId() == null) {
            throw new ContaArgumentException("A movimentação deve estar associada a uma conta.");
        }

        Conta conta = contaRepository.findById(movimentacao.getConta().getId())
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada"));

        if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.DEPOSITO) {
            if (movimentacao.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ContaArgumentException("Valor de depósito deve ser maior que 0.");
            }
            conta.setSaldo(conta.getSaldo().add(movimentacao.getValor()));
        }
        else if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.SAQUE) {
            if (conta.getSaldo().compareTo(movimentacao.getValor()) < 0) {
                throw new ContaArgumentException("Saldo insuficiente para saque.");
            }
            conta.setSaldo(conta.getSaldo().subtract(movimentacao.getValor()));
        }
        else {
            throw new ContaArgumentException("Tipo de movimentação inválido.");
        }

        movimentacao.setConta(conta);
        movimentacao.setDataMovimentacao(LocalDateTime.now());

        contaRepository.save(conta);
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> listarMovimentacoes(){
        return movimentacaoRepository.findAll();
    }

    public Movimentacao buscarPorId(Long id){
        return movimentacaoRepository.findById(id)
                .orElseThrow(() -> new MovimentacaoNotFoundException("Movimentação com ID: " + id + " não encontrada."));
    }

}


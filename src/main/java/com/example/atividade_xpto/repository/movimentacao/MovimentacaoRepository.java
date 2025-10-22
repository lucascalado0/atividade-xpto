package com.example.atividade_xpto.repository.movimentacao;

import com.example.atividade_xpto.core.models.movimentacao.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByContaIdAndDataMovimentacaoBetween(Long contaId, LocalDateTime dataInicio, LocalDateTime dataFim);

    List<Movimentacao> findByContaClienteId(Long clienteId);
}

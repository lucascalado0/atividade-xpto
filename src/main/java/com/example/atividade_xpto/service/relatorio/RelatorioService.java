package com.example.atividade_xpto.service.relatorio;

import com.example.atividade_xpto.core.models.clientes.Cliente;
import com.example.atividade_xpto.core.models.conta.Conta;
import com.example.atividade_xpto.core.models.enums.TipoMovimentacao;
import com.example.atividade_xpto.core.models.movimentacao.Movimentacao;
import com.example.atividade_xpto.exception.clientes.ClienteNotFoundException;
import com.example.atividade_xpto.repository.clientes.ClienteRepository;
import com.example.atividade_xpto.repository.movimentacao.MovimentacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final ClienteRepository clienteRepository;

    private final MovimentacaoRepository movimentacaoRepository;

    public RelatorioService(ClienteRepository clienteRepository, MovimentacaoRepository movimentacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public String gerarRelatorioSaldo(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));

        List<Movimentacao> movimentacoes = movimentacaoRepository.findByContaClienteId(clienteId);

        long creditos = movimentacoes.stream()
                .filter(m -> m.getTipoMovimentacao() == TipoMovimentacao.DEPOSITO)
                .count();

        long debitos = movimentacoes.stream()
                .filter(m -> m.getTipoMovimentacao() == TipoMovimentacao.SAQUE)
                .count();

        int totalMovimentacoes = movimentacoes.size();


        BigDecimal taxaPorMovimentacao;
        if (totalMovimentacoes <= 10) {
            taxaPorMovimentacao = BigDecimal.valueOf(1.00);
        } else if (totalMovimentacoes <= 20) {
            taxaPorMovimentacao = BigDecimal.valueOf(0.75);
        } else {
            taxaPorMovimentacao = BigDecimal.valueOf(0.50);
        }

        BigDecimal valorPago = taxaPorMovimentacao.multiply(BigDecimal.valueOf(totalMovimentacoes));


        BigDecimal saldoInicial;

        if (!movimentacoes.isEmpty()) {
            saldoInicial = movimentacoes.getFirst().getConta().getSaldo();
        } else {
            saldoInicial = cliente.getContas().stream()
                    .map(Conta::getSaldo)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }



        BigDecimal saldoAtual = cliente.getContas().stream()
                .map(Conta::getSaldo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        String endereco = cliente.getEnderecos().stream()
                .map(e -> String.format("%s, %s, %s, %s, %s, %s",
                        e.getRua(),
                        e.getNumero(),
                        e.getBairro(),
                        e.getCidade(),
                        e.getEstado(),
                        e.getCep()))
                .collect(Collectors.joining(" | "));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        return String.format("""
            Relatório de saldo do cliente %s:
            Cliente: %s - Cliente desde: %s;
            Endereço: %s;
            Movimentações de crédito: %d;
            Movimentações de débito: %d;
            Total de movimentações: %d;
            Valor pago pelas movimentações: R$ %,.2f;
            Saldo inicial: R$ %,.2f;
            Saldo atual: R$ %,.2f;
            """,
                cliente.getNome(),
                cliente.getNome(),
                cliente.getDataCadastro().format(fmt),
                endereco,
                creditos,
                debitos,
                totalMovimentacoes,
                valorPago,
                saldoInicial,
                saldoAtual
        );
    }

    public String gerarRelatorioTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataAtualFormatada = LocalDateTime.now().format(fmtData);


        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de saldo de todos os clientes;\n");


        for (Cliente cliente : clientes) {

            BigDecimal saldoAtual = cliente.getContas().stream()
                    .map(Conta::getSaldo)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);


            String dataCadastroFormatada = cliente.getDataCadastro().format(fmtData);


            relatorio.append(String.format(
                    "Cliente: %s - Cliente desde: %s – Saldo em %s: %,.2f\n",
                    cliente.getNome(),
                    dataCadastroFormatada,
                    dataAtualFormatada,
                    saldoAtual
            ));
        }


        return relatorio.toString();
    }
}



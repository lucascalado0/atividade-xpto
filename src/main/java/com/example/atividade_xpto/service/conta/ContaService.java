package com.example.atividade_xpto.service.conta;

import com.example.atividade_xpto.core.models.clientes.Cliente;
import com.example.atividade_xpto.core.models.conta.Conta;
import com.example.atividade_xpto.exception.clientes.ClienteNotFoundException;
import com.example.atividade_xpto.exception.contas.ContaNotFoundException;
import com.example.atividade_xpto.repository.clientes.ClienteRepository;
import com.example.atividade_xpto.repository.conta.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;


    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    public Conta novaConta(Conta conta, Long clienteId) {
        Cliente cliente =  clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com ID " + clienteId + " não encontrado."));

        conta.setCliente(cliente);

        return contaRepository.save(conta);
    }

    public Conta obterContaPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNotFoundException("Conta com ID " + id + " não encontrada."));
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Conta atualizarConta(Long id, Conta contaAtualizada) {
        Conta contaExistente = obterContaPorId(id);

        contaExistente.setSaldo(contaAtualizada.getSaldo());

        return contaRepository.save(contaExistente);
    }

    public void deletarContaPorId(Long id) {
        Conta conta = obterContaPorId(id);
        contaRepository.delete(conta);
    }

}

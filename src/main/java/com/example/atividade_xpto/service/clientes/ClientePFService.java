package com.example.atividade_xpto.service.clientes;


import com.example.atividade_xpto.core.models.conta.Conta;
import com.example.atividade_xpto.core.models.endereco.Endereco;
import com.example.atividade_xpto.exception.clientes.ClienteJaExistenteException;
import com.example.atividade_xpto.exception.clientes.ClienteNotFoundException;
import com.example.atividade_xpto.core.models.clientes.ClientePF;
import com.example.atividade_xpto.repository.clientes.ClientePFRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ClientePFService {

    private ClientePFRepository clientePFRepository;

    public ClientePFService(ClientePFRepository clientePFRepository) {
        this.clientePFRepository = clientePFRepository;
    }

    public ClientePF cadastrarClientePF(ClientePF clientePF) {
        boolean exists = clientePFRepository.existsByCpf(clientePF.getCpf());
        if (exists) {
            throw new ClienteJaExistenteException("Cliente com CPF " + clientePF.getCpf() + " já existe.");
        }

        if (clientePF.getContas() != null) {
            for (Conta conta : clientePF.getContas()) {
                conta.setCliente(clientePF);
            }
        }

        if (clientePF.getEnderecos() != null) {
            for (Endereco endereco : clientePF.getEnderecos()) {
                endereco.setCliente(clientePF);
            }
        }

        return clientePFRepository.save(clientePF);
    }

    public Optional<ClientePF> findByCpf(String cpf) {
        if (clientePFRepository.existsByCpf(cpf)){
            return clientePFRepository.findByCpf(cpf);
        } else {
            throw new ClienteNotFoundException("Cliente com CPF " + cpf + " não encontrado.");
        }
    }

    public List<ClientePF> listarClientesPF() {
        return clientePFRepository.findAll();
    }

    public ClientePF atualizarClientePF(String cpf, ClientePF clientePFAtualizado) {
        ClientePF clienteExistente = clientePFRepository.findByCpf(cpf)
                        .orElseThrow(() -> new ClienteNotFoundException("Cliente com CPF " + cpf + " não encontrado."));

        clienteExistente.setEmail(clientePFAtualizado.getEmail());
        clienteExistente.setTelefone(clientePFAtualizado.getTelefone());

        return clientePFRepository.save(clienteExistente);
        }

    @Transactional
    public void deleteByCpf(String cpf) {
        clientePFRepository.deleteByCpf(cpf);
    }
}

package com.example.atividade_xpto.service.impl;


import com.example.atividade_xpto.core.models.Conta;
import com.example.atividade_xpto.core.models.Endereco;
import com.example.atividade_xpto.exception.clientes.ClienteJaExistenteException;
import com.example.atividade_xpto.exception.clientes.ClienteNotFoundException;
import com.example.atividade_xpto.core.models.ClientePF;
import com.example.atividade_xpto.repository.ClientePFRepository;
import com.example.atividade_xpto.service.ClientePFService;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ClientePFServiceImpl implements ClientePFService {

    private ClientePFRepository clientePFRepository;

    public ClientePFServiceImpl(ClientePFRepository clientePFRepository) {
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

    @Override
    public Optional<ClientePF> findByCpf(String cpf) {
        if (clientePFRepository.existsByCpf(cpf)){
            return clientePFRepository.findByCpf(cpf);
        } else {
            throw new ClienteNotFoundException("Cliente com CPF " + cpf + " não encontrado.");
        }
    }

    public List<ClientePF> findAll() {
        return clientePFRepository.findAll();
    }

    public ClientePF atualizarClientePF(String cpf, ClientePF clientePFAtualizado) {
        ClientePF clienteExistente = clientePFRepository.findByCpf(cpf)
                        .orElseThrow(() -> new ClienteNotFoundException("Cliente com CPF " + cpf + " não encontrado."));

        clienteExistente.setEmail(clientePFAtualizado.getEmail());
        clienteExistente.setTelefone(clientePFAtualizado.getTelefone());

        return clientePFRepository.save(clienteExistente);
        }

    public void deleteById(Long id) {
        clientePFRepository.deleteById(id);
    }
}

package com.example.atividade_xpto.service;

import com.example.atividade_xpto.core.models.ClientePJ;
import com.example.atividade_xpto.core.models.Conta;
import com.example.atividade_xpto.core.models.Endereco;
import com.example.atividade_xpto.exception.clientes.ClienteJaExistenteException;
import com.example.atividade_xpto.exception.clientes.ClienteNotFoundException;
import com.example.atividade_xpto.repository.ClientePJRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePJService {

    private final ClientePJRepository clientePJRepository;

    public ClientePJService(ClientePJRepository clientePJRepository) {
        this.clientePJRepository = clientePJRepository;
    }

    public ClientePJ cadastrarClientePJ(ClientePJ clientePJ) {
        boolean exists = clientePJRepository.existsByCnpj(clientePJ.getCnpj());
        if (exists) {
            throw new ClienteJaExistenteException("Cliente com CNPJ " + clientePJ.getCnpj() + " já existe.");
        }

        if (clientePJ.getContas() != null) {
            for (Conta conta : clientePJ.getContas()) {
                conta.setCliente(clientePJ);
            }
        }

        if (clientePJ.getEnderecos() != null) {
            for (Endereco endereco : clientePJ.getEnderecos()) {
                endereco.setCliente(clientePJ);
            }
        }

        return clientePJRepository.save(clientePJ);
    }


    public Optional<ClientePJ> buscarPeloCnpj(String cnpj) {
        if (clientePJRepository.existsByCnpj(cnpj)){
            return clientePJRepository.findByCnpj(cnpj);
        } else {
            throw new ClienteNotFoundException("Cliente com CNPJ " + cnpj + " não encontrado.");
        }
    }

    public List<ClientePJ> listarClientesPJ() {
        return clientePJRepository.findAll();
    }

    public ClientePJ atualizarClientePF(String cnpj, ClientePJ clientePJAtualizado) {
        ClientePJ clienteExistente = clientePJRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com CNPJ " + cnpj + " não encontrado."));

        clienteExistente.setEmail(clientePJAtualizado.getEmail());
        clienteExistente.setTelefone(clientePJAtualizado.getTelefone());

        return clientePJRepository.save(clienteExistente);
    }

    @Transactional
    public void deleteByCnpj(String cnpj) {
        clientePJRepository.deleteByCnpj(cnpj);
    }
}

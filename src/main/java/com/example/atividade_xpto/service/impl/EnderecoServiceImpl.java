package com.example.atividade_xpto.service.impl;

import com.example.atividade_xpto.core.mappers.EnderecoMapper;
import com.example.atividade_xpto.core.models.Cliente;
import com.example.atividade_xpto.core.models.Endereco;
import com.example.atividade_xpto.exception.enderecos.EnderecoNotFoundException;
import com.example.atividade_xpto.repository.ClientePFRepository;
import com.example.atividade_xpto.repository.ClienteRepository;
import com.example.atividade_xpto.repository.EnderecoRepository;
import com.example.atividade_xpto.service.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Endereco novoEndereco(Endereco endereco, Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente com ID " + clienteId + " não encontrado para associar o endereço."));

        endereco.setCliente(cliente);

        return enderecoRepository.save(endereco);
    }

    public Endereco atualizarEndereco(Long id, Endereco dadosNovos) {

        Endereco enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNotFoundException("Endereço com ID " + id + " não encontrado."));

        enderecoExistente.setRua(dadosNovos.getRua());
        enderecoExistente.setEstado(dadosNovos.getEstado());
        enderecoExistente.setCidade(dadosNovos.getCidade());
        enderecoExistente.setBairro(dadosNovos.getBairro());
        enderecoExistente.setCep(dadosNovos.getCep());
        enderecoExistente.setNumero(dadosNovos.getNumero());

        return enderecoRepository.save(enderecoExistente);
    }

    public Endereco obterEnderecoPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNotFoundException("Endereço com ID " + id + " não encontrado."));
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }


}

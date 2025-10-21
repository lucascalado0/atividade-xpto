package com.example.atividade_xpto.controller;

import com.example.atividade_xpto.core.dtos.clientes.ClientePFDTO;
import com.example.atividade_xpto.core.mappers.ClientePFMapper;
import com.example.atividade_xpto.exception.clientes.ClienteNotFoundException;
import com.example.atividade_xpto.core.models.ClientePF;
import com.example.atividade_xpto.service.impl.ClientePFServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes-pf")
public class ClientePFController {

    private final ClientePFServiceImpl clientePFService;
    private final ClientePFMapper clientePFMapper;

    public ClientePFController(ClientePFServiceImpl clientePFService, ClientePFMapper clientePFMapper) {
        this.clientePFService = clientePFService;
        this.clientePFMapper = clientePFMapper;
    }

    @PostMapping
    public ResponseEntity<ClientePFDTO> novoClientePF(@RequestBody ClientePFDTO clientePFDTO) {
        ClientePF clientePF = clientePFMapper.toEntity(clientePFDTO);
        ClientePF novoClientePF = clientePFService.cadastrarClientePF(clientePF);

        return ResponseEntity.ok().body(clientePFMapper.toDTO(novoClientePF));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientePFDTO> obterClientePFPorCpf(@Valid @PathVariable String cpf){
        ClientePF clientePF = clientePFService.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com CPF " + cpf + " não encontrado."));
        return ResponseEntity.ok(clientePFMapper.toDTO(clientePF));
    }

    @GetMapping
    public ResponseEntity<List<ClientePFDTO>> obterTodosClientesPF() {
        List<ClientePF> clientesPF = clientePFService.findAll();

        List<ClientePFDTO> dtos = clientesPF.stream()
                .map(clientePFMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClientePFDTO> atualizarClientePF(@PathVariable String cpf, @Valid @RequestBody ClientePFDTO clientePFDTO) {
        ClientePF clientePFAtualizado = clientePFService.atualizarClientePF(cpf, clientePFDTO);
        return ResponseEntity.ok(clientePFMapper.toDTO(clientePFAtualizado));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarClientePFPorCpf(@PathVariable String cpf) {
        ClientePF clientePF = clientePFService.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com CPF " + cpf + " não encontrado."));
        clientePFService.deleteById(clientePF.getId());
        return ResponseEntity.noContent().build();
    }
}

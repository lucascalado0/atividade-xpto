package com.example.atividade_xpto.controller.conta;

import com.example.atividade_xpto.core.dtos.conta.ContaDTO;
import com.example.atividade_xpto.core.mappers.conta.ContaMapper;
import com.example.atividade_xpto.core.models.conta.Conta;
import com.example.atividade_xpto.service.conta.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;
    private final ContaMapper contaMapper;

    public ContaController(ContaService contaService, ContaMapper contaMapper) {
        this.contaService = contaService;
        this.contaMapper = contaMapper;
    }

    @PostMapping
    public ResponseEntity<ContaDTO> novaConta (@Valid @RequestBody ContaDTO contaDTO) {
        Conta conta = contaMapper.toEntity(contaDTO);
        Long clienteId = contaDTO.getClienteId();

        Conta contaSalva = contaService.novaConta(conta, clienteId);

        ContaDTO dtoResposta = contaMapper.toDTO(contaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResposta);
    }

    @GetMapping
    public ResponseEntity <List<ContaDTO>> listarTodasContas() {
        List<Conta> contas = contaService.listarContas();

        List<ContaDTO> dtos = contaMapper.toDTOList(contas);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> obterContaPorId(@PathVariable Long id) {
        Conta conta = contaService.obterContaPorId(id);

        return ResponseEntity.ok(contaMapper.toDTO(conta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> atualizarConta(@PathVariable Long id, @RequestBody ContaDTO contaDTO) {
        Conta conta = contaMapper.toEntity(contaDTO);
        Conta contaAtualizada = contaService.atualizarConta(id, conta);
        return ResponseEntity.ok(contaMapper.toDTO(contaAtualizada));
    }
}

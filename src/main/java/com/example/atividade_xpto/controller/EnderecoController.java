package com.example.atividade_xpto.controller;

import com.example.atividade_xpto.core.dtos.enderecos.EnderecoDTO;
import com.example.atividade_xpto.core.mappers.EnderecoMapper;
import com.example.atividade_xpto.core.models.Endereco;
import com.example.atividade_xpto.service.impl.EnderecoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoServiceImpl enderecoService;
    private final EnderecoMapper enderecoMapper;

    public EnderecoController(EnderecoServiceImpl enderecoService, EnderecoMapper enderecoMapper) {
        this.enderecoService = enderecoService;
        this.enderecoMapper = enderecoMapper;
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> novoEndereco(@Valid @RequestBody  EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        Long clienteId = enderecoDTO.getClienteId(); // Supondo que o DTO tenha um campo clienteId

        Endereco enderecoSalvo = enderecoService.novoEndereco(endereco, clienteId);

        EnderecoDTO dtoResposta = enderecoMapper.toDTO(enderecoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResposta);
    }

    @GetMapping
    public ResponseEntity <List<EnderecoDTO>> listarTodosEnderecos() {
        List<Endereco> enderecos = enderecoService.listarEnderecos();
        List<EnderecoDTO> dtos = enderecoMapper.toDTOList(enderecos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> obterEnderecoPorId(@PathVariable Long id) {
        Endereco endereco = enderecoService.obterEnderecoPorId(id);
        return ResponseEntity.ok(enderecoMapper.toDTO(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, endereco);
        return ResponseEntity.ok(enderecoMapper.toDTO(enderecoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}

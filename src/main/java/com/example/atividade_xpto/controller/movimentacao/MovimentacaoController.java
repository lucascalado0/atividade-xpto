package com.example.atividade_xpto.controller.movimentacao;

import com.example.atividade_xpto.core.dtos.movimentacao.MovimentacaoDTO;
import com.example.atividade_xpto.core.mappers.movimentacao.MovimentacaoMapper;
import com.example.atividade_xpto.service.movimentacao.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;
    private final MovimentacaoMapper movimentacaoMapper;

    public MovimentacaoController(MovimentacaoService movimentacaoService, MovimentacaoMapper movimentacaoMapper) {
        this.movimentacaoService = movimentacaoService;
        this.movimentacaoMapper = movimentacaoMapper;
    }

    @PostMapping
    public ResponseEntity<MovimentacaoDTO> novaMovimentacao(@Valid @RequestBody MovimentacaoDTO movimentacaoDTO) {
        var movimentacao = movimentacaoMapper.toEntity(movimentacaoDTO);
        var novaMovimentacao = movimentacaoService.registrarMovimentacao(movimentacao);

        return ResponseEntity.ok().body(movimentacaoMapper.toDTO(novaMovimentacao));
    }

    @GetMapping
    public ResponseEntity<Iterable<MovimentacaoDTO>> listarMovimentacoes() {
        var movimentacoes = movimentacaoService.listarMovimentacoes();
        var movimentacoesDTO = movimentacaoMapper.toDTOList(movimentacoes);

        return ResponseEntity.ok().body(movimentacoesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> obterMovimentacaoPorId(@PathVariable Long id) {
        var movimentacao = movimentacaoService.buscarPorId(id);
        var movimentacaoDTO = movimentacaoMapper.toDTO(movimentacao);
        return ResponseEntity.ok().body(movimentacaoDTO);

    }
}
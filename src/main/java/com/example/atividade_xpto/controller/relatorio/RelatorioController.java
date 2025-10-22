package com.example.atividade_xpto.controller.relatorio;

import com.example.atividade_xpto.service.relatorio.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {


    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/saldo/{clienteId}")
    public ResponseEntity<String> gerarRelatorio(@PathVariable Long clienteId) {
        String relatorio = relatorioService.gerarRelatorioSaldo(clienteId);
        return ResponseEntity.ok(relatorio);
    }
}

package com.example.atividade_xpto.controller.relatorio;

import com.example.atividade_xpto.service.relatorio.RelatorioService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping("/saldo/todos")
    public ResponseEntity<String> gerarRelatorioTodos() {
        String relatorio = relatorioService.gerarRelatorioTodosClientes();
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/saldo/{clienteId}/periodo")
    public ResponseEntity<String> gerarRelatorioPeriodo(
            @PathVariable Long clienteId,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim) {

        String relatorio = relatorioService.gerarRelatorioSaldoPorPeriodo(clienteId, dataInicio, dataFim);
        return ResponseEntity.ok(relatorio);
    }
}

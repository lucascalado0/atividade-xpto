package com.example.atividade_xpto.controller;

import com.example.atividade_xpto.core.dtos.clientes.ClientePJDTO;
import com.example.atividade_xpto.core.mappers.ClientePJMapper;
import com.example.atividade_xpto.core.models.ClientePJ;
import com.example.atividade_xpto.exception.clientes.ClienteNotFoundException;
import com.example.atividade_xpto.service.ClientePJService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes-pj")
public class ClientePJController {

    private final ClientePJMapper clientePJMapper;
    private final ClientePJService clientePJService;

    public ClientePJController(ClientePJMapper clientePJMapper, ClientePJService clientePJService) {
        this.clientePJMapper = clientePJMapper;
        this.clientePJService = clientePJService;
    }

    @PostMapping
    public ResponseEntity<ClientePJDTO> novoClientePJ(@RequestBody ClientePJDTO clientePJDTO) {
        ClientePJ clientePJ = clientePJMapper.toEntity(clientePJDTO);
        ClientePJ novoClientePJ = clientePJService.cadastrarClientePJ(clientePJ);

        return ResponseEntity.ok().body(clientePJMapper.toDTO(novoClientePJ));
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<ClientePJDTO> obterClientePJPorCnpj(@Valid @PathVariable String cnpj){
        ClientePJ clientePJ = clientePJService.buscarPeloCnpj(cnpj)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com CNPJ " + cnpj + " não encontrado."));
        return ResponseEntity.ok(clientePJMapper.toDTO(clientePJ));
    }

    @GetMapping
    public ResponseEntity<List<ClientePJDTO>> obterTodosClientesPJ() {
        List<ClientePJ> clientesPJ = clientePJService.listarClientesPJ();

        List<ClientePJDTO> dtos = clientePJMapper.toDTOList(clientesPJ);

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<ClientePJDTO> atualizarClientePJ(@PathVariable String cnpj, @Valid @RequestBody ClientePJDTO clientePJDTO) {
        ClientePJ clientePJ = clientePJMapper.toEntity(clientePJDTO);
        ClientePJ clientePJAtualizado = clientePJService.atualizarClientePF(cnpj, clientePJ);
        return ResponseEntity.ok(clientePJMapper.toDTO(clientePJAtualizado));
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deletarClientePJPorCnpj(@PathVariable String cnpj) {
        ClientePJ clientePJ = clientePJService.buscarPeloCnpj(cnpj)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com CNPJ " + cnpj + " não encontrado."));
        clientePJService.deleteByCnpj(clientePJ.getCnpj());
        return ResponseEntity.noContent().build();
    }
}

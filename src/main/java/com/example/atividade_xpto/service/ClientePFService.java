package com.example.atividade_xpto.service;

import com.example.atividade_xpto.core.models.ClientePF;

import java.util.Optional;

public interface ClientePFService {
    Optional<ClientePF> findByCpf(String cpf);
}

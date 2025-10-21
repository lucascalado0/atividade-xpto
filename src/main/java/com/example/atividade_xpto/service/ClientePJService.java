package com.example.atividade_xpto.service;

import com.example.atividade_xpto.core.models.ClientePJ;

import java.util.Optional;

public interface ClientePJService {
    Optional<ClientePJ> findByCnpj(String cnpj);
}

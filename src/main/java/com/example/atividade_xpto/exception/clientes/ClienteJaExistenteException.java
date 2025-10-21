package com.example.atividade_xpto.exception.clientes;

public class ClienteJaExistenteException extends RuntimeException {
    public ClienteJaExistenteException(String message) {
        super(message);
    }
}

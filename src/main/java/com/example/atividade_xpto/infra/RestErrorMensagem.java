package com.example.atividade_xpto.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMensagem {
    private HttpStatus status;
    private String mensagem;
    private LocalDateTime timestamp;
}

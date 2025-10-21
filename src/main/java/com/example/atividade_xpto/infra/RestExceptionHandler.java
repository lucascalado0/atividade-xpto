package com.example.atividade_xpto.infra;


import com.example.atividade_xpto.exception.clientes.ClienteArgumentException;
import com.example.atividade_xpto.exception.clientes.ClienteJaExistenteException;
import com.example.atividade_xpto.exception.clientes.ClienteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    private ResponseEntity<RestErrorMensagem>ClienteNotFoundHandler(ClienteNotFoundException exception){
        RestErrorMensagem restErrorMensagem = new RestErrorMensagem(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensagem);
    }

    @ExceptionHandler(ClienteArgumentException.class)
    private ResponseEntity<RestErrorMensagem> ClienteArgumentHandler(ClienteArgumentException exception){
        RestErrorMensagem restErrorMensagem = new RestErrorMensagem(HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMensagem);
    }

    @ExceptionHandler(ClienteJaExistenteException.class)
    private ResponseEntity<RestErrorMensagem> ClienteJaExistenteHandler(Exception exception){
        RestErrorMensagem restErrorMensagem = new RestErrorMensagem(HttpStatus.CONFLICT, exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restErrorMensagem);
    }
}

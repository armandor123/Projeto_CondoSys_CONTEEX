package br.com.condosys.api.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GlobalExceptionHandler {

    //Retorna o Http 404 quando a pessoa nao existe.
    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> tratarPessoaNaoEncontradaException(
            PessoaNaoEncontradaException exception
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", exception.getMessage()));
    }

    //Retorna http 409 quando documento ja esta cadastrado
    @ExceptionHandler(DocumentoJaCadastradoException.class)
    public ResponseEntity<Map<String, String>> tratarDocumentoDuplicado(
            DocumentoJaCadastradoException exception
    ){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", exception.getMessage()));

    }
}

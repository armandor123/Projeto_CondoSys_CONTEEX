package br.com.condosys.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;


// Centraliza o tratamento de erros da API.
@RestControllerAdvice
public class GlobalExceptionHandler {


    // Pessoa não encontrada -> 404.
    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> tratarPessoaNaoEncontrada(
            PessoaNaoEncontradaException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "erro", exception.getMessage()
                ));
    }


    // Documento duplicado -> 409.
    @ExceptionHandler(DocumentoJaCadastradoException.class)
    public ResponseEntity<Map<String, String>> tratarDocumentoDuplicado(
            DocumentoJaCadastradoException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "erro", exception.getMessage()
                ));
    }


    // Dados inválidos enviados no JSON -> 400.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(
            MethodArgumentNotValidException exception
    ) {

        Map<String, String> erros = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(erro ->
                        erros.put(
                                erro.getField(),
                                erro.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erros);
    }
}

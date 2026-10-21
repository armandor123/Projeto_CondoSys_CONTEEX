package br.com.condosys.api.controller;

import br.com.condosys.api.domain.model.Usuario;
import br.com.condosys.api.dto.UsuarioRequest;
import br.com.condosys.api.dto.UsuarioResponse;
import br.com.condosys.api.mapper.UsuarioMapper;
import br.com.condosys.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Endpoints HTTP relacionados a Usuario.
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;


    // Spring injeta Service e Mapper.
    public UsuarioController(
            UsuarioService usuarioService,
            UsuarioMapper usuarioMapper
    ) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }


    // POST /usuarios
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(
            @Valid @RequestBody UsuarioRequest request
    ) {

        Usuario usuario = usuarioService.cadastrar(
                request.pessoaId(),
                request.email(),
                request.senha(),
                request.perfil()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioMapper.toResponse(usuario));
    }


    // GET /usuarios
    @GetMapping
    public List<UsuarioResponse> listarTodos() {

        return usuarioService.listarTodos()
                .stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }
}
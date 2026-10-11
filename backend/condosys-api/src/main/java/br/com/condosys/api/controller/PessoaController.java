package br.com.condosys.api.controller;

import br.com.condosys.api.domain.model.Pessoa;
import br.com.condosys.api.service.PessoaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.UUID;
import java.util.List;


//controller responsavel pelas requisições HTTP de pessoa.
@RestController
@RequestMapping("/pessoas")

public class PessoaController {
    private final PessoaService pessoaService;

    //Spring ijeta o serviço pelo construtor.
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // GET /pessoas
    // Retorna todas as pessoas cadastradas.
    @GetMapping
    public List<Pessoa> listarTodas() {
        return pessoaService.listarTodas();
    }

    //post /pessoa
  // cadadstra uma nova pessoa.

    @PostMapping
    public Pessoa cadastrar(@RequestBody Pessoa pessoa) {
        return pessoaService.salvar(pessoa);

    }

    //GETT /PESSOA//{ID}
    //BUSCA UMA PESSOA PELO UUID.

    @GetMapping("/{id}")
    public Pessoa buscarPorId(@PathVariable UUID id) {
        return pessoaService.buscarPorId(id);
    }


}

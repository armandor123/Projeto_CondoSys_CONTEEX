package br.com.condosys.api.service;

import br.com.condosys.api.domain.model.Pessoa;
import br.com.condosys.api.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import br.com.condosys.api.exception.DocumentoJaCadastradoException;
import br.com.condosys.api.exception.PessoaNaoEncontradaException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



// Regras de negócio relacionadas a Pessoa.
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;


    // Spring injeta o Repository pelo construtor.
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


    // Salva uma nova pessoa, evitando documento duplicado.
    public Pessoa salvar(Pessoa pessoa) {

        if (pessoaRepository.existsByDocumento(pessoa.getDocumento())) {
            throw new DocumentoJaCadastradoException();
        }

        return pessoaRepository.save(pessoa);
    }


    // Retorna todas as pessoas cadastradas.
    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }


    // Busca uma pessoa pelo documento.
    public Optional<Pessoa> buscarPorDocumento(String documento) {
        return pessoaRepository.findByDocumento(documento);
    }


    // Busca uma pessoa pelo ID ou gera erro se não existir.
    public Pessoa buscarPorId(UUID id) {
        return pessoaRepository.findById(id)
                .orElseThrow(PessoaNaoEncontradaException::new);

    }
}


package br.com.condosys.api.repository;

import br.com.condosys.api.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


// Acesso ao banco para Usuario.
public interface UsuarioRepository
        extends JpaRepository<Usuario, UUID> {

    // Busca pelo e-mail.
    Optional<Usuario> findByEmail(String email);

    // Verifica e-mail duplicado.
    boolean existsByEmail(String email);

    // Verifica se a Pessoa já possui usuário.
    boolean existsByPessoa_Id(UUID pessoaId);
}
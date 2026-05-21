package br.com.condosys.dao;

import br.com.condosys.config.FabricaConexao;
import br.com.condosys.model.Usuario;
import br.com.condosys.util.SegurancaUtil;
import java.sql.*;

public class UsuarioDAO {

    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT * FROM tabela_usuarios WHERE email = ? AND senha = ? AND ativo = TRUE";
        
        try (Connection conexao = FabricaConexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            // 🔐 A MÁGICA ACONTECE AQUI: Criptografa a senha antes de buscar no banco
            String senhaHasheada = SegurancaUtil.gerarHash(senha);
            
            ps.setString(1, email);
            ps.setString(2, senhaHasheada); // Envia o Hash para comparar com o Hash do banco
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("nome"),
                        rs.getString("documento"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
}
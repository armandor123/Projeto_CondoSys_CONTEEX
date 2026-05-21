package br.com.condosys.main; // <-- Corrigido para a sua pasta exata!

import br.com.condosys.dao.UsuarioDAO;
import br.com.condosys.model.Usuario;
import br.com.condosys.util.EstiloUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaLogin extends JFrame {
	
	private static final long serialVersionUID = 1L; // <-- ADICIONE ESTA LINHA AQUI!
	
    // Declaração das variáveis
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton btnEntrar;

    public TelaLogin() {
        // Configurações básicas da janela
        setTitle("CondoSys - Login");
        setSize(400, 500); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

     // ==== 1. ADICIONAR LOGO (ESTILO CABEÇALHO/ESTENDIDO) ====
        try {
        	// O getClass().getResource() diz ao Java para procurar DENTRO do source code (o 'src')
        	ImageIcon iconeOriginal = new ImageIcon(getClass().getResource("/imagens/logo.png"));
            
            // --- A MÁGICA DO REDIMENSIONAMENTO ---
            // Pegamos  imagem e a esticamos horizontalmente. 
            // Novo tamanho sugerido: 360 de largura (quase a janela toda) por 100 de altura.
            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(360, 100, Image.SCALE_SMOOTH);
            
            // Coloca a imagem estendida em um Label
            JLabel lblLogo = new JLabel(new ImageIcon(imagemRedimensionada));
            
            // --- NOVO POSICIONAMENTO ---
            // Centraliza o logo horizontalmente e coloca no topo.
            // X=20 (para não colar na borda), Y=10 (perto do topo), Largura=360, Altura=100
            lblLogo.setBounds(20, 10, 360, 100); 
            add(lblLogo);
            
        } catch (Exception e) {
            System.out.println("Aviso: Imagem do logo não encontrada em 'imagens/logo.png'");
        }

        // ==== 2. CAMPO DE E-MAIL ====
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(50, 160, 300, 20);
        lblEmail.setFont(EstiloUtil.FONTE_LABEL);
        add(lblEmail);

        campoEmail = new JTextField();
        campoEmail.setFont(EstiloUtil.FONTE_INPUT);
        campoEmail.setBorder(new LineBorder(EstiloUtil.COR_PRIMARIA, 1));
        campoEmail.setBounds(50, 180, 300, 40);
        add(campoEmail);

        // ==== 3. CAMPO DE SENHA ====
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 230, 300, 20);
        lblSenha.setFont(EstiloUtil.FONTE_LABEL);
        add(lblSenha);

        campoSenha = new JPasswordField();
        campoSenha.setFont(EstiloUtil.FONTE_INPUT);
        campoSenha.setBorder(new LineBorder(EstiloUtil.COR_PRIMARIA, 1));
        campoSenha.setBounds(50, 250, 300, 40);
        add(campoSenha);

        // ==== 4. BOTÃO ENTRAR ====
        btnEntrar = new JButton("ENTRAR");
        btnEntrar.setFont(EstiloUtil.FONTE_BOTAO); // Usando a sua fonte
        btnEntrar.setForeground(Color.WHITE);      // Usando a sua cor
        btnEntrar.setBackground(EstiloUtil.COR_PRIMARIA); 
        btnEntrar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        btnEntrar.setFocusPainted(false); 
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEntrar.setBounds(50, 320, 300, 40);
        add(btnEntrar);

        // ==== 5. A MÁGICA DO ENTER ====
        // Como o botão já foi criado em cima, o Enter sabe quem ele deve "clicar"
        campoSenha.addActionListener(e -> btnEntrar.doClick());

        // ==== 6. LÓGICA DE LOGIN ====
        btnEntrar.addActionListener(e -> {
            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());

            // Validação simples
            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha o e-mail e a senha.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Comunica com o Banco de Dados
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuarioLogado = dao.autenticar(email, senha);

            if (usuarioLogado != null) {
                // Login com sucesso!
                this.dispose(); // Fecha a tela de login
                
                // Abre a Tela Principal (Corrigido para abrir a sua tela exata!)
                new TelaPrincipal().setVisible(true); 
            } else {
                // Login falhou
                JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos!", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
 // ==== O "BOTÃO DE LIGAR" DO SISTEMA ====
    public static void main(String[] args) {
        // Quando o sistema iniciar, ele vai criar e mostrar esta Tela de Login
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}
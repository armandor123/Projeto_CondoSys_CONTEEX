package br.com.condosys.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SegurancaUtil {

    public static String gerarHash(String senhaOriginal) {
        try {
            // Usa o algoritmo SHA-256 nativo do Java
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senhaOriginal.getBytes(StandardCharsets.UTF_8));
            
            // Converte os bytes embaralhados para texto legível (Hexadecimal)
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", 0xFF & b));
            }
            return hexString.toString();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar criptografia da senha", e);
        }
    }
}
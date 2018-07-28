package br.com.baiocchilousa.algamoney.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Classe de auxilio para criação de senha encriptada com BCrypt
 * @author leolo
 *
 */
public class GeradorSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin")); //<----- COLOQUE A SENHA AQUI
    }
}

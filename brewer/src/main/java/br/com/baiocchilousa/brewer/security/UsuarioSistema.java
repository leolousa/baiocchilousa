package br.com.baiocchilousa.brewer.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.baiocchilousa.brewer.model.Usuario;
/**
 * Classe que extende o User do Spring Secutity para que
 * possamos incluir outras propriedades neste objeto
 * Ex.: Nome do usuário
 * @author leolo
 *
 */
public class UsuarioSistema extends User{

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	

}

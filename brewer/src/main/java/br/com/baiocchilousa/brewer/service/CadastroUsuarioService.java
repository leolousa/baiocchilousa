package br.com.baiocchilousa.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.baiocchilousa.brewer.model.Usuario;
import br.com.baiocchilousa.brewer.repository.UsuarioRepository;
import br.com.baiocchilousa.brewer.service.exception.EmailUsuarioJaCadastradoException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Transactional
	public void salvar(Usuario usuario){
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		
		if(usuarioExistente.isPresent()) {
			throw new EmailUsuarioJaCadastradoException("Usuário já cadastrado com este e-mail!");
		}
		
		usuarios.save(usuario);
	}
}

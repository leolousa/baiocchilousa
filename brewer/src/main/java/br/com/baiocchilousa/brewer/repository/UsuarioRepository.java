package br.com.baiocchilousa.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.brewer.model.Usuario;
import br.com.baiocchilousa.brewer.repository.helper.usuario.UsuariosQueries;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuariosQueries{

	public Optional<Usuario> findByEmail(String email);
}

package br.com.baiocchilousa.brewer.service;

import br.com.baiocchilousa.brewer.repository.UsuarioRepository;

public enum StatusUsuario {

	ATIVAR {
		@Override
		public void executar(Long[] codigos, UsuarioRepository usuarios) {
			usuarios.findByCodigoIn(codigos).forEach(u -> u.setAtivo(true));
			
		}
	},
	DESATIVAR {
		@Override
		public void executar(Long[] codigos, UsuarioRepository usuarios) {
			usuarios.findByCodigoIn(codigos).forEach(u -> u.setAtivo(false));
			
		}
	};
	
	public abstract void executar(Long[] codigos, UsuarioRepository usuarios);
}

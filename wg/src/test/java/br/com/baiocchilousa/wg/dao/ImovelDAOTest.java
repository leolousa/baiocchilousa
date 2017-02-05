package br.com.baiocchilousa.wg.dao;

import org.junit.Test;

import br.com.baiocchilousa.wg.domain.Imovel;
import br.com.baiocchilousa.wg.domain.TipoImovel;
import br.com.baiocchilousa.wg.domain.Usuario;

public class ImovelDAOTest {

	@Test
	public void salvar(){
		
		TipoImovel tipoImovel = new TipoImovel();
		tipoImovel.setId(1L);
		tipoImovel.setAtivo(true);
		tipoImovel.setNome("Casa");
		
		Usuario usuarioRegistro = new Usuario();
		usuarioRegistro.setId(1L);
		usuarioRegistro.setAtivo(true);
		usuarioRegistro.setApelido("Leo");
		
		Usuario usuarioAtualizacao = new Usuario();
		usuarioAtualizacao.setId(1L);
		usuarioAtualizacao.setAtivo(true);
		usuarioAtualizacao.setApelido("Leo");
		

		
		Imovel imovel = new Imovel();
		imovel.setCodigo("CAV2001");
		imovel.setNome("Imóvel de teste 1");
		imovel.setDescricao("Imóvel de 4 quartos com sala e cozinha para a praia");
		imovel.setTipoImovel(tipoImovel);
		imovel.setAtivo(true);
		imovel.setUsuarioAtualizacao(usuarioAtualizacao);		
	}
}

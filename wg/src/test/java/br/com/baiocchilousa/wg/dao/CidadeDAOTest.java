package br.com.baiocchilousa.wg.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.baiocchilousa.wg.dao.CidadeDAO;
import br.com.baiocchilousa.wg.dao.UfDAO;
import br.com.baiocchilousa.wg.domain.Cidade;
import br.com.baiocchilousa.wg.domain.Uf;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void salvar() {
		UfDAO ufDAO = new UfDAO();

		Uf uf = ufDAO.buscar(1L);

		Cidade cidade = new Cidade();
		cidade.setNome("Cidade Teste");
		cidade.setUf(uf);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

	@Test
	@Ignore
	public void listar() {

		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.listar("nome", false);

		for (Cidade cidade : cidades) {
			System.out.println(cidade.getId() + " - " + cidade.getNome() + " - " + cidade.getUf().getSigla());
		}

	}

	@Test
	@Ignore
	public void buscar() {

		Long cod = 3L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(cod);
		
		System.out.println("Código da cidade: " + cidade.getId());
		System.out.println("Nome da cidade: " + cidade.getNome());
		System.out.println("UF da cidade: " + cidade.getUf().getSigla());
		System.out.println("Nome do Estado: " + cidade.getUf().getNome());


	}
	
	@Test
	@Ignore
	public void excluir() {

		Long cod = 5565L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(cod);
		
		cidadeDAO.excluir(cidade);
		
		System.out.println("Cidade removida!");
		System.out.println("Código da cidade: " + cidade.getId());
		System.out.println("Nome da cidade: " + cidade.getNome());
		System.out.println("UF da cidade: " + cidade.getUf().getSigla());
		System.out.println("Nome do Estado: " + cidade.getUf().getNome());


	}
	
	
	@Test
	@Ignore
	public void editar(){

		Long codCidade = 5565L;
		Long codUF = 10L;
		
		UfDAO ufDAO = new UfDAO();
		Uf uf = ufDAO.buscar(codUF);
		
		System.out.println("Código do Estado: " + uf.getId());
		System.out.println("Nome do Estado: " + uf.getNome());
		System.out.println("Sigla do Estado: " + uf.getSigla());
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codCidade);
		
		System.out.println("Cidade a ser editada!");
		System.out.println("Código da cidade: " + cidade.getId());
		System.out.println("Nome da cidade: " + cidade.getNome());
		System.out.println("UF da cidade: " + cidade.getUf().getSigla());
		System.out.println("Nome do Estado: " + cidade.getUf().getNome());
		
		cidade.setNome("Cidade de teste 2");
		cidade.setUf(uf);
		
		cidadeDAO.editar(cidade);
	}
	
	
	@Test

	public void buscaPorUF() {
		Long idUf = 1L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.buscaPorUf(idUf, "nome", false);

		for (Cidade cidade : cidades) {
			System.out.println(cidade.getId() + " - " + cidade.getNome() + " - " + cidade.getUf().getSigla());
		}

	}
	
	
}

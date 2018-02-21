package br.com.baiocchilousa.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Classe que 'Envelopa' a página da paginação Spring
 * de uma lista para uso no HTML, vizando melhorar o código nas páginas HTML
 * 
 * @author leolo
 *
 */

public class PageWrapper<T> {

	private Page<T> page;
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		
//		this.uriBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest); BUG do Spring quando pesquisando com espaços "Long Neck"
//		Endereço do erro para acompanhameneo: https://jira.spring.io/browse/SPR-10172
		
		String httpUrl = httpServletRequest.getRequestURL().append(
				httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20").replaceAll("excluido", "");
		
		this.uriBuilder = ServletUriComponentsBuilder.fromHttpUrl(httpUrl);// BUG do Spring quando pesquisando com espaços "Long Neck"

	}
	
	//Método que relorna o conteúdo da página para exibição no HTML
	public List<T> getConteudo() {
		return page.getContent();
	}
	
	//Método para testar se o retorno da página está vazio no HTML
	public boolean isVazia() {
		return page.getContent().isEmpty();
	}
	
	//Retorna o número da página atual para paginação no HTML
	public int getAtual() {
		return page.getNumber();
	}
	
	//Retorna se a página é a primeira
	public boolean getPrimeira() {
		return page.isFirst();
	}
	
	//Retorna se a página é a última
	public boolean isUltima() {
		return page.isLast();
	}
	
	//Retorna o total de páginas da paginação para uso no HTML
	public int getTotal() {
		return page.getTotalPages();
	}
	
	//Retorna o link para utilização nos controles de paginação no HTML
	public String urlParaPagina(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	
	//Retorna a string com a propriedade('campo') a ser ordenado no HTML
	public String urlOrdenada(String propriedade) {
		
		//Criamos outro UriComponentsBuilder para não atrapalharmos os outros links
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString()); 
		
		String valorSort = String.format("%s,%s", propriedade, inverterDirecao(propriedade));
		
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}
	
	//Retorna a direção da ordenação ('ASC' ou 'DESC')
	public String inverterDirecao(String propriedade) {
		String direcao = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		
		if(order != null) {
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		return direcao;
	}
	
	//Retorna a ordem para alteração do SPAN com ícone dropup (^) da página HTML
	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("asc");
	}
	
	//Retorna se a página possui alguma ordenação
	public boolean ordenada(String propriedade) {
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		
		if(order == null) {
			return false;
		}
		
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}
}
	
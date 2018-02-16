package br.com.baiocchilousa.brewer.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.model.ItemVenda;

/**
 * Classe que controla a tabela de vendas no Cadastro de vendas
 * só é acessada pelo controle da sessão do usuário <TabelasItensSession>
 * @author leolo
 *
 */
class TabelaItensVenda {
	
	private String uuid;
	private List<ItemVenda> itens = new ArrayList<>();
	
	
	
	public TabelaItensVenda(String uuid) {
		this.uuid = uuid;
	}

	//Traz valor total 
	public BigDecimal getValorTotal() {
		return itens.stream() //Java8
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)//Reduzo a lista de BigDecimal, somando (add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Cerveja cerveja, Integer quantidade) {
		
		Optional<ItemVenda> itemVendaOptional = buscarItemPorCerveja(cerveja);
		
		ItemVenda itemVenda = null;

		if (itemVendaOptional.isPresent()) {
			itemVenda = itemVendaOptional.get();
			itemVenda.setQuantidade(itemVenda.getQuantidade() + quantidade);
		} else {
			itemVenda = new ItemVenda();
			itemVenda.setCerveja(cerveja);
			itemVenda.setQuantidade(quantidade);
			itemVenda.setValorUnitario(cerveja.getValor());
			itens.add(0, itemVenda);//Adiciona e fica sempre em primeiro na lista
		}
			
	}

	
	public void alterarQuantidadeItens(Cerveja cerveja, Integer quantidade) {
		ItemVenda itemVenda = buscarItemPorCerveja(cerveja).get();
		itemVenda.setQuantidade(quantidade);
	}
	
	public void excluirItem(Cerveja cerveja) {
		//Java8 - sem utilizar For
		int indice = IntStream.range(0, itens.size())//Gera uma sequencia de inteiros de 0 a qtd de itens.size()
			.filter(i -> itens.get(i).getCerveja().equals(cerveja)) //Filtra no stream i qual o item no índice é igual ao indice da Cervaja que estamos recebendo
			.findAny().getAsInt(); //Encontre qualquer uma e retorne o indice
		itens.remove(indice);
	}
	
	public int total() {
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}
	
	private Optional<ItemVenda> buscarItemPorCerveja(Cerveja cerveja) {
		//Verifica se já existe a cerveja na lista para não duplicar na lista
		return itens.stream()
				.filter(i -> i.getCerveja().equals(cerveja))
				.findAny();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensVenda other = (TabelaItensVenda) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public String getUuid() {
		return uuid;
	}
}

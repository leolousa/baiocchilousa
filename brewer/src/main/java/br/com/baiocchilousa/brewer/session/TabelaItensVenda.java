package br.com.baiocchilousa.brewer.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.model.ItemVenda;

/**
 * Classe que controla a tabela de vendas no Cadastro de vendas
 * @author leolo
 *
 */
@SessionScope //Indica que será criado um componente para cada usuário logado!
@Component
public class TabelaItensVenda {
	
	private List<ItemVenda> itens = new ArrayList<>();
	
	//Traz valor total 
	public BigDecimal getValorTotal() {
		return itens.stream() //Java8
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)//Reduzo a lista de BigDecimal, somando (add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Cerveja cerveja, Integer quantidade) {
		
		//Verifica se já existe a cerveja na lista para não duplicar na lista
		Optional<ItemVenda> itemVendaOptional =  itens.stream()
			.filter(i -> i.getCerveja().equals(cerveja))
			.findAny();
		
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
	
	public int total() {
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}
	
}

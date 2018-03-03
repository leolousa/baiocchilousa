package br.com.baiocchilousa.brewer.service.event.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.model.ItemVenda;
import br.com.baiocchilousa.brewer.repository.CervejaRepository;
/**
 * Classe que escuta os eventos das vendas das cervejas para atualizar o estoque
 * @author leolo
 *
 */
@Component
public class VendaListener {

	@Autowired
	private CervejaRepository cervejas;
	
	@EventListener
	public void vendaEmitida(VendaEvent vendaEvent) {
		for(ItemVenda item : vendaEvent.getVenda().getItens()) {
			Cerveja cerveja = cervejas.getOne(item.getCerveja().getCodigo());
			cerveja.setQuantidadeEstoque(cerveja.getQuantidadeEstoque() - item.getQuantidade());
			cervejas.save(cerveja);
		}
	}
}

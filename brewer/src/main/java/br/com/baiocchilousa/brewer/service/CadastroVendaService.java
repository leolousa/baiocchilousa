package br.com.baiocchilousa.brewer.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.baiocchilousa.brewer.model.ItemVenda;
import br.com.baiocchilousa.brewer.model.StatusVenda;
import br.com.baiocchilousa.brewer.model.Venda;
import br.com.baiocchilousa.brewer.repository.VendaRepository;

@Service
public class CadastroVendaService {

	@Autowired
	private VendaRepository vendas;
	
	@Transactional
	public Venda salvar(Venda venda){
		if(venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		}
		
		if(venda.getDataEntrega() != null) {
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega()
					, venda.getHorarioEntrega() != null ? venda.getHorarioEntrega() : LocalTime.NOON));
		}
		
		return vendas.saveAndFlush(venda);
	}

	@Transactional
	public void emitir(Venda venda) {

		venda.setStatus(StatusVenda.EMITIDA);
		salvar(venda);
	}

}

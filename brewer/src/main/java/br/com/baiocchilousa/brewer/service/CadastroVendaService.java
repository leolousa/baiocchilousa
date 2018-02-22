package br.com.baiocchilousa.brewer.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.baiocchilousa.brewer.model.StatusVenda;
import br.com.baiocchilousa.brewer.model.Venda;
import br.com.baiocchilousa.brewer.repository.VendaRepository;

/**
 * Classe de serviços (regras de negócio costumam ficar nesta classe) 
 * @author leolo
 *
 */
@Service
public class CadastroVendaService {

	@Autowired
	private VendaRepository vendas;
	
	@Transactional
	public Venda salvar(Venda venda){
		
		if(venda.isSalvarProibido()) {
			throw new RuntimeException("Usuário tentando salvar uma venda proibida!");
		}
		
		if(venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		} else {
			Venda vendaExistente = vendas.findOne(venda.getCodigo());
			venda.setDataCriacao(vendaExistente.getDataCriacao());
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
	
	
	@PreAuthorize("#venda.usuario == principal.usuario or hasRole('CANCELAR_VENDA')")//Só permite cancelar, ao usuário que criou a venda ou Administrador
	@Transactional
	public void cancelar(Venda venda) {
		Venda vendaExistente = vendas.findOne(venda.getCodigo());
		vendaExistente.setStatus(StatusVenda.CANCELADA);
		vendas.save(vendaExistente);
	}

}

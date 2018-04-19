package br.com.baiocchilousa.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.baiocchilousa.brewer.repository.CervejaRepository;
import br.com.baiocchilousa.brewer.repository.ClienteRepository;
import br.com.baiocchilousa.brewer.repository.VendaRepository;

@Controller
public class DashboardController {

	@Autowired
	private VendaRepository vendas;
	
	@Autowired
	private CervejaRepository cervejas;
	
	@Autowired
	private ClienteRepository clientes;
	
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("dashboard");
		mv.addObject("vendasNoAno", vendas.vendasNoAno());
		mv.addObject("vendasNoMes", vendas.vendasNoMes());
		mv.addObject("ticketMedio", vendas.valorTicketMedioNoAno());
		mv.addObject("valorItensEstoque", cervejas.valorItensEstoque());
		mv.addObject("totalClientes", clientes.count());
		
		return mv;
	}
}

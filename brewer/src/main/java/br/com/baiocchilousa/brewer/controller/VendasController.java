package br.com.baiocchilousa.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.repository.CervejaRepository;
import br.com.baiocchilousa.brewer.session.TabelaItensVenda;

@Controller
@RequestMapping("/vendas")
public class VendasController {

	@Autowired
	private CervejaRepository cervejas;
	
	@Autowired
	private TabelaItensVenda tabelaItensVenda;
	
	@GetMapping("/nova")
	public String nova() {
		return "/venda/cadastro-venda";
	}
	
	@PostMapping("/item")
	public ModelAndView adcionarItem(Long codigoCerveja) {
		Cerveja cerveja = cervejas.findOne(codigoCerveja);
		tabelaItensVenda.adicionarItem(cerveja, 1);
		ModelAndView mv = new ModelAndView("venda/tabela-itens-venda");
		mv.addObject("itens", tabelaItensVenda.getItens());
		
		return mv;
	}
}

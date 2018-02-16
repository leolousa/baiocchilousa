package br.com.baiocchilousa.brewer.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.repository.CervejaRepository;
import br.com.baiocchilousa.brewer.session.TabelasItensSession;

@Controller
@RequestMapping("/vendas")
public class VendasController {

	@Autowired
	private CervejaRepository cervejas;
	
	@Autowired
	private TabelasItensSession tabelaItens;
	
	@GetMapping("/nova")
	public ModelAndView nova() {
		ModelAndView mv = new ModelAndView("/venda/cadastro-venda");
		mv.addObject("uuid", UUID.randomUUID().toString());//ID para o formulário na página de vendas (para cada tela)
		return mv;
	}
	
	@PostMapping("/item")
	public ModelAndView adcionarItem(Long codigoCerveja, String uuid) {
		Cerveja cerveja = cervejas.findOne(codigoCerveja);
		tabelaItens.adicionarItem(uuid, cerveja, 1);
		return mvTabelaItensVenda(uuid);
	}
	
	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoCerveja") Cerveja cerveja
			, Integer quantidade, String uuid) {
		tabelaItens.alterarQuantidadeItens(uuid, cerveja, quantidade);
		return mvTabelaItensVenda(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoCerveja}")
	public ModelAndView excluirItem(@PathVariable("codigoCerveja") Cerveja cerveja
			, @PathVariable("uuid") String uuid) {
		tabelaItens.excluirItem(uuid, cerveja);
		return mvTabelaItensVenda(uuid);
	}

	//Método que retorna a página de tabela-itens-venda
	private ModelAndView mvTabelaItensVenda(String uuid) {
		ModelAndView mv = new ModelAndView("venda/tabela-itens-venda");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		return mv;
	}
}

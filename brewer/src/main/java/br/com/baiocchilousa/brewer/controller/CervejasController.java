package br.com.baiocchilousa.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.model.Origem;
import br.com.baiocchilousa.brewer.model.Sabor;
import br.com.baiocchilousa.brewer.repository.EstiloRepository;
import br.com.baiocchilousa.brewer.service.CadastroCervejaService;

@Controller
public class CervejasController {
	
	//private static final Logger logger = LoggerFactory.getLogger(CervejasController.class);

	@Autowired
	private EstiloRepository estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja){
		
		ModelAndView mv = new ModelAndView("cerveja/cadastro-cerveja");
		
		mv.addObject("sabores", Sabor.values());// Enum do objeto Sabores
		mv.addObject("estilos", estilos.findAll());//Podemos colocar o método de busca diretamente pois não temos regras para acessar os Estilos
		mv.addObject("origens", Origem.values());//Enum do objeto Origens
		
		return mv;
	}
	
	@RequestMapping(value="/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model,
			RedirectAttributes attributes){
		
		if(result.hasErrors()){
			return novo(cerveja);
		}
		
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");

		return new ModelAndView("redirect:/cervejas/novo");
	}
	

}

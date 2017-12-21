package br.com.baiocchilousa.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.baiocchilousa.brewer.model.Cerveja;

@Controller
public class CervejasController {
	
	//private static final Logger logger = LoggerFactory.getLogger(CervejasController.class);

	@RequestMapping("/cervejas/novo")
	public String novo(Cerveja cerveja){		
		return "cerveja/cadastro-cerveja";
	}
	
	@RequestMapping(value="/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model,
			RedirectAttributes attributes){
		
		if(result.hasErrors()){
			return novo(cerveja);
		}
		
		attributes.addFlashAttribute("mensagem","Cerveja salva com sucesso!");
		
		return "redirect:/cervejas/novo";
	}
	

}

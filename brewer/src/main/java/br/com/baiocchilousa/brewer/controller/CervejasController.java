package br.com.baiocchilousa.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.baiocchilousa.brewer.controller.page.PageWrapper;
import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.model.Origem;
import br.com.baiocchilousa.brewer.model.Sabor;
import br.com.baiocchilousa.brewer.repository.CervejaRepository;
import br.com.baiocchilousa.brewer.repository.EstiloRepository;
import br.com.baiocchilousa.brewer.repository.filter.CervejaFilter;
import br.com.baiocchilousa.brewer.service.CadastroCervejaService;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {
	
	//private static final Logger logger = LoggerFactory.getLogger(CervejasController.class);

	@Autowired
	private EstiloRepository estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	@Autowired
	private CervejaRepository cervejas;	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cerveja cerveja){
		
		ModelAndView mv = new ModelAndView("cerveja/cadastro-cerveja");
		
		mv.addObject("sabores", Sabor.values());// Enum do objeto Sabores
		mv.addObject("estilos", estilos.findAll());//Podemos colocar o método de busca diretamente pois não temos regras para acessar os Estilos
		mv.addObject("origens", Origem.values());//Enum do objeto Origens
		
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, RedirectAttributes attributes){
		
		if(result.hasErrors()){
			return novo(cerveja);
		}
		
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");

		return new ModelAndView("redirect:/cervejas/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result,
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cerveja/pesquisa-cervejas");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		
		//System.out.println(">>> page number: " + pageable.getPageNumber());
		
		//mv.addObject("cervejas", cervejas.findAll(pageable));// Spring Data JPA
		
		PageWrapper<Cerveja> paginaWrapper = new PageWrapper<>(cervejas.filtrar(cervejaFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);//Criteria do Hibernate
		return mv;
		
	}
}

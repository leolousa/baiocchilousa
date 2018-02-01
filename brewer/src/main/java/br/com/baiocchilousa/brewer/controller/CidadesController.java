package br.com.baiocchilousa.brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.baiocchilousa.brewer.controller.page.PageWrapper;
import br.com.baiocchilousa.brewer.model.Cidade;
import br.com.baiocchilousa.brewer.repository.CidadeRepository;
import br.com.baiocchilousa.brewer.repository.EstadoRepository;
import br.com.baiocchilousa.brewer.repository.filter.CidadeFilter;
import br.com.baiocchilousa.brewer.service.CadastroCidadeService;
import br.com.baiocchilousa.brewer.service.exception.CidadeJaCadastradaException;

@Controller
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private CidadeRepository cidades;
	
	@Autowired
	private EstadoRepository estados;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@RequestMapping("/nova")
	public ModelAndView novo(Cidade cidade){
		ModelAndView mv = new ModelAndView("cidade/cadastro-cidade");
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@PostMapping("/nova")
	//@CacheEvict(value = "cidades", allEntries = true) //Limpa todo cache
	@CacheEvict(value = "cidades", key = "#cidade.estado.codigo", condition = "#cidade.temEstado()") //Limpa o cache apenas da key que foi incluída 
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes){
		
		if(result.hasErrors()){
			return novo(cidade);
		}
		
		try {
			cadastroCidadeService.salvar(cidade);
		} catch (CidadeJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(cidade);
		}
		
		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
		return new ModelAndView("redirect:/cidades/nova");
	}

	//@Cacheable("cidades") //Habilitar o cache para o método
	@Cacheable(value = "cidades", key = "#codigoEstado") //Habilitar o cache com a chave a ser observada
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {
		
		try {//Simulando uma demora na requisição
			Thread.sleep(1000);
		} catch (InterruptedException e) {	}
		
		return cidades.findByEstadoCodigo(codigoEstado);
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result,
			@PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cidade/pesquisa-cidades");
		mv.addObject("estados", estados.findAll());
		
		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(cidades.filtrar(cidadeFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);//Criteria do Hibernate
		return mv;
		
	}

}

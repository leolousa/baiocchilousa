package br.com.baiocchilousa.brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.baiocchilousa.brewer.controller.page.PageWrapper;
import br.com.baiocchilousa.brewer.model.Cliente;
import br.com.baiocchilousa.brewer.model.TipoPessoa;
import br.com.baiocchilousa.brewer.repository.ClienteRepository;
import br.com.baiocchilousa.brewer.repository.EstadoRepository;
import br.com.baiocchilousa.brewer.repository.filter.ClienteFilter;
import br.com.baiocchilousa.brewer.service.CadastroClienteService;
import br.com.baiocchilousa.brewer.service.exception.CpfCnpjClienteJaCadastradoException;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private EstadoRepository estados;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@Autowired
	private ClienteRepository clientes;	
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente){
		ModelAndView mv = new ModelAndView("cliente/cadastro-cliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@PostMapping(value="/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes){
		
		if(result.hasErrors()){
			return novo(cliente);
		}
		
		try {
			cadastroClienteService.salvar(cliente);
		} catch (CpfCnpjClienteJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
		
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		return new ModelAndView("redirect:/clientes/novo");
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result,
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cliente/pesquisa-clientes");
		
		PageWrapper<Cliente> paginaWrapper = new PageWrapper<>(clientes.filtrar(clienteFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);//Criteria do Hibernate
		return mv;
		
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Cliente> pesquisar(String nome) {
		validarTamanhoDoNome(nome);
		return clientes.findByNomeStartingWithIgnoreCase(nome);
		
	}

	//Método para validar a entrada da pesquisa rápida
	private void validarTamanhoDoNome(String nome) {
		if(StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	//Método para trara uma IlegalArgumentExeption no Controller
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIlegalArgumenteException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
}

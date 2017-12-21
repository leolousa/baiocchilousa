package br.com.baiocchilousa.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CidadesController {

	@RequestMapping("/cidades/novo")
	public String novo(){
		return "cidade/cadastro-cidade";
	}
}

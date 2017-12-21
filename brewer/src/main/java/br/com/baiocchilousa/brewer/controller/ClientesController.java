package br.com.baiocchilousa.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientesController {

	@RequestMapping("/clientes/novo")
	public String novo(){
		return "cliente/cadastro-cliente";
	}
	

}

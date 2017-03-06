package br.com.baiocchilousa.wg.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/*
 * Caminho do repositório de serviços: http://localhost:8080/wg/rest/wgservice
 * NOme do serviço: wgservice
 */

@Path("wgservice")
public class WgService {

	@GET
	public String exibir(){
		return "Teste de serviço do WG";
	}
}

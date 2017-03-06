package br.com.baiocchilousa.wg.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.baiocchilousa.wg.dao.ClienteDAO;
import br.com.baiocchilousa.wg.domain.Cliente;

/*
 * Caminho do repositório de serviços: http://localhost:8080/wg/rest/cliente
 * Nome do serviço: cliente
 */
@Path("cliente")
public class ClienteService {

	// http://localhost:8080/wg/rest/cliente
	@GET
	public String listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> clientes = clienteDAO.listar("tsRegistro", true);
		Gson gson = new Gson();
		String json = gson.toJson(clientes);
		return json;
	}

	// http://localhost:8080/wg/rest/cliente/10
	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long id) {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(id);
		Gson gson = new Gson();
		String json = gson.toJson(cliente);
		return json;
	}

	// http://localhost:8080/wg/rest/cliente
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Cliente cliente = gson.fromJson(json, Cliente.class);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);

		// TODO: Pode buscar o cliente salvo para exibir o código do cliente
		// inserido no banco

		String jsonSaida = gson.toJson(cliente);
		return jsonSaida;
	}

	// http://localhost:8080/wg/rest/cliente
	@PUT
	public String editar(String json) {
		Gson gson = new Gson();
		Cliente cliente = gson.fromJson(json, Cliente.class);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.editar(cliente);

		// TODO: Pode buscar o cliente salvo para exibir o código do cliente
		// inserido no banco

		String jsonSaida = gson.toJson(cliente);
		return jsonSaida;
	}
	
	
	// http://localhost:8080/wg/rest/cliente/10
		@DELETE
		public String excluir(String json) {
			Gson gson = new Gson();
			Cliente cliente = gson.fromJson(json, Cliente.class);
			
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.buscar(cliente.getId());
			clienteDAO.excluir(cliente);
			
			String jsonSaida = gson.toJson(cliente);
			return jsonSaida;
		}

}

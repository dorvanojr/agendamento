package br.com.agendamento.dao;

import java.util.List;
import br.com.agendamento.entity.Cliente;


public interface ClienteDao {

	public void save(Cliente cliente);
	public List<Cliente> list(String nome);
	public List<Cliente> listLoginAll(int id);
	public List<Cliente> listLoginNome(String nome, int idEmpresa);
	public List<Cliente> listAll();
	public void remove(Cliente cliente);
	public void update(Cliente cliente);
	public List<Cliente> listId(int id);
	
}

package br.com.agendamento.dao;

import java.util.List;

import br.com.agendamento.entity.Cliente;
import br.com.agendamento.entity.User;
import br.com.agendamento.entity.Userauth;


public interface UsuarioDao {

	public void save(Cliente cliente);
	Userauth retornaUsuario(String login, String senha);
	
	public void saveLogin(User user);
	public List<Cliente> list(String nome);
	public List<Cliente> listAll();
	public void remove(Cliente cliente);
	public void update(Cliente cliente);
	public List<User> consultaLogin(String login, String senha);
	public List<User> listUsuario(String nome);
	List<User>  retornaEmpresa(String login);
	
}

package br.com.agendamento.dao;

import java.util.List;
import br.com.agendamento.entity.User;


public interface UserDao {

	
	public List<User> list(String nome);
	
}

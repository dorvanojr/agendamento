package br.com.agendamento.dao.impl;


import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.agendamento.dao.UserDao;
import br.com.agendamento.entity.User;




public class UserDaoImpl  implements UserDao, Serializable {

	private static final long serialVersionUID = 1L;
	@Inject private EntityManager em;
	
	
	public void beginTransaction() {

		em.getTransaction().begin();
	}

	public void commit() {
		em.getTransaction().commit();
	}

	public void rollback() {
		em.getTransaction().rollback();
	}

	public void closeTransaction() {
		em.close();
	}

	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void flush() {
		em.flush();
	}

	
	@Transactional
	 public List<User> list(String nome) {  
		  List<User> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("User.findList");  
	  
	            consulta.setParameter("login",nome);  
	         
	             
	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    
	    
	 }
	


}

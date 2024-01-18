package br.com.agendamento.dao.impl;


import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.agendamento.dao.UsuarioDao;
import br.com.agendamento.entity.Cliente;
import br.com.agendamento.entity.User;
import br.com.agendamento.entity.Userauth;


public class UsuarioDaoImpl  implements UsuarioDao, Serializable {

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
	public void saveLogin(User user) {
		beginTransaction();	
		em.persist(user);
		commit();
			
	}

	@Transactional
	public void save(Cliente cliente) {
		beginTransaction();	
		em.persist(cliente);
		commit();
			
	}
	
	
	@Transactional
	  public void remove(Cliente cliente) {
		    beginTransaction();   
	        Cliente c = em.getReference(Cliente.class, cliente.getIdCliente());
	        em.remove(c);
	        commit();
	       
	    }
	

	@Transactional
	  public void update(Cliente cliente) {
		    beginTransaction();
		    em.merge(cliente); 
		    commit();
			rollback();
	       
	    }
	  
	
	
	
	@Transactional
	  public List<Cliente> listAll() {  
		  List<Cliente> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("Cliente.findList");  
	  
	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    	    
	 }
	@Transactional
	 public List<Cliente> list(String nome) {  
		  List<Cliente> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("Cliente.findAll");  
	  
	            consulta.setParameter("nome",nome);  
	            consulta.setParameter("status",0);  
	             
	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    
	    
	 }
	 @Transactional
	   public Userauth retornaUsuario(String login, String senha) {
		   StringBuilder hql = new StringBuilder();
	        hql.append(" SELECT ua FROM Userauth ua ");
	        hql.append(" INNER JOIN FETCH ua.user u ");
	        hql.append(" where u.username = :login ");
	        Query q = em.createQuery(hql.toString());
	        q.setParameter("login", login);
	        return (Userauth) q.getSingleResult();
	    }
	
	   
	   
	   @Transactional
	   public List<User> retornaEmpresa(String login) {
		   List<User> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("User.findList");  
	            consulta.setParameter("login",login);  
	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    }
	
	public List<User> consultaLogin(String login, String senha){
		
		  List<User> result = null;  
		
		  
		  try {  
	            Query consulta = em.createNamedQuery("Login.findLogin");  
	  
	            consulta.setParameter("login",login);  
	            consulta.setParameter("senha",senha);  
	             
	            result = consulta.getResultList();
	            System.out.println("ocorreu o erro: " + result.toString());      
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
		
		return result;
		
		
		
		
	}
	
	
	@Transactional
	 public List<User> listUsuario(String nome) {  
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
	 
	 public EntityManager getEm() {
			return em;
		}

		public void setEm(EntityManager em) {
			this.em = em;
		}

		


	


}

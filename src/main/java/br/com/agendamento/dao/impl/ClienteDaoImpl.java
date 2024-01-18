package br.com.agendamento.dao.impl;


import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import br.com.agendamento.dao.ClienteDao;
import br.com.agendamento.entity.Cliente;



public class ClienteDaoImpl  implements ClienteDao, Serializable {

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
	public void save(Cliente cliente) {
		try{
			beginTransaction();	
			em.persist(cliente);
			commit();
			
		}catch(Exception ex){
			System.out.println("erro" + ex.getMessage());
			ex.getStackTrace();
		}
	
			
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
		try{
			 beginTransaction();
			 em.merge(cliente); 
			 commit();
		}catch(Exception ex){
			System.out.println("erro" + ex.getMessage());
			ex.getStackTrace();
		}
		   
			
	       
	    }
	  
	@Transactional
	  public List<Cliente> listAll() {  
		  List<Cliente> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("Cliente.findList");  
	            consulta.setParameter("status",1); 
	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    
	    
	 }
	@Transactional
	    public List<Cliente> listLoginNome(String nome, int id) {
		   StringBuilder hql = new StringBuilder();
	        hql.append(" SELECT cli FROM Cliente cli ");
	        hql.append(" INNER JOIN FETCH  cli.empresa em ");
	        hql.append(" where cli.status = :status");
	        hql.append(" and em.idEmpresa = :id");
	        hql.append(" and cli.nome = :nome");
	        Query q = em.createQuery(hql.toString());
	        q.setParameter("status", 1);  
	        q.setParameter("id", id);  
	        q.setParameter("nome", nome);  
	        List<Cliente> singleResult = (List<Cliente>) q.getResultList();
			return singleResult;  
	    }
	
	@Transactional
	  public List<Cliente> listLoginAll(int id) {
		   StringBuilder hql = new StringBuilder();
	        hql.append(" SELECT cli FROM Cliente cli ");
	        hql.append(" INNER JOIN FETCH  cli.empresa em ");
	        hql.append(" where cli.status = :status");
	        hql.append(" and em.id = :id");
	        Query q = em.createQuery(hql.toString());
	        q.setParameter("status", 1);  
	        q.setParameter("id", id);  
	        List<Cliente> singleResult = (List<Cliente>) q.getResultList();
			return singleResult;   
	    }
	
	@Transactional
	 public List<Cliente> list(String nome) {  
		  List<Cliente> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("Cliente.findAll");  
	  
	            consulta.setParameter("nome",nome);  
	            consulta.setParameter("status",1);  
	             
	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    
	    
	 }
	 
	 
		@Transactional
		 public List<Cliente> listId(int id) {  
			  List<Cliente> resultado = null;  

		        try {  
		            Query consulta = em.createNamedQuery("Cliente.findId");  
		  
		            consulta.setParameter("id",id);  
		            consulta.setParameter("status",1);  
		             
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

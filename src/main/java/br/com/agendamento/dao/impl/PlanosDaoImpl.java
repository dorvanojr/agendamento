package br.com.agendamento.dao.impl;


import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.agendamento.dao.PlanosDao;
import br.com.agendamento.dao.UserDao;
import br.com.agendamento.entity.ClinicaPlanosSaude;
import br.com.agendamento.entity.Medico;
import br.com.agendamento.entity.Planos;
import br.com.agendamento.entity.User;




public class PlanosDaoImpl  implements PlanosDao, Serializable {

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
	 public List<Planos> list() {  
		  List<Planos> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("Planos.findAll");  

	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    
	    
	 }
	@Transactional
	public void save(ClinicaPlanosSaude clinicaPlanosSaude) {
		try{
			beginTransaction();	
			em.persist(clinicaPlanosSaude);
			commit();
			
		}catch(Exception ex){
			System.out.println("erro" + ex.getMessage());
			ex.getStackTrace();
		}
	
			
	}
	


}

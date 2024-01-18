package br.com.agendamento.dao.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.agendamento.dao.EspecialidadeDao;
import br.com.agendamento.entity.Clinica;
import br.com.agendamento.entity.Especialidade;
import br.com.agendamento.entity.Medico;


public class EspecialidadeDaoImpl  implements EspecialidadeDao, Serializable {

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
	  public List<Especialidade> listAll() {  
		  List<Especialidade> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("Especialidade.findAll");  
	        
	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    
	    
	 }
	
	
	public List<Especialidade> listAllId(int id){  
		  List<Especialidade> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("Especialidade.findId");  
	            consulta.setParameter("id", id);
	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    
	    
	 }
	
	public List<Especialidade> listAllNome(String nome){  
		  List<Especialidade> resultado = null;  

	        try {  
	            Query consulta = em.createNamedQuery("Especialidade.findNome");  
	            consulta.setParameter("nome", nome);
	            resultado = consulta.getResultList();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
			return resultado;
	    
	    
	 }
	
	

	@Transactional
	public List<Clinica> listClinicaPorEsp(int id){
		try{
			 List<Clinica> resultList  = new ArrayList<Clinica>();
		StringBuilder hql = new StringBuilder();
        hql.append(" SELECT cli FROM Clinica cli ");
        hql.append(" INNER JOIN FETCH cli.especialidade esp ");
        hql.append(" where ");
        hql.append(" esp.idEspecialidade = :id ");
        Query q = em.createQuery(hql.toString());
        q.setParameter("id", id);    
          resultList = (List<Clinica>) q.getResultList();
             return resultList;
		}catch(Exception ex){
			System.out.print("Erro sql " + ex.fillInStackTrace());
		}
		return null;
		
		
	}
	
	@Transactional
	public Clinica listClinicaPorEspObject(int id){
		try{
			 Clinica resultList  = new Clinica();
		StringBuilder hql = new StringBuilder();
        hql.append(" SELECT cli FROM Clinica cli ");
        hql.append(" INNER JOIN FETCH cli.especialidade esp ");
        hql.append(" where ");
        hql.append(" esp.idEspecialidade = :id ");
        Query q = em.createQuery(hql.toString());
        q.setParameter("id", id);    
          resultList =  (Clinica) q.getSingleResult();
             return resultList;
		}catch(Exception ex){
			System.out.print("Erro sql " + ex.fillInStackTrace());
		}
		return null;
		
		
	}
	 
	

	@Transactional
	public List<Medico> listMedicoPorClinica(int id){
		try{
			 List<Medico> resultList  = new ArrayList<Medico>();
		StringBuilder hql = new StringBuilder();
        hql.append(" SELECT me FROM Medico me ");
        hql.append(" INNER JOIN FETCH me.clinica cli ");
        hql.append(" INNER JOIN FETCH cli.especialidade esp");
        hql.append(" where ");
        hql.append(" cli.idClinica = :id ");
        Query q = em.createQuery(hql.toString());
        q.setParameter("id", id);    
          resultList = (List<Medico>) q.getResultList();
             return resultList;
		}catch(Exception ex){
			System.out.print("Erro sql " + ex.fillInStackTrace());
		}
		return null;
		
		
	}
	
	
	
	 
	
	public Especialidade listPorNome(String nome){  
		
		Especialidade especialidade = new Especialidade();
	        try {  
	            Query consulta = em.createNamedQuery("Especialidade.findNome");  
	            consulta.setParameter("nome", nome);
	            especialidade = (Especialidade) consulta.getSingleResult();
	                   
	        } catch (Exception e) {  
	            System.out.println("ocorreu o erro: " + e.getMessage());  
	        } 
	        
		return especialidade;
	    
	    
	 }
	
	
	
	 
		
	 public EntityManager getEm() {
			return em;
		}

		public void setEm(EntityManager em) {
			this.em = em;
		}

		

	

}

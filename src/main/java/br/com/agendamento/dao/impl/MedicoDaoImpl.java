package br.com.agendamento.dao.impl;


import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.agendamento.dao.MedicoDao;
import br.com.agendamento.entity.Cliente;
import br.com.agendamento.entity.Medico;

public class MedicoDaoImpl  implements MedicoDao, Serializable {

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
	public void save(Medico medico) {
		try{
			beginTransaction();	
			em.persist(medico);
			commit();
			
		}catch(Exception ex){
			System.out.println("erro" + ex.getMessage());
			ex.getStackTrace();
		}
	
			
	}
	
	
	@Transactional
	  public void update(Medico medico) {
		try{
			 beginTransaction();
			 em.merge(medico); 
			 commit();
		}catch(Exception ex){
			System.out.println("erro" + ex.getMessage());
			ex.getStackTrace();
		}
     
	  }
	
	
	  public List<Medico> listMedicoPorClinicaIDNomeMedico(int id, String nomeMedico) {
		   StringBuilder hql = new StringBuilder();
	        hql.append(" SELECT med FROM Medico med ");
	        hql.append(" INNER JOIN FETCH  med.clinica cli ");
	        hql.append(" where cli.idClinica = :id");
	        hql.append(" and med.nmMedico = :nome");
	        Query q = em.createQuery(hql.toString());
	       	q.setParameter("id", id);  
	       	q.setParameter("nome", nomeMedico); 
	        List<Medico> singleResult = (List<Medico>) q.getResultList();
			return singleResult;  
	    }
	  
	  public List<Medico> listMedicoPorClinicaID(int id) {
		   StringBuilder hql = new StringBuilder();
	        hql.append(" SELECT med FROM Medico med ");
	        hql.append(" INNER JOIN FETCH  med.clinica cli ");
	        hql.append(" where cli.idClinica = :id");
	        Query q = em.createQuery(hql.toString());
	       	q.setParameter("id", id);  
	        List<Medico> singleResult = (List<Medico>) q.getResultList();
			return singleResult;  
	    }
	  
	  
		@Transactional
		  public void remove(Medico medico) {
			    beginTransaction();   
		        Medico c = em.getReference(Medico.class, medico.getIdMedico());
		        em.remove(c);
		        commit();
		       
		    }
		
	 
	 public EntityManager getEm() {
			return em;
		}

		public void setEm(EntityManager em) {
			this.em = em;
		}

		
		@Transactional
		  public List<Medico> listAllID(int id) {  
			  List<Medico> resultado = null;  

		        try {  
		            Query consulta = em.createNamedQuery("Medico.findId");  
		            consulta.setParameter("id",id); 
		            resultado = consulta.getResultList();
		                   
		        } catch (Exception e) {  
		            System.out.println("ocorreu o erro: " + e.getMessage());  
		        } 
		        
				return resultado;
		    
		    
		 }
	

}

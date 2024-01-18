package br.com.agendamento.dao.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.agendamento.dao.ClinicaDao;
import br.com.agendamento.entity.Cliente;
import br.com.agendamento.entity.Clinica;

public class ClinicaDaoImpl  implements ClinicaDao, Serializable {

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
	public void save(Clinica clinica) {
		try{
			beginTransaction();	
			em.persist(clinica);
			commit();
			
		}catch(Exception ex){
			System.out.println("erro" + ex.getMessage());
			ex.getStackTrace();
		}
	
			
	}
	
	
	
	
	@Transactional
	  public void update(Clinica clinica) {
		try{
			 beginTransaction();
			 em.merge(clinica); 
			 commit();
		}catch(Exception ex){
			System.out.println("erro" + ex.getMessage());
			ex.getStackTrace();
		}
     
	  }

	 public EntityManager getEm() {
			return em;
		}

		public void setEm(EntityManager em) {
			this.em = em;
		}

		

	

}

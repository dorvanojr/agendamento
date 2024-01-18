package br.com.agendamento.dao.impl;


import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.agendamento.dao.HorarioDiaSemanalDao;
import br.com.agendamento.dao.MedicoDao;
import br.com.agendamento.entity.AgendaConfirm;
import br.com.agendamento.entity.Cliente;
import br.com.agendamento.entity.HorarioDiaSemanal;
import br.com.agendamento.entity.Medico;

public class HorarioDiaSemanalDaoImpl  implements HorarioDiaSemanalDao, Serializable {

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
	public void save(HorarioDiaSemanal diaSemanal) {
		try{
			beginTransaction();	
			em.persist(diaSemanal);
			commit();
			
		}catch(Exception ex){
			System.out.println("erro" + ex.getMessage());
			ex.getStackTrace();
		}
	
			
	}
	
	
	@Transactional
	public void save(AgendaConfirm a) {
		try{
			beginTransaction();	
			em.persist(a);
			commit();
			
		}catch(Exception ex){
			System.out.println("erro" + ex.getMessage());
			ex.getStackTrace();
		}
	
			
	}
	
	@Transactional
	  public void remove(HorarioDiaSemanal horarioDiaSemanal) {
		    beginTransaction();   
	        HorarioDiaSemanal c = em.getReference(HorarioDiaSemanal.class, horarioDiaSemanal.getIdHorariodiasemanal());
	        em.remove(c);
	        commit();
	       
	    }
	
	  public  List<HorarioDiaSemanal> listAllID(int id){
		   StringBuilder hql = new StringBuilder();
	        hql.append(" SELECT hor FROM HorarioDiaSemanal hor ");
	        hql.append(" INNER JOIN FETCH  hor.Medico med ");
	        hql.append(" where ");
	        hql.append("  med.idMedico = :id");
	        Query q = em.createQuery(hql.toString());
	       	q.setParameter("id", id);  
	        List<HorarioDiaSemanal> singleResult = (List<HorarioDiaSemanal>) q.getResultList();
			return singleResult;  
	    }
	  
	  
	  public  List<HorarioDiaSemanal> listAllIDHorario(int id){
		   StringBuilder hql = new StringBuilder();
	        hql.append(" SELECT hor FROM HorarioDiaSemanal hor ");
	        hql.append(" where ");
	        hql.append("  hor.idHorariodiasemanal = :id");
	        Query q = em.createQuery(hql.toString());
	       	q.setParameter("id", id);  
	        List<HorarioDiaSemanal> singleResult = (List<HorarioDiaSemanal>) q.getResultList();
			return singleResult;  
	    }
	  
	  public  List<AgendaConfirm> listAgendaConfirmID(int id){
		   StringBuilder hql = new StringBuilder();
	        hql.append(" SELECT hor FROM AgendaConfirm hor ");
	        hql.append(" INNER JOIN FETCH  hor.Medico med ");
	        hql.append(" INNER JOIN FETCH  hor.cliente cli ");
	        hql.append(" INNER JOIN FETCH  hor.clinica clin ");
	        hql.append(" INNER JOIN FETCH  hor.horarioDiaSemanal dias ");
	        hql.append(" where ");
	        hql.append("  med.idMedico = :id");
	        Query q = em.createQuery(hql.toString());
	       	q.setParameter("id", id);  
	        List<AgendaConfirm> singleResult = (List<AgendaConfirm>) q.getResultList();
			return singleResult;  
	    }

	 
	 public EntityManager getEm() {
			return em;
		}

		public void setEm(EntityManager em) {
			this.em = em;
		}

		
	

}

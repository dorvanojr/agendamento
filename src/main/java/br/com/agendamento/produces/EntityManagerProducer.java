package br.com.agendamento.produces;

import java.io.Serializable;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import br.com.agendamento.listener.EMF;




public class EntityManagerProducer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;

	@Produces
	
	protected EntityManager createEntityManager() {
		entityManager = EMF.createEntityManager();
		return entityManager;
	}

	protected void closeEntityManager(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}
}

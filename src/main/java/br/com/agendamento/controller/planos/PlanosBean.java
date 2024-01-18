package br.com.agendamento.controller.planos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.controller.util.GenericDto;
import br.com.agendamento.dao.ClinicaDao;
import br.com.agendamento.dao.EspecialidadeDao;
import br.com.agendamento.dao.PlanosDao;
import br.com.agendamento.dao.impl.PlanosDaoImpl;
import br.com.agendamento.entity.ClinicaPlanosSaude;
import br.com.agendamento.entity.Especialidade;
import br.com.agendamento.entity.Planos;
import br.com.agendamento.entity.User;



@ConversationScoped
@Named
public class PlanosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Inject
	private Conversation conversation;
	@Inject
    private PlanosDaoImpl dao;
	@Inject
	private Planos planos;
	@Inject
	private ClinicaPlanosSaude clinicaPlanosSaude;
	private List<Planos> listas;
	private List<Planos> selectedPlanos;
	
	private static GenericDto instance;

	public static GenericDto getInstance() {
	      if(instance == null) {
	         instance = new GenericDto();
	      }
	      return instance;
	 }

	    @PostConstruct
		public void init() {
			log.info("Inicializando a conversacao no Bean "
					+ this.getClass().getCanonicalName());
			beginConversation();
			
		}
	    
	    
		private void beginConversation() {
			if (conversation.isTransient()) {
				conversation.begin();
				log.info("ConversaÃ§Ã£o iniciada - ID:" + conversation.getId());
			    
			}
		}
		@PreDestroy
	    public void endConversation() {
			if (!conversation.isTransient()) {
				System.out.println("Conversação encerrada, id: " + conversation.getId());
				conversation.end();
			}
		}
	
	 
	   
		public List<Planos> listPlanosAll() {
			try {
				
				PlanosDao dao = getDao();
				
				listas = dao.list();

			} catch (Exception ex) {

			}
			return listas;

		}


		public void update(User user) {
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				   
				   beginConversation();
				   System.out.println("usuario login" + GenericDto.login + " Singleton " +getInstance().getLogin());
				   PlanosDao dao = getDao();
				 
				   for(Planos planos : selectedPlanos){
					   
					   clinicaPlanosSaude.setPlanos(planos);
					   clinicaPlanosSaude.setClinica(user.getClinica());
						  
					   dao.save(clinicaPlanosSaude);
				   }
				   
				   
				  
				   dao.save(clinicaPlanosSaude);
				
				context.addMessage(null, new FacesMessage("Resultado",
						"Cadastrado com sucesso!!! " + ""));

			} catch (Exception ex) {

				context.addMessage(null, new FacesMessage("Resultado",
						"Erro ao Cadastrar!!! " + ex.getMessage()));
				ex.getStackTrace();
			}finally{
				endConversation();
				
			}
		}
	
	
	

	

	public PlanosDaoImpl getDao() {
		return dao;
	}

	public void setDao(PlanosDaoImpl dao) {
		this.dao = dao;
	}

	public Planos getPlanos() {
		return planos;
	}

	public void setPlanos(Planos planos) {
		this.planos = planos;
	}

	public List<Planos> getListas() {
		return listas;
	}

	public void setListas(List<Planos> listas) {
		this.listas = listas;
	}

	public List<Planos> getSelectedPlanos() {
		return selectedPlanos;
	}

	public void setSelectedPlanos(List<Planos> selectedPlanos) {
		this.selectedPlanos = selectedPlanos;
	}

	public ClinicaPlanosSaude getClinicaPlanosSaude() {
		return clinicaPlanosSaude;
	}

	public void setClinicaPlanosSaude(ClinicaPlanosSaude clinicaPlanosSaude) {
		this.clinicaPlanosSaude = clinicaPlanosSaude;
	}
	


	
}

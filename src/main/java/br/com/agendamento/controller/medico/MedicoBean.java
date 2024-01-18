package br.com.agendamento.controller.medico;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.controller.util.GenericDto;
import br.com.agendamento.dao.EspecialidadeDao;
import br.com.agendamento.dao.MedicoDao;
import br.com.agendamento.dao.UserDao;
import br.com.agendamento.dao.impl.EspecialidadeDaoImpl;
import br.com.agendamento.dao.impl.MedicoDaoImpl;
import br.com.agendamento.dao.impl.UserDaoImpl;
import br.com.agendamento.entity.Clinica;
import br.com.agendamento.entity.Especialidade;
import br.com.agendamento.entity.Medico;
import br.com.agendamento.entity.Email;
import br.com.agendamento.entity.Endereco;
import br.com.agendamento.entity.Telefone;
import br.com.agendamento.entity.User;
import br.com.agendamento.entity.Userauth;




@ConversationScoped
@Named
public class MedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private Conversation conversation;
	@Inject
    private MedicoDaoImpl dao;
	@Inject
	private Userauth userAuth;
	@Inject
	private User user; 
	@Inject
	private Medico medico;
	
	@Inject
	private Medico medico1;
	@Inject
	private Clinica clinica;
	@Inject
    private EspecialidadeDaoImpl especialidadeDaoImpl;
	@Inject
	private Telefone telefone;
	@Inject
	private Endereco endereco;
	@Inject
	private Email email;
	private List<Medico> medicos;
	private List<Medico> listas;
	private Integer idMedico;
	private String parametros;
	@Inject
	private UserDaoImpl UserDAO;
	private DataModel<Medico> listaMedicosData;
	private int idEspecialidade;
	
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
	
	 
	   



	
	public void save() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			   beginConversation();
			   System.out.println("usuario login" + GenericDto.login + " Singleton " +getInstance().getLogin());

			    MedicoDao dao = getDao();
			    UserDao dao1 = getUserDAO();
			    
			    EspecialidadeDao espDao = getEspecialidadeDaoImpl();
				   List<Especialidade> lista =  espDao.listAllId(getIdEspecialidade());
					for (Especialidade esp :lista) {
						clinica.setEspecialidade(esp);
					} 
			    
				List<User> users = dao1.list(getInstance().getLogin());
				
				for (User f : users) {
			       medico.setClinica(f.getClinica());
				}
					
				endereco.setMedico(medico);
				telefone.setMedico(medico);
				email.setMedico(medico);
				medico.addTelefones(telefone);
				medico.addEnderecos(endereco);
				medico.addEmails(email);
				dao.save(medico);
				
			context.addMessage(null, new FacesMessage("Resultado",
					"Cadastrado com sucesso!!! " + ""));

		} catch (Exception ex) {

			context.addMessage(null, new FacesMessage("Resultado",
					"Erro ao Cadastrar!!! " + ex.getMessage()));
			ex.fillInStackTrace();
		}finally{
			endConversation();
		}
	}
	
	@Transactional
	public void update() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			  
		
			   
			   MedicoDao dao = getDao();
			   
			   
			   dao.update(medico1);	

			context.addMessage(null, new FacesMessage("Resultado",
					"update com sucesso!!! " + ""));

		} catch (Exception ex) {

			context.addMessage(null, new FacesMessage("Resultado",
					"Erro ao Cadastrar!!! " + ex.getMessage()));
			ex.fillInStackTrace();
		}
	}
	
	@Transactional
	public void excluir(Medico medico) {

		MedicoDao dao = getDao();
	    dao.remove(medico);
		
	}
	
	 @Transactional
		public String acao() {
		    beginConversation();
		    MedicoDao dao = getDao();
		    UserDao dao1 = getUserDAO();
		    
			List<User> users = dao1.list(getInstance().getLogin());
			
			for (User f : users) {
		    
              medicos = dao.listMedicoPorClinicaIDNomeMedico(f.getClinica().getIdClinica(), parametros);
              
              
			}
			//
			listaMedicosData = new ListDataModel<Medico>(medicos);
			parametros = "";
			return "";
		}
	 
		@Transactional
		public List<Medico> listMedicosAll() {
			 beginConversation();
			try {
				
				 MedicoDao dao = getDao();
				    UserDao dao1 = getUserDAO();
				    
					List<User> users = dao1.list(getInstance().getLogin());
					
					for (User f : users) {
				    
		              listas = dao.listMedicoPorClinicaID(f.getClinica().getIdClinica());
		              
		              
					}

			} catch (Exception ex) {

			}
			return listas;

		}

	 @Transactional
		public void onRowEdit(RowEditEvent event) {
			FacesContext context = FacesContext.getCurrentInstance();

			try {

				MedicoDao dao = getDao();
				
			

				Medico medico1 = (Medico) event.getObject();

				dao.update(medico1);

				context.addMessage(null, new FacesMessage("Resultado",
						"Alterado com sucesso!!! " + ""));

			} catch (Exception ex) {

				context.addMessage(null, new FacesMessage("Resultado",
						"Alterado com sucesso!!! " + ""));
			}

		}
	 
	 
	   @Transactional
		public void onRowCancel(RowEditEvent event) {
			FacesMessage msg = new FacesMessage("Edit Cancelled",
					((Medico) event.getObject()).getNmMedico());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public void setListas(List<Medico> listas) {
		this.listas = listas;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}
	

	public List<Medico> getListas() {
		return listas;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}



	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MedicoDaoImpl getDao() {
		return dao;
	}

	public void setDao(MedicoDaoImpl dao) {
		this.dao = dao;
	}
	public Userauth getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(Userauth userAuth) {
		this.userAuth = userAuth;
	}

	public UserDaoImpl getUserDAO() {
		return UserDAO;
	}

	public void setUserDAO(UserDaoImpl userDAO) {
		UserDAO = userDAO;
	}
	

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public DataModel<Medico> getListaMedicosData() {
		return listaMedicosData;
	}

	public void setListaMedicosData(DataModel<Medico> listaMedicosData) {
		this.listaMedicosData = listaMedicosData;
	}

	public Medico getMedico1() {
		return medico1;
	}

	public void setMedico1(Medico medico1) {
		this.medico1 = medico1;
	}

	public int getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(int idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public EspecialidadeDaoImpl getEspecialidadeDaoImpl() {
		return especialidadeDaoImpl;
	}

	public void setEspecialidadeDaoImpl(EspecialidadeDaoImpl especialidadeDaoImpl) {
		this.especialidadeDaoImpl = especialidadeDaoImpl;
	}
	
	
	
}

package br.com.agendamento.controller.clinica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.controller.util.GenericDto;
import br.com.agendamento.dao.ClinicaDao;
import br.com.agendamento.dao.EspecialidadeDao;
import br.com.agendamento.dao.impl.ClinicaDaoImpl;
import br.com.agendamento.dao.impl.EspecialidadeDaoImpl;
import br.com.agendamento.entity.Clinica;
import br.com.agendamento.entity.Email;
import br.com.agendamento.entity.Endereco;
import br.com.agendamento.entity.Especialidade;
import br.com.agendamento.entity.Telefone;
import br.com.agendamento.entity.User;
import br.com.agendamento.entity.Userauth;


@ConversationScoped
@Named
public class ClinicaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Inject
	private Conversation conversation;
	@Inject
    private ClinicaDaoImpl dao;
	@Inject
    private EspecialidadeDaoImpl especialidadeDaoImpl;
	@Inject
	private Userauth userAuth;
	@Inject
	private User user; 
	@Inject
	private Clinica clinica; 
	@Inject
	private Telefone telefone;
	@Inject
	private Endereco endereco;
	@Inject
	private Email email;
	private List<Clinica> clinicas;
	private List<Clinica> listas;
	private List<Especialidade> listasEspecialidade;
	private Integer idclinica;
	private String parametros;
	private int idEspecialidade;
	private String NomeEspecialidade;
	@Inject
	private Especialidade especialidade;
	
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
				
			   EspecialidadeDao espDao = getEspecialidadeDaoImpl();
			   List<Especialidade> lista =  espDao.listAllId(getIdEspecialidade());
				for (Especialidade esp :lista) {
					clinica.setEspecialidade(esp);
				} 
			   
			    ClinicaDao dao = getDao();    
				endereco.setClinica(clinica);
				telefone.setClinica(clinica);
				userAuth.setNameAuthority("ROLE_CLIN");
				userAuth.setUser(user);
				user.setClinica(clinica);
				user.setEnable(1);
				user.addUserAlth(userAuth);
				email.setClinica(clinica);
				clinica.addTelefones(telefone);
				clinica.addEnderecos(endereco);
				clinica.addEmails(email);
				clinica.addUser(user);
				dao.save(clinica);
				
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
	
	

	
	public void update(User user) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			   
			   beginConversation();
			   System.out.println("usuario login" + GenericDto.login + " Singleton " +getInstance().getLogin());
			   ClinicaDao dao = getDao();
			  
			   EspecialidadeDao espDao = getEspecialidadeDaoImpl();
			   List<Especialidade> lista =  espDao.listAllNome(user.getClinica().getEspecialidade().getNome());
				for (Especialidade esp :lista) {
					clinica.setEspecialidade(esp);
				} 
			   clinica.setIdClinica(user.getClinica().getIdClinica());
			   clinica.setNomeFantasia(user.getClinica().getNomeFantasia());
			   clinica.setCnpj(user.getClinica().getCnpj());
			   clinica.setRazaoSocial(user.getClinica().getRazaoSocial());
			   
			   if(user.getClinica().getEndereco().size() > 0){
				   if(user.getClinica().getEndereco().get(0).getIdEndereco() != 0){
				   endereco.setClinica(user.getClinica());
				   clinica.addEnderecos(user.getClinica().getEndereco().get(0));
				   }
			   }else if(user.getClinica().getEndereco().size() == 0){
				   endereco.setClinica(clinica);
				   clinica.addEnderecos(endereco);
			   }
			   if(user.getClinica().getTelefone().size() > 0){
			     if(user.getClinica().getTelefone().get(0).getIdTelefone() != 0){
			       telefone.setClinica(user.getClinica());
				   clinica.addTelefones(user.getClinica().getTelefone().get(0));
			     }
			   }else if(user.getClinica().getTelefone().size() == 0){
			      telefone.setClinica(clinica);
				   clinica.addTelefones(telefone);
			   }

			   
			   if(user.getClinica().getEmail().size() > 0){
			     if(user.getClinica().getEmail().get(0).getIdEmail() != 0){
				   email.setClinica(user.getClinica());
				   clinica.addEmails(user.getClinica().getEmail().get(0));
			    } 
			   }else if(user.getClinica().getEmail().size() == 0){
				   email.setClinica(clinica);
				   clinica.addEmails(email);
			   }
			   
			   clinica.addUser(user);
			   dao.update(clinica);
			
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
	


	public Integer getIdclinica() {
		return idclinica;
	}

	public void setIdclinica(Integer idclinica) {
		this.idclinica = idclinica;
	}

	public void setListas(List<Clinica> listas) {
		this.listas = listas;
	}

	public List<Clinica> getclinicas() {
		return clinicas;
	}
	

	public List<Clinica> getListas() {
		return listas;
	}

	public void setclinicas(List<Clinica> clinicas) {
		this.clinicas = clinicas;
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

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ClinicaDaoImpl getDao() {
		return dao;
	}

	public void setDao(ClinicaDaoImpl dao) {
		this.dao = dao;
	}
	public Userauth getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(Userauth userAuth) {
		this.userAuth = userAuth;
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

	public List<Especialidade> getListasEspecialidade() {
		return listasEspecialidade;
	}

	public void setListasEspecialidade(List<Especialidade> listasEspecialidade) {
		this.listasEspecialidade = listasEspecialidade;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public String getNomeEspecialidade() {
		return NomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		NomeEspecialidade = nomeEspecialidade;
	}
	

	
}

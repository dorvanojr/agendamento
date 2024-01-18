package br.com.agendamento.controller.agendamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.controller.util.GenericDto;
import br.com.agendamento.dao.EspecialidadeDao;
import br.com.agendamento.dao.HorarioDiaSemanalDao;
import br.com.agendamento.dao.MedicoDao;
import br.com.agendamento.dao.UserDao;
import br.com.agendamento.dao.impl.ClinicaDaoImpl;
import br.com.agendamento.dao.impl.EspecialidadeDaoImpl;
import br.com.agendamento.dao.impl.HorarioDiaSemanalDaoImpl;
import br.com.agendamento.dao.impl.MedicoDaoImpl;
import br.com.agendamento.dao.impl.UserDaoImpl;
import br.com.agendamento.entity.AgendaConfirm;
import br.com.agendamento.entity.Cliente;
import br.com.agendamento.entity.Clinica;
import br.com.agendamento.entity.Email;
import br.com.agendamento.entity.Endereco;
import br.com.agendamento.entity.Especialidade;
import br.com.agendamento.entity.HorarioDiaSemanal;
import br.com.agendamento.entity.Medico;
import br.com.agendamento.entity.Telefone;
import br.com.agendamento.entity.User;
import br.com.agendamento.entity.Userauth;


@ConversationScoped
@Named
public class AgendamentoBean implements  Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Inject
	private Conversation conversation;
	@Inject
	private Medico medico;
	@Inject
	private AgendaConfirm  agendaConfirm;
	@Inject
    private ClinicaDaoImpl dao;
	@Inject
    private EspecialidadeDaoImpl especialidadeDaoImpl;
	@Inject
    private MedicoDaoImpl medicoDaoImpl;
	@Inject
	private HorarioDiaSemanalDaoImpl daoImpl;
	@Inject
	HorarioDiaSemanal horarioDiaSemanal;
	@Inject
	private UserDaoImpl userDaoImpl;
	@Inject
	private Userauth userAuth;
	@Inject
	private User user; 
	@Inject
	private Clinica clinica; 
	@Inject
	private Cliente cliente; 
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
	private List<Medico> medicos;
	private List<Medico> listarMedicos;
	private List<AgendaConfirm> listarAgendaConfirms;
	private static GenericDto instance;
	private int idClinica;
	private int idMedicos;
	private boolean disableTab = true;
	private boolean disableTab1 = true;
	private boolean disableTab2 = true;
    private String selectedCountry; 
    private List<HorarioDiaSemanal> listaHorarios;
    private int activeTabIndex = 0 ;
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
	
		@Transactional
		public void getListaMedicos() {
			
			      doSomeCounting("1");
			      setDisableTab(false);
				    System.out.println("id Clinica " + clinica.getIdClinica());
				   GenericDto.setMedicos(new ArrayList<Medico>());
				
				   EspecialidadeDao dao = getEspecialidadeDaoImpl();

				  
				    GenericDto.setMedicos(dao.listMedicoPorClinica(clinica.getIdClinica()));
					medicos  =  GenericDto.getMedicos();
					
				   System.out.println("Cadastro com sucesso");
			
		} 
		
		public void doSomeCounting(String tabIndex){
		    // your counting logic
		    try {
		        activeTabIndex = Integer.parseInt(tabIndex);
		        System.out.println("showing tab: "+activeTabIndex);
		    } catch (NumberFormatException e) {}
		}

	
		
		public void getListaMedicosID() {	
			try{
			  beginConversation();
				System.out.println("id Medicos " + medico.getIdMedico());
			   GenericDto.setMedicos(new ArrayList<Medico>());
			
			  MedicoDao dao = getMedicoDaoImpl();

			  
			   setListarMedicos(dao.listAllID(medico.getIdMedico()));
				setDisableTab(false);
				 getListaHorario();
			   System.out.println("Cadastro com sucesso");
			}catch(Exception ex){
				 System.out.println("Erro" + ex.getMessage());
				 ex.fillInStackTrace();
			}finally{
				setIdEspecialidade(0);
			}
		} 
	
		public void save(){
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				
			HorarioDiaSemanalDao dao = getDaoImpl();
			
			for(Medico medico : listarMedicos){
				medico.setHorarioDiaSemanais(new ArrayList<HorarioDiaSemanal>());
				medico.getHorarioDiaSemanais().add(horarioDiaSemanal);
				agendaConfirm.setClinica(medico.getClinica());
				agendaConfirm.setHorarioDiaSemanal(horarioDiaSemanal);
				agendaConfirm.setMedico(medico);
			}
			
			 UserDao dao1 = getUserDaoImpl();
			    
			List<User> users = dao1.list(getInstance().getLogin());
				
			for (User f : users) {
			    
	           agendaConfirm.setCliente(f.getCliente());
	              
	              
			}
			dao.save(agendaConfirm);
				
						
			context.addMessage(null, new FacesMessage("Resultado",
					"Agendamento Realizado com Sucesso!!! "));

		} catch (Exception ex) {

			context.addMessage(null, new FacesMessage("Resultado",
					"Erro ao Cadastrar!!! " + ex.getMessage()));
			ex.fillInStackTrace();
		}finally{
			endConversation();
		}
		}
		
		
		public void listarAgendaConfirmada(){
			  
			HorarioDiaSemanalDao dao = getDaoImpl();
			
			 listarAgendaConfirms = dao.listAgendaConfirmID(getIdMedicos());
			
		}
		
		public void getListaHorario(){
			GenericDto.setHorarioDiaSemanals(new ArrayList<HorarioDiaSemanal>());
			listaHorarios = new ArrayList<HorarioDiaSemanal>();
			HorarioDiaSemanalDao dao = getDaoImpl();
			
			GenericDto.setHorarioDiaSemanals(dao.listAllID(medico.getIdMedico()));
			
			listaHorarios = GenericDto.getHorarioDiaSemanals();
			
		}
		
	
		
		public void localeChanged(ValueChangeEvent e) {
		      //assign new value to country
		      selectedCountry = e.getNewValue().toString(); 
		      System.out.println("id Clinica " +  e.getNewValue().toString());
		   }

	   
		public void habAba1(){
			beginConversation();
			getInstance();
			medicos  =  GenericDto.getMedicos();
			
		}

		public void habAba2(){
			beginConversation();
			for(Medico medico : listarMedicos){
				medico.setHorarioDiaSemanais(new ArrayList<HorarioDiaSemanal>());
				medico.getHorarioDiaSemanais().add(horarioDiaSemanal);
			}
			 doSomeCounting("2");
			setDisableTab1(false);
		}
		public void habAba3(){
			beginConversation();
			 doSomeCounting("3");
			setDisableTab2(false);
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

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public int getIdClinica() {
		return idClinica;
	}

	public void setIdClinica(int idClinica) {
		this.idClinica = idClinica;
	}

	public boolean isDisableTab() {
		return disableTab;
	}

	public void setDisableTab(boolean disableTab) {
		this.disableTab = disableTab;
	}

	public boolean isDisableTab1() {
		return disableTab1;
	}

	public void setDisableTab1(boolean disableTab1) {
		this.disableTab1 = disableTab1;
	}

	public boolean isDisableTab2() {
		return disableTab2;
	}

	public void setDisableTab2(boolean disableTab2) {
		this.disableTab2 = disableTab2;
	}
	

	public String getSelectedCountry() {
	      return selectedCountry;
	   }

	   public void setSelectedCountry(String selectedCountry) {
	      this.selectedCountry = selectedCountry;
	   }

	public MedicoDaoImpl getMedicoDaoImpl() {
		return medicoDaoImpl;
	}

	public void setMedicoDaoImpl(MedicoDaoImpl medicoDaoImpl) {
		this.medicoDaoImpl = medicoDaoImpl;
	}

	public List<Clinica> getClinicas() {
		return clinicas;
	}

	public void setClinicas(List<Clinica> clinicas) {
		this.clinicas = clinicas;
	}

	public List<Medico> getListarMedicos() {
		return listarMedicos;
	}

	public void setListarMedicos(List<Medico> listarMedicos) {
		this.listarMedicos = listarMedicos;
	}

	public int getIdMedicos() {
		return idMedicos;
	}

	public void setIdMedicos(int idMedicos) {
		this.idMedicos = idMedicos;
	}

	public HorarioDiaSemanalDaoImpl getDaoImpl() {
		return daoImpl;
	}

	public void setDaoImpl(HorarioDiaSemanalDaoImpl daoImpl) {
		this.daoImpl = daoImpl;
	}

	public List<HorarioDiaSemanal> getListaHorarios() {
		return listaHorarios;
	}

	public void setListaHorarios(List<HorarioDiaSemanal> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public static void setInstance(GenericDto instance) {
		AgendamentoBean.instance = instance;
	}

	public AgendaConfirm getAgendaConfirm() {
		return agendaConfirm;
	}

	public void setAgendaConfirm(AgendaConfirm agendaConfirm) {
		this.agendaConfirm = agendaConfirm;
	}

	public UserDaoImpl getUserDaoImpl() {
		return userDaoImpl;
	}

	public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}
	   
	public HorarioDiaSemanal getHorarioDiaSemanal() {
		return horarioDiaSemanal;
	}

	public void setHorarioDiaSemanal(HorarioDiaSemanal horarioDiaSemanal) {
		this.horarioDiaSemanal = horarioDiaSemanal;
	}

	public List<AgendaConfirm> getListarAgendaConfirms() {
		return listarAgendaConfirms;
	}

	public void setListarAgendaConfirms(List<AgendaConfirm> listarAgendaConfirms) {
		this.listarAgendaConfirms = listarAgendaConfirms;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getActiveTabIndex() {
		return activeTabIndex;
	}

	public void setActiveTabIndex(int activeTabIndex) {
		this.activeTabIndex = activeTabIndex;
	}
	
	
}

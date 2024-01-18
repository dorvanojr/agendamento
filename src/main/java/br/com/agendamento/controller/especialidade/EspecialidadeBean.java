package br.com.agendamento.controller.especialidade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.controller.util.GenericDto;
import br.com.agendamento.dao.EspecialidadeDao;
import br.com.agendamento.dao.impl.ClinicaDaoImpl;
import br.com.agendamento.dao.impl.EspecialidadeDaoImpl;
import br.com.agendamento.entity.Clinica;
import br.com.agendamento.entity.Especialidade;
import br.com.agendamento.entity.Medico;
import br.com.agendamento.entity.User;


@ConversationScoped
@Named
public class EspecialidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private Conversation conversation;
	@Inject
	private EspecialidadeDaoImpl dao;
	@Inject
    private ClinicaDaoImpl clinicaDaoImpl;
	@Inject
	private Especialidade especialidade;
	private List<Especialidade> listas;
	private Integer idCliente;
	private String NomeImg;
	private int idEspecialidade;
	private int idClinica;
	private List<Clinica> clinicas;
	private List<Medico> medicos;
	private static GenericDto instance;
	private Clinica clinica;


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
	
	 
	  

   

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	

	
	
	public void getListaClinicas() {	
		   clinicas = new ArrayList<Clinica>();
		
		   EspecialidadeDao dao = getDAO();

		   clinicas = dao.listClinicaPorEsp(getIdEspecialidade());
		   System.out.println("Cadastro com sucesso");
	
	} 



	@Transactional	
	public void getListaMedicos(int id) {	
		try{
			System.out.println("erro" + clinicas.get(0).getIdClinica());
		   beginConversation();
		   medicos = new ArrayList<Medico>();
		
		   EspecialidadeDao dao = getDAO();

		   medicos = dao.listMedicoPorClinica(id);
		   System.out.println("Cadastro com sucesso");
		}catch(Exception ex){
			 System.out.println("Erro" + ex.getMessage());
			 ex.fillInStackTrace();
		}finally{
			setIdEspecialidade(0);
			
		}
	} 

   


	public List<Especialidade> listEspecialidadeAll() {
		try {
			
			EspecialidadeDao dao = getDAO();
			
			listas = dao.listAll();

		} catch (Exception ex) {

		}
		return listas;

	}

	public List<Especialidade> listEspecialidadeSelect(User user) {
		List<Especialidade> list = new  ArrayList<Especialidade>();
	
		if(listas == null  ){
 	    	listas = new  ArrayList<Especialidade>();
		}
		
			
			
    			if(listas.size() == 0 ){
			EspecialidadeDao dao1 = getDAO();
			
			listas = dao1.listAll();
			
			for(Especialidade espec : listas){
				if(!espec.getNome().equals(user.getClinica().getEspecialidade().getNome())){
					list.add(espec);	
				}
			}
		 }
		return list;

	}


	public List<SelectItem> getEspecialidadeAllID() {  
	    List<SelectItem> items = new ArrayList<SelectItem>();
	    EspecialidadeDao dao = getDAO();
		
		listas = dao.listAll();
	    
	    for (Especialidade e : this.listas) {  
	        // observem que o value do meu SelectItem é a própria entidade  
	        items.add(new SelectItem(e, e.getNome()));  
	    }  
	    return items;  
	}  
	
	
	public List<SelectItem> getAllAtividades(User user) throws SQLException {
		final List<SelectItem> listaComboBoxAtiv = new ArrayList<SelectItem>(0);
		try{
		
		listas = dao.listAll();
		for (int i = 1; i <= (listas.size()); i++) {
			SelectItem item = new SelectItem();
		  if(!listas.get(i-1).getNome().equals(user.getClinica().getEspecialidade().getNome())){
			item.setLabel(listas.get(i -1).getNome());
			item.setValue(listas.get(i -1).getIdEspecialidade());
			listaComboBoxAtiv.add(item);	
	      }else{
	    	  if(user.getClinica().getEspecialidade().getNome().equals("")  || user.getClinica().getEspecialidade().getNome() != null){
			        item.setLabel(listas.get(i -1).getNome());
			        item.setValue(listas.get(i -1).getIdEspecialidade());
			        listaComboBoxAtiv.add(0,item);
			    }else{
			    	listaComboBoxAtiv.add(new SelectItem(0,"Selecione"));
			    	
			    }
 
	      }
		}
	 }catch(Exception ex){
		 System.out.println("Erro" + ex.getMessage());
	 }
		return listaComboBoxAtiv;
	}
	
	

	
	public EspecialidadeDaoImpl getDAO() {
		return dao;
	}

	
	
	
	public String getNomeImg() {
		return NomeImg;
	}

	public void setNomeImg(String nomeImg) {
		NomeImg = nomeImg;
	}

	public EspecialidadeDaoImpl getDao() {
		return dao;
	}

	public void setDao(EspecialidadeDaoImpl dao) {
		this.dao = dao;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public List<Especialidade> getListas() {
		return listas;
	}

	public void setListas(List<Especialidade> listas) {
		this.listas = listas;
	}

	public int getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(int idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public ClinicaDaoImpl getClinicaDaoImpl() {
		return clinicaDaoImpl;
	}

	public void setClinicaDaoImpl(ClinicaDaoImpl clinicaDaoImpl) {
		this.clinicaDaoImpl = clinicaDaoImpl;
	}

	public List<Clinica> getClinicas() {
		return clinicas;
	}

	public void setClinicas(List<Clinica> clinicas) {
		this.clinicas = clinicas;
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

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}


	
	
	
}

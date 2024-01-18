package br.com.agendamento.controller.horario;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.agendamento.dao.EspecialidadeDao;
import br.com.agendamento.dao.HorarioDiaSemanalDao;
import br.com.agendamento.dao.MedicoDao;
import br.com.agendamento.dao.UserDao;
import br.com.agendamento.dao.impl.HorarioDiaSemanalDaoImpl;
import br.com.agendamento.dao.impl.MedicoDaoImpl;
import br.com.agendamento.entity.Especialidade;
import br.com.agendamento.entity.HorarioDiaSemanal;
import br.com.agendamento.entity.Medico;
import br.com.agendamento.entity.User;

@ConversationScoped
@Named
public class HorarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private Conversation conversation;
	@Inject
	private HorarioDiaSemanal horarioDiaSemanal;
	@Inject
	private HorarioDiaSemanal horarioDiaSemanal1;
	@Inject
	private MedicoDaoImpl medicoDaoImpl;
	@Inject
	private HorarioDiaSemanalDaoImpl daoImpl;
	private List<HorarioDiaSemanal> listaHorarios;
	private List<Medico> listarMedicos;
	private int idMedico;
	private Time horario;
	private Date dtinicial;
	private Date dtfinal;
	private static GenericDto instance;

	public static GenericDto getInstance() {
		if (instance == null) {
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
			System.out.println("Conversação encerrada, id: "
					+ conversation.getId());
			conversation.end();
		}
	}

	public void addListaHorario() throws ParseException {
		if (GenericDto.getHorarioDiaSemanals() == null) {
			GenericDto
			.setHorarioDiaSemanals(new ArrayList<HorarioDiaSemanal>());
		}

		for (Medico medico : getListaMedicosID()) {

			horarioDiaSemanal.setMedico(medico);

		}
		horarioDiaSemanal.setDataDisponivel(dtinicial);
		horarioDiaSemanal.setHorario(dtfinal);

		GenericDto.getHorarioDiaSemanals().add(horarioDiaSemanal);
       
//		 for ( int i = 0; i < GenericDto.getHorarioDiaSemanals().size(); i++ ) {
//	            for ( int j = i + 1; j < GenericDto.getHorarioDiaSemanals().size(); j++ ) {
//	                if ( GenericDto.getHorarioDiaSemanals().get( i ).getMedico().getIdMedico() == GenericDto.getHorarioDiaSemanals().get( j ).getMedico().getIdMedico() &&  GenericDto.getHorarioDiaSemanals().get( i ).getHorario().equals(GenericDto.getHorarioDiaSemanals().get( j ).getHorario())) {
//	                	   GenericDto.getHorarioDiaSemanals().remove((Object) GenericDto.getHorarioDiaSemanals().get( i ));
//	                	   listaHorarios.remove((Object) GenericDto.getHorarioDiaSemanals().get( i ));
//	                    break;
//	                }else{
//	                	
//	                }
//	            }
//	        }
		
		listaHorarios = GenericDto.getHorarioDiaSemanals();
		save();

	}
	
	public void getListaHorario(){
		GenericDto.setHorarioDiaSemanals(new ArrayList<HorarioDiaSemanal>());
		listaHorarios = new ArrayList<HorarioDiaSemanal>();
		HorarioDiaSemanalDao dao = getDaoImpl();
		
		GenericDto.setHorarioDiaSemanals(dao.listAllID(getIdMedico()));
		
		listaHorarios = GenericDto.getHorarioDiaSemanals();
		
	}
	
	public void save(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			    beginConversation();

			    HorarioDiaSemanalDao horaDao = getDaoImpl();
			    
				horaDao.save(horarioDiaSemanal);
						
						
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

	public List<Medico> getListaMedicosID() {
		try {
			beginConversation();
			System.out.println("id Medicos " + idMedico);
			GenericDto.setMedicos(new ArrayList<Medico>());

			MedicoDao dao = getMedicoDaoImpl();

			setListarMedicos(dao.listAllID(idMedico));
			System.out.println("Cadastro com sucesso");
		} catch (Exception ex) {
			System.out.println("Erro" + ex.getMessage());
			ex.fillInStackTrace();
		} finally {

		}
		return getListarMedicos();
	}

	public void excluir() {
		System.out.println("estou aqui o excluir");
		 HorarioDiaSemanalDao horaDao = getDaoImpl();
		 
		List<HorarioDiaSemanal>  list = horaDao.listAllIDHorario(idMedico);
		for(HorarioDiaSemanal semanal: list){
		  horaDao.remove(semanal);
		
		}
          GenericDto.setHorarioDiaSemanals(horaDao.listAllID(getIdMedico()));
		
		listaHorarios = GenericDto.getHorarioDiaSemanals();
	}

	public HorarioDiaSemanal getHorarioDiaSemanal() {
		return horarioDiaSemanal;
	}

	public void setHorarioDiaSemanal(HorarioDiaSemanal horarioDiaSemanal) {
		this.horarioDiaSemanal = horarioDiaSemanal;
	}

	public MedicoDaoImpl getMedicoDaoImpl() {
		return medicoDaoImpl;
	}

	public void setMedicoDaoImpl(MedicoDaoImpl medicoDaoImpl) {
		this.medicoDaoImpl = medicoDaoImpl;
	}

	public List<HorarioDiaSemanal> getListaHorarios() {
		return listaHorarios;
	}

	public void setListaHorarios(List<HorarioDiaSemanal> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}

	public List<Medico> getListarMedicos() {
		return listarMedicos;
	}

	public void setListarMedicos(List<Medico> listarMedicos) {
		this.listarMedicos = listarMedicos;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}

	public Date getDtinicial() {
		return dtinicial;
	}

	public void setDtinicial(Date dtinicial) {
		this.dtinicial = dtinicial;
	}

	public Date getDtfinal() {
		return dtfinal;
	}

	public void setDtfinal(Date dtfinal) {
		this.dtfinal = dtfinal;
	}

	public HorarioDiaSemanalDaoImpl getDaoImpl() {
		return daoImpl;
	}

	public void setDaoImpl(HorarioDiaSemanalDaoImpl daoImpl) {
		this.daoImpl = daoImpl;
	}

	public HorarioDiaSemanal getHorarioDiaSemanal1() {
		return horarioDiaSemanal1;
	}

	public void setHorarioDiaSemanal1(HorarioDiaSemanal horarioDiaSemanal1) {
		this.horarioDiaSemanal1 = horarioDiaSemanal1;
	}
	
	

}

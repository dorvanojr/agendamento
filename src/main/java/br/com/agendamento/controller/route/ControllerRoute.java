package br.com.agendamento.controller.route;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ConversationScoped
@Named
public class ControllerRoute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private Conversation conversation;

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
	
	@Transactional
	public String cunsultarMedico() {
		beginConversation();
		return "br/com/agendamento/controller/medico/consultaMedico.jsf?faces-redirect=true";
	}
	
	@Transactional
	public String cadastroHorario(){ 
		return "br/com/agendamento/controller/horario/cadastroHorario.jsf?faces-redirect=true";
	}

	@Transactional
	public String cadastroAgendamento() {
		beginConversation();
		return "br/com/agendamento/controller/agendamento/cadastroAgendamento.jsf?faces-redirect=true";
	}

	

	@Transactional
	public String voltar() {
        endConversation();
		return "br/com/vidro/controller/login/login";

	}


	
	@Transactional
	public String voltarLogin() {
		beginConversation();
	    return "/login.jsf?faces-redirect=true";
		
	}
	
	@Transactional
	public String listarAgendamento() {
		beginConversation();
	    return "br/com/agendamento/controller/agendamento/listarAgendamento.jsf?faces-redirect=true";
		
	}
	
	
	@Transactional
	public String voltarUsuario() {
	beginConversation();
	    return "../usuario.jsf?faces-redirect=true";
		
	}
	
    @Transactional
	public String cadastroUsuario() {
    	beginConversation();
	    return "br/com/agendamento/controller/usuario/cadastroUsuario.jsf?faces-redirect=true";
		
	}
    
    @Transactional
   	public String home(){
       	endConversation();
   		return "/imagem.jsf?faces-redirect=true";
   	}
 
 
}

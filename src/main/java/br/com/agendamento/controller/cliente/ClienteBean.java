package br.com.agendamento.controller.cliente;

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
import br.com.agendamento.dao.ClienteDao;
import br.com.agendamento.dao.impl.ClienteDaoImpl;
import br.com.agendamento.dao.impl.UsuarioDaoImpl;
import br.com.agendamento.entity.Cliente;
import br.com.agendamento.entity.Email;
import br.com.agendamento.entity.Endereco;
import br.com.agendamento.entity.PlanoSaude;
import br.com.agendamento.entity.Telefone;
import br.com.agendamento.entity.User;


@ConversationScoped
@Named
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private Conversation conversation;
	@Inject
	private ClienteDaoImpl dao;
	@Inject
	private UsuarioDaoImpl usuarioDaoImpl;
	@Inject
	private Cliente cliente;
	@Inject
	private Cliente cliente1;
	@Inject
	private Telefone telefone;
	@Inject
	private Endereco endereco;
	@Inject
	private Email email;
	@Inject
	PlanoSaude planoSaude;
	private List<Cliente> clientes;
	private List<Cliente> listas;
	private Integer idCliente;
	private String parametros;
	private  List<User> users;
	
	public List<User> getUsers() {
		
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

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
	
	 
	  
		
	


	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public void setListas(List<Cliente> listas) {
		this.listas = listas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void save(User user) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			   
			   beginConversation();
			   System.out.println("usuario login" + GenericDto.login + " Singleton " +getInstance().getLogin());
			   ClienteDao dao = getDAO();
			   cliente.setIdCliente(user.getCliente().getIdCliente());
			   cliente.setNome(user.getCliente().getNome());
			   cliente.setCpf(user.getCliente().getCpf());
			   cliente.setDataNasc(user.getCliente().getDataNasc());
			   cliente.setObservacao(user.getCliente().getObservacao());
			   cliente.setStatus(0);
			   if(user.getCliente().getImagem() != null){
				   cliente.setImagem(user.getCliente().getImagem());
			   }
			   if(user.getCliente().getEndereco().size() > 0){
				   if(user.getCliente().getEndereco().get(0).getIdEndereco() != 0){
				   endereco.setCliente(user.getCliente());
				   cliente.addEnderecos(user.getCliente().getEndereco().get(0));
				   }
			   }else if(user.getCliente().getEndereco().size() == 0){
				   endereco.setCliente(cliente);
				   cliente.addEnderecos(endereco);
			   }
			   if(user.getCliente().getTelefone().size() > 0){
			     if(user.getCliente().getTelefone().get(0).getIdTelefone() != 0){
			       telefone.setCliente(user.getCliente());
				   cliente.addTelefones(user.getCliente().getTelefone().get(0));
			     }
			   }else if(user.getCliente().getTelefone().size() == 0){
			      telefone.setCliente(cliente);
				   cliente.addTelefones(telefone);
			   }
			   if(user.getCliente().getPlanoasaude().size() > 0){
			     if(user.getCliente().getPlanoasaude().get(0).getIdPlanoSaude() != 0){
				   planoSaude.setCliente(user.getCliente());
				   cliente.addPlanoSaudes(user.getCliente().getPlanoasaude().get(0));
			     } 
			   }else if(user.getCliente().getPlanoasaude().size() == 0){
				   planoSaude.setCliente(cliente);
				   cliente.addPlanoSaudes(planoSaude);
			   }
			   
			   
			   if(user.getCliente().getEmail().size() > 0){
			     if(user.getCliente().getEmail().get(0).getIdEmail() != 0){
				   email.setCliente(user.getCliente());
				   cliente.addEmails(user.getCliente().getEmail().get(0));
			     } 
			   }else if(user.getCliente().getEmail().size() == 0){
				   email.setCliente(cliente);
				   cliente.addEmails(email);
			   }
			   dao.update(cliente);
			
			context.addMessage(null, new FacesMessage("Resultado",
					"Cadastrado com sucesso!!! " + ""));

		} catch (Exception ex) {

			context.addMessage(null, new FacesMessage("Resultado",
					"Erro ao Cadastrar!!! " + ex.getMessage()));
			ex.getStackTrace();
		}finally{
			endConversation();
			clientes = dao.listId(cliente.getIdCliente());
		}
	}
	

	
	
  

  
	

	




	public List<Cliente> getListas() {
		return listas;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ClienteDaoImpl getDAO() {
		return dao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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


	public Cliente getCliente1() {
		return cliente1;
	}


	public void setCliente1(Cliente cliente1) {
		this.cliente1 = cliente1;
	}



	public UsuarioDaoImpl getUsuarioDaoImpl() {
		return usuarioDaoImpl;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public PlanoSaude getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(PlanoSaude planoSaude) {
		this.planoSaude = planoSaude;
	}
	
	

	
}

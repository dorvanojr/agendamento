package br.com.agendamento.controller.usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.controller.util.GenericDto;
import br.com.agendamento.dao.ClienteDao;
import br.com.agendamento.dao.UserDao;
import br.com.agendamento.dao.UsuarioDao;
import br.com.agendamento.dao.impl.ClienteDaoImpl;
import br.com.agendamento.dao.impl.UserDaoImpl;
import br.com.agendamento.dao.impl.UsuarioDaoImpl;
import br.com.agendamento.entity.Cliente;
import br.com.agendamento.entity.Email;
import br.com.agendamento.entity.User;
import br.com.agendamento.entity.Userauth;

@ConversationScoped
@Named
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private Conversation conversation;
	@Inject
	private UsuarioDaoImpl Usuariodao;
	@Inject
	private ClienteDaoImpl DAO;
	@Inject
	private UserDaoImpl UserDAO;
	@Inject
	private Cliente cliente;
	@Inject
	private User user;
	@Inject
	private Userauth userAuth;
	@Inject
	private Email email;
	private String xres;
	private String ares;
	private String l;
	private String a;
	private List<Cliente> clientes;
	private List<Cliente> listas;
	private Part part;
	private String statusMessage;
	private List<User> users;
	private String NomeImg;
	private DataModel listaClientes;
	private int codigoEmpresa;
	 
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
	

	    public DataModel getListarCliente(Cliente cliente) {
	    	ServletContext sContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
	    	UsuarioDao dao = getUsuariodao();
			clientes = dao.list(cliente.getNome());
			File folder = new File(sContext.getRealPath("/temp"));
			if (!folder.exists())
				folder.mkdirs();

			for (Cliente f : clientes) {
				setNomeImg(String.valueOf(f.getIdCliente()));
				String nomeArquivo = f.getIdCliente() + ".jpg";
				String arquivo = sContext.getRealPath("/temp") + File.separator
						+ nomeArquivo;

				criaArquivo(f.getImagem(), arquivo);
			}

	        listaClientes = new ListDataModel(clientes);
	        return listaClientes;
	    }
	
	    public String prepararAlterarCliente(){
	        cliente = (Cliente)(listaClientes.getRowData());
	        return "/br/com/vidro/controller/usuario/updateUsuario.jsf?faces-redirect=true";
	    }
	
	
	public List<Cliente> getClientes() {

		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@Transactional
	public void update() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			  beginConversation();
			  ClienteDao dao = getDAO();  
			  dao.update(cliente);	

			context.addMessage(null, new FacesMessage("Resultado",
					"update com sucesso!!! " + ""));

		} catch (Exception ex) {

			context.addMessage(null, new FacesMessage("Resultado",
					"Erro ao Cadastrar!!! " + ex.getMessage()));
			ex.fillInStackTrace();
		
		}finally{
			endConversation();
			listUsuario();
			getListarCliente(cliente);
		}
	}

	public void save() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
	
			beginConversation();
			ClienteDao dao = getDAO();
			
//			Empresa empresa = getUsuariodao().retornaEmpresaId(getCodigoEmpresa());
//			System.out.println("empresa usuario" + getCodigoEmpresa());
//			telefone.setCliente(cliente);
//			telefone.setEmpresa(empresa);
//		
//			email.setEmpresa(empresa);
//			endereco.setCliente(cliente);
//			endereco.setEmpresa(empresa);
//			documentos.setCliente(cliente);
//			documentos.setEmpresa(empresa);
			userAuth.setNameAuthority("ROLE_USER");
			userAuth.setUser(user);
			//user.setEmpresa(empresa);
			user.setCliente(cliente);
			user.setEnable(1);
			user.addUserAlth(userAuth);
			email.setCliente(cliente);
			cliente.addEmails(email);
//			cliente.addEnderecos(endereco);
//			cliente.addTelefones(telefone);
//			cliente.addDocumentos(documentos);
			cliente.addUser(user);
			dao.save(cliente);
			
			context.addMessage(null, new FacesMessage("Resultado",
					"Cadastrado com sucesso!!! " + ""));

		} catch (Exception ex) {

			context.addMessage(null, new FacesMessage("Resultado",
					"Erro ao Cadastrar!!! " + ex.getMessage()));
			ex.fillInStackTrace();
		} finally {
			endConversation();
			listUsuario();
		}

	}

	@Transactional
	public List<Cliente> listUsuario() {
		try {
			ServletContext sContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			System.out.println(cliente.getNome());
			UsuarioDao dao = getUsuariodao();
			clientes = dao.list(cliente.getNome());
			getListarCliente(cliente);
			File folder = new File(sContext.getRealPath("/temp"));
			if (!folder.exists())
				folder.mkdirs();

			for (Cliente f : clientes) {
				String nomeArquivo = f.getIdCliente() + ".jpg";
				String arquivo = sContext.getRealPath("/temp") + File.separator
						+ nomeArquivo;

				criaArquivo(f.getImagem(), arquivo);
			}

		} catch (Exception ex) {

		}

		return clientes;

	}
	
	public String listarNomeFantasia(List<Cliente> clientes){
		String resultado = null;
		for(Cliente cliente : clientes){
			if(cliente.getNomeFantasia() != null){
			    resultado = cliente.getNomeFantasia();
			}
		}
		return resultado;
	}
	
	@Transactional
	public void excluir() {

		ClienteDao dao = getDAO();

		for (Cliente cliente : clientes) {
			dao.remove(cliente);
		}
		clientes = dao.list(cliente.getNome());

	}

	public List<User> listUsuariologado() {

		try {

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext external = context.getExternalContext();
			String login = external.getRemoteUser();
            GenericDto.login = login;
			System.out.println("usuario 123456" + GenericDto.login);

			ServletContext sContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			UserDao dao = getUserDAO();
			users = dao.list(login);
			File folder = new File(sContext.getRealPath("/temp"));
			if (!folder.exists())
				folder.mkdirs();

			for (User f : users) {
				String nomeArquivo = f.getCliente().getIdCliente() + ".jpg";
				String arquivo = sContext.getRealPath("/temp") + File.separator
						+ nomeArquivo;

				criaArquivo(f.getCliente().getImagem(), arquivo);
			}

		} catch (Exception ex) {

		}

		return users;

	}

	// public String LoginTela() {
	//
	//
	// setL(login.getLogin());
	// setA(login.getSenha());
	//
	//
	// UsuarioDao dao = getDAO();
	// List<Login> Listar;
	// try {
	// Listar = dao.consultaLogin(getL(), getA());
	//
	//
	// setXres("");
	// setAres("");
	// for (Login u : Listar) {
	// setXres(u.getLogin());
	// setAres(u.getSenha());
	// }
	//
	// if (getXres() != "" && getAres() != "") {
	// Login logUsu = new Login();
	// logUsu.setLogin(getXres());
	// logUsu.setSenha(getAres());
	//
	//
	// return "imagem";
	//
	// }
	// if ("".equals(getXres()) && "".equals(getAres())) {
	//
	// return "invalid";
	// }
	// } catch (Exception ex) {
	// ex.getStackTrace();
	//
	// }
	// return "";
	// }

	private void criaArquivo(byte[] bytes, String arquivo) {
		FileOutputStream fos;

		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);

			fos.flush();
			fos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

//	public String uploadFile() throws IOException {
//		ServletContext sContext = (ServletContext) FacesContext
//				.getCurrentInstance().getExternalContext().getContext();
//		// Extract file name from content-disposition header of file part
//		String fileName = getFileName(part);
//		System.out.println("***** fileName: " + fileName);
//
//		String basePath = "C:" + File.separator + "anexos" + File.separator;
//		String arquivo = sContext.getRealPath("/temp") + File.separator
//				+ fileName;
//		File outputFilePath = new File(arquivo);
//
//		// Copy uploaded file to destination path
//		InputStream inputStream = null;
//		OutputStream outputStream = null;
//		try {
//			inputStream = part.getInputStream();
//			outputStream = new FileOutputStream(outputFilePath);
//
//			int read = 0;
//			final byte[] bytes = new byte[1024];
//			while ((read = inputStream.read(bytes)) != -1) {
//				outputStream.write(bytes, 0, read);
//			}
//
//			File fileQuadrado = new File(arquivo);
//			System.out.println("***** fileName: " + arquivo);
//			byte[] bFileQuadrado = new byte[(int) fileQuadrado.length()];
//			setNomeImg(fileName);
//			System.out.println("***** fileName: " + fileName);
//			try {
//				// Quadrado
//				FileInputStream fileInputStreamQuadrado = new FileInputStream(
//						fileQuadrado);
//				fileInputStreamQuadrado.read(bFileQuadrado);
//				fileInputStreamQuadrado.close();
//
//				// salvando na banco
//				UsuarioDao dao = getDAO();
//				cliente.setImagem(bFileQuadrado);
//				telefone.setCliente(cliente);
//				email.setCliente(cliente);
//				endereco.setCliente(cliente);
//				documentos.setCliente(cliente);
//				cliente.addEmails(email);
//				cliente.addEnderecos(endereco);
//				cliente.addTelefones(telefone);
//				cliente.addDocumentos(documentos);
//				user.setCliente(cliente);
//				dao.saveLogin(user);
//
//			} catch (Exception ex) {
//
//				ex.getStackTrace();
//			}
//			statusMessage = "File upload successfull !!";
//		} catch (IOException e) {
//			e.printStackTrace();
//			statusMessage = "File upload failed !!";
//		} finally {
//			if (outputStream != null) {
//				outputStream.close();
//			}
//			if (inputStream != null) {
//				inputStream.close();
//			}
//		}
//		return null; // return to same page
//	}
	
	public String uploadFile() throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
		
		beginConversation();
		ServletContext sContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		// Extract file name from content-disposition header of file part
		String fileName = getFileName(part);
		System.out.println("***** fileName: " + fileName);
        
		String basePath = "C:" + File.separator + "anexos" + File.separator;
		String arquivo = sContext.getRealPath("/temp") + File.separator
				+ fileName;
		String arquivo1 = sc.getRealPath("/temp") + File.separator+ fileName;
		File outputFilePath = new File(arquivo);

		// Copy uploaded file to destination path
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = part.getInputStream();
			outputStream = new FileOutputStream(outputFilePath);

			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			File fileQuadrado = new File(arquivo);
			   System.out.println("***** fileName: " + arquivo);
			byte[] bFileQuadrado = new byte[(int) fileQuadrado.length()];
			
            setNomeImg(fileName);
            System.out.println("***** fileName: " + fileName);
            try {
				// Quadrado
				FileInputStream fileInputStreamQuadrado = new FileInputStream(
						fileQuadrado);
				fileInputStreamQuadrado.read(bFileQuadrado);
				fileInputStreamQuadrado.close();
				
				cliente.setImagem(bFileQuadrado);
			} catch (Exception ex) {

				ex.getStackTrace();
			}
			statusMessage = "File upload successfull !!";
		} catch (IOException e) {
			e.printStackTrace();
			statusMessage = "File upload failed !!";
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return null; // return to same page
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	// Extract file name from content-disposition header of file part
	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("***** partHeader: " + partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

	public List<Cliente> getListas() {
		return listas;
	}

	public ClienteDaoImpl getDAO() {
		return DAO;
	}

	public void setDao(ClienteDaoImpl DAO) {
		this.DAO = DAO;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setLogin(User user) {
		this.user = user;
	}

	

	public String getXres() {
		return xres;
	}

	public void setXres(String xres) {
		this.xres = xres;
	}

	public String getAres() {
		return ares;
	}

	public void setAres(String ares) {
		this.ares = ares;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public UserDaoImpl getUserDAO() {
		return UserDAO;
	}

	public void setUserDAO(UserDaoImpl userDAO) {
		UserDAO = userDAO;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getNomeImg() {
		return NomeImg;
	}

	public void setNomeImg(String nomeImg) {
		NomeImg = nomeImg;
	}
	
	public Userauth getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(Userauth userAuth) {
		this.userAuth = userAuth;
	}

	public UsuarioDaoImpl getUsuariodao() {
		return Usuariodao;
	}

	public void setUsuariodao(UsuarioDaoImpl usuariodao) {
		Usuariodao = usuariodao;
	}

	public int getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(int codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	
	

}

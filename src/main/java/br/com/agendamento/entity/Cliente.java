package br.com.agendamento.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.*;



/**
 *
 */
@Entity
@Table(name="cliente")
@NamedQueries({  
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c WHERE c.nome = :nome and c.status = :status"),
    @NamedQuery(name = "Cliente.findList", query = "SELECT c FROM Cliente c WHERE c.status = :status"),
    @NamedQuery(name="Cliente.findListUser", query="SELECT u.username, c.nome, c.imagem FROM User u , Cliente c  where   u.username = :login"),
    @NamedQuery(name = "Cliente.findId", query = "SELECT c FROM Cliente c WHERE c.idCliente = :id and c.status = :status")})  
public class Cliente implements Serializable {

  
	private static final long serialVersionUID = 1L;
	
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
	private int idCliente;
	@Column(name = "nome")
	private String nome;
	@Column(name = "imagem")
    private byte[] imagem;
	@Column(name = "imagem2")
    private byte[] imagem2;
	@Column(name = "status")
    private int status;
	@Column(name = "nomeFantasia")
    private String nomeFantasia;
	@Column(name = "observacao")
    private String observacao;
	@Column(name = "dataNasc")
    private Date dataNasc;
	@Column(name = "estadoCivil")
    private String estadoCivil;
	@Column(name = "cpf")
	private String cpf;
	
	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
    private List<Telefone> telefone;
	
	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
    private List<Endereco> endereco;

	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
    private List<Email> email;
	
    @OneToMany(mappedBy = "cliente",  cascade = CascadeType.ALL)
    private List<User> user;
    
    @OneToMany(mappedBy = "cliente",  cascade = CascadeType.ALL)
    private List<PlanoSaude> planoasaude;
    
    @OneToMany(mappedBy = "cliente",  cascade = CascadeType.ALL)
    private List<AgendaConfirm> agendaConfirms;
    
	public void setUser(List<User> user) {
		this.user = user;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public List<User> getUser() {
		return user;
	}
	
	public List<Email> getEmail() {
		return email;
	}
	public void setEmail(List<Email> email) {
		this.email = email;
	}
	
	
	
	public List<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	
	
	
	public List<PlanoSaude> getPlanoasaude() {
		return planoasaude;
	}
	public void setPlanoasaude(List<PlanoSaude> planoasaude) {
		this.planoasaude = planoasaude;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	public byte[] getImagem2() {
		return imagem2;
	}
	public void setImagem2(byte[] imagem2) {
		this.imagem2 = imagem2;
	}
	
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public List<AgendaConfirm> getAgendaConfirms() {
		return agendaConfirms;
	}
	public void setAgendaConfirms(List<AgendaConfirm> agendaConfirms) {
		this.agendaConfirms = agendaConfirms;
	}
	public List<Telefone> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}
	public void addUser(User a){
		if(this.user==null){
		     this.user = new ArrayList<User>();
		}
		this.user.add(a);
	}
	
	public void addEmails(Email a){
		if(this.email==null){
		     this.email = new ArrayList<Email>();
		}
		this.email.add(a);
	}
	
	public void addEnderecos(Endereco a){
		if(this.endereco==null){
		     this.endereco = new ArrayList<Endereco>();
		}
		this.endereco.add(a);
	}

	
	public void addTelefones(Telefone a){
		if(this.telefone==null){
		     this.telefone = new ArrayList<Telefone>();
		}
		this.telefone.add(a);
	}
	
	public void addPlanoSaudes(PlanoSaude a){
		if(this.planoasaude==null){
		     this.planoasaude = new ArrayList<PlanoSaude>();
		}
		this.planoasaude.add(a);
	}
	
	public void addAgendaConfirms(AgendaConfirm a){
		if(this.agendaConfirms==null){
		     this.agendaConfirms = new ArrayList<AgendaConfirm>();
		}
		this.agendaConfirms.add(a);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agendaConfirms == null) ? 0 : agendaConfirms.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataNasc == null) ? 0 : dataNasc.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((estadoCivil == null) ? 0 : estadoCivil.hashCode());
		result = prime * result + idCliente;
		result = prime * result + Arrays.hashCode(imagem);
		result = prime * result + Arrays.hashCode(imagem2);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((planoasaude == null) ? 0 : planoasaude.hashCode());
		result = prime * result + status;
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (agendaConfirms == null) {
			if (other.agendaConfirms != null)
				return false;
		} else if (!agendaConfirms.equals(other.agendaConfirms))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNasc == null) {
			if (other.dataNasc != null)
				return false;
		} else if (!dataNasc.equals(other.dataNasc))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (estadoCivil == null) {
			if (other.estadoCivil != null)
				return false;
		} else if (!estadoCivil.equals(other.estadoCivil))
			return false;
		if (idCliente != other.idCliente)
			return false;
		if (!Arrays.equals(imagem, other.imagem))
			return false;
		if (!Arrays.equals(imagem2, other.imagem2))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (planoasaude == null) {
			if (other.planoasaude != null)
				return false;
		} else if (!planoasaude.equals(other.planoasaude))
			return false;
		if (status != other.status)
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}

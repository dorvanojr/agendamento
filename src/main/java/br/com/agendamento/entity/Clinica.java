package br.com.agendamento.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


import java.util.List;

import javax.persistence.*;

import br.com.agendamento.controller.util.EntidadeImutavel;

/**
 *
 */
@Entity
@Table(name="clinica")
@NamedQueries({  
    @NamedQuery(name = "Clinica.findList", query = "SELECT e FROM Clinica e"),
    @NamedQuery(name = "Clinica.findAll", query = "SELECT e FROM Clinica e "),
    @NamedQuery(name = "Clinica.findId", query = "SELECT e FROM Clinica e WHERE e.idClinica = :id")})  
public class Clinica  implements EntidadeImutavel, Serializable {

  
	private static final long serialVersionUID = 1L;
	
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClinica")
	private int idClinica;
	@Column(name = "nomeFantasia")
	private String nomeFantasia;
	@Column(name = "razaoSocial")
	private String razaoSocial;
	private String cnpj;
   @OneToMany(mappedBy = "clinica",  cascade = CascadeType.ALL)
    private List<User> user;

	@OneToMany(mappedBy="clinica", cascade = CascadeType.ALL)
    private List<Email> email;
    
	@OneToMany(mappedBy="clinica", cascade = CascadeType.ALL)
    private List<Endereco> endereco;
   
	@OneToMany(mappedBy="clinica", cascade = CascadeType.ALL)
    private List<Telefone> telefone;
    
	@OneToMany(mappedBy="clinica", cascade = CascadeType.ALL)
    private List<Medico> medico;
	
    @OneToMany(mappedBy = "clinica",  cascade = CascadeType.ALL)
	private List<AgendaConfirm> agendaConfirms;
	  
	
	@JoinColumn(name = "idEspecialidade", referencedColumnName = "idEspecialidade", insertable = true, updatable = true, nullable =  true)   
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Especialidade especialidade;

	
	public int getIdClinica() {
		return idClinica;
	}
	public void setIdClinica(int idClinica) {
		this.idClinica = idClinica;
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
	public List<Telefone> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	
	
	public List<AgendaConfirm> getAgendaConfirms() {
		return agendaConfirms;
	}
	public void setAgendaConfirms(List<AgendaConfirm> agendaConfirms) {
		this.agendaConfirms = agendaConfirms;
	}
	public List<Medico> getMedico() {
		return medico;
	}
	public void setMedico(List<Medico> medico) {
		this.medico = medico;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
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
	

	
	public void addUser(User a){
		if(this.user==null){
		     this.user = new ArrayList<User>();
		}
		this.user.add(a);
	}
	
	public void addMedicos(Medico a){
		if(this.medico==null){
		     this.medico = new ArrayList<Medico>();
		}
		this.medico.add(a);
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
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((especialidade == null) ? 0 : especialidade.hashCode());
		result = prime * result + idClinica;
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
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
		Clinica other = (Clinica) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
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
		if (especialidade == null) {
			if (other.especialidade != null)
				return false;
		} else if (!especialidade.equals(other.especialidade))
			return false;
		if (idClinica != other.idClinica)
			return false;
		if (medico == null) {
			if (other.medico != null)
				return false;
		} else if (!medico.equals(other.medico))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
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

	@Override
	public Boolean getExcluido() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setExcluido(Boolean attr) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return getIdClinica();
	}

	
	
	
}

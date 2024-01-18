package br.com.agendamento.entity;


import java.io.Serializable;
import java.util.ArrayList;



import java.util.List;

import javax.persistence.*;

import br.com.agendamento.controller.util.EntidadeImutavel;

/**
 *
 */
@Entity
@Table(name="medico")
@NamedQueries({  
    @NamedQuery(name = "Medico.findList", query = "SELECT e FROM Medico e"),
    @NamedQuery(name = "Medico.findAll", query = "SELECT e FROM Medico e "),
    @NamedQuery(name = "Medico.findId", query = "SELECT e FROM Medico e WHERE e.idMedico = :id")})  
public class Medico implements EntidadeImutavel, Serializable {

  
	private static final long serialVersionUID = 1L;
	
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMedico")
	private int idMedico;
	@Column(name = "nmMedico")
	private String nmMedico;
	@Column(name = "horarioAtendimento")
	private String horarioAtendimento;
	@Column(name = "diasAtendimento")
	private String diasAtendimento;
	@Column(name = "cpfCnpj")
	private String cpfCnpj;
	@Column(name = "observacaoMedicinal")
	private String observacaoMedicinal;
	
    @JoinColumn(name = "idClinica", referencedColumnName = "idClinica", insertable = true, updatable = true, nullable =  true)   
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Clinica clinica;
    
    @JoinColumn(name = "idEspecialidade", referencedColumnName = "idEspecialidade", insertable = true, updatable = true, nullable =  true)   
   	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
   	private Especialidade especialidade;
	
	@OneToMany(mappedBy="Medico", cascade = CascadeType.ALL)
    private List<Email> email;
    
	@OneToMany(mappedBy="Medico", cascade = CascadeType.ALL)
    private List<Endereco> endereco;
   
	@OneToMany(mappedBy="Medico", cascade = CascadeType.ALL)
    private List<Telefone> telefone;
	
	@OneToMany(mappedBy="Medico", cascade = CascadeType.ALL)
    private List<HorarioDiaSemanal> horarioDiaSemanais;
    
	
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

	
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public String getNmMedico() {
		return nmMedico;
	}
	public void setNmMedico(String nmMedico) {
		this.nmMedico = nmMedico;
	}
	public String getHorarioAtendimento() {
		return horarioAtendimento;
	}
	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}
	public String getDiasAtendimento() {
		return diasAtendimento;
	}
	public void setDiasAtendimento(String diasAtendimento) {
		this.diasAtendimento = diasAtendimento;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public Especialidade getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	public String getObservacaoMedicinal() {
		return observacaoMedicinal;
	}
	public void setObservacaoMedicinal(String observacaoMedicinal) {
		this.observacaoMedicinal = observacaoMedicinal;
	}
	
	
	public List<HorarioDiaSemanal> getHorarioDiaSemanais() {
		return horarioDiaSemanais;
	}
	public void setHorarioDiaSemanais(List<HorarioDiaSemanal> horarioDiaSemanais) {
		this.horarioDiaSemanais = horarioDiaSemanais;
	}
	
	
	public Clinica getClinica() {
		return clinica;
	}
	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
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
	
	public void addHorarioDiaSemanal(HorarioDiaSemanal a){
		if(this.horarioDiaSemanais==null){
		     this.horarioDiaSemanais = new ArrayList<HorarioDiaSemanal>();
		}
		this.horarioDiaSemanais.add(a);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clinica == null) ? 0 : clinica.hashCode());
		result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = prime * result
				+ ((diasAtendimento == null) ? 0 : diasAtendimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((especialidade == null) ? 0 : especialidade.hashCode());
		result = prime
				* result
				+ ((horarioAtendimento == null) ? 0 : horarioAtendimento
						.hashCode());
		result = prime * result + idMedico;
		result = prime * result
				+ ((nmMedico == null) ? 0 : nmMedico.hashCode());
		result = prime
				* result
				+ ((observacaoMedicinal == null) ? 0 : observacaoMedicinal
						.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		Medico other = (Medico) obj;
		if (clinica == null) {
			if (other.clinica != null)
				return false;
		} else if (!clinica.equals(other.clinica))
			return false;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null)
				return false;
		} else if (!cpfCnpj.equals(other.cpfCnpj))
			return false;
		if (diasAtendimento == null) {
			if (other.diasAtendimento != null)
				return false;
		} else if (!diasAtendimento.equals(other.diasAtendimento))
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
		if (horarioAtendimento == null) {
			if (other.horarioAtendimento != null)
				return false;
		} else if (!horarioAtendimento.equals(other.horarioAtendimento))
			return false;
		if (idMedico != other.idMedico)
			return false;
		if (nmMedico == null) {
			if (other.nmMedico != null)
				return false;
		} else if (!nmMedico.equals(other.nmMedico))
			return false;
		if (observacaoMedicinal == null) {
			if (other.observacaoMedicinal != null)
				return false;
		} else if (!observacaoMedicinal.equals(other.observacaoMedicinal))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return getIdMedico();
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
	

	
	
}

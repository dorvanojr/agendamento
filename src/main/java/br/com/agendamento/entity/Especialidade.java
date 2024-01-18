package br.com.agendamento.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.agendamento.controller.util.EntidadeImutavel;

@Entity
@Table(name="especialidade")
@NamedQueries({  
    @NamedQuery(name = "Especialidade.findList", query = "SELECT e FROM Especialidade e"),
    @NamedQuery(name = "Especialidade.findAll", query = "SELECT e FROM Especialidade e "),
    @NamedQuery(name = "Especialidade.findNome", query = "SELECT e FROM Especialidade e WHERE e.idEspecialidade = :id"),
    @NamedQuery(name = "Especialidade.findId", query = "SELECT e FROM Especialidade e WHERE e.nome = :nome")})  
public class Especialidade implements EntidadeImutavel, Serializable {

  
	private static final long serialVersionUID = 1L;
	
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEspecialidade")
	private int idEspecialidade;
	@Column(name = "nome")
	private String nome;
	@OneToMany(mappedBy="especialidade", cascade = CascadeType.ALL)
    private List<Medico> medicos;
    
	@OneToMany(mappedBy="especialidade", cascade = CascadeType.ALL)
    private List<Clinica> clinicas;

	
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
	
	
	

	public int getIdEspecialidade() {
		return idEspecialidade;
	}
	public void setIdEspecialidade(int idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void addMedicos(Medico a){
		if(this.medicos==null){
		     this.medicos = new ArrayList<Medico>();
		}
		this.medicos.add(a);
	}
	
	public void addClinicas(Clinica a){
		if(this.clinicas==null){
		     this.clinicas = new ArrayList<Clinica>();
		}
		this.clinicas.add(a);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clinicas == null) ? 0 : clinicas.hashCode());
		result = prime * result + idEspecialidade;
		result = prime * result + ((medicos == null) ? 0 : medicos.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Especialidade other = (Especialidade) obj;
		if (clinicas == null) {
			if (other.clinicas != null)
				return false;
		} else if (!clinicas.equals(other.clinicas))
			return false;
		if (idEspecialidade != other.idEspecialidade)
			return false;
		if (medicos == null) {
			if (other.medicos != null)
				return false;
		} else if (!medicos.equals(other.medicos))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return getIdEspecialidade();
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


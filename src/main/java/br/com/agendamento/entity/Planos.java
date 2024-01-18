package br.com.agendamento.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.agendamento.controller.util.EntidadeImutavel;

@Entity
@Table(name="planos")
@NamedQueries({  
    @NamedQuery(name = "Planos.findAll", query = "SELECT e FROM Planos e "),
    @NamedQuery(name = "Planos.findId", query = "SELECT e FROM Planos e WHERE e.idPlanos = :id")})  
public class Planos  implements EntidadeImutavel, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlanos")
    private int idPlanos;
	
	@Column(name = "nomePlanos")
    private String nomePlanos;

	public int getIdPlanos() {
		return idPlanos;
	}

	public void setIdPlanos(int idPlanos) {
		this.idPlanos = idPlanos;
	}

	public String getNomePlanos() {
		return nomePlanos;
	}

	public void setNomePlanos(String nomePlanos) {
		this.nomePlanos = nomePlanos;
	}

	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return getIdPlanos();
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

package br.com.agendamento.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="email")
public class Email implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmail")
	private int idEmail;
	
	@Column(name = "gtalk")
	private String gtalk;
	
	@Column(name = "homepage")
	private String homepage;
	
	@Column(name = "email")
	private String email;
	@JoinColumn(name = "idCliente", nullable = true)
    @ManyToOne
	private Cliente cliente;
	
	@JoinColumn(name = "idClinica", nullable = true)
    @ManyToOne
	private Clinica clinica;
	
	@JoinColumn(name = "idMedico", nullable = true)
    @ManyToOne
	private Medico Medico;


	public int getIdEmail() {
		return idEmail;
	}
	public void setIdEmail(int idEmail) {
		this.idEmail = idEmail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getGtalk() {
		return gtalk;
	}
	public void setGtalk(String gtalk) {
		this.gtalk = gtalk;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public Clinica getClinica() {
		return clinica;
	}
	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	public Medico getMedico() {
		return Medico;
	}
	public void setMedico(Medico medico) {
		Medico = medico;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Medico == null) ? 0 : Medico.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((clinica == null) ? 0 : clinica.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gtalk == null) ? 0 : gtalk.hashCode());
		result = prime * result
				+ ((homepage == null) ? 0 : homepage.hashCode());
		result = prime * result + idEmail;
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
		Email other = (Email) obj;
		if (Medico == null) {
			if (other.Medico != null)
				return false;
		} else if (!Medico.equals(other.Medico))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (clinica == null) {
			if (other.clinica != null)
				return false;
		} else if (!clinica.equals(other.clinica))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gtalk == null) {
			if (other.gtalk != null)
				return false;
		} else if (!gtalk.equals(other.gtalk))
			return false;
		if (homepage == null) {
			if (other.homepage != null)
				return false;
		} else if (!homepage.equals(other.homepage))
			return false;
		if (idEmail != other.idEmail)
			return false;
		return true;
	}


}

package br.com.agendamento.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * 
 */
@Entity
@Table(name = "telefone")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTelefone")
	private int idTelefone;
	@Column(name = "residencial")
	private String residencial;
	@Column(name = "whatapps")
	private String whatapps;
	@Column(name = "celular")
	private String celular;
	@Column(name = "fax")
	private String fax;
	@Column(name = "telefone")
	private String telefone;
	@JoinColumn(name = "idCliente", nullable = true)
    @ManyToOne
	private Cliente cliente;
	
	@JoinColumn(name = "idClinica", nullable = true)
    @ManyToOne
	private Clinica clinica;

	@JoinColumn(name = "idMedico", nullable = true)
    @ManyToOne
    private Medico Medico;
	
	public Medico getMedico() {
		return Medico;
	}
	public void setMedico(Medico medico) {
		Medico = medico;
	}
	
	public int getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(int idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getResidencial() {
		return residencial;
	}

	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}

	public String getWhatapps() {
		return whatapps;
	}

	public void setWhatapps(String whatapps) {
		this.whatapps = whatapps;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Medico == null) ? 0 : Medico.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((clinica == null) ? 0 : clinica.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + idTelefone;
		result = prime * result
				+ ((residencial == null) ? 0 : residencial.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result
				+ ((whatapps == null) ? 0 : whatapps.hashCode());
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
		Telefone other = (Telefone) obj;
		if (Medico == null) {
			if (other.Medico != null)
				return false;
		} else if (!Medico.equals(other.Medico))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
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
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (idTelefone != other.idTelefone)
			return false;
		if (residencial == null) {
			if (other.residencial != null)
				return false;
		} else if (!residencial.equals(other.residencial))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (whatapps == null) {
			if (other.whatapps != null)
				return false;
		} else if (!whatapps.equals(other.whatapps))
			return false;
		return true;
	}

	
}

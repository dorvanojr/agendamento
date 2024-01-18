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
@Table(name="clinicaPlanosSaude")
public class ClinicaPlanosSaude implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClinicaPlanosSaude")
	private int idClinicaPlanosSaude;
	
	@JoinColumn(name = "idPlanos", nullable = true)
    @ManyToOne
	private Planos planos;
	
	@JoinColumn(name = "idClinica", nullable = true)
    @ManyToOne
	private Clinica clinica;

	public int getIdClinicaPlanosSaude() {
		return idClinicaPlanosSaude;
	}

	public void setIdClinicaPlanosSaude(int idClinicaPlanosSaude) {
		this.idClinicaPlanosSaude = idClinicaPlanosSaude;
	}

	public Planos getPlanos() {
		return planos;
	}

	public void setPlanos(Planos planos) {
		this.planos = planos;
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
		result = prime * result + ((clinica == null) ? 0 : clinica.hashCode());
		result = prime * result + idClinicaPlanosSaude;
		result = prime * result + ((planos == null) ? 0 : planos.hashCode());
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
		ClinicaPlanosSaude other = (ClinicaPlanosSaude) obj;
		if (clinica == null) {
			if (other.clinica != null)
				return false;
		} else if (!clinica.equals(other.clinica))
			return false;
		if (idClinicaPlanosSaude != other.idClinicaPlanosSaude)
			return false;
		if (planos == null) {
			if (other.planos != null)
				return false;
		} else if (!planos.equals(other.planos))
			return false;
		return true;
	}
	
	
	
}

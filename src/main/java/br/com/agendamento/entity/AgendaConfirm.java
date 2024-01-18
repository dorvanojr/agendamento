package br.com.agendamento.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="agendaconfirm")
public class AgendaConfirm implements Serializable {

	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAgendaconfirm")
	private int idAgendaconfirm;
	
	@JoinColumn(name = "idCliente",  referencedColumnName = "idCliente", insertable = true, updatable = true, nullable =  true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Cliente cliente;
	
	@JoinColumn(name = "idClinica",  referencedColumnName = "idClinica", insertable = true, updatable = true, nullable =  true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Clinica clinica;
	
	@JoinColumn(name = "idHorariodiasemanal", referencedColumnName = "idHorariodiasemanal", insertable = true, updatable = true, nullable =  true)   
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private HorarioDiaSemanal horarioDiaSemanal;
	
	@JoinColumn(name = "idMedico", nullable = true)
    @ManyToOne
    private Medico Medico;

	public int getIdAgendaconfirm() {
		return idAgendaconfirm;
	}

	public void setIdAgendaconfirm(int idAgendaconfirm) {
		this.idAgendaconfirm = idAgendaconfirm;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public HorarioDiaSemanal getHorarioDiaSemanal() {
		return horarioDiaSemanal;
	}

	public void setHorarioDiaSemanal(HorarioDiaSemanal horarioDiaSemanal) {
		this.horarioDiaSemanal = horarioDiaSemanal;
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
		result = prime
				* result
				+ ((horarioDiaSemanal == null) ? 0 : horarioDiaSemanal
						.hashCode());
		result = prime * result + idAgendaconfirm;
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
		AgendaConfirm other = (AgendaConfirm) obj;
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
		if (horarioDiaSemanal == null) {
			if (other.horarioDiaSemanal != null)
				return false;
		} else if (!horarioDiaSemanal.equals(other.horarioDiaSemanal))
			return false;
		if (idAgendaconfirm != other.idAgendaconfirm)
			return false;
		return true;
	}
	
	
	
}

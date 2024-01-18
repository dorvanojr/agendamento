package br.com.agendamento.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="horariodiasemanal")
public class HorarioDiaSemanal implements Serializable {

  
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHorariodiasemanal")
	private int idHorariodiasemanal;
	@Column(name = "dataDisponivel")
	private Date dataDisponivel;
	@Column(name = "horario")
	private Date horario;
	
    @JoinColumn(name = "idMedico", referencedColumnName = "idMedico", insertable = true, updatable = true, nullable =  true)   
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Medico Medico;
    
    @OneToMany(mappedBy = "horarioDiaSemanal",  cascade = CascadeType.ALL)
	private List<AgendaConfirm> agendaConfirms;
	
	public int getIdHorariodiasemanal() {
		return idHorariodiasemanal;
	}
	public void setIdHorariodiasemanal(int idHorariodiasemanal) {
		this.idHorariodiasemanal = idHorariodiasemanal;
	}
	public Date getDataDisponivel() {
		return dataDisponivel;
	}
	public void setDataDisponivel(Date dataDisponivel) {
		this.dataDisponivel = dataDisponivel;
	}
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public Medico getMedico() {
		return Medico;
	}
	public void setMedico(Medico medico) {
		Medico = medico;
	}
	
	
	public List<AgendaConfirm> getAgendaConfirms() {
		return agendaConfirms;
	}
	public void setAgendaConfirms(List<AgendaConfirm> agendaConfirms) {
		this.agendaConfirms = agendaConfirms;
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
		result = prime * result + ((Medico == null) ? 0 : Medico.hashCode());
		result = prime * result
				+ ((dataDisponivel == null) ? 0 : dataDisponivel.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + idHorariodiasemanal;
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
		HorarioDiaSemanal other = (HorarioDiaSemanal) obj;
		if (Medico == null) {
			if (other.Medico != null)
				return false;
		} else if (!Medico.equals(other.Medico))
			return false;
		if (dataDisponivel == null) {
			if (other.dataDisponivel != null)
				return false;
		} else if (!dataDisponivel.equals(other.dataDisponivel))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (idHorariodiasemanal != other.idHorariodiasemanal)
			return false;
		return true;
	}
	
	
	
}	
	

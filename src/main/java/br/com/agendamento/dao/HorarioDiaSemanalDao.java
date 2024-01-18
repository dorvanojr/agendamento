package br.com.agendamento.dao;

import java.util.List;

import br.com.agendamento.entity.AgendaConfirm;
import br.com.agendamento.entity.HorarioDiaSemanal;
import br.com.agendamento.entity.Medico;


public interface HorarioDiaSemanalDao {
    
	public void save(HorarioDiaSemanal horarioDiaSemanal); 
	public List<HorarioDiaSemanal> listAllID(int id);
	public  List<HorarioDiaSemanal> listAllIDHorario(int id);
	public void remove(HorarioDiaSemanal horarioDiaSemanal);
	public void save(AgendaConfirm a);
	public  List<AgendaConfirm> listAgendaConfirmID(int id);
}

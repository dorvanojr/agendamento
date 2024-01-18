package br.com.agendamento.dao;

import java.util.List;

import br.com.agendamento.entity.ClinicaPlanosSaude;
import br.com.agendamento.entity.Planos;
import br.com.agendamento.entity.User;



public interface PlanosDao {

	
	public List<Planos> list();
	public void save(ClinicaPlanosSaude clinicaPlanosSaude);
	
}

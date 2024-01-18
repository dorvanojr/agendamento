package br.com.agendamento.dao;

import java.util.List;

import br.com.agendamento.entity.Medico;


public interface MedicoDao {

	public void save(Medico Medico);
	public void update(Medico medico);
	public void remove(Medico medico);
	public List<Medico> listMedicoPorClinicaIDNomeMedico(int id, String NomeMedico);
	public List<Medico> listMedicoPorClinicaID(int id);
	public List<Medico> listAllID(int id);
}

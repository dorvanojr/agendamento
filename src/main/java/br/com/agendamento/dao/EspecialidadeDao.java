package br.com.agendamento.dao;

import java.util.List;

import br.com.agendamento.entity.Clinica;
import br.com.agendamento.entity.Especialidade;
import br.com.agendamento.entity.Medico;


public interface EspecialidadeDao {


	public List<Especialidade> listAll();
	public List<Especialidade> listAllId(int id);
	public List<Especialidade> listAllNome(String nome);
	public Especialidade listPorNome(String nome);
	public List<Clinica> listClinicaPorEsp(int Id);
	public List<Medico>  listMedicoPorClinica(int id);
	public Clinica listClinicaPorEspObject(int id);
}

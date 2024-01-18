package br.com.agendamento.controller.util;

import java.util.List;

import br.com.agendamento.entity.Clinica;
import br.com.agendamento.entity.HorarioDiaSemanal;
import br.com.agendamento.entity.Medico;

public class GenericDto {

	public static String login;
	public static String senha;
    public static int id;
    public static List<Medico> medicos;
    public static List<HorarioDiaSemanal> horarioDiaSemanals;
     
	public String getLogin() {
		return login;
	}

	public static int getId() {
		return id;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String senha) {
		GenericDto.senha = senha;
	}

	public static List<Medico> getMedicos() {
		return medicos;
	}

	public static void setMedicos(List<Medico> medicos) {
		GenericDto.medicos = medicos;
	}

	public static List<HorarioDiaSemanal> getHorarioDiaSemanals() {
		return horarioDiaSemanals;
	}

	public static void setHorarioDiaSemanals(
			List<HorarioDiaSemanal> horarioDiaSemanals) {
		GenericDto.horarioDiaSemanals = horarioDiaSemanals;
	}


	
}

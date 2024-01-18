package br.com.agendamento.controller.util;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.agendamento.entity.Clinica;
import br.com.agendamento.entity.Especialidade;


@FacesConverter(value="clinicaConverter", forClass = Clinica.class)
public class ClinicaConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {
		if (value != null) {
			return (Clinica)this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {

		if (value != null && !"".equals(value)) {

			Clinica entity = (Clinica) value;
			if (entity.getIdClinica() == 0) {
				return null;
			}

			// adiciona item como atributo do componente
			this.addAttribute(component, entity);

		   int codigo = entity.getIdClinica();
			if (codigo != 0) {
				return String.valueOf(codigo);
			}
		}

		return (String) value;
	}

	protected void addAttribute(UIComponent component, Clinica o) {
		String key = o.getId().toString(); // codigo da empresa como chave neste caso
		this.getAttributesFrom(component).put(key, o);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}
	
}	

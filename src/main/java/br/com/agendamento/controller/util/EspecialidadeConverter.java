package br.com.agendamento.controller.util;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import javax.inject.Named;

import br.com.agendamento.entity.Especialidade;


@Named
@FacesConverter(value = "especialidadeConverter", forClass = Especialidade.class)
public class EspecialidadeConverter implements Converter{
	 @Override
	    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
	        if (value != null && !value.isEmpty()) {
	            return (Especialidade) uiComponent.getAttributes().get(value);
	        }
	        return null;
	    }

	    @Override
	    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
	        if (value instanceof Especialidade) {
	        	Especialidade entity= (Especialidade) value;
	            if (entity != null && entity instanceof Especialidade && entity.getNome() != null) {
	                uiComponent.getAttributes().put( entity.getNome().toString(), entity);
	                return entity.getNome().toString();
	            }
	        }
	        return "";
	    }
	
}	
package br.com.agendamento.controller.util;


import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

import br.com.agendamento.controller.agendamento.AgendamentoBean;

public class LocaleChangeListener implements ValueChangeListener {
   
   @Override
   public void processValueChange(ValueChangeEvent event)
      throws AbortProcessingException {
      
      //access country bean directly
      AgendamentoBean userData = (AgendamentoBean) FacesContext.getCurrentInstance().
      getExternalContext().getSessionMap().get("userData");
      userData.setSelectedCountry(event.getNewValue().toString());
   }
}
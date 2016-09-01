package br.edu.ifpb.mt.dac.sysmarket.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.entities.Endereco;
import br.edu.ifpb.mt.dac.sysmarket.service.EnderecoService;

@Named
@RequestScoped
public class EnderecoConverter implements Converter {
	
	@Inject
	private EnderecoService service;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		long id = Long.parseLong(value);

		try {
			return service.getById(id);
		} catch (Exception e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Endereco) value).getId();
		return (id != null) ? id.toString() : null;
	}
}

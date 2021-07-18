package br.com.zupacademy.achiley.mercadolivre.produto.caracteriscas;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.achiley.mercadolivre.produto.ProdutoForm;

@Component
public class AvoidsDuplicateCharacteristicsValidator implements Validator  {

	@Override
	public boolean supports(Class<?> klass) {
		return ProdutoForm.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		if(errors.hasErrors()) {
			return ;
		}
		
		ProdutoForm form = (ProdutoForm) obj;
		
		Set<String> duplicados = form.buscarCaracteristicasDuplicadas();
		if(!duplicados.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "Voce inseriu caracteristicas iguais: "+duplicados);
		}
	}
}

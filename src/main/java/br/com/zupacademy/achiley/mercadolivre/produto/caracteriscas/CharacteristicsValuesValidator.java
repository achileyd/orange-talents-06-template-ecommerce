package br.com.zupacademy.achiley.mercadolivre.produto.caracteriscas;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.achiley.mercadolivre.produto.ProdutoForm;

@Component
public class CharacteristicsValuesValidator implements Validator  {

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

		Boolean camposVazios = form.temCaracteristicaComCamposVazios();
		if(camposVazios) {
			errors.rejectValue("caracteristicas", null, "As caracteristicas nao podem ter campos vazios");
		}
		
		Set<String> duplicados = form.buscarCaracteristicasDuplicadas();
		if(!duplicados.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "Voce inseriu caracteristicas iguais: "+duplicados);
		}
	}
}

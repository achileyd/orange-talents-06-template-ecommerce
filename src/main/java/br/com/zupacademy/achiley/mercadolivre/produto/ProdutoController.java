package br.com.zupacademy.achiley.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.achiley.mercadolivre.produto.caracteriscas.CharacteristicsValuesValidator;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;

@RestController
public class ProdutoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired 
	private CharacteristicsValuesValidator validator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators( validator);
	}

	@PostMapping("/produtos")
	@Transactional
	public String cadastrar(@RequestBody @Valid ProdutoForm form, @AuthenticationPrincipal Usuario usuarioLogado) {
		Produto produto = form.converter(manager, usuarioLogado);
		manager.persist(produto);

		return produto.ToString();
	}

}

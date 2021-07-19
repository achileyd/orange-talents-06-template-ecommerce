package br.com.zupacademy.achiley.mercadolivre.produto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.achiley.mercadolivre.produto.caracteriscas.AvoidsDuplicateCharacteristicsValidator;
import br.com.zupacademy.achiley.mercadolivre.produto.imagens.ImagemDoProdutoForm;
import br.com.zupacademy.achiley.mercadolivre.produto.imagens.UploaderFake;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired 
	private AvoidsDuplicateCharacteristicsValidator validator;
	
	@Autowired
	private UploaderFake uploaderFake;

	@InitBinder(value = "produtoForm")
	public void init(WebDataBinder binder) {
		binder.addValidators( validator);
	}

	@PostMapping
	@Transactional
	public String cadastrar(@RequestBody @Valid ProdutoForm form, @AuthenticationPrincipal Usuario usuarioLogado) {
		Produto produto = form.converter(manager, usuarioLogado);
		manager.persist(produto);

		return produto.toString();
	}
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public String adicionarImagens(@PathVariable("id") Long id, @Valid ImagemDoProdutoForm form, @AuthenticationPrincipal Usuario usuarioLogado) {
		
		Produto produto = manager.find(Produto.class, id);
		
		if(!produto.pertenceAoUsuario(usuarioLogado)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploaderFake.envia(form.getImagens());
		produto.associaImagens(links);
		
		manager.merge(produto);
		
		return produto.toString();
		
	}
	
}

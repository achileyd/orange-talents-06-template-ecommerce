package br.com.zupacademy.achiley.mercadolivre.produto.opinioes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.achiley.mercadolivre.produto.Produto;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;

@RestController
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "produtos/{id}/opiniao")
	@Transactional
	public String adicionarOpiniao(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoProdutoForm form, 
								   @AuthenticationPrincipal Usuario usuarioLogado) {
		
		Produto produto = manager.find(Produto.class, id);
		
		OpiniaoProduto opiniao = form.converter(produto, usuarioLogado);
		
		manager.persist(opiniao);
		
		return opiniao.toString();
		
	}

}

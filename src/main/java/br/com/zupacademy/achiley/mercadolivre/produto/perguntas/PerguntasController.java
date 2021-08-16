package br.com.zupacademy.achiley.mercadolivre.produto.perguntas;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.achiley.mercadolivre.produto.Produto;
import br.com.zupacademy.achiley.mercadolivre.produto.perguntas.email.EmailNotificador;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;

@RestController
public class PerguntasController {
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EmailNotificador notificador;
	
	@PostMapping(value = "produtos/{id}/perguntas")
	@Transactional
	public String adicionarPergunta(@PathVariable("id") Long id, @RequestBody @Valid PerguntaProdutoForm form, 
								   @AuthenticationPrincipal Usuario usuarioLogado) {
		
		Produto produto = manager.find(Produto.class, id);
		
		PerguntaProduto pergunta = form.converter(produto, usuarioLogado);
		
		manager.persist(pergunta);
		
		notificador.novaPergunta(pergunta);
		
		return pergunta.toString();
		
	}
	
}

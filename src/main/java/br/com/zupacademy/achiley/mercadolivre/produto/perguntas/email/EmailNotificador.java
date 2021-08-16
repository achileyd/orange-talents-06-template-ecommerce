package br.com.zupacademy.achiley.mercadolivre.produto.perguntas.email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.achiley.mercadolivre.produto.perguntas.PerguntaProduto;

@Component
public class EmailNotificador {
	
	@Autowired
	private Emails emails;
	
	public void novaPergunta(@NotNull @Valid PerguntaProduto pergunta) {
		emails.enviaEmail("novapergunta@mercadolivre.com", 
						  pergunta.getProduto().getVendedor().getUsername(),
						  "Você recebeu uma nova pergunta", 
						  "Olá, \nVocê recebeu uma nova pergunta de " +pergunta.getPossivelComprador().getUsername()+ 
						  " sobre o produto " +pergunta.getProduto().getNome() + 
			       		  ". \nAcesse sua conta para ver os detalhes.");
	}

}

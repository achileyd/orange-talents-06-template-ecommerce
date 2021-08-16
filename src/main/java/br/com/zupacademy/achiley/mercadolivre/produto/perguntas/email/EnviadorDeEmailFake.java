package br.com.zupacademy.achiley.mercadolivre.produto.perguntas.email;

import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmailFake implements Emails {

	@Override
	public void enviaEmail(String remetente, String destinatario, String assunto, String mensagem) {
		System.out.println("De: " +remetente);
		System.out.println("Para: " +destinatario);
		System.out.println("Assunto: " +assunto);
		System.out.println(mensagem);
		
	}

	
	
}

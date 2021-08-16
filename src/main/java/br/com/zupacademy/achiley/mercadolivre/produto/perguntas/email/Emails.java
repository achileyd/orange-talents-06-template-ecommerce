package br.com.zupacademy.achiley.mercadolivre.produto.perguntas.email;

public interface Emails {
	
	public void enviaEmail(String remetente, String destinatario, String assunto, String mensagem);
}

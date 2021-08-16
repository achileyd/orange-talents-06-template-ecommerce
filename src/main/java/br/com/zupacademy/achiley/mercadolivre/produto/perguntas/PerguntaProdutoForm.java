package br.com.zupacademy.achiley.mercadolivre.produto.perguntas;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.achiley.mercadolivre.produto.Produto;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;

public class PerguntaProdutoForm {
	@NotBlank
	private String titulo;
	
	@Deprecated
	public PerguntaProdutoForm() {
		
	}
	
	public PerguntaProdutoForm(@NotBlank String titulo) {
		super();
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public PerguntaProduto converter(@NotNull @Valid Produto produto, @NotNull @Valid Usuario possivelComprador) {
		return new PerguntaProduto(titulo, possivelComprador, produto);
	}
}

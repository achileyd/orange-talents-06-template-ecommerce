package br.com.zupacademy.achiley.mercadolivre.produto.opinioes;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.achiley.mercadolivre.produto.Produto;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;

public class OpiniaoProdutoForm {
	
	@NotNull
	@Min(1)
	@Max(5)
	private Integer nota;
	@NotBlank
	@Size(max= 50)
	private String titulo;
	@NotBlank
	@Size(max= 500)
	private String descricao;
	
	@Deprecated 
	public OpiniaoProdutoForm() {
		
	}
	
	public OpiniaoProdutoForm(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank @Size(max = 50) String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public OpiniaoProduto converter(Produto produto, Usuario comprador) {
		return new OpiniaoProduto(nota, titulo, descricao, comprador, produto);
	}
}

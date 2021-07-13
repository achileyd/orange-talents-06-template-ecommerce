package br.com.zupacademy.achiley.mercadolivre.produto.caracteriscas;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.achiley.mercadolivre.produto.Produto;

public class CaracteristicasProdutoForm {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	@Deprecated
	public CaracteristicasProdutoForm() {
		
	}
	
	public CaracteristicasProdutoForm(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public CaracteristicasProduto converter(@NotNull Produto produto) {
		return new CaracteristicasProduto(nome, descricao, produto);
	}
	

}

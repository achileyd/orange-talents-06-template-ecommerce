package br.com.zupacademy.achiley.mercadolivre.categoria;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.achiley.mercadolivre.shared.ExistsId;
import br.com.zupacademy.achiley.mercadolivre.shared.UniqueValue;

public class CategoriaForm {
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@ExistsId(domainClass = Categoria.class)
	private Long categoriaMaeId;
	
	@Deprecated
	public CategoriaForm() {
		
	}
	
	public CategoriaForm(@NotBlank String nome) {
		super();
		this.nome = nome;
	}
	
	
	public String getNome() {
		return nome;
	}

	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}

	public Categoria converter() {
		
		Categoria categoria = new Categoria (nome);
		
		if(!(categoriaMaeId == null)) {
			categoria.setCategoriaMaeId(categoriaMaeId);
		}
		
		return categoria;
	}
}

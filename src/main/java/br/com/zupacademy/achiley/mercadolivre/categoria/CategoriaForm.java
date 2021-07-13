package br.com.zupacademy.achiley.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.achiley.mercadolivre.shared.ExistsId;
import br.com.zupacademy.achiley.mercadolivre.shared.UniqueValue;

public class CategoriaForm {
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
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

	public Categoria converter(EntityManager manager) {
		
		Categoria categoria = new Categoria (nome);
		
		if(categoriaMaeId != null) {
			Categoria mae = manager.find(Categoria.class, categoriaMaeId);
			categoria.setCategoriaMae(mae);
		}
		
		return categoria;
	}
}

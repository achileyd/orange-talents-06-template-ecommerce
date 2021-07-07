package br.com.zupacademy.achiley.mercadolivre.categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	private Long categoriaMaeId;
	
	@Deprecated
	public Categoria () {
		
	}

	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}

	public void setCategoriaMaeId(Long categoriaMaeId) {
		this.categoriaMaeId = categoriaMaeId;
	}

	@Override
	public String toString() {
		
		if(!(categoriaMaeId == null)) {
			return "Categoria [\nNome = " + nome + 
				   "\nCategoria Relacionada = " +categoriaMaeId +
				   "\n]";
		}
		
		return "Categoria [\nNome = " + nome + "\n]";
	}
}

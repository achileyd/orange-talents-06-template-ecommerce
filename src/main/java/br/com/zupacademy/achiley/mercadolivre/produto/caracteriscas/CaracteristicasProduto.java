package br.com.zupacademy.achiley.mercadolivre.produto.caracteriscas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.achiley.mercadolivre.produto.Produto;
import io.jsonwebtoken.lang.Assert;

@Entity
@Table(name = "caracteristicas_do_produto")
public class CaracteristicasProduto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaracteristicasProduto other = (CaracteristicasProduto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	@Deprecated
	public CaracteristicasProduto() {
		
	}

	public CaracteristicasProduto(@NotBlank String nome, @NotBlank String descricao, @Valid @NotNull Produto produto) {
		Assert.hasLength(nome, "O campo nome nao pode estar em branco");
		Assert.hasLength(descricao, "O campo descricao nao pode estar em branco");
		Assert.notNull(produto, "O objeto produto nao pode ser nulo");
		
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
	
	@Override
	public String toString() {
		return "Caracteristicas do Produto = [" +
				"\nNome = " + nome + 
				"\nDescricao = "+ descricao + "]";
	}

}

package br.com.zupacademy.achiley.mercadolivre.produto.opinioes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.achiley.mercadolivre.produto.Produto;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

@Entity
@Table(name= "opinioes_produto")
public class OpiniaoProduto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		OpiniaoProduto other = (OpiniaoProduto) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Deprecated
	public OpiniaoProduto() {
		
	}
	
	public OpiniaoProduto(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank @Size(max = 50) String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull @Valid Usuario usuario,
			@NotNull @Valid Produto produto) {
		Assert.isTrue(nota > 0 && nota <= 5, "A nota deve ser um valor entre 1 e 5");
		Assert.hasLength(titulo, "Titulo nao pode estar em branco");
		Assert.hasLength(descricao, "Descricao nao pode estar em branco");
		Assert.notNull(produto, "O objeto Produto nao pode ser nulo");
		Assert.notNull(usuario, "O objeto Usuario nao pode ser nulo");
		Assert.isTrue(titulo.length() <= 50, "O titulo deve ter no maximo 50 caracteres");
		Assert.isTrue(descricao.length() <= 500, "O titulo deve ter no maximo 50 caracteres");
		
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	@Override
	public String toString() {
		return "Opiniao [\nNota = " + nota +
				" \nTitulo = " + titulo +
				" \nDescricao = " + descricao +
				" \nComprador = "+ usuario +
				" \nProduto = " + produto +
				" \n]";
	}
	
}

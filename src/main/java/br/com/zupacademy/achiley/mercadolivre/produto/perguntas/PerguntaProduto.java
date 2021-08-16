package br.com.zupacademy.achiley.mercadolivre.produto.perguntas;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zupacademy.achiley.mercadolivre.produto.Produto;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;

@Entity
@Table(name= "perguntas_produto")
public class PerguntaProduto implements Comparable<PerguntaProduto>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String titulo;
	private LocalDateTime instanteCriacao = LocalDateTime.now();
	@NotNull
	@Valid
	@ManyToOne
	private Usuario possivelComprador;
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		PerguntaProduto other = (PerguntaProduto) obj;
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
	public PerguntaProduto() {
		
	}

	public PerguntaProduto(@NotBlank String titulo, @NotNull @Valid Usuario possivelComprador, @NotNull @Valid Produto produto) {
		Assert.hasLength(titulo, "O titulo nao pode estar em branco");
		Assert.notNull(possivelComprador, "O objeto usuario nao pode ser nulo");
		Assert.notNull(produto, "O objeto produto nao pode ser nulo");
		
		this.titulo = titulo;
		this.possivelComprador = possivelComprador;
		this.produto = produto;
	}
	
	
	public Produto getProduto() {
		return produto;
	}

	public Usuario getPossivelComprador() {
		return possivelComprador;
	}

	@Override
	public String toString() {
		return "Pergunta [\nTitulo = " + titulo +
				" \nUsuario = " + possivelComprador +
				" \nProduto = " + produto +
				" \n]";
	}

	@Override
	public int compareTo(PerguntaProduto o) {
		return this.instanteCriacao.compareTo(o.instanteCriacao);
	}
	
}

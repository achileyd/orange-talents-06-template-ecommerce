package br.com.zupacademy.achiley.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zupacademy.achiley.mercadolivre.categoria.Categoria;
import br.com.zupacademy.achiley.mercadolivre.produto.caracteriscas.CaracteristicasProduto;
import br.com.zupacademy.achiley.mercadolivre.produto.caracteriscas.CaracteristicasProdutoForm;
import br.com.zupacademy.achiley.mercadolivre.produto.imagens.ImagemDoProduto;
import br.com.zupacademy.achiley.mercadolivre.produto.opinioes.OpiniaoProduto;
import br.com.zupacademy.achiley.mercadolivre.produto.perguntas.PerguntaProduto;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

@Entity
@Table(name = "produtos")
public class Produto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal preco;
	@NotNull
	@Positive
	private Integer quantidadeDisponivel;
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	private LocalDateTime momentoCadastro = LocalDateTime.now();
	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;
	@NotNull
	@Valid
	@ManyToOne
	private Usuario vendedor;
	@NotNull
	@OneToMany(mappedBy = "produto",cascade = CascadeType.PERSIST)
	private Set<CaracteristicasProduto> caracteristicas = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemDoProduto> imagens = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<OpiniaoProduto> opinioes = new HashSet<>();	
	
	@OneToMany(mappedBy = "produto")
	@OrderBy("instanteCriacao asc")
	private SortedSet<PerguntaProduto> perguntas = new TreeSet<>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Deprecated
	public Produto() {
		
	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal preco, @NotNull @Positive Integer quantidadeDisponivel,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Categoria categoria,
			@NotNull Usuario vendedor, @NotNull @Valid Collection<CaracteristicasProdutoForm> caracteristicas) {
		Assert.hasLength(nome, "O campo nome nao pode estar em branco");
		Assert.hasLength(descricao, "O campo descricao nao pode estar em branco");
		Assert.notNull(categoria, "O objeto categoria nao pode estar nulo");
		Assert.notNull(vendedor, "O objeto vendedor nao pode estar nulo");
		Assert.isTrue(preco.intValue() > 0, "O salario deve ser maior que 0");
		
		this.nome = nome;
		this.preco = preco;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.vendedor = vendedor;
		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.converter(this))
				.collect(Collectors.toSet()));
		
		Assert.isTrue(this.caracteristicas.size() >= 3, "O produto deve ter no mínimo 3 características");
	}

	public String getNome() {
		return nome;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public String toString() {
		return "Produto [\nNome = " +nome+ 
				   "\nPreco = "+preco+
				   "\nQuantidade = " +quantidadeDisponivel+ 
				   "\nDescricao = " +descricao+ 
				   "\nCadastrado em = " +momentoCadastro+ 
				   "\nCaracteristicas = " +caracteristicas+
				   "\nCategoria = " +categoria+ 
				   "\nVendedor = " +vendedor+ 
				   "\nImagens = " +imagens+"]";
	}

	public boolean pertenceAoUsuario(Usuario usuarioLogado) {
		return this.vendedor.equals(usuarioLogado);
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemDoProduto> imagens = links.stream()
				.map(link -> new ImagemDoProduto(link, this))
				.collect(Collectors.toSet());

		this.imagens.addAll(imagens);
	}
	
	public <T extends Comparable<T>> SortedSet<T> associaPerguntas(Function<PerguntaProduto, T> funcaoAssocia) {
		return this.perguntas.stream().map(funcaoAssocia)
				.collect(Collectors.toCollection(TreeSet :: new));
	}

}

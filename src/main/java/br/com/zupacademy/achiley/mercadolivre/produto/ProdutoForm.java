package br.com.zupacademy.achiley.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zupacademy.achiley.mercadolivre.categoria.Categoria;
import br.com.zupacademy.achiley.mercadolivre.produto.caracteriscas.CaracteristicasProdutoForm;
import br.com.zupacademy.achiley.mercadolivre.shared.ExistsId;
import br.com.zupacademy.achiley.mercadolivre.usuario.Usuario;

public class ProdutoForm {
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
	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaId;
	@NotNull
	@Size(min = 3)
	@Valid
	private List<CaracteristicasProdutoForm> caracteristicas = new ArrayList<>();

	@Deprecated
	public ProdutoForm() {

	}

	public ProdutoForm(@NotBlank String nome, @NotNull @Positive BigDecimal preco,
			@NotNull @Positive Integer quantidadeDisponivel, @NotBlank @Size(max = 1000) String descricao,
			@NotNull Long categoriaId, @Valid @NotNull @Size(min = 3) List<CaracteristicasProdutoForm> caracteristicas) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas.addAll(caracteristicas);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public List<CaracteristicasProdutoForm> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<CaracteristicasProdutoForm> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Produto converter(EntityManager manager, @NotNull Usuario vendedor) {
		Categoria categoria = manager.find(Categoria.class, categoriaId);

		return new Produto(nome, preco, quantidadeDisponivel, descricao, categoria, vendedor, caracteristicas);
	}

	public Set<String> buscarCaracteristicasDuplicadas() {
		HashSet<String> duplicados = new HashSet<>();
		HashSet<String> result = new HashSet<>();

		for (CaracteristicasProdutoForm caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();

			if (!result.contains(nome)) {
				result.add(nome);
			} else {
				duplicados.add(nome);
			}

		}
		return duplicados;
	}

}

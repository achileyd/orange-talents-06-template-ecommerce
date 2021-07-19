package br.com.zupacademy.achiley.mercadolivre.produto.imagens;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import br.com.zupacademy.achiley.mercadolivre.produto.Produto;

@Entity
@Table(name = "imagens_do_produto")
public class ImagemDoProduto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@URL
	private String link;
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
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
		ImagemDoProduto other = (ImagemDoProduto) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	@Deprecated
	public ImagemDoProduto() {
		
	}
	
	public ImagemDoProduto(@NotBlank @URL  String link, @Valid @NotNull Produto produto) {
		this.link = link;
		this.produto = produto;
	}
	
	public String toString() {
		return "Imagem do Produto [\nId = " +id+ 
				   "\nLink = "+link+"\n]";
	}
	
}

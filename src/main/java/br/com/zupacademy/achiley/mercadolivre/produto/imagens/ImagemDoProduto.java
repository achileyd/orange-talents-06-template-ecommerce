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
		ImagemDoProduto other = (ImagemDoProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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

package br.com.zupacademy.achiley.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
@Table(name ="usuarios")
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min = 6)
	private String senha;
	@NotNull
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Deprecated
	public Usuario() {
		
	}

	public Usuario(@NotBlank @Email String email, @Valid @NotNull SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(email), "O login nao pode estar em branco");
		Assert.notNull(senhaLimpa, "O objeto do tipo senha nao pode estar nulo");
		
		this.email = email;
		this.senha = senhaLimpa.hash();
	}
}

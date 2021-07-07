package br.com.zupacademy.achiley.mercadolivre.usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

/**
 * Representa uma senha limpa no sistema
 * @author achileydias
 *
 */
public class SenhaLimpa {
	
	private String senha;
	
	public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
		Assert.hasLength(senha, "O login nao pode estar em branco");
		Assert.isTrue(senha.length() >= 6, "A senha deve ter no minimo 6 caracteres ");
		
		this.senha = senha;
	}

	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}

}

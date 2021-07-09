package br.com.zupacademy.achiley.mercadolivre.config.security;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	
	@NotBlank
	private String email;
	@NotBlank
	private String senha;
	
	@Deprecated
	public LoginForm() {
		
	}
	
	public LoginForm(@NotBlank String email, @NotBlank String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}


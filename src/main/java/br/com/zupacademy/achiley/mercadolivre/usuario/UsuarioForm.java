package br.com.zupacademy.achiley.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import br.com.zupacademy.achiley.mercadolivre.shared.UniqueValue;

public class UsuarioForm {
	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	@NotBlank
	@Size(min = 6)
	private String senha;
	
	@Deprecated 
	public UsuarioForm(){
		
	}

	public UsuarioForm(@NotBlank @Email String email, @NotBlank @Min(6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario converter() {
		return new Usuario(email, new SenhaLimpa(senha));
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	
}

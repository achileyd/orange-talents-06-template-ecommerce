package br.com.zupacademy.achiley.mercadolivre.usuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/usuarios")
	public ResponseEntity<Void> cadastrar(@RequestBody @Valid UsuarioForm form) {
		Usuario usuario = form.converter();
		repository.save(usuario);
		
		return ResponseEntity.ok().build();
	}
}

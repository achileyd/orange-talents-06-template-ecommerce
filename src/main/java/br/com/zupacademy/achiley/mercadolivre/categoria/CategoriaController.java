package br.com.zupacademy.achiley.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CategoriaController {
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping("/categorias")
	@Transactional
	public String cadastrar(@RequestBody @Valid CategoriaForm form) {
		Categoria categoria = form.converter(manager);
		manager.persist(categoria);
		
		return categoria.toString();
	}
}

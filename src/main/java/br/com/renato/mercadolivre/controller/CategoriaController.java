package br.com.renato.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.mercadolivre.controller.form.CategoriaForm;
import br.com.renato.mercadolivre.model.Categoria;
import br.com.renato.mercadolivre.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaForm categoriaForm){
		Categoria categoria = categoriaForm.toModel(categoriaRepository);
		categoriaRepository.save(categoria);
		return ResponseEntity.ok().build();
	}
}

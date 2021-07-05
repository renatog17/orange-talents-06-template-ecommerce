package br.com.renato.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.mercadolivre.controller.form.UsuarioForm;
import br.com.renato.mercadolivre.model.Usuario;
import br.com.renato.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm){
		Usuario usuario = usuarioForm.toModel();
		usuarioRepository.save(usuario);
		return ResponseEntity.ok().build();
	}
}

package br.com.renato.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.mercadolivre.controller.form.PerguntaForm;
import br.com.renato.mercadolivre.model.Pergunta;
import br.com.renato.mercadolivre.model.Produto;
import br.com.renato.mercadolivre.model.Usuario;
import br.com.renato.mercadolivre.repository.PerguntaRepository;
import br.com.renato.mercadolivre.repository.ProdutoRepository;

@RequestMapping("/perguntas")
@RestController
public class PerguntaController {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	PerguntaRepository perguntaRepository;
	@PostMapping(value = "/{id}")
	public ResponseEntity<?> cadastrarPergunta(@RequestBody @Valid PerguntaForm perguntaForm,
			@PathVariable Long id, @AuthenticationPrincipal Usuario user){
		Produto produto = produtoRepository.findById(id).get(); 
		Pergunta pergunta = perguntaForm.toModel(user, produto);
		perguntaRepository.save(pergunta);
		return ResponseEntity.ok().build();
	}
}

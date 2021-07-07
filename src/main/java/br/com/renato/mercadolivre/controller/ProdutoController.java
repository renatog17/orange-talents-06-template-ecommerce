package br.com.renato.mercadolivre.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.mercadolivre.controller.form.ProdutoForm;
import br.com.renato.mercadolivre.model.Caracteristica;
import br.com.renato.mercadolivre.model.Produto;
import br.com.renato.mercadolivre.repository.CaracteristicaRepository;
import br.com.renato.mercadolivre.repository.CategoriaRepository;
import br.com.renato.mercadolivre.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private CaracteristicaRepository caracteristicaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoForm produtoForm){
		Produto produto = produtoForm.toModelProduto(categoriaRepository);
		produtoRepository.save(produto);
		Set<Caracteristica> caracteristicas = produtoForm.toModelCaracteristica(produto);
		caracteristicaRepository.saveAll(caracteristicas);
		return ResponseEntity.ok().build();
	}
}

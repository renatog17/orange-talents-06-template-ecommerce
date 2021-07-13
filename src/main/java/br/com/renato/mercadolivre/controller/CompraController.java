package br.com.renato.mercadolivre.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.renato.mercadolivre.controller.form.CompraForm;
import br.com.renato.mercadolivre.model.Compra;
import br.com.renato.mercadolivre.model.Produto;
import br.com.renato.mercadolivre.model.Usuario;
import br.com.renato.mercadolivre.model.enumcompra.Gateway;
import br.com.renato.mercadolivre.repository.CompraRepository;
import br.com.renato.mercadolivre.repository.ProdutoRepository;
import br.com.renato.mercadolivre.util.Emails;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private Emails emails;

	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?> compra(@PathVariable Long id, @RequestBody @Valid CompraForm compraForm,
			@AuthenticationPrincipal Usuario user, UriComponentsBuilder uri) {
		Optional<Produto> possivelProduto = produtoRepository.findById(id);
		if (possivelProduto.isEmpty() || compraForm.getQtd() >= possivelProduto.get().getQtdDisponivel()) {
			return ResponseEntity.badRequest().build();
		}
		Produto produto = possivelProduto.get();
		Compra compra = compraForm.toModel(user, produto);
		compraRepository.save(compra);
		emails.novaCompra(compra);
		String retorno = compraForm.getGateway().toString().toLowerCase()+"?buyerId="+compra.getId()+"&redirectUrl={urlRetornoAppPosPagamento}";
		return new ResponseEntity<String>(retorno, HttpStatus.FOUND);

	}
}

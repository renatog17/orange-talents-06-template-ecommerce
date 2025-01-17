package br.com.renato.mercadolivre.controller;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.renato.mercadolivre.config.security.TokenService;
import br.com.renato.mercadolivre.controller.dto.ProdutoDto;
import br.com.renato.mercadolivre.controller.form.ImagemForm;
import br.com.renato.mercadolivre.controller.form.OpiniaoForm;
import br.com.renato.mercadolivre.controller.form.ProdutoForm;
import br.com.renato.mercadolivre.controller.form.UploaderFake;
import br.com.renato.mercadolivre.model.Caracteristica;
import br.com.renato.mercadolivre.model.Opiniao;
import br.com.renato.mercadolivre.model.Produto;
import br.com.renato.mercadolivre.model.Usuario;
import br.com.renato.mercadolivre.repository.CaracteristicaRepository;
import br.com.renato.mercadolivre.repository.CategoriaRepository;
import br.com.renato.mercadolivre.repository.OpiniaoRepository;
import br.com.renato.mercadolivre.repository.ProdutoRepository;
import br.com.renato.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private CaracteristicaRepository caracteristicaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private OpiniaoRepository opiniaoRepository;
	@Autowired
	private UploaderFake uploaderFake;
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoForm produtoForm,
			@RequestHeader("Authorization") String token) {
		Long idUsuario = tokenService.getIdUsuario(token.substring(7));
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		Produto produto = produtoForm.toModelProduto(categoriaRepository, usuario);
		produtoRepository.save(produto);
		Set<Caracteristica> caracteristicas = produtoForm.toModelCaracteristica(produto);
		caracteristicaRepository.saveAll(caracteristicas);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDto> buscar(@PathVariable Long id){
		Produto produto = produtoRepository.findById(id).get();
		ProdutoDto produtoDto = new ProdutoDto(produto);
		return ResponseEntity.ok(produtoDto);
	}
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public ResponseEntity<?> adicionaImagens(@PathVariable Long id, @Valid ImagemForm imagemForm,
			@RequestHeader("Authorization") String token) {
		Set<String> links = uploaderFake.envia(imagemForm.getImagens());

		Long idUsuario = tokenService.getIdUsuario(token.substring(7));
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		Produto produto = produtoRepository.getById(id);

		if (usuario.getId() != produto.getUsuario().getId()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		produto.associaImagens(links);
		produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/{id}/opinioes")
	public ResponseEntity<?> adicionaOpinioes(@RequestBody @Valid OpiniaoForm opiniaoForm,
			@PathVariable Long id, @AuthenticationPrincipal Usuario user){
		Produto produto = produtoRepository.findById(id).get();
		Opiniao opiniao = opiniaoForm.toModel(user, produto);
		opiniaoRepository.save(opiniao);
		return null;
	}
	
	
}

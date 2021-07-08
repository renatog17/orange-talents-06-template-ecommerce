package br.com.renato.mercadolivre.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renato.mercadolivre.model.Opiniao;
import br.com.renato.mercadolivre.model.Produto;
import br.com.renato.mercadolivre.model.Usuario;
import br.com.renato.mercadolivre.repository.ProdutoRepository;
import br.com.renato.mercadolivre.repository.UsuarioRepository;

public class OpiniaoForm {

	@Min(1)
	@Max(5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String descricao;

	public Opiniao toModel(Usuario usuario, Produto produto) {
		Opiniao opiniao = new Opiniao(this.nota, this.titulo, this.descricao, usuario, produto);
		return opiniao;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

}

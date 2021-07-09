package br.com.renato.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.renato.mercadolivre.model.Pergunta;
import br.com.renato.mercadolivre.model.Produto;
import br.com.renato.mercadolivre.model.Usuario;

public class PerguntaForm {

	@NotBlank
	private String titulo;

	public String getTitulo() {
		return titulo;
	}

	public Pergunta toModel(Usuario user, Produto produto) {
		Pergunta pergunta = new Pergunta(titulo, produto, user);
		return pergunta;
	}

}

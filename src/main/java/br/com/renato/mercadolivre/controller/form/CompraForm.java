package br.com.renato.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.renato.mercadolivre.model.Compra;
import br.com.renato.mercadolivre.model.Produto;
import br.com.renato.mercadolivre.model.Usuario;
import br.com.renato.mercadolivre.model.enumcompra.Gateway;

public class CompraForm {

	@NotNull
	@Positive
	private Integer qtd;
	@NotNull
	private Gateway gateway;

	public Integer getQtd() {
		return qtd;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public Compra toModel(Usuario usuario, Produto produto) {
		Compra compra = new Compra(gateway, produto, usuario, qtd);
		return compra;
	}
}

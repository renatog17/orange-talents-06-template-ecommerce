package br.com.renato.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;

public class CaracteristicaForm {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}

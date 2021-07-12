package br.com.renato.mercadolivre.controller.dto;

import br.com.renato.mercadolivre.model.Caracteristica;

public class CaracteristicaDto {

	private String nome;
	private String descricao;
	public CaracteristicaDto(Caracteristica caracteristica) {
		super();
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
}

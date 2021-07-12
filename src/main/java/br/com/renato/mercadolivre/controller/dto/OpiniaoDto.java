package br.com.renato.mercadolivre.controller.dto;

import br.com.renato.mercadolivre.model.Opiniao;

public class OpiniaoDto {

	private String titulo;
	private String descricao;
	private Integer nota;

	public OpiniaoDto(Opiniao opiniao) {
		super();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.nota = opiniao.getNota();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	}

	
}

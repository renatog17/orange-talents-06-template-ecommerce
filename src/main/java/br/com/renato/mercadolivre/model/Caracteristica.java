package br.com.renato.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	@ManyToOne
	private Produto produto;

	public Caracteristica(String nome, String descricao, Produto produto) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}

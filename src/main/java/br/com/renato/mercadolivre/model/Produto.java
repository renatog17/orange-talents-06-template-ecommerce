package br.com.renato.mercadolivre.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double valor;
	private Integer qtdDisponivel;
	private String descricao;
	@ManyToOne
	private Categoria categoria;
	private LocalDate instanteCadastro = LocalDate.now();
	@OneToMany(mappedBy = "produto")
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	
	public Produto(String nome, Double valor, Integer qtdDisponivel, String descricao, Categoria categoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public Integer getQtdDisponivel() {
		return qtdDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDate getInstanteCadastro() {
		return instanteCadastro;
	}

	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	
	
	
}

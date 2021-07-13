package br.com.renato.mercadolivre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.renato.mercadolivre.model.enumcompra.Gateway;
import br.com.renato.mercadolivre.model.enumcompra.StatusCompra;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Gateway gateway;
	@Enumerated(EnumType.STRING)
	private StatusCompra status = StatusCompra.INICIADO;
	@NotNull
	@ManyToOne
	private Produto produto;
	@NotNull
	@ManyToOne
	private Usuario comprador;
	@NotNull
	private Double valorNoMomento;
	@Positive
	private Integer quantidade;
	private LocalDate instanteCompra = LocalDate.now();

	public Compra(@NotBlank Gateway gateway, @NotNull Produto produto, @NotNull Usuario comprador,
			@Positive Integer quantidade) {
		super();
		this.gateway = gateway;
		this.produto = produto;
		this.comprador = comprador;
		this.quantidade = quantidade;
		produto.abaterQuantidade(quantidade);
		this.valorNoMomento = produto.getValor();
	}

	public Usuario getComprador() {
		return comprador;
	}

	public Produto getProduto() {
		return produto;
	}

	public Long getId() {
		return id;
	}
	
	
	
}

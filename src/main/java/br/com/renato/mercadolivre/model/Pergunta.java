package br.com.renato.mercadolivre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String titulo;
	private LocalDate instanteCriacao = LocalDate.now();
	@NotNull
	@ManyToOne
	private Produto produto;
	@NotNull
	@ManyToOne
	private Usuario usuario;

	@Deprecated
	public Pergunta() {
		super();
	}

	public Pergunta(@NotBlank String titulo, @NotNull Produto produto, @NotNull Usuario usuario) {
		super();
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}

	public Usuario getPerguntador() {
		return usuario;
	}

	public Usuario getDonoProduto() {
		return produto.getUsuario();
	}

	public String getTitulo() {
		return titulo;
	}

}

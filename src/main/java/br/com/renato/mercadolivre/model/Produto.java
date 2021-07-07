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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private Double valor;
	@PositiveOrZero
	@NotNull
	private Integer qtdDisponivel;
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	@NotNull
	@ManyToOne
	private Categoria categoria;
	private LocalDate instanteCadastro = LocalDate.now();
	@OneToMany(mappedBy = "produto")
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	public Produto(@NotBlank String nome, @NotNull @Positive Double valor,
			@PositiveOrZero @NotNull Integer qtdDisponivel, @NotBlank @Size(max = 1000) String descricao,
			@NotNull Categoria categoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
	}
	
	
}

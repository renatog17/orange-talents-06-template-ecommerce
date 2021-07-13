package br.com.renato.mercadolivre.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@NotNull
	@ManyToOne
	private Usuario usuario;
	private LocalDate instanteCadastro = LocalDate.now();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	@OneToMany(mappedBy = "produto")
	private List<Opiniao> opinioes = new ArrayList<>();
	@OneToMany(mappedBy = "produto")
	private List<Pergunta> perguntas = new ArrayList<>();

	public Produto() {
		
	}
	
	public Produto(@NotBlank String nome, @NotNull @Positive Double valor,
			@PositiveOrZero @NotNull Integer qtdDisponivel, @NotBlank @Size(max = 1000) String descricao,
			@NotNull Categoria categoria, @NotNull Usuario usuario) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
		.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
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

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public List<Opiniao> getOpinioes() {
		return opinioes;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}
	
	public Double mediaOpiniao() {
		Double somaNota = 0.0;
		for (Opiniao opiniao : opinioes) {
			somaNota = somaNota + opiniao.getNota();
		}
		return somaNota/this.opinioes.size();
	}
	
	public void abaterQuantidade(Integer quantidade) {
		this.qtdDisponivel = this.qtdDisponivel-quantidade; 
	}
}

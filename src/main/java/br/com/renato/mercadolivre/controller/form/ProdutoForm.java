package br.com.renato.mercadolivre.controller.form;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.renato.mercadolivre.config.validacao.Exists;
import br.com.renato.mercadolivre.model.Caracteristica;
import br.com.renato.mercadolivre.model.Categoria;
import br.com.renato.mercadolivre.model.Produto;
import br.com.renato.mercadolivre.model.Usuario;
import br.com.renato.mercadolivre.repository.CategoriaRepository;

public class ProdutoForm {
	
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
	@NotBlank @Exists(domainClass = Categoria.class, fieldName = "nome")
	private String categoria;
	@Size(min = 3)
	private Set<@Valid CaracteristicaForm> caracteristicas = new HashSet<>();

	@Override
	public String toString() {
		return "ProdutoForm [nome=" + nome + ", valor=" + valor + ", qtdDisponivel=" + qtdDisponivel + ", descricao="
				+ descricao + ", categoria=" + categoria + ", caracteristicas=" + caracteristicas + "]";
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

	public String getCategoria() {
		return categoria;
	}

	public Set<CaracteristicaForm> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto toModelProduto(CategoriaRepository categoriaRepository, Usuario usuario) {
		Optional<Categoria> categoria = categoriaRepository.findByNome(this.categoria);
	
		if (categoria.isPresent()) {
			Produto produto = new Produto(this.nome, this.valor, this.qtdDisponivel, this.descricao, categoria.get(), usuario);
			return produto;
		}
		return null;
	}
	
	public Set<Caracteristica> toModelCaracteristica(Produto produto) {
		
		Set<Caracteristica> caracteristicasModel = new HashSet<>();
		for (CaracteristicaForm caracteristicaForm : caracteristicas) {
			Caracteristica caracteristica = new Caracteristica(caracteristicaForm.getNome(), caracteristicaForm.getDescricao(), produto);
			caracteristicasModel.add(caracteristica);
		}
		return caracteristicasModel;
	}

	
}

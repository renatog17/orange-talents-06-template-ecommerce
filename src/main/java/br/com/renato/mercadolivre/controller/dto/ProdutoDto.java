package br.com.renato.mercadolivre.controller.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.renato.mercadolivre.model.Caracteristica;
import br.com.renato.mercadolivre.model.ImagemProduto;
import br.com.renato.mercadolivre.model.Opiniao;
import br.com.renato.mercadolivre.model.Pergunta;
import br.com.renato.mercadolivre.model.Produto;

public class ProdutoDto {

	String nome;
	String descricao;
	Double mediaNotas;
	Double preco;
	Integer qtdNotas;
	List<String> perguntas = new ArrayList<>();
	List<OpiniaoDto> opinioes = new ArrayList<>();
	List<String> imagens = new ArrayList<>();
	List<CaracteristicaDto> caracteristicas = new ArrayList<>();

	public ProdutoDto(Produto produto) {
		super();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.mediaNotas = produto.mediaOpiniao();
		this.preco = produto.getValor();
		this.qtdNotas = produto.getOpinioes().size();
		this.perguntas = produto.getPerguntas()
				.stream().map(p -> p.getTitulo()).collect(Collectors.toList());
		this.opinioes = produto.getOpinioes()
				.stream().map(OpiniaoDto::new).collect(Collectors.toList());
		this.imagens = produto.getImagens()
				.stream().map(i -> i.getLink()).collect(Collectors.toList());
		this.caracteristicas = produto.getCaracteristicas()
				.stream().map(CaracteristicaDto::new).collect(Collectors.toList());
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Double getPreco() {
		return preco;
	}

	public Integer getQtdNotas() {
		return qtdNotas;
	}

	public List<String> getPerguntas() {
		return perguntas;
	}

	public List<OpiniaoDto> getOpinioes() {
		return opinioes;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public List<CaracteristicaDto> getCaracteristicas() {
		return caracteristicas;
	}

}

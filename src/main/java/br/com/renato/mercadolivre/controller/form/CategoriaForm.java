package br.com.renato.mercadolivre.controller.form;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.renato.mercadolivre.config.validacao.ExistsNome;
import br.com.renato.mercadolivre.config.validacao.UniqueValue;
import br.com.renato.mercadolivre.model.Categoria;
import br.com.renato.mercadolivre.repository.CategoriaRepository;

public class CategoriaForm {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	String nome;
	@ExistsNome(domainClass = Categoria.class, fieldName = "nome")
	String nomeCategoriaMae;

	public Categoria toModel(CategoriaRepository categoriaRepository){
		System.out.println(this.nomeCategoriaMae + "aqui");
		Categoria categoria = new Categoria(this.nome);
		if (this.nomeCategoriaMae != null) {
			Optional<Categoria> categoriaMae = categoriaRepository.findByNome(this.nomeCategoriaMae);
			categoria.setCategoriaMae(categoriaMae.get());
		}
		return categoria;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeCategoriaMae() {
		return nomeCategoriaMae;
	}

}

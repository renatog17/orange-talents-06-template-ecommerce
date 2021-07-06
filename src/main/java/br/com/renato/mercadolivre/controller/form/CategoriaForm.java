package br.com.renato.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.renato.mercadolivre.config.validacao.UniqueValue;
import br.com.renato.mercadolivre.model.Categoria;
import br.com.renato.mercadolivre.repository.CategoriaRepository;

public class CategoriaForm {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	String nome;
	String nomeCategoriaMae;

	public Categoria toModel(CategoriaRepository categoriaRepository) {
		System.out.println(this.nomeCategoriaMae+"aqui");
		Categoria categoria = new Categoria(this.nome);
		if (this.nomeCategoriaMae != null) {
			Categoria categoriaMae = categoriaRepository.findByNome(this.nomeCategoriaMae);
			categoria.setCategoriaMae(categoriaMae);
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

package br.com.renato.mercadolivre.model.util;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

/**
 * Representa uma senha limpa no sistema
 * @author renato.oliveira
 *
 */
public class SenhaLimpa {

	@NotBlank @Length(min = 6)
	private String senha;

	public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
		super();
		Assert.hasLength(senha, "senha não pode ser em branco");
		Assert.isTrue(senha.length()>=6, "Senha tem que ter no mínimo 6 caracteres");
		this.senha = senha;
	}
	
	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
}

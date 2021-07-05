package br.com.renato.mercadolivre.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.renato.mercadolivre.config.validacao.UniqueValue;
import br.com.renato.mercadolivre.model.Usuario;
import br.com.renato.mercadolivre.model.util.SenhaLimpa;

public class UsuarioForm {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "login")
	private String login;
	@NotBlank
	@Size(min = 6)
	private String senha;

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario toModel() {
		return new Usuario(login, new SenhaLimpa(senha));
	}
}

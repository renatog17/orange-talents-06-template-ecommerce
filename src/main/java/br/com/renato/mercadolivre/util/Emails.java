package br.com.renato.mercadolivre.util;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renato.mercadolivre.model.Compra;
import br.com.renato.mercadolivre.model.Pergunta;

@Service
public class Emails {

	@Autowired
	private Mailer mailer;

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		// return new RestTemplate().postForEntity("endereco", mandrilMessage,
		// String.class);
		mailer.send("<html></html>", "Nova pergunta", pergunta.getPerguntador().getLogin(),
				"novapergunta@mercadolivre.com", pergunta.getDonoProduto().getLogin());
	}
	
	public void novaCompra(@NotNull @Valid Compra compra) {
		mailer.send("<html></html>", "Nova compra", compra.getComprador().getLogin(), 
				"novacompra@mercadolivre.com", compra.getProduto().getUsuario().getLogin());
	}

}

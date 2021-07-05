package br.com.renato.mercadolivre.config.validacao;

public class ErroValidacao {

	private String campo;
	private String erro;

	public ErroValidacao(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
}

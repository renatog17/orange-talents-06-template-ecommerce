package br.com.renato.mercadolivre.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagemForm {

	@Size(min=1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();

	public ImagemForm(@Size(min = 1) List<MultipartFile> imagens) {
		super();
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}
}

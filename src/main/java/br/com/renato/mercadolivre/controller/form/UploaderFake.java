package br.com.renato.mercadolivre.controller.form;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake {

	/**
	 * 
	 * @param imagens
	 * @return links para imagens
	 */
	public Set<String> envia(List<MultipartFile> imagens){
		return imagens.stream()
				.map(imagem -> "http://fake.io/"
						+imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}
}

package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.response;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Image;

public class ImageRs {

	private String link;

	public ImageRs(Image image) {
		link = image.getLink();
	}
	public String getLink() {
		return link;
	}
}

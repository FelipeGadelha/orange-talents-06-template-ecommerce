package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImageRq {

	@NotNull
	@Size(min = 1)
	private List<MultipartFile> images = new ArrayList<>();
	
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	public List<MultipartFile> getImages() {
		return images;
	}
}

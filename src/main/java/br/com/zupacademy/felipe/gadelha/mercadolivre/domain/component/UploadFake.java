package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploadFake implements Uploader {

	public Set<String> send(List<MultipartFile> images) {
		return images.stream()
				.map(i -> "http://bucket.io"
						+ i.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}

package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

	Set<String> send(List<MultipartFile> images);

}

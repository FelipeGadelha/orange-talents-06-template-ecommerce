package br.com.zupacademy.felipe.gadelha.mercadolivre.convert;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JacksonParse {
	
	@Autowired
	private ObjectMapper mapper;
	private JacksonJsonParser jackson = new JacksonJsonParser();
	
	public <T> String toJson(T t) throws JsonProcessingException {
		return mapper.writeValueAsString(t);
	}

	public Map<String, Object> toMap(String json) {
		return jackson.parseMap(json);
	}
	
	

}

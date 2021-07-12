package br.com.zupacademy.felipe.gadelha.mercadolivre.api.v1.dto.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Product;

public class ProductDetailsRs {

	private String name;
	private BigDecimal price;
	private Map<String, String> features = new HashMap<>();
	private String description;
	private double avgNotes;
	private Integer totalNotes;
	private List<OpinionRs> opinions = new ArrayList<>();
	private List<AskRs> ask = new ArrayList<>();
	private Set<ImageRs> images = new HashSet<>();

	public ProductDetailsRs(Product product) {
		this.name = product.getName();
		this.price = product.getPrice();
		this.features = product.getFeatures();
		this.description = product.getDescription();
		this.avgNotes = product.avgNotes();
		this.totalNotes = product.totalNotes();
		
		
		this.opinions = product.getOpinions()
				.stream().map(OpinionRs::new)
				.collect(Collectors.toList());
		
		
		this.ask = product.getAsk()
				.stream().map(AskRs::new)
				.collect(Collectors.toList());
		
		
		this.images = product.getImages()
				.stream().map(ImageRs::new)
				.collect(Collectors.toSet());
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Map<String, String> getFeatures() {
		return features;
	}

	public String getDescription() {
		return description;
	}

	public double getAvgNotes() {
		return avgNotes;
	}

	public Integer getTotalNotes() {
		return totalNotes;
	}

	public List<OpinionRs> getOpinions() {
		return opinions;
	}

	public List<AskRs> getAsk() {
		return ask;
	}

	public Set<ImageRs> getImages() {
		return images;
	}

	@Override
	public String toString() {
		return "ProductDetailsRs [name=" + name + ", price=" + price + ", features=" + features + ", description="
				+ description + ", avgNotes=" + avgNotes + ", totalNotes=" + totalNotes + ", opinions=" + opinions
				+ ", ask=" + ask + ", images=" + images + "]";
	}
}

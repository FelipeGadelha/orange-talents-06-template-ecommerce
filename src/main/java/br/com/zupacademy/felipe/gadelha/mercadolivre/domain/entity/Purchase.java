package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "purchases")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private User buyer;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private GatewayType gateway;
	@NotNull
	@Positive
	@Column(nullable = false)
	private Integer quantity;
	@NotNull
	@Positive
	@Column(nullable = false)
	private BigDecimal productValue;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PurchaseStatus status = PurchaseStatus.STARTED;
	
	@Size(max = 2)
	@JsonManagedReference
	@OneToMany(mappedBy = "purchase",cascade = CascadeType.MERGE)
	private Set<Transaction> transactions = new HashSet<>();
	
	@Deprecated
	public Purchase() {	}

	public Purchase(User buyer, Product product, Integer quantity, 
			BigDecimal productValue, GatewayType gateway) {
		this.buyer = buyer;
		this.product = product;
		this.quantity = quantity;
		this.productValue = productValue;
		this.gateway = gateway;
	}

	public Long getId() {
		return id;
	}

	public User getBuyer() {
		return buyer;
	}

	public Product getProduct() {
		return product;
	}

	public GatewayType getGateway() {
		return gateway;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getProductValue() {
		return productValue;
	}
	public String getBuyerName() {
		return this.buyer.getUsername();
	}
	public String getProductName() {
		return this.product.getName();
	}
	public Long getSellerId() {
		return this.product.getOwner().getId();
	}
	public String getSellerName() {
		return this.product.getOwner().getUsername();
	}
	public String getGatewayUrl(UriComponentsBuilder uriBuilder) {
		return this.gateway.getUri(this, uriBuilder);
	}
	public void addTransaction(Transaction transaction) {
		Assert.isTrue(!this.transactions.contains(transaction),"Essa transação já foi processada");
		boolean successfulTransaction = this.transactions
				.stream().anyMatch(Transaction::isSuccessful);
		Assert.isTrue(!successfulTransaction, "Essa compra já foi processada com sucesso");
		this.transactions.add(transaction);
	}
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", buyer=" + buyer + ", product=" + product + ", gateway=" + gateway
				+ ", quantity=" + quantity + ", productValue=" + productValue + ", status=" + status + ", transactions="
				+ transactions + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
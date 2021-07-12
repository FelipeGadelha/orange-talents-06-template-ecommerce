package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank @NotNull
	private String gatewayId;
	@NotNull
	@Column(nullable = false)
	private GatewayStatus status;
	@PastOrPresent @CreationTimestamp
	@Column(name="creation_date", nullable = false)
	private LocalDateTime creationDate;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(nullable = false)
	private Purchase purchase;

	@Deprecated
	public Transaction() {	}

	public Transaction(String gatewayId, GatewayStatus status, Purchase purchase) {
		this.gatewayId = gatewayId;
		this.status = status;
		this.purchase = purchase;
	}
	public Long getId() {
		return id;
	}
	public String getGatewayId() {
		return gatewayId;
	}
	public GatewayStatus getStatus() {
		return status;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public boolean isSuccessful() {
		return this.status.equals(GatewayStatus.SUCCESS);
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", gatewayId=" + gatewayId + ", status=" + status + ", creationDate="
				+ creationDate + ", purchase=" + purchase + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gatewayId == null) ? 0 : gatewayId.hashCode());
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
		Transaction other = (Transaction) obj;
		if (gatewayId == null) {
			if (other.gatewayId != null)
				return false;
		} else if (!gatewayId.equals(other.gatewayId))
			return false;
		return true;
	}
}

package br.com.zupacademy.felipe.gadelha.mercadolivre.api.handler;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ExceptionDetails{
	
	protected String title;
	protected int status;
	protected String type;
	protected String details;
	protected String developerMessage;
	protected OffsetDateTime timestamp;
	
	protected ExceptionDetails(Builder<?> builder) {
		this.title = builder.title;
		this.status = builder.status;
		this.type = builder.type;
		this.details = builder.details;
		this.developerMessage = builder.developerMessage;
		this.timestamp = builder.timestamp;
	}
	
	@SuppressWarnings("rawtypes")
	public static Builder<?> builder() {
        return new Builder() {
            @Override
            public Builder<?> getThis() {
                return this;
            }
        };
    }

	public abstract static class Builder<T extends Builder<T>> {
		private String title;
		private int status;
		private String type;
		private String details;
		private String developerMessage;
		private OffsetDateTime timestamp;

		public abstract T getThis();
		
		public T title(String title) {
			this.title = title;
			return this.getThis();
		}
		public T status(int status) {
			this.status = status;
			return this.getThis();
		}
		public T type(String type) {
			this.type = type;
			return this.getThis();
		}
		public T details(String details) {
			this.details = details;
			return this.getThis();
		}
		public T developerMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this.getThis();
		}
		public T timestamp(OffsetDateTime timestamp) {
			this.timestamp = timestamp;
			return this.getThis();
		}
		
		public ExceptionDetails build() {
			return new ExceptionDetails(this);
		}
	}

	public String getTitle() {
		return title;
	}

	public int getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}
	
	public String getDetails() {
		return details;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public String getTimestamp() {
		return timestamp.toString();
	}

	@Override
	public String toString() {
		return "title= " + title + ", status= " + status + ", type= " + type + ", details= " + details
				+ ", developerMessage= " + developerMessage + ", timestamp= " + timestamp;
	}
}

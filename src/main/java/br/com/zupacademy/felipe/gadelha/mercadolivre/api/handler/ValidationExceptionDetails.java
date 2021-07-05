package br.com.zupacademy.felipe.gadelha.mercadolivre.api.handler;

import java.util.Map;
import java.util.Set;

public class ValidationExceptionDetails extends ExceptionDetails{

	private final Map<String, Set<String>> errors;
	
	protected ValidationExceptionDetails(Builder builder) {
		super(builder);
		this.errors = builder.errors;
		
	}

	public static Builder builder() {
        return new Builder();
    }
	
	public static class Builder extends ExceptionDetails.Builder<Builder> {
		
		private Map<String, Set<String>> errors;
		
		protected Builder() {
			super();
		}
		
		public Builder errors(Map<String, Set<String>> map) {
			this.errors = map;
			return this;
		}

		@Override
		public Builder getThis() {
			return this;
		}
		
		@Override
		public ValidationExceptionDetails build() {
            return new ValidationExceptionDetails(this);
        }
	}
	
	public Map<String, Set<String>> getErrors() {
		return errors;
	}
}
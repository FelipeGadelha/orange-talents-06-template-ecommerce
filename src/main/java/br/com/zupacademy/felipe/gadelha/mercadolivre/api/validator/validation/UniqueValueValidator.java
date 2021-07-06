package br.com.zupacademy.felipe.gadelha.mercadolivre.api.validator.validation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zupacademy.felipe.gadelha.mercadolivre.api.validator.annotation.UniqueValue;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>{

	private String domainAttribute;
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(UniqueValue params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		var query = manager.createQuery(
				"select 1 from " + klass.getName() + " where " + domainAttribute + " =:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, 
				"Foi encontrado mais de um " + klass.getSimpleName() + " com o atributo " 
						+ domainAttribute + " = " + value);
		return list.isEmpty();
	}

}
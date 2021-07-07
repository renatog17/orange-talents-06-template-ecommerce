package br.com.renato.mercadolivre.config.validacao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsNomeValidator implements ConstraintValidator<ExistsNome, Object>{

	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;
	
	public void initialize(ExistsNome params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String v = (String) value;
		if (v==null || v.length()==0) {
			System.out.println("entrou");
			return true;
		}
		Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+domainAttribute+"=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		//Assert.state(list.size() <= 1, "Uma "+klass+" com o atributo "+domainAttribute+" já existe!");
		return !list.isEmpty();
	}

}

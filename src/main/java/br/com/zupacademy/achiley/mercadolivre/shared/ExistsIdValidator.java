package br.com.zupacademy.achiley.mercadolivre.shared;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object>  {
	
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(ExistsId params) {
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid (Object value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		}
		Query query = manager.createQuery("select 1 from "+klass.getName()+" where id=:value");
		query.setParameter("value", value);
		List<?> result = query.getResultList();
		
	    if (result.isEmpty()){
	    	return false;
	    }

	    return true;
	}
}

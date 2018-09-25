package com.glovoapp.backender.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.glovoapp.backender.exception.NoDescriptionsValuesException;
import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;

@Component
@ConfigurationProperties(prefix="backender.criteria.box")
public class BoxCriteria implements Criteria {

	
	List<String> descriptions;
	List<Predicate<Order>> predicates;
	
	@PostConstruct
	private void init() throws NoDescriptionsValuesException {
		loadPredicates();
	}
	
	@Override
	public List<Order> applyCriteria(List<Order> orders, Courier courier) {
		if (!courier.getBox()) {
			predicates.forEach(predicate -> orders.removeIf(predicate));
		}
		return orders;
	}
	
	private void loadPredicates() throws NoDescriptionsValuesException {
		predicates = new ArrayList<>();
		if (null == descriptions || descriptions.size() == 0) {
			throw new NoDescriptionsValuesException("Invalid description criteria, please add a valid criteria description" + descriptions);
		}
		descriptions.forEach(q -> {
			predicates.add(order -> StringUtils.containsIgnoreCase(order.getDescription(), q));
		});
		
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
}

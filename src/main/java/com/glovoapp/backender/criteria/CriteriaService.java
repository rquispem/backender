package com.glovoapp.backender.criteria;

import java.util.List;

import org.springframework.stereotype.Service;

import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;

@Service
public class CriteriaService {

	private final CriteriaFactory criteriaFactory;

	public CriteriaService(final CriteriaFactory criteriaFactory) {
		this.criteriaFactory = criteriaFactory;
	}

	public List<Order> applyFilterCriteria(List<Order> orders, Courier courier) {
		getAllCriterias().forEach(c -> c.applyCriteria(orders, courier));
		return orders;
	}

	private List<Criteria> getAllCriterias() {
		return criteriaFactory.getAll();
	}

}

package com.glovoapp.backender.criteria.comparators;

import java.util.Comparator;

import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;

public class VipComparator implements SimpleComparator {

	@Override
	public Comparator<Order> comparator(Courier courier) {
		return Comparator.comparing(Order::getVip).reversed();
	}
	

}

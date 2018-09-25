package com.glovoapp.backender.criteria.comparators;

import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;
import com.glovoapp.backender.util.DistanceCalculator;

public class DistanceComparator implements SimpleComparator {

	@Override
	public java.util.Comparator<Order> comparator(Courier courier) {
		
		java.util.Comparator<Order> comparator = java.util.Comparator.comparing(o -> DistanceCalculator.calculateDistance(courier.getLocation(), o.getPickup()));
		
		return comparator;
	}

	

}

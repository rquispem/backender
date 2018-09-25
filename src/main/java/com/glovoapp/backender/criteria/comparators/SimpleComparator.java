package com.glovoapp.backender.criteria.comparators;

import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;

public interface SimpleComparator {

	public java.util.Comparator<Order> comparator(Courier courier);

}

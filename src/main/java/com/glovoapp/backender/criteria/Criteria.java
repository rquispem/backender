package com.glovoapp.backender.criteria;

import java.util.List;

import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;

public interface Criteria{

	public List<Order> applyCriteria(List<Order> orders, Courier courier );
}

package com.glovoapp.backender.criteria;

import java.util.Comparator;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.glovoapp.backender.criteria.comparators.ComparatorFactory;
import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;

@Component
@ConfigurationProperties(prefix = "backender.criteria.order.priority")
public class SortedCriteria implements Criteria {

	// list of sort priorities
	List<String> list;
	private ComparatorFactory factory;

	public SortedCriteria(ComparatorFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<Order> applyCriteria(List<Order> orders, Courier courier) {

		orders.sort(buildComparator(courier));

		return orders;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	private Comparator<Order> buildComparator(Courier courier) {
		Comparator<Order> comparator = null;

		for (String s : list) {
			if (null == comparator) {
				comparator = factory.getComparator(s).comparator(courier);
			} else {
				comparator.thenComparing(factory.getComparator(s).comparator(courier));
			}

		}
		return comparator;
	}

}

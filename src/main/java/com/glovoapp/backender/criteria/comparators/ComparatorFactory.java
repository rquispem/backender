package com.glovoapp.backender.criteria.comparators;

import org.springframework.stereotype.Component;

@Component
public class ComparatorFactory {

	public SimpleComparator getComparator(String s) {
		SimpleComparator comparator = null;
		switch (s) {
		case "distance":
			comparator = new DistanceComparator();
			break;
		case "vip":
			comparator = new VipComparator();
			break;

		case "food":
			comparator = new FoodComparator();
			break;
		}
		return comparator;
	}

}

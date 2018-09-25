package com.glovoapp.backender.criteria;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CriteriaFactory {

	final Criteria boxCriteria;
	final Criteria sortedCriteria;
	final Criteria vehicleCriteria;
	
	public CriteriaFactory(final BoxCriteria boxCriteria, final SortedCriteria sortedCriteria, final VehicleCriteria vehicleCriteria) {
		this.boxCriteria = boxCriteria;
		this.sortedCriteria = sortedCriteria;
		this.vehicleCriteria = vehicleCriteria;
	}
	List<Criteria> getAll() {
		return Arrays.asList(boxCriteria, vehicleCriteria, sortedCriteria);
	}
}

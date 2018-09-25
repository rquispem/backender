package com.glovoapp.backender.criteria;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;
import com.glovoapp.backender.model.Vehicle;
import com.glovoapp.backender.util.DistanceCalculator;

@Component
public class VehicleCriteria implements Criteria {

	@Value("${backender.criteria.order.distance}")
	private double defaultOrderDistance;
	
	@Override
	public List<Order> applyCriteria(List<Order> orders, Courier courier) {
		Predicate<Order> predicate = order -> (DistanceCalculator.calculateDistance(courier.getLocation(), order.getPickup()) > defaultOrderDistance);
		if (courier.getVehicle() == Vehicle.BICYCLE) {
			orders.removeIf(predicate);
		}
		return orders;
	}

}

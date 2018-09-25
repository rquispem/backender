package com.glovoapp.backender.criteria;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.glovoapp.backender.model.Order;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoxCriterisTest {

	List<Predicate<Order>> predicates;
	
	void before() {
		List<String> descriptions = Arrays.asList("piza", "cake");
		descriptions.forEach(q -> {
			predicates.add(order -> StringUtils.containsIgnoreCase(order.getDescription(), q));
		});
	}
	
	@Test 
	void applyCriteria() {
		List<Predicate<Order>> predicates = Arrays.asList(a);
	}
}

package com.glovoapp.backender.criteria;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.glovoapp.backender.model.Order;
import com.glovoapp.backender.util.CourierUtil;
import com.glovoapp.backender.util.OrdersUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CriteriaServiceTest {

	@MockBean
	CriteriaFactory criteriaFactory;
	
	@Autowired
	CriteriaService criteriaService;
	
	@Test
	void applyCriteriaTest() {
		List<Criteria> criterias = Arrays.asList(new BoxCriteria());
		when(criteriaFactory.getAll()).thenReturn(criterias);
		
		List<Order> orders = criteriaService.applyFilterCriteria(OrdersUtil.allOrders(), CourierUtil.findById("courier-1"));
		assertNotNull(orders);
		assertTrue(!orders.isEmpty());
	}
	
}

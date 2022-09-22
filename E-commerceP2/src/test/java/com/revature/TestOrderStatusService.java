package com.revature;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.OrderStatus;
import com.revature.repos.OrderStatusRepo;
import com.revature.services.OrderStatusServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOrderStatusService {

	@InjectMocks
	private static OrderStatusServiceImpl oStatusServ;
	
	@Mock
	private static OrderStatusRepo oStatusMockRepo;
	
	private static OrderStatus os1,os2;
	/*
	 * private static Order o1, o2; private static User u1 , u2;
	 */
	
	static List<OrderStatus> dummyDB;
	
	@BeforeAll
	static void setUp() throws Exception{
		oStatusMockRepo = Mockito.mock(OrderStatusRepo.class);
		
		oStatusServ = new OrderStatusServiceImpl();
		
		os1 = new OrderStatus(1, "processed");
		
		
		dummyDB = new ArrayList<OrderStatus>();
		
		dummyDB.add(os1);
	
	}
	
	@Test
	@org.junit.jupiter.api.Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	public void checkMockInjection() {
		assertThat(oStatusMockRepo).isNotNull();
		assertThat(oStatusServ).isNotNull();
	}
	
	@Test
	@org.junit.jupiter.api.Order(2)
	@DisplayName("2. addOrderStatus Test")
	public void addOrderStatusTest() {
		os2 = new OrderStatus(2, "pending");
		when(oStatusMockRepo.save(os2)).thenReturn(os2);
		
		assertEquals(os2, oStatusServ.addOrderStatus(os2));
	}
	
	@Test
	@org.junit.jupiter.api.Order(3)
	@DisplayName("3. getOrderStatusById Test")
	public void getOrderStatusByIdTest() {
		when(oStatusServ.getOrderStatusById(1)).thenReturn(os1);
		
		assertEquals(os1, oStatusServ.getOrderStatusById(1));
		
	}
	
	@Test
	@org.junit.jupiter.api.Order(4)
	@DisplayName("4. getOrderStatusByOrderStatus Test")
	public void getOrderStatusByOrderStatusTest() {
		when(oStatusServ.getOrderStatusByOrderStatus("processed")).thenReturn(os1);
		
		assertEquals(os1, oStatusServ.getOrderStatusByOrderStatus("processed"));
	}
	
	@Test
	@org.junit.jupiter.api.Order(5)
	@DisplayName("5. updateOrderStatus Test")
	public void updateOrderStatusTest() {
		
		
		when(oStatusMockRepo.updateOrderStatus("processed",1)).thenReturn(true);
		when(oStatusServ.updateOrderStatus(os1)).thenReturn(true);
		assertEquals(true , oStatusServ.updateOrderStatus(os1));
	}
	
	@Test
	@org.junit.jupiter.api.Order(6)
	@DisplayName("6. deleteOrderStatus Test")
	public void deleteOrderStatusTest() {
		oStatusServ.deleteOrderStatus(os1);
		
		verify(oStatusMockRepo, times(1)).delete(os1);
	}
}

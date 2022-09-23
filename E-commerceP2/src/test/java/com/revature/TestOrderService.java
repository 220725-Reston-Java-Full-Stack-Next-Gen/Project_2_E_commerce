package com.revature;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


import com.revature.models.Order;
import com.revature.models.OrderStatus;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.OrderRepo;
import com.revature.services.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOrderService {

	@InjectMocks
	private static OrderServiceImpl orderServ;

	@Mock
	private static OrderRepo orderMockRepo;
	

	private static Order o1, o2;
	private static User u1 , u2;
	
	static List<Order> dummyDB;
	
	@BeforeAll
	static void setUp() throws Exception{
		orderMockRepo = Mockito.mock(OrderRepo.class);
		
		orderServ = new OrderServiceImpl(orderMockRepo);
		
		u1= new User(1,"t1","12345", "test1", "one", "test1Ave", "test1St", "test1Ct", 60412, "123456","test1@email.com", new UserRole(2,"user"), LocalDate.now(), LocalDate.now());
		u2 = new User(2,"t2","12345", "test2", "two", "test2Ave", "test2St", "test2Ct", 60417, "1234546","test2@email.com", new UserRole(2,"user"), LocalDate.now(), LocalDate.now());
		
		o1 = new Order(1,5,52.00,LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),u1,new Payment(1,"visa","pending","1234567890656",LocalDate.of(26,9,9),655, LocalDate.now(),LocalDate.now(),u1), new OrderStatus(1,"pending"));
		o2 = new Order(2,7,82.00,LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),u2,new Payment(2,"visa","pending","1234567876595",LocalDate.of(28,9,8),222, LocalDate.now(),LocalDate.now(),u2), new OrderStatus(2,"pending"));
		
		dummyDB = new ArrayList<Order>();
		dummyDB.add(o1);
		dummyDB.add(o2);
	}
	
	@Test
	@org.junit.jupiter.api.Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	public void checkMockInjection() {
		assertThat(orderMockRepo).isNotNull();
		assertThat(orderServ).isNotNull();
	}
	
	
	@Test
	@org.junit.jupiter.api.Order(2)
	@DisplayName("2. addOrder Test")
	public void addOrderTest() {
		Order o3 = new Order(3,4,32.00,LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),new User(3,"t3","12345", "test3", "three", "test3Ave", "test3St", "test3Ct", 60412, "1234234","test3@email.com", new UserRole(2,"user"), LocalDate.now(), LocalDate.now()),new Payment(3,"visa","pending","1234567876789",LocalDate.of(25,9,5),333, LocalDate.now(),LocalDate.now(),new User(3,"t3","12345", "test3", "three", "test3Ave", "test3St", "test3Ct", 60412, "1234234","test3@email.com", new UserRole(2,"user"), LocalDate.now(), LocalDate.now())), new OrderStatus(2,"pending"));
		
		when(orderMockRepo.save(o3)).thenReturn(o3);
		
		assertEquals(o3,orderServ.addOrder(o3));
		
	}
	
	@Test
	@org.junit.jupiter.api.Order(3)
	@DisplayName("3. getOrderById Test")
	public void getOrderByIdTest() {
		
		when(orderServ.getOrderById(1)).thenReturn(o1);
		assertEquals(o1, orderServ.getOrderById(1));
	}
	
	@Test
	@org.junit.jupiter.api.Order(4)
	@DisplayName("4. getOrderByOwner Test")
	public void getOrderByOrderOwnerTest() {
		
		when(orderServ.getOrdersByOrderOwner(u1)).thenReturn(dummyDB.subList(0, 1));
		
		assertEquals(dummyDB.subList(0, 1),orderServ.getOrdersByOrderOwner(u1));
	}
	
	@Test
	@org.junit.jupiter.api.Order(5)
	@DisplayName("5. getOrderByStatus Test")
	public void getOrderByStatusTest() {
		OrderStatus os1 = new OrderStatus(1,"pending");
		
		when(orderServ.getOrdersByStatus(os1)).thenReturn(dummyDB.subList(0, 1));
		
		assertEquals(dummyDB.subList(0, 1),orderServ.getOrdersByStatus(os1));
	}
	
	@Test
	@org.junit.jupiter.api.Order(6)
	@DisplayName("6. updateOrder Test")
	public void updateOrderTest() {
		o1.setOrderQuantity(2);
		o1.setOrderTotalPrice(15);
		o1.setDateModified(LocalDateTime.now());
		
		when(orderServ.updateOrder(o1)).thenReturn(1);
		
		assertEquals(1, orderServ.updateOrder(o1));
		
	}
	
	@Test
	@org.junit.jupiter.api.Order(7)
	@DisplayName("7. deleteOrder Test")
	public void deleteOrderTest() {
		
		orderServ.deleteOrder(o2);
		
		verify(orderMockRepo, times(1)).delete(o2);
	}
}

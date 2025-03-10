package com.mbm.bookingtests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import payload.order.Customer;
import payload.order.Item;
import payload.order.Order;
import payload.order.Payment;
import payload.order.ShippingAddress;

public class PostAPITest {
	@Test
	public void createOrderPayload() throws JsonProcessingException {

		List<Item> itemList = List.of(new Item("P001", "Laptop", 1, 1200.50, 100.00));

		Order order = Order.builder().orderId(123456).orderStatus("Processing").createdAt("2024-02-28T10:30:00Z")
				.isGift(false)
				.customer(new Customer("CUST123", "John Doe", "+1234567890", "john.doe@example.com",
						new ShippingAddress("123 Main St", "New York", "NY", "USA", "10001")))
				.payment(new Payment("CreditCard", "TRA123", 1200.00, "$")).items(itemList).build();
		
		Order order2 = order.toBuilder().build();
		Order order3 = order.toBuilder().createdAt("2025-03-03T10:30:00Z").build();

		// .items(new ArrayList<>(List.of(new Item("P001", "Laptop", 1, 1200.50,
		// 100.00))))
		/*
		 * Order order = new Order(); order.setOrderId(123456);
		 * order.setOrderStatus("Processing");
		 * order.setCreatedAt("2024-02-28T10:30:00Z"); order.setGift(false);
		 * 
		 * //set Customer & Address
		 * 
		 * ShippingAddress address = new ShippingAddress();
		 * address.setStreet("123 Main St"); address.setCity("New York");
		 * address.setState("NY"); address.setCountry("USA"); address.setZip("10001");
		 * 
		 * Customer customer = new Customer(); customer.setCustomerId("CUST123");
		 * customer.setName("John Doe"); customer.setPhone("+1234567890");
		 * customer.setEmail("john.doe@example.com");
		 * customer.setShippingAddress(address);
		 * 
		 * Payment payment = new Payment(); payment.setAmountPaid(1200.00);
		 * payment.setCurrency("$"); payment.setMethod("CreditCard");
		 * payment.setTransactionId("TRA123");
		 * 
		 * order.setPayment(payment);
		 * 
		 * Item item = new Item(); item.setProductId("P001"); item.setName("Laptop");
		 * item.setPrice(1200.50); item.setDiscount(100.00); item.setQuantity(1);
		 * List<Item> list = new ArrayList<>(); list.add(item); order.setItems(list);
		 * order.setCustomer(customer); //convert POJO to JSON
		 */
		ObjectMapper objectMapper = new ObjectMapper();
		String responseBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order3);
		System.out.println(responseBody);
		System.exit(0);
		baseURI = "https://api.example.com";
		given().contentType(ContentType.JSON).body(responseBody).when().post("/orders").then().statusCode(201).log()
				.body();

	}
}

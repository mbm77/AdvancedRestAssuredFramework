package payload.order;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder=true)
public class Order {
	@Builder.Default
	private int orderId = 12345;
	@Builder.Default
	private Customer customer = new Customer("CUST345", "Sing Song", "+976567565", "singsong@example.com",
			new ShippingAddress("123 Main St", "London", "London", "UK", "18881"));
	@Builder.Default
	private List<Item> items = List.of(new Item("P001", "Desktop", 1, 1200.50, 100.00));
	@Builder.Default
	private Payment payment = new Payment("DebitCard", "TRA123", 1200.00, "$");
	@Builder.Default
	private String orderStatus = "Completed";
	@Builder.Default
	private String createdAt = "2024-02-28T10:30:00Z";
	private boolean isGift = true;
	/*
	 * public int getOrderId() { return orderId; }
	 * 
	 * public void setOrderId(int orderId) { this.orderId = orderId; }
	 * 
	 * public Customer getCustomer() { return customer; }
	 * 
	 * public void setCustomer(Customer customer) { this.customer = customer; }
	 * 
	 * public Payment getPayment() { return payment; }
	 * 
	 * public void setPayment(Payment payment) { this.payment = payment; }
	 * 
	 * public List<Item> getItems() { return items; }
	 * 
	 * public void setItems(List<Item> items) { this.items = items; }
	 * 
	 * public String getOrderStatus() { return orderStatus; }
	 * 
	 * public void setOrderStatus(String orderStatus) { this.orderStatus =
	 * orderStatus; }
	 * 
	 * public String getCreatedAt() { return createdAt; }
	 * 
	 * public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
	 * 
	 * public boolean isGift() { return isGift; }
	 * 
	 * public void setGift(boolean isGift) { this.isGift = isGift; }
	 * 
	 */
}

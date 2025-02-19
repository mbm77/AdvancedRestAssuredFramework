package poijiUtils;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

import payload.Booking;
import payload.BookingDates;

public class BookingExcel {

	@ExcelRow
	private int rowIndex;

	@ExcelCellName("First Name")
	private String firstname;

	@ExcelCellName("Last Name")
	private String lastname;

	@ExcelCellName("Total Price")
	private String totalprice;

	@ExcelCellName("Deposit Paid")
	private String depositpaid;

	@ExcelCellName("Check-in Date")
	private String checkin;

	@ExcelCellName("Check-out Date")
	private String checkout;

	@ExcelCellName("Additional Needs")
	private String additionalneeds;

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getTotalprice() {
		return Integer.parseInt(totalprice);
	}

	public boolean isDepositpaid() {
		return Boolean.parseBoolean(depositpaid.toLowerCase());
	}

	public String getCheckin() {
		return checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	

	@Override
	public String toString() {
		return "Row: " + rowIndex + " | Name: " + firstname + " " + lastname + " | Price: " + totalprice
				+ " | Deposit: " + depositpaid + " | Check-in: " + checkin + " | Check-out: " + checkout
				+ " | Additional Needs " + ": " + additionalneeds;
	}

	public Booking toBooking() {
		return Booking.builder().firstname(firstname).lastname(lastname).totalprice(Integer.parseInt(totalprice))
				.depositpaid(Boolean.parseBoolean(depositpaid.toLowerCase()))
				.bookingdates(new BookingDates(checkin, checkout)).additionalneeds(additionalneeds).build();
	}
}

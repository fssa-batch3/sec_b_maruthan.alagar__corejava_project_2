package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.BillItems;
import in.fssa.mambilling.service.BillItemsService;
import in.fssa.mambilling.service.BillService;
import in.fssa.mambilling.service.PriceService;
import in.fssa.mambilling.service.ProductService;
import in.fssa.mambilling.service.UserService;

public class TestCreateBill {

	ProductService ps = new ProductService();
	PriceService priceService = new PriceService();
	UserService userservice = new UserService();
	BillService billservice = new BillService();
	BillItemsService billitemsservice = new BillItemsService();

	@Test
	public void testCreateBillWithValidData() {

		BillItems billitems1 = new BillItems(1, 1, 90);
		BillItems billitems2 = new BillItems(2, 2, 10);
		BillItems billitems3 = new BillItems(3, 3, 10);

		List<BillItems> items = new ArrayList<BillItems>();
		items.add(billitems1);
		items.add(billitems2);
		items.add(billitems3);

		assertDoesNotThrow(() -> {
			billservice.createBill(5, items);
		});

	}

	@Test
	public void testCreateBillWithInvalidData() {
		Exception exception = assertThrows(ValidationException.class, () -> {
			billservice.createBill(4, null);
		});
		String expectedMessage = "Invalid Product Details";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateBillWithInvalidUserId() {
		Exception exception = assertThrows(ValidationException.class, () -> {
			billservice.createBill(0, null);
		});
		String expectedMessage = "Invalid User ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateBillWithNotExistingUserId() {

		BillItems billitems1 = new BillItems(1, 1, 90);
		BillItems billitems2 = new BillItems(2, 2, 10);
		BillItems billitems3 = new BillItems(3, 3, 10);

		List<BillItems> items = new ArrayList<BillItems>();
		items.add(billitems1);
		items.add(billitems2);
		items.add(billitems3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			billservice.createBill(1500, items);
		});
		String expectedMessage = "User Not Exists";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}

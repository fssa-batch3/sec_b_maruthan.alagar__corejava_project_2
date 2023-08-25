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

public class TestCreateBillItems {
	ProductService ps = new ProductService();
	PriceService priceService = new PriceService();
	UserService userservice = new UserService();
	BillService billservice = new BillService();
	BillItemsService billitemsservice = new BillItemsService();

	@Test
	public void testCreateBillItemsWithValidData() {

		BillItems billitems1 = new BillItems(1, 1, 90);
		BillItems billitems2 = new BillItems(2, 2, 10);
		BillItems billitems3 = new BillItems(3, 3, 10);

		List<BillItems> items = new ArrayList<BillItems>();
		items.add(billitems1);
		items.add(billitems2);
		items.add(billitems3);

		assertDoesNotThrow(() -> {
			billitemsservice.createBillItems(5, items);
		});

	}

	@Test
	public void testCreateBillItemsWithInvalidBillId() {

		BillItems billitems1 = new BillItems(1, 1, 90);
		BillItems billitems2 = new BillItems(2, 2, 10);
		BillItems billitems3 = new BillItems(3, 3, 10);

		List<BillItems> items = new ArrayList<BillItems>();
		items.add(billitems1);
		items.add(billitems2);
		items.add(billitems3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			billitemsservice.createBillItems(-1500, items);
		});
		String expectedMessage = "Invalid Bill ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	
	@Test
	public void testCreateBillItemsWithInvalidProductData() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			billitemsservice.createBillItems(7, null);
		});
		String expectedMessage = "Invalid Product Details";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateBillItemsWithInvalidProductId() {

		BillItems billitems1 = new BillItems(-1, 1, 90);


		List<BillItems> items = new ArrayList<BillItems>();
		items.add(billitems1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			billitemsservice.createBillItems(2, items);
		});
		String expectedMessage = "Invalid Product ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateBillItemsWithNotExistingProductId() {

		BillItems billitems1 = new BillItems(1210, 1, 90);


		List<BillItems> items = new ArrayList<BillItems>();
		items.add(billitems1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			billitemsservice.createBillItems(2, items);
		});
		String expectedMessage = "Product Not found with this ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	
	@Test
	public void testCreateBillItemsWithInvalidPriceId() {

		BillItems billitems1 = new BillItems(1, -1, 90);


		List<BillItems> items = new ArrayList<BillItems>();
		items.add(billitems1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			billitemsservice.createBillItems(2, items);
		});
		String expectedMessage = "Invalid Price ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateBillItemsWithNotExistingPriceId() {

		BillItems billitems1 = new BillItems(1, 3231, 90);


		List<BillItems> items = new ArrayList<BillItems>();
		items.add(billitems1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			billitemsservice.createBillItems(2, items);
		});
		String expectedMessage = "Price Not found with this ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateBillItemsWithInvalidQuantity() {

		BillItems billitems1 = new BillItems(1, 1, 0);


		List<BillItems> items = new ArrayList<BillItems>();
		items.add(billitems1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			billitemsservice.createBillItems(2, items);
		});
		String expectedMessage = "Invalid product quantity for this product id 1";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}

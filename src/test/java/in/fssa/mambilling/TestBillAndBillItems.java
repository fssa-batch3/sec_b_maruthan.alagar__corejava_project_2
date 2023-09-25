package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.dao.GraphDAO;
import in.fssa.mambilling.exception.ServiceException;
import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.service.BillItemsService;
import in.fssa.mambilling.service.BillService;

public class TestBillAndBillItems {
	
	BillService billService = new BillService();

	@Test
	public void testFindAllBills() {

		assertDoesNotThrow(() -> {
			billService.getAllBills();
		});

	}
	
	@Test
	public void testFindAllRecentBills() {

		assertDoesNotThrow(() -> {
			billService.getAllRecentbills();
		});

	}
	
	@Test
	public void testFindAllBillsByUserNumber() {

		assertDoesNotThrow(() -> {
			billService.getAllUserbills(7810061572l);
		});

	}

	@Test
	public void testFindBillswithInvalidPhoneNumber() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			billService.getAllUserbills(0);
		});
		String expectedMessage = "Phone Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
	@Test
	public void testFindBillswithNotExistingPhoneNumber() {

		Exception exception = assertThrows(ServiceException.class, () -> {
			billService.getAllUserbills(6787776787l);
		});
		String expectedMessage = "User Not Found or Invalid Phone Number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	@Test
	public void testFindAllDateTotalAmounts() {
		GraphDAO graphDAO = new GraphDAO();
		assertDoesNotThrow(() -> {
			graphDAO.findGraphDetails();
		});

	}
	
	@Test
	public void testAllbillItemsByBillId() {
		BillItemsService bs = new BillItemsService();
		assertDoesNotThrow(() -> {
			bs.findByBillId(1);
		});

	}
	@Test
	public void testAllbillItemsByInvalidBillId() {
		BillItemsService bs = new BillItemsService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			bs.findByBillId(0);
		});
		String expectedMessage = "Invalid Bill ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
}

package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ServiceException;
import in.fssa.mambilling.exception.ValidationException;
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
			billService.getAllUserbills(6787878787l);
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
			billService.getAllUserbills(6787876787l);
		});
		String expectedMessage = "User Not Found or Invalid Phone Number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

}

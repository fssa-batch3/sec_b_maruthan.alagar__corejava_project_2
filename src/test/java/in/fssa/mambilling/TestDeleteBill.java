package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.service.BillService;

public class TestDeleteBill {

	@Test
	public void testAllbillItemsByBillId() {
		BillService bs = new BillService();
		assertDoesNotThrow(() -> {
			bs.deleteBill(23);
		});

	}
	@Test
	public void testAllbillItemsByInvalidBillId() {
		BillService bs = new BillService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			bs.deleteBill(0);
		});
		String expectedMessage = "Invalid Bill ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
}

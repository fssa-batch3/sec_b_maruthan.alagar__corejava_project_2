package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.service.ProductService;

public class TestDeleteProduct {
	ProductService productService = new ProductService();

	@Test
	public void testDeleteProductWithValidProductID() {

		assertDoesNotThrow(() -> {
			productService.deleteProduct(88);
		});

	}

	@Test
	public void testDeleteProductWithInvalidProductId() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.deleteProduct(0);
		});

		String expectedMessage = "Invalid Product ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

}

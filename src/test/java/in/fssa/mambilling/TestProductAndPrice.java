package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.service.PriceService;
import in.fssa.mambilling.service.ProductService;

public class TestProductAndPrice {
	ProductService productService = new ProductService(); 
	PriceService priceService = new PriceService();
	
	@Test
	public void testFindAllProduct() {

		assertDoesNotThrow(() -> {
			productService.getAll();
		});

	}
	
	
	@Test
	public void testFindProductWithPriceWithvalidProductID() {

		assertDoesNotThrow(() -> {
			productService.getProductDetail(2);
		});

	}
	@Test
	public void testFindProductWithPriceWithInvalidProductID() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.getProductDetail(-56666666);
		});
		String expectedMessage = "Invalid Product ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
	@Test
	public void testFindAllPrice() {

		assertDoesNotThrow(() -> {
			priceService.getAll();
		});

	}
	

}

package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.model.Product.QuantityType;
import in.fssa.mambilling.service.ShopService;

public class TestShop {
	@Test
	public void testShopFindbyIdWithValidData() {
		ShopService shopService = new ShopService();

		assertDoesNotThrow(() -> {
			shopService.getByShopId(1);
		});

	}
	@Test
	public void testFindByIDWithInvalidId() {
		ShopService shopService = new ShopService();
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.getByShopId(0);
		});
		String expectedMessage = "Invalid Shop ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	
	

}

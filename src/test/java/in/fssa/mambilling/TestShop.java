package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ValidationException;
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

	
	@Test
	public void testShopLoginWithValidData() {
		ShopService shopService = new ShopService();

		assertDoesNotThrow(() -> {
			shopService.getShopForLogin("evergreensupermarket@gmail.com", "Abcd@123");
		});

	}
	
	@Test
	public void testShopLoginWithEmailEmpty() {
		ShopService shopService = new ShopService();
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.getShopForLogin("", "Abcd@123");
		});
		String expectedMessage = "User Email cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testShopLoginWithEmailNull() {
		ShopService shopService = new ShopService();
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.getShopForLogin(null, "Abcd@123");
		});
		String expectedMessage = "User Email cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testShopLoginWithInvalidEmail() {
		ShopService shopService = new ShopService();
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.getShopForLogin("evergreensupermarket", "Abcd@123");
		});
		String expectedMessage = "User Email doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testShopLoginWithPasswordEmpty() {
		ShopService shopService = new ShopService();
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.getShopForLogin("evergreensupermarket@gmail.com", "");
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testShopLoginWithPasswordNull() {
		ShopService shopService = new ShopService();
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.getShopForLogin("evergreensupermarket@gmail.com", null);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testShopLoginWithInvalidPassword() {
		ShopService shopService = new ShopService();
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.getShopForLogin("evergreensupermarket@gmail.com", "abcd");
		});
		String expectedMessage = "Password doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	

}

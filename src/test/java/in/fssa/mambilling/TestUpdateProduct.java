package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.model.Product.QuantityType;
import in.fssa.mambilling.service.ProductService;

public class TestUpdateProduct {
	ProductService productService = new ProductService();

	@Test
	@Order(1)
	public void testUpdateProductWithValidData() {
		
		 int min = 1; // Minimum value for the random number
	        int max = 2000; // Maximum value for the random number
	        int numberOfRandomNumbers = 1000; // Set the number of random numbers you want to generate

	        Random rand = new Random();
	        int randomNumber = 0;
	        for (int i = 0; i < numberOfRandomNumbers; i++) {
	             randomNumber = rand.nextInt(max - min + 1) + min;
	           
	        }

		Price price = new Price(6, 1, 0);
		Product prod = new Product("Bun", randomNumber, QuantityType.nos, null, price);

		assertDoesNotThrow(() -> {
			productService.updateProduct(prod, 11);
		});

	}
	
	@Test
	@Order(2)
	public void testUpdateProductWithValidPrice() {
		
		 int min = 1; // Minimum value for the random number
	        int max = 7000; // Maximum value for the random number
	        int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

	        Random rand = new Random();
	        int randomNumber = 0;
	        for (int i = 0; i < numberOfRandomNumbers; i++) {
	             randomNumber = rand.nextInt(max - min + 1) + min;
	           
	        }

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Dairy Millk", randomNumber, QuantityType.nos, "choculate", price);

		assertDoesNotThrow(() -> {
			productService.updateProduct(prod,3);
		});

	}
	
	@Test
	@Order(3)
	public void testUpdateProductWithNotExistingProductDetails() {
		
		 int min = 1; // Minimum value for the random number
	        int max = 20009; // Maximum value for the random number
	        int numberOfRandomNumbers = 1000; // Set the number of random numbers you want to generate

	        Random rand = new Random();
	        int randomNumber = 0;
	        for (int i = 0; i < numberOfRandomNumbers; i++) {
	             randomNumber = rand.nextInt(max - min + 1) + min;
	           
	        }

		
		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Soap", randomNumber, QuantityType.nos, "Arisi", price);
		
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod, 12225);
		});
		String expectedMessage = "Product Doesn't Exists";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(4)
	public void testUpdateProductWithInvalidProductID() {

		
		Price price = new Price(1200, 1, 0);
		Product prod = new Product("RiIIce", 150, QuantityType.g, "Arisi", price);
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod, 0);
		});
		String expectedMessage = "Invalid Product ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	@Order(5)
	public void testUpdateProductWithProductNull() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(null, 1);
		});
		String expectedMessage = "Invalid Product Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(6)
	public void testUpdateProductWithProductNameEmpty() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("", 10, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod,2);
		});

		String expectedMessage = "Product Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(7)
	public void testUpdateProductWithProductNameNull() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product(null, 1000, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod,4);
		});

		String expectedMessage = "Product Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(8)
	public void testUpdateProductWithSpecialNameEmpty() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("PeaAce", 1000, QuantityType.g, "", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod,6);
		});

		String expectedMessage = "Special Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(9)
	public void testUpdateProductWithInvalidQuantity() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("RicXe", 0, QuantityType.g, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod,5);
		});

		String expectedMessage = "Invalid Product Quantity";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(10)
	public void testUpdateProductWithQuantityTypeNull() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("RiXce", 1, null, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod,12);
		});

		String expectedMessage = "Invalid Product Quantity Type";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(11)
	public void testUpdateProductWithExistingProductDetails() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Soap", 1, QuantityType.nos, null, price);
		

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod,6);
		});

		String expectedMessage = "Product Already Exists";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}



	@Test
	@Order(12)
	public void testUpdateProductWithPriceNull() {

		Product prod = new Product("Ribce", 81, QuantityType.g, "Arisi", null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod, 3);
		});

		String expectedMessage = "Invalid Price Deatils";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(13)
	public void testUpdateProductWithInvalidMrp() {
		 int min = 1; // Minimum value for the random number
	        int max = 2000; // Maximum value for the random number
	        int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

	        Random rand = new Random();
	        int randomNumber = 0;
	        for (int i = 0; i < numberOfRandomNumbers; i++) {
	             randomNumber = rand.nextInt(max - min + 1) + min;
	           
	        }
		
		

		Price price = new Price(-1, 1, 0);
		Product prod = new Product("Riceebeeeee", randomNumber, QuantityType.g, "ArIsi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod,5);
		});

		String expectedMessage = "MRP must be greater than 0";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(14)
	public void testUpdateProductWithInvalidTax() {
		
		 int min = 1; // Minimum value for the random number
	        int max = 2000; // Maximum value for the random number
	        int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

	        Random rand = new Random();
	        int randomNumber = 0;
	        for (int i = 0; i < numberOfRandomNumbers; i++) {
	             randomNumber = rand.nextInt(max - min + 1) + min;
	           
	        }

		Price price = new Price(100, -1, 1);
		Product prod = new Product("Ricvee", randomNumber, QuantityType.g, "arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod,3);
		});

		String expectedMessage = "Tax must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(15)
	public void testUpdateProductWithInvalidDiscount() {
		
		 int min = 1; // Minimum value for the random number
	        int max = 2000; // Maximum value for the random number
	        int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

	        Random rand = new Random();
	        int randomNumber = 0;
	        for (int i = 0; i < numberOfRandomNumbers; i++) {
	             randomNumber = rand.nextInt(max - min + 1) + min;
	           
	        }


		Price price = new Price(100, 1, -1);
		Product prod = new Product("Ricvcee", randomNumber, QuantityType.g, "Arisii", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.updateProduct(prod,4);
		});

		String expectedMessage = "Discount must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

}

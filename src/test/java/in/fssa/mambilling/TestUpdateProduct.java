package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.model.Product.QuantityType;
import in.fssa.mambilling.service.ProductService;

public class TestUpdateProduct {
	ProductService productService = new ProductService();

	@Test
	public void testUpdateProductWithValidData() {

		Price price = new Price(6, 1, 0);
		Product prod = new Product("Breads", 1090, QuantityType.nos, null, price);

		assertDoesNotThrow(() -> {
			productService.update(prod, 1);
		});

	}
	
	@Test
	public void testUpdateProductWithNotExistingProductDetails() {

		
		Price price = new Price(1200, 1, 0);
		Product prod = new Product("White Sugar", 143, QuantityType.g, "Arisi", price);
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod, 95);
		});
		String expectedMessage = "Product Doesn't Exists";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductWithInvalidProductID() {

		
		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Rice", 150, QuantityType.g, "Arisi", price);
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod, 0);
		});
		String expectedMessage = "Invalid Product ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateProductWithProductNull() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(null, 1);
		});
		String expectedMessage = "Invalid Product Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateProductWithProductNameEmpty() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("", 10, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,2);
		});

		String expectedMessage = "Product Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateProductWithProductNameNull() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product(null, 1000, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,4);
		});

		String expectedMessage = "Product Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateProductWithSpecialNameEmpty() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Peace", 1000, QuantityType.g, "", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,6);
		});

		String expectedMessage = "Special Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateProductWithInvalidQuantity() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Rice", 0, QuantityType.g, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,5);
		});

		String expectedMessage = "Invalid Product Quantity";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateProductWithQuantityTypeNull() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Rice", 1, null, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,2);
		});

		String expectedMessage = "Invalid Product Quantity Type";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpadetProductWithExistingProductDetails() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Rice", 60, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,2);
		});

		String expectedMessage = "Product Already Exists";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateProductWithValidPrice() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("milk", 500, QuantityType.ml, "Arisi", price);

		assertDoesNotThrow(() -> {
			productService.update(prod,3);
		});

	}

	@Test
	public void testUpdateProductWithPriceNull() {

		Product prod = new Product("Rice", 1, QuantityType.g, "Arisi", null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod, 3);
		});

		String expectedMessage = "Invalid Price Deatils";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateProductWithInvalidMrp() {

		Price price = new Price(-1, 1, 0);
		Product prod = new Product("Riceeeeeee", 109, QuantityType.g, "ArIsi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,5);
		});

		String expectedMessage = "MRP must be greater than 0";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateProductWithInvalidTax() {

		Price price = new Price(100, -1, 1);
		Product prod = new Product("Ricee", 14, QuantityType.g, "arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,3);
		});

		String expectedMessage = "Tax must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateProductWithInvalidDiscount() {

		Price price = new Price(100, 1, -1);
		Product prod = new Product("Ricee", 30, QuantityType.g, "Arisii", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,4);
		});

		String expectedMessage = "Discount must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

}

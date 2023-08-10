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

public class TestCreateProduct {

	ProductService productService = new ProductService();

	@Test
	public void testCreateProductWithValidData() {

		Price price = new Price(12, 1, 0);
		Product prod = new Product("Soap", 1, QuantityType.nos, null, price);

		assertDoesNotThrow(() -> {
			productService.create(prod);
		});

	}

	@Test
	public void testCreateProductWithInvalidData() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(null);
		});
		String expectedMessage = "Invalid Product Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWithProductNameEmpty() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("", 1000, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Product Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductWithProductNameNull() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product(null, 1000, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Product Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductWithSpecialNameEmpty() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Rice", 1000, QuantityType.g, "", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Special Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductWithInvalidQuantity() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Rice", 0, QuantityType.g, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Invalid Product Quantity";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductWithQuantityTypeNull() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Rice", 1, null, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Invalid Product Quantity Type";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductWithExistingProductDetails() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Rice", 1000, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Product Already Exists";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductWithValidPrice() {

		Price price = new Price(30, 1, 0);
		Product prod = new Product("White Sugar", 1, QuantityType.g, null, price);

		assertDoesNotThrow(() -> {
			productService.create(prod);
		});

	}

	@Test
	public void testCreateProductWithPriceNull() {

		Product prod = new Product("Tea Powder", 1, QuantityType.g, "3 roses", null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Invalid Price Deatils";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductWithInvalidMrp() {

		Price price = new Price(-1, 1, 0);
		Product prod = new Product("Note", 1, QuantityType.nos, null, price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "MRP must be greater than 0";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductWithInvalidTax() {

		Price price = new Price(100, -1, 1);
		Product prod = new Product("Book", 1, QuantityType.g, "Story Book", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Tax must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateProductWithInvalidDiscount() {

		Price price = new Price(100, 1, -1);
		Product prod = new Product("Basmati Rice", 1, QuantityType.g, "Basmathi Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Discount must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
}

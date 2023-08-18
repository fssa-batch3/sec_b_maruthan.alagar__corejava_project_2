package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.model.Product.QuantityType;
import in.fssa.mambilling.service.ProductService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCreateProduct {

	ProductService productService = new ProductService();

	

	@Test
	@Order(1)
	public void testCreateProductWithValidData() {

		Price price = new Price(12, 1, 0);
		Product prod = new Product("Soaps", 785, QuantityType.nos, null, price);

		assertDoesNotThrow(() -> {
			productService.create(prod);
		});

	}

	@Test
	@Order(2)
	public void testCreateProductWithInvalidData() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(null);
		});
		String expectedMessage = "Invalid Product Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(3)
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
	@Order(4)
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
	@Order(5)
	public void testCreateProductWithSpecialNameEmpty() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Ricee", 1000, QuantityType.g, "", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Special Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(6)
	public void testCreateProductWithInvalidQuantity() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Riceee", 0, QuantityType.g, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Invalid Product Quantity";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(7)
	public void testCreateProductWithQuantityTypeNull() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Riceee", 1, null, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Invalid Product Quantity Type";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(8)
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
	@Order(9)
	public void testCreateProductWithValidPrice() {

		Price price = new Price(30, 1, 0);
		Product prod = new Product("Jagguhy", 71, QuantityType.g, null, price);

		assertDoesNotThrow(() -> {
			productService.create(prod);
		});

	}

	@Test
	@Order(10)
	public void testCreateProductWithPriceNull() {

		Product prod = new Product("Tea Powder", 1782, QuantityType.g, null, null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Invalid Price Deatils";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(11)
	public void testCreateProductWithInvalidMrp() {

		Price price = new Price(-1, 1, 0);
		Product prod = new Product("Notes", 71, QuantityType.nos, null, price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "MRP must be greater than 0";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(12)
	public void testCreateProductWithInvalidTax() {

		Price price = new Price(100, -1, 1);
		Product prod = new Product("Book", 13, QuantityType.g, "StoRy Book", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Tax must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(13)
	public void testCreateProductWithInvalidDiscount() {

		Price price = new Price(100, 1, -1);
		Product prod = new Product("Basmati Rice", 14, QuantityType.g, "Basmathi Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(prod);
		});

		String expectedMessage = "Discount must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
}

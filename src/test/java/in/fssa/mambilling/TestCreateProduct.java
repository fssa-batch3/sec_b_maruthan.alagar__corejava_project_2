 package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import in.fssa.mambilling.exception.ValidationException;
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

		int min = 1; // Minimum value for the random number
		int max = 10000; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		int randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextInt(max - min + 1) + min;

		}

		Price price = new Price(12, 1, 0);
		Product prod = new Product("Sofas", randomNumber, QuantityType.nos, null, price);

		assertDoesNotThrow(() -> {
			productService.createProduct(prod);
		});

	}

	@Test
	@Order(2)
	public void testCreateProductWithValidPrice() {

		int min = 1; // Minimum value for the random number
		int max = 2000; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		int randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextInt(max - min + 1) + min;

		}

		Price price = new Price(30, 1, 0);
		Product prod = new Product("VelLlam", randomNumber, QuantityType.g, null, price);

		assertDoesNotThrow(() -> {
			productService.createProduct(prod);
		});

	}

	@Test
	@Order(3)
	public void testCreateProductWithInvalidData() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(null);
		});
		String expectedMessage = "Invalid Product Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(4)
	public void testCreateProductWithProductNameEmpty() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("", 1090, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "Product Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(5)
	public void testCreateProductWithProductNameNull() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product(null, 1000, QuantityType.g, "Pachai Rice", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "Product Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(6)
	public void testCreateProductWithSpecialNameEmpty() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("RRicee", 1000, QuantityType.g, "", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "Special Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(7)
	public void testCreateProductWithInvalidQuantity() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("RiScce", 0, QuantityType.g, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "Invalid Product Quantity";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(8)
	public void testCreateProductWithQuantityTypeNull() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Ricceee", 1, null, "Arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "Invalid Product Quantity Type";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(9)
	public void testCreateProductWithExistingProductDetails() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("Sofas", 1690, QuantityType.nos, "Sofa seater", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "Product Already Exists";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(10)
	public void testCreateProductWithPriceNull() {

		Product prod = new Product("TeEaPPowder", 1782, QuantityType.g, null, null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "Invalid Price Deatils";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(11)
	public void testCreateProductWithInvalidMrp() {

		int min = 1; // Minimum value for the random number
		int max = 10000; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		int randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextInt(max - min + 1) + min;

		}

		Price price = new Price(-1, 1, 0);
		Product prod = new Product("NNkotes", randomNumber, QuantityType.nos, null, price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "MRP must be greater than 0";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(12)
	public void testCreateProductWithInvalidTax() {

		int min = 1; // Minimum value for the random number
		int max = 10000; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		int randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextInt(max - min + 1) + min;

		}

		Price price = new Price(100, -1, 1);
		Product prod = new Product("Booo oeoook", randomNumber, QuantityType.g, "StoRy Book", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "Tax must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(13)
	public void testCreateProductWithInvalidDiscount() {

		int min = 1; // Minimum value for the random number
		int max = 10000; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		int randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextInt(max - min + 1) + min;

		}

		Price price = new Price(100, 1, -1);
		Product prod = new Product("Basmati RRRice", randomNumber, QuantityType.g, "Basmath iArisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(prod);
		});

		String expectedMessage = "Discount must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
}

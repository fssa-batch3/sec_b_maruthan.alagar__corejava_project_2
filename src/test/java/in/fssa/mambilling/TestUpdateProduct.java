package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.model.Product.QuantityType;
import in.fssa.mambilling.service.ProductService;

public class TestUpdateProduct {
	ProductService productService = new ProductService();

	@Test
	@Order(1)
	public void testUpdateProductWithValidData() {

		Price price = new Price(6, 1, 0);
		Product prod = new Product("BreDDads", 19670, QuantityType.nos, null, price);

		assertDoesNotThrow(() -> {
			productService.update(prod, 1);
		});

	}
	
	@Test
	@Order(2)
	public void testUpdateProductWithValidPrice() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("mMilKKss", 590, QuantityType.ml, "Arisi", price);

		assertDoesNotThrow(() -> {
			productService.update(prod,3);
		});

	}
	
	@Test
	@Order(3)
	public void testUpdateProductWithNotExistingProductDetails() {

		
		Price price = new Price(1200, 1, 0);
		Product prod = new Product("WhiIte Sugar", 143, QuantityType.g, "Arisi", price);
		
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod, 95);
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
			productService.update(prod, 0);
		});
		String expectedMessage = "Invalid Product ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	@Order(5)
	public void testUpdateProductWithProductNull() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(null, 1);
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
			productService.update(prod,2);
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
			productService.update(prod,4);
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
			productService.update(prod,6);
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
			productService.update(prod,5);
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
			productService.update(prod,2);
		});

		String expectedMessage = "Invalid Product Quantity Type";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(11)
	public void testUpadetProductWithExistingProductDetails() {

		Price price = new Price(1200, 1, 0);
		Product prod = new Product("BreDDads", 19670, QuantityType.nos, null, price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,2);
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
			productService.update(prod, 3);
		});

		String expectedMessage = "Invalid Price Deatils";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(13)
	public void testUpdateProductWithInvalidMrp() {

		Price price = new Price(-1, 1, 0);
		Product prod = new Product("Riceebeeeee", 109, QuantityType.g, "ArIsi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,5);
		});

		String expectedMessage = "MRP must be greater than 0";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(14)
	public void testUpdateProductWithInvalidTax() {

		Price price = new Price(100, -1, 1);
		Product prod = new Product("Ricvee", 14, QuantityType.g, "arisi", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,3);
		});

		String expectedMessage = "Tax must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	@Order(15)
	public void testUpdateProductWithInvalidDiscount() {

		Price price = new Price(100, 1, -1);
		Product prod = new Product("Ricvcee", 30, QuantityType.g, "Arisii", price);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.update(prod,4);
		});

		String expectedMessage = "Discount must be between 0 and 100";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

}

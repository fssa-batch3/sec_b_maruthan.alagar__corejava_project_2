package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Shop;
import in.fssa.mambilling.service.ShopService;

public class TestUpdateShop {
	@Test
	public void testUpdateShopWithValidData() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();

		// Generate a random phone number
		long min = 6000000001L; // Minimum value for the random number
		long max = 9999999999L; // Maximum value for the random number
		Random rand = new Random();
		long phoneNumber = rand.nextLong(max - min + 1) + min;

		newShop.setShopName("EverGreen Supermarket");
		newShop.setLicenseNumber("23784894098987");
		newShop.setGSTNNumber("33CCCEV7409R1Z8");
		newShop.setPhoneNumber(phoneNumber);
		newShop.setEmail("evergreensupermarket@gmail.com");
		newShop.setAddress("789 Nheru St, Trichy");
		newShop.setOwnerName("Rahu Raman");
		newShop.setPrintName("Evergreen");
		newShop.setPassword("Ever@1234");
		newShop.setId(1);

		assertDoesNotThrow(() -> {
			shopService.updateShop(newShop);
			
		});
	}

	@Test
	public void testUpdateShopWithInvalidData() {
		ShopService shopService = new ShopService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(null);
		});
		String expectedMessage = "Invalid Shop Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithInvalidLicenceNumber() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber("7688767shdjhbdcvb");
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setPhoneNumber(7810061572l);
		newShop.setEmail("example@example.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "License Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithEmptyLicenceNumber() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber("");
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setPhoneNumber(7810061572l);
		newShop.setEmail("example@example.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "License Number cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithNullLicenceNumber() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber(null);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setPhoneNumber(7810061572l);
		newShop.setEmail("example@example.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "License Number cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithInvalidGSTNNumber() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setGSTNNumber("InvalidGSTNNumber");
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber("23784894098987");
		newShop.setPhoneNumber(7810061572l);
		newShop.setEmail("example@example.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "GSTN Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithEmptyGSTNNumber() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setGSTNNumber("");
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber("23784894098987");
		newShop.setPhoneNumber(7810061572l);
		newShop.setEmail("example@example.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "GSTN Number cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithNullGSTNNumber() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setGSTNNumber(null);
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber("23784894098987");
		newShop.setPhoneNumber(7810061572l);
		newShop.setEmail("example@example.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "GSTN Number cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithInvalidPhoneNumber() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPhoneNumber(123456789);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@example.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Phone Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithInvalidEmail() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Email doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithEmptyEmail() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Email cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithNullEmail() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setShopName("Example Shop");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail(null);
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Email cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithInvalidShopName() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setShopName("we7667623yjgsf");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Shop Name doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithEmptyShopName() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setShopName("");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Shop Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithNullShopName() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setShopName(null);
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setAddress("Sample Address");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Shop Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithEmptyAddress() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setAddress("");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithNullAddress() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setAddress(null);
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setOwnerName("Maruthan");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");
		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithEmptyOwnerName() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setOwnerName("");
		newShop.setAddress("chennai");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Owner Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithNullOwnerName() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setOwnerName(null);
		newShop.setAddress("chennai");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Owner Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithInvalidOwnerName() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setOwnerName("376truigdjhfbjh3e7");
		newShop.setAddress("chennai");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setPrintName("Print Name");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Owner Name doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithEmptyPrintName() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPrintName("");
		newShop.setOwnerName("Maruthan");
		newShop.setAddress("chennai");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Print Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithNullPrintName() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setOwnerName(null);
		newShop.setAddress("chennai");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setPrintName(null);
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Print Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithInvalidPrintName() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPrintName("65568jhgjhgt");
		newShop.setOwnerName("Maruthan");
		newShop.setAddress("chennai");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setPassword("Evt@12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Print Name doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithInvalidPassword() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPrintName("Print Name");
		newShop.setOwnerName("Maruthan");
		newShop.setAddress("chennai");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setPassword("vsdwsd12345");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Password doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithNullPassword() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPrintName("Print Name");
		newShop.setOwnerName("Maruthan");
		newShop.setAddress("chennai");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setPassword(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateShopWithEmptyPassword() {
		ShopService shopService = new ShopService();

		Shop newShop = new Shop();
		newShop.setPrintName("Print name");
		newShop.setOwnerName("Maruthan");
		newShop.setAddress("chennai");
		newShop.setShopName("Example Shop");
		newShop.setPhoneNumber(9234567890l);
		newShop.setGSTNNumber("12ABCDE1234F1Z5");
		newShop.setLicenseNumber("23784894098987");
		newShop.setEmail("example@gmail.com");
		newShop.setPassword("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			shopService.updateShop(newShop);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}

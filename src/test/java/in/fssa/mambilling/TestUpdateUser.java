package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.User;
import in.fssa.mambilling.service.UserService;

public class TestUpdateUser {
	@Test
	public void testCreateUserWithValidData() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("Maruthan");
		newUser.setPhoneNumber(8978675645l);
		newUser.setEmail("maruthudivyaalagarchittu@gmail.com");
		newUser.setAddress("Pudukkottai");

		assertDoesNotThrow(() -> {
			userService.updateUser(8978675645l , newUser);
		});

	}

	@Test
	public void testUpdateUserWithInvalidData() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(0,null);
		});
		String expectedMessage = "Invalid User Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUserWithEmailNull() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(6787879878l);
		newUser.setEmail(null);
		newUser.setAddress("Pudukkottai");

		assertDoesNotThrow(() -> {
			userService.updateUser(6787879878l,newUser);
		});

	}

	@Test
	public void testUpdateUserWithEmailEmpty() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("Maruthan");
		newUser.setPhoneNumber(6787879878l);
		newUser.setEmail("");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(6787879878l,newUser);

		});
		String expectedMessage = "Email cannot be Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateUserWithInvalidEmail() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(6787879878l);
		newUser.setEmail("Amaruthan6767");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(6787879878l,newUser);

		});
		String expectedMessage = "Email doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	
	@Test
	public void testUpdateUserWithInvalidNewPhoneNumber() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(0);
		newUser.setEmail("maruthanalagar@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(6787879878l,newUser);

		});
		String expectedMessage = "New Phone Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateUserWithInvalidPhoneNumber() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(6787879878l);
		newUser.setEmail("maruthanalagar@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(0,newUser);

		});
		String expectedMessage = "Phone Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateUserWithNotExistingPhoneNumber() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(6787879878l);
		newUser.setEmail("maruthanalagar@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(7810061562l,newUser);

		});
		String expectedMessage = "User Not Exists With this Number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateUserWithExistingNewPhoneNumber() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(6787879878l);
		newUser.setEmail("maruthanalagar@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(6787878787l,newUser);

		});
		String expectedMessage = "User Already Exists With this New Number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateUserWithInvalidNewPhoneNumberPattern() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(1234567890l);
		newUser.setEmail("maruthanalagar@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(6787878787l,newUser);

		});
		String expectedMessage = "New Phone Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateUserWithInvalidPhoneNumberPattern() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(8234567890l);
		newUser.setEmail("maruthanalagar@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(1234567890l,newUser);

		});
		String expectedMessage = "Phone Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	

	
	@Test
	public void testUpdateUserWithNameNull() {
		UserService userService = new UserService();

		User newUser = new User();


		long min = 6000000001l; // Minimum value for the random number
		long max = 9999999999l; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		long randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextLong(max - min + 1) + min;

		}

		newUser.setName(null);
		newUser.setPhoneNumber(randomNumber);
		newUser.setEmail("someexamble@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(6787878787l,newUser);

		});
		String expectedMessage = "Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateUserWithNameEmpty() {
		UserService userService = new UserService();

		User newUser = new User();


		long min = 6000000001l; // Minimum value for the random number
		long max = 9999999999l; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		long randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextLong(max - min + 1) + min;

		}

		newUser.setName("");
		newUser.setPhoneNumber(randomNumber);
		newUser.setEmail("someexamble@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(6787878787l,newUser);

		});
		String expectedMessage = "Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateUserWithInvalidNamePattern() {
		UserService userService = new UserService();

		User newUser = new User();


		long min = 6000000001l; // Minimum value for the random number
		long max = 9999999999l; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		long randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextLong(max - min + 1) + min;

		}

		newUser.setName("Maruthan1234");
		newUser.setPhoneNumber(randomNumber);
		newUser.setEmail("someexamble@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(6787878787l,newUser);

		});
		String expectedMessage = "Name doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateUserWithAddressEmpty() {
		UserService userService = new UserService();

		User newUser = new User();


		long min = 6000000001l; // Minimum value for the random number
		long max = 9999999999l; // Maximum value for the random number
		int numberOfRandomNumbers = 100; // Set the number of random numbers you want to generate

		Random rand = new Random();
		long randomNumber = 0;
		for (int i = 0; i < numberOfRandomNumbers; i++) {
			randomNumber = rand.nextLong(max - min + 1) + min;

		}

		newUser.setName("Maruthan");
		newUser.setPhoneNumber(randomNumber);
		newUser.setEmail("someexamble@gmail.com");
		newUser.setAddress(" ");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.updateUser(6787878787l,newUser);

		});
		String expectedMessage = "Address cannot be Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateUserWithAddressNull() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(6787878787l);
		newUser.setEmail("sample@gmail.com");
		newUser.setAddress(null);

		assertDoesNotThrow(() -> {
			userService.updateUser(6787878787l,newUser);
		});

	}
	
	
	
	@Test
	public void testUpdateUserWithAddressAndEmailNull() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setName("Maruthan");
		newUser.setPhoneNumber(6787878787l);
		newUser.setEmail(null);
		newUser.setAddress(null);

		assertDoesNotThrow(() -> {
			userService.updateUser(6787878787l,newUser);
		});

	}


}


package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.User;
import in.fssa.mambilling.service.UserService;

public class TestCreateUser {
	@Test
	public void testCreateUserWithValidData() {
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
		newUser.setEmail("maruthudivyaalagarchittu@gmail.com");
		newUser.setAddress("Pudukkottai");

		assertDoesNotThrow(() -> {
			userService.createUser(newUser);
		});

	}

	@Test
	public void testCreateUserWithInvalidData() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(null);
		});
		String expectedMessage = "Invalid User Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithEmailNull() {
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
		newUser.setEmail(null);
		newUser.setAddress("Pudukkottai");

		assertDoesNotThrow(() -> {
			userService.createUser(newUser);
		});

	}

	@Test
	public void testCreateUserWithEmailEmpty() {
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
		newUser.setEmail("");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.createUser(newUser);

		});
		String expectedMessage = "Email cannot be Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testCreateUserWithInvalidEmail() {
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
		newUser.setEmail("Amaruthan6767");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.createUser(newUser);

		});
		String expectedMessage = "Email doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	
	@Test
	public void testCreateUserWithInvalidPhoneNumber() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(0);
		newUser.setEmail("maruthanalagar@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.createUser(newUser);

		});
		String expectedMessage = "Phone Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithInvalidPhoneNumberPattern() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(1234567890l);
		newUser.setEmail("maruthanalagar@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.createUser(newUser);

		});
		String expectedMessage = "Phone Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithExistingUserDetails() {
		UserService userService = new UserService();

		User newUser = new User();


		newUser.setName("Maruthan");
		newUser.setPhoneNumber(7810061572l);
		newUser.setEmail("maruthanalagar@gmail.com");
		newUser.setAddress("Pudukkottai");

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.createUser(newUser);

		});
		String expectedMessage = "User Already Exists";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithNameNull() {
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

			userService.createUser(newUser);

		});
		String expectedMessage = "Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithNameEmpty() {
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

			userService.createUser(newUser);

		});
		String expectedMessage = "Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithInvalidNamePattern() {
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

			userService.createUser(newUser);

		});
		String expectedMessage = "Name doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testCreateUserWithAddressEmpty() {
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

			userService.createUser(newUser);

		});
		String expectedMessage = "Address cannot be Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testCreateUserWithAddressNull() {
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
		newUser.setEmail("sample@gmail.com");
		newUser.setAddress(null);

		assertDoesNotThrow(() -> {
			userService.createUser(newUser);
		});

	}
	
	
	
	@Test
	public void testCreateUserWithAddressAndEmailNull() {
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
		newUser.setEmail(null);
		newUser.setAddress(null);

		assertDoesNotThrow(() -> {
			userService.createUser(newUser);
		});

	}


}

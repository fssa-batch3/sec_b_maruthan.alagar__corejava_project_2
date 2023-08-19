package in.fssa.mambilling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.service.UserService;

public class TestUser {

	UserService userService = new UserService();

	@Test
	public void testFindAllUser() {

		assertDoesNotThrow(() -> {
			userService.getAllUsers();
		});

	}

	@Test
	public void testFindWithValidPhoneNumber() {

		assertDoesNotThrow(() -> {
			userService.getByPhoneNumber(6787879878l);
		});

	}

	@Test
	public void testFindwithInvalidPhoneNumber() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.getByPhoneNumber(-56666666l);
		});
		String expectedMessage = "Phone Number doesn't match the Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

}

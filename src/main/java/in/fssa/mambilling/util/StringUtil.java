package in.fssa.mambilling.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.mambilling.exception.ValidationException;

/**
 * The StringUtil class provides utility methods for working with strings and
 * performing string validations.
 */
public class StringUtil {
	/**
	 * Validates if a string is null or empty and throws a ValidationException if it
	 * is.
	 *
	 * @param input     The input string to validate.
	 * @param inputName The name of the input parameter for error messages.
	 * @throws ValidationException If the input string is null or empty.
	 */
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be Null or Empty"));

		}

		rejectIfInvalidName(input, inputName);

	}

	/**
	 * Validates if a string contains only letters and spaces using a regular
	 * expression pattern.
	 *
	 * @param name      The string to validate.
	 * @param nameInput The name of the string parameter for error messages.
	 * @throws ValidationException If the string doesn't match the pattern.
	 */
	public static void rejectIfInvalidName(String name, String nameInput) throws ValidationException {

		String regexPattern = "^[a-zA-Z ]+$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(name);

		if (!matcher.matches()) {
			throw new ValidationException(nameInput.concat(" doesn't match the Pattern"));
		}
	}

	/**
	 * Checks if a string is valid (not null and not empty).
	 *
	 * @param newString The string to check for validity.
	 * @return True if the string is valid; otherwise, false.
	 */
	public static boolean isValidString(String newString) {

		if (newString == null || "".equals(newString.trim())) {

			return false;
		}
		return true;

	}

	/**
	 * Checks if a string is invalid (null or empty).
	 *
	 * @param newString The string to check for invalidity.
	 * @return True if the string is invalid; otherwise, false.
	 */
	public static boolean isInvalidString(String newString) {

		if (!isValidString(newString)) {

			return true;
		}
		return false;

	}

}

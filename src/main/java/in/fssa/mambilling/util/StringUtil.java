package in.fssa.mambilling.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.mambilling.Exception.ValidationException;


public class StringUtil {

	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be Null or Empty"));
			
		}
		
		rejectIfInvalidName(input,inputName);
		
		
	}
	
	public static void rejectIfInvalidName(String name,String nameInput) throws ValidationException {

		String regexPattern = "[A-Za-z]+(\\s[A-Za-z]+)*";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(name);

		if (!matcher.matches()) {
			throw new ValidationException(nameInput.concat(" doesn't match the Pattern"));
		}
	}


	public static boolean isValidString(String newString) {

		if (newString == null || "".equals(newString.trim())) {

			return false;
		}
		return true;

	}

	public static boolean isInvalidString(String newString) {

		if (!isValidString(newString)) {

			return true;
		}
		return false;

	}

}


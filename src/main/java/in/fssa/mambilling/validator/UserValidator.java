package in.fssa.mambilling.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.dao.UserDAO;
import in.fssa.mambilling.model.User;
import in.fssa.mambilling.util.StringUtil;

public class UserValidator {

	public static void validate(User newUser) throws ValidationException {

		if (newUser == null) {
			throw new ValidationException("Invalid User Input");
		}

		StringUtil.rejectIfInvalidString(newUser.getName(), "Name");
		validatePhoneNumber(newUser.getPhoneNumber(), "Phone Number");

		if (newUser.getEmail() != null) {
			ValidateEmail(newUser.getEmail(), "Email");
		}
		if (newUser.getAddress() != null) {
			ValidateAddress(newUser.getAddress(), "Address");
		}

		UserDAO userdao = new UserDAO();
		try {
			User existingCheckUser = userdao.findByPhoneNumber(newUser.getPhoneNumber());

			if (existingCheckUser != null) {
				throw new ValidationException("User Already Exists");
			}
		} catch (PersistanceException e) {

			throw new ValidationException(e.getMessage());
		}

	}

	public static void validateUpdate(long phoneNumber, User newUser) throws ValidationException {

		if (newUser == null) {
			throw new ValidationException("Invalid User Input");
		}

		validatePhoneNumber(phoneNumber, "Phone Number");
		validatePhoneNumber(newUser.getPhoneNumber(), "New Phone Number");
		StringUtil.rejectIfInvalidString(newUser.getName(), "Name");

		if (newUser.getEmail() != null) {
			ValidateEmail(newUser.getEmail(), "Email");
		}

		if (newUser.getAddress() != null) {
			ValidateAddress(newUser.getAddress(), "Address");
		}
		UserDAO userdao = new UserDAO();
		try {

			User CheckExistOrNot = userdao.findByPhoneNumber(phoneNumber);

			if (CheckExistOrNot == null) {
				throw new ValidationException("User Not Exists With this Number");
			}
			User AlreadyExistsOrNot = null;

			if (phoneNumber != newUser.getPhoneNumber()) {
				AlreadyExistsOrNot = userdao.findByPhoneNumber(newUser.getPhoneNumber());

			}

			if (AlreadyExistsOrNot != null) {
				throw new ValidationException("User Already Exists With this New Number");
			}

		} catch (PersistanceException e) {

			throw new ValidationException(e.getMessage());
		}

	}

	public static void validatePhoneNumber(long phoneNumber, String inputName) throws ValidationException {
		String regexPattern = "^[6-9]\\d{9}$";

		String number = Long.toString(phoneNumber);
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(number);
		if (!matcher.matches()) {
			throw new ValidationException(inputName.concat(" doesn't match the Pattern"));
		}
	}

	public static void ValidateAddress(String address, String inputName) throws ValidationException {
		if ("".equals(address.trim())) {
			throw new ValidationException(inputName.concat(" cannot be Empty"));

		}

		String regexPattern = "^[A-Za-z0-9\\s.,-]+$";

		Pattern pattern = Pattern.compile(regexPattern);

		Matcher matcher = pattern.matcher(address);

		if (!matcher.matches()) {
			throw new ValidationException(inputName.concat(" doesn't match the Pattern"));
		}

	}

	public static void ValidateEmail(String email, String inputName) throws ValidationException {

		if ("".equals(email.trim())) {
			throw new ValidationException(inputName.concat(" cannot be Empty"));

		}

		String regexPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			throw new ValidationException(inputName.concat(" doesn't match the Pattern"));
		}
	}

}

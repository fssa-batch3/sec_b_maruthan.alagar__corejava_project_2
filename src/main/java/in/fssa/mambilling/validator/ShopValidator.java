package in.fssa.mambilling.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Shop;
import in.fssa.mambilling.util.StringUtil;

/**
 * The ShopValidator class provides methods for validating shop-related
 * operations.
 */
public class ShopValidator {
	/**
	 * Validates the creation of a new shop.
	 *
	 * @param newShop The new shop to validate.
	 * @throws ValidationException If any validation errors are encountered.
	 */
	public static void validate(Shop newShop) throws ValidationException {
		if (newShop == null) {
			throw new ValidationException("Invalid Shop Input");
		}

		StringUtil.rejectIfInvalidString(newShop.getShopName(), "Shop Name");
		StringUtil.rejectIfInvalidString(newShop.getPrintName(), "Print Name");
		StringUtil.rejectIfInvalidString(newShop.getOwnerName(), "Owner Name");

		validateLicenceNumber(newShop.getLicenseNumber());
		validateGSTNNumber(newShop.getGSTNNumber());
		validateEmail(newShop.getEmail(), "Email");
		validateAddress(newShop.getAddress(), "Address");
		validatePassword(newShop.getPassword());
		validatePhoneNumber(newShop.getPhoneNumber(), "Phone Number");

	}
	
	
	/**
	 * Validates the creation of a new shop.
	 *
	 * @param newShop The new shop to validate.
	 * @throws ValidationException If any validation errors are encountered.
	 */
	public static void validateUpdate(Shop newShop) throws ValidationException {
		if (newShop == null) {
			throw new ValidationException("Invalid Shop Input");
		}

		StringUtil.rejectIfInvalidString(newShop.getShopName(), "Shop Name");
		StringUtil.rejectIfInvalidString(newShop.getPrintName(), "Print Name");
		StringUtil.rejectIfInvalidString(newShop.getOwnerName(), "Owner Name");

		validateLicenceNumber(newShop.getLicenseNumber());
		validateGSTNNumber(newShop.getGSTNNumber());
		validateEmail(newShop.getEmail(), "Email");
		validateAddress(newShop.getAddress(), "Address");
		validatePhoneNumber(newShop.getPhoneNumber(), "Phone Number");

	}
	
	
	

	/**
	 * Validates a phone number.
	 *
	 * @param phoneNumber The phone number to validate.
	 * @param inputName   The name of the input (e.g., "Phone Number") for error
	 *                    messages.
	 * @throws ValidationException If the phone number is invalid.
	 */
	public static void validatePhoneNumber(long phoneNumber, String inputName) throws ValidationException {
		String regexPattern = "^[6-9]\\d{9}$";

		String number = Long.toString(phoneNumber);
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(number);
		if (!matcher.matches()) {
			throw new ValidationException(inputName.concat(" doesn't match the Pattern"));
		}
	}

	/**
	 * Validates an address.
	 *
	 * @param address   The address to validate.
	 * @param inputName The name of the input (e.g., "Address") for error messages.
	 * @throws ValidationException If the address is invalid.
	 */
	public static void validateAddress(String address, String inputName) throws ValidationException {
		if (address == null || "".equals(address.trim())) {
			throw new ValidationException(inputName.concat(" cannot be Null or Empty"));

		}

		String regexPattern = "^[A-Za-z0-9\\s.,-]+$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(address);

		if (!matcher.matches()) {
			throw new ValidationException(inputName.concat(" doesn't match the Pattern"));
		}
	}

	/**
	 * Validates an email address.
	 *
	 * @param email     The email address to validate.
	 * @param inputName The name of the input (e.g., "Email") for error messages.
	 * @throws ValidationException If the email address is invalid.
	 */
	public static void validateEmail(String email, String inputName) throws ValidationException {
		if (email == null || "".equals(email.trim())) {
			throw new ValidationException(inputName.concat(" cannot be Null or Empty"));

		}

		String regexPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			throw new ValidationException(inputName.concat(" doesn't match the Pattern"));
		}
	}

	/**
	 * Validates a License Number.
	 *
	 * @param licenceNumber The License Number to validate.
	 * @throws ValidationException If the License Number is invalid.
	 */
	public static void validateLicenceNumber(String licenceNumber) throws ValidationException {

		if (licenceNumber == null || "".equals(licenceNumber.trim())) {
			throw new ValidationException("License Number cannot be Null or Empty");

		}
		String regexPattern = "^[0-9]{1}\\d{2}\\d{4}\\d{7}$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(licenceNumber);

		if (!matcher.matches()) {
			throw new ValidationException("License Number doesn't match the Pattern");
		}
	}

	/**
	 * Validates a GSTN Number.
	 *
	 * @param gstnNumber The GSTN Number to validate.
	 * @throws ValidationException If the GSTN Number is invalid.
	 */
	public static void validateGSTNNumber(String gstnNumber) throws ValidationException {
		if (gstnNumber == null || "".equals(gstnNumber.trim())) {
			throw new ValidationException("GSTN Number cannot be Null or Empty");

		}
		String regexPattern = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(gstnNumber);

		if (!matcher.matches()) {
			throw new ValidationException("GSTN Number doesn't match the Pattern");
		}
	}

	/**
	 * Validates a password against a regex pattern.
	 *
	 * @param password The password to validate.
	 * @throws ValidationException If the password is invalid.
	 */
	public static void validatePassword(String password) throws ValidationException {
		if (password == null || "".equals(password.trim())) {
			throw new ValidationException("Password cannot be Null or Empty");
		}

		String regexPattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*?[0-9])(?=.*?[!@#$%^&*+`~=?|<>/]).{8,}";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(password);

		if (!matcher.matches()) {
			throw new ValidationException("Password doesn't match the Pattern");
		}
	}

	/**
	 * Validates a shop ID.
	 *
	 * @param id The ID of the shop to validate.
	 * @throws ValidationException If the shop ID is invalid.
	 */
	public static void validateShopId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("Invalid Shop ID");
		}
	}
	
	/**
	 * Validates user login details against database records.
	 *
	 * @param userEmail    The email provided by the user for login.
	 * @param userpassword The password provided by the user for login.
	 * @param dbEmail      The email retrieved from the database.
	 * @param dbPassword   The password retrieved from the database.
	 * @return True if the provided login details match the database records, false otherwise.
	 * @throws ValidationException If there is a validation error with the user email.
	 */
	public static boolean validateLoginDetails(String userEmail, String userpassword, String dbEmail, String dbPassword)
			throws ValidationException {

		boolean isDetailsCorrect = false;

		validateEmail(userEmail, "User Email");

		if (!dbEmail.equals(userEmail.trim()) ) {
			isDetailsCorrect = false;
		}
		if (!dbPassword.equals(userpassword.trim())) {
			isDetailsCorrect = false;
		}

		if (dbEmail.equals(userEmail.trim()) && dbPassword.equals(userpassword.trim())) {
			isDetailsCorrect = true;
		}

		return isDetailsCorrect;

	}

}

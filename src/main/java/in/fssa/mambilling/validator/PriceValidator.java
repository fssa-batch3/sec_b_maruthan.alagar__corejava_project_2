package in.fssa.mambilling.validator;

import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Price;

/**
 * The PriceValidator class provides methods for validating price-related
 * operations.
 */
public class PriceValidator {
	/**
	 * Validates a Price object.
	 *
	 * @param newPrice The Price object to validate.
	 * @throws ValidationException If any validation errors are encountered.
	 */
	public static void validate(Price newPrice) throws ValidationException {

		if (newPrice == null) {
			throw new ValidationException("Invalid Price Details");
		}

		validateMrp(newPrice.getMrp());
		validateTax(newPrice.getTax());
		validateDiscount(newPrice.getDiscount());

	}

	/**
	 * Validates a price ID.
	 *
	 * @param id The ID of the price to validate.
	 * @throws ValidationException If the price ID is invalid.
	 */
	public static void validatePriceId(int id) throws ValidationException {
		if (id < 1) {
			throw new ValidationException("Invalid Price ID");
		}

	}

	/**
	 * Validates the MRP (Maximum Retail Price) value.
	 *
	 * @param mrp The MRP value to validate.
	 * @throws ValidationException If the MRP value is not valid (less than or equal
	 *                             to 0).
	 */
	public static void validateMrp(double mrp) throws ValidationException {
		if (mrp <= 0) {
			throw new ValidationException("MRP must be greater than 0");
		}
	}

	/**
	 * Validates the tax value.
	 *
	 * @param tax The tax value to validate.
	 * @throws ValidationException If the tax value is not valid (less than 0 or
	 *                             greater than 100).
	 */
	public static void validateTax(double tax) throws ValidationException {
		if (tax < 0 || tax > 100) {
			throw new ValidationException("Tax must be between 0 and 100");
		}
	}

	/**
	 * Validates the discount value.
	 *
	 * @param discount The discount value to validate.
	 * @throws ValidationException If the discount value is not valid (less than 0
	 *                             or greater than 100).
	 */
	public static void validateDiscount(double discount) throws ValidationException {
		if (discount < 0 || discount > 100) {
			throw new ValidationException("Discount must be between 0 and 100");
		}
	}

}

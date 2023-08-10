package in.fssa.mambilling.validator;

import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.model.Price;

public class PriceValidator {

	public static void validate(Price newPrice) throws ValidationException {

		if (newPrice == null) {
			throw new ValidationException("Invalid Price Deatils");
		}

		validateMrp(newPrice.getMrp());
		validateTax(newPrice.getTax());
		validateDiscount(newPrice.getDiscount());

	}

	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("Invalid Product ID");
		}

	}

	public static void validateMrp(double mrp) throws ValidationException {
		if (mrp <= 0) {
			throw new ValidationException("MRP must be greater than 0");
		}
	}

	public static void validateTax(double tax) throws ValidationException {
		if (tax < 0 || tax > 100) {
			throw new ValidationException("Tax must be between 0 and 100");
		}
	}

	public static void validateDiscount(double discount) throws ValidationException {
		if (discount < 0 || discount > 100) {
			throw new ValidationException("Discount must be between 0 and 100");
		}
	}

}

package in.fssa.mambilling.validator;

import java.util.List;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.dao.UserDAO;
import in.fssa.mambilling.model.BillItems;
import in.fssa.mambilling.model.User;

/**
 * The BillValidator class provides methods for validating bill-related
 * operations.
 */

public class BillValidator {

	/**
	 * Validates a user ID and a list of bill items.
	 *
	 * @param id        The ID of the user creating the bill.
	 * @param billItems The list of bill items to validate.
	 * @throws ValidationException If any validation errors are encountered.
	 */
	public static void validate(int id, List<BillItems> billItems) throws ValidationException {

		if (id < 1) {
			throw new ValidationException("Invalid User ID");
		}

		if (billItems == null) {
			throw new ValidationException("Invalid Product Details");
		}

		UserDAO userdao = new UserDAO();
		try {
			User existingCheckUser = userdao.findById(id);

			if (existingCheckUser == null) {
				throw new ValidationException("User Not Exists");
			}
		} catch (PersistanceException e) {

			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * Validates a bill ID.
	 *
	 * @param id The ID of the bill to validate.
	 * @throws ValidationException If the bill ID is invalid.
	 */
	public static void validateBillId(int id) throws ValidationException {
		if (id < 1) {
			throw new ValidationException("Invalid Bill ID");
		}

	}

}

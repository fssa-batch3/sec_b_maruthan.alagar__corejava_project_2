package in.fssa.mambilling.validator;

import java.util.List;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.dao.UserDAO;
import in.fssa.mambilling.model.BillItems;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.model.User;


public class BillValidator {

	public static void validate(int id , List<BillItems> billItems) throws ValidationException {

		if (id < 1) {
			throw new ValidationException("Invalid User ID");
		}
		
		if(billItems == null) {
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

}

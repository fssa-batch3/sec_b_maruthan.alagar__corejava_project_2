//package in.fssa.mambilling.validator;
//
//import java.util.List;
//
//import in.fssa.mambilling.Exception.PersistanceException;
//import in.fssa.mambilling.Exception.ValidationException;
//import in.fssa.mambilling.dao.BillDAO;
//import in.fssa.mambilling.dao.UserDAO;
//import in.fssa.mambilling.model.BillItems;
//import in.fssa.mambilling.model.User;
//
//public class BillItemsValidator {
//	
//	public static void validate(int billId , List<BillItems> billItems) throws ValidationException {
//
//		if (billId < 1) {
//			throw new ValidationException("Invalid Bill ID");
//		}
//		
//		if(billItems == null) {
//			throw new ValidationException("Invalid Product Details");
//		}
//
//		BillDAO billdao = new BillDAO();
//		try {
//			User existingCheckBill = userdao.findById(id);
//
//			if (existingCheckUser == null) {
//				throw new ValidationException("User Not Exists");
//			}
//		} catch (PersistanceException e) {
//
//			throw new ValidationException(e.getMessage());
//		}
//
//	}
//	
//	
//
//}

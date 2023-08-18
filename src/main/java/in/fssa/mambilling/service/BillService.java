//package in.fssa.mambilling.service;
//
//import java.util.List;
//
//import in.fssa.mambilling.Exception.PersistanceException;
//import in.fssa.mambilling.Exception.ServiceException;
//import in.fssa.mambilling.Exception.ValidationException;
//import in.fssa.mambilling.dao.BillDAO;
//import in.fssa.mambilling.model.BillItems;
//import in.fssa.mambilling.model.Product;
//import in.fssa.mambilling.validator.BillValidator;
//import in.fssa.mambilling.validator.ProductValidator;
//
//public class BillService {
//
//	BillDAO billdao = new BillDAO();
//
//	public void create(int userId  , ) throws ValidationException, ServiceException {
//
//		try {
//			BillValidator.validate(userId , products);
//			billdao.create(userId);
//		} catch (PersistanceException e) {
//			throw new ServiceException("Failed to create Bill");
//		}
//
//	}
//	
//	
////	public void create(int userId , List<BillItems> billItems) throws ValidationException, ServiceException {
////
////		int billId = 0;
////		try {
////			BillValidator.validate(userId , billItems);
////			billId = billdao.create(userId);
////		} catch (PersistanceException e) {
////			throw new ServiceException("Failed to create Bill");
////		}
////
////		try {
////			System.out.println(billId);
////			priceservice.create(newProduct.getPrice(), productId);
////		} catch (ServiceException e) {
////			System.out.println("Failed to create product price");
////			removeRow(productId);
////
////		} catch (ValidationException e) {
////			removeRow(productId);
////			throw new ValidationException(e.getMessage());
////
////		}
////
////	}
//
//}

//package in.fssa.mambilling.service;
//
//import java.util.List;
//
//import in.fssa.mambilling.Exception.PersistanceException;
//import in.fssa.mambilling.Exception.ServiceException;
//import in.fssa.mambilling.Exception.ValidationException;
//import in.fssa.mambilling.dao.BillItemsDAO;
//import in.fssa.mambilling.dao.PriceDAO;
//import in.fssa.mambilling.model.BillItems;
//import in.fssa.mambilling.model.Price;
//import in.fssa.mambilling.validator.PriceValidator;
//
//public class BilltemsService {
//	
//	BillItemsDAO billitemsdao = new BillItemsDAO();
//
//	public void create(int billId , List<BillItems> billItems) throws ValidationException, ServiceException {
//
//		try {
//			PriceValidator.validateId(productId);
//			PriceValidator.validate(newPrice);
//			pricedao.create(newPrice, productId);
//		} catch (PersistanceException e) {
//			e.printStackTrace();
//			throw new ServiceException("Failed to create Price");
//		}
//
//	}
//
//}

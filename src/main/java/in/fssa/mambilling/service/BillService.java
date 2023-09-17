package in.fssa.mambilling.service;

import java.util.List;
import java.util.Map;

import in.fssa.mambilling.dao.BillDAO;
import in.fssa.mambilling.dao.GraphDAO;
import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.exception.ServiceException;
import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Bill;
import in.fssa.mambilling.model.BillItems;
import in.fssa.mambilling.model.User;
import in.fssa.mambilling.validator.BillValidator;

/**
 * The BillService class provides a service layer for managing bills, including
 * creation, retrieval, and removal operations, as well as fetching lists of
 * bills.
 */
public class BillService {

	BillDAO billDAO = new BillDAO();
	BillItemsService billitemsservice = new BillItemsService();

	/**
	 * Creates a new bill and associates bill items with it.
	 *
	 * @param userId    The ID of the user for whom the bill is being created.
	 * @param billItems A list of BillItems representing the items to be associated
	 *                  with the bill.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public void createBill(int userId, List<BillItems> billItems) throws ValidationException, ServiceException {

		int billId = 0;
		try {
			BillValidator.validate(userId, billItems);
			billId = billDAO.create(userId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage()); 
		}

		try {
			System.out.println(billId);
			billitemsservice.createBillItems(billId, billItems);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			removeRow(billId);

		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			removeRow(billId);

		}

	}

	/**
	 * Removes a bill and its associated items from the database.
	 *
	 * @param billId The ID of the bill to be removed.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 * @throws ValidationException If validation of input parameters fails.
	 */
	public void removeRow(int billId) throws ServiceException, ValidationException {

		try {
			BillValidator.validateBillId(billId);
			billDAO.dropRow(billId);
		} catch (PersistanceException e) {
			throw new ServiceException("Failed to create Bill Items");
		}

	}

	/**
	 * Retrieves a list of all bills from the database.
	 *
	 * @return A List of Bill objects representing all bills in the database.
	 * @throws ServiceException If there's an issue with the database operation or a
	 *                          service-level error occurs.
	 */
	public List<Bill> getAllBills() throws ServiceException {

		try {
			return billDAO.findAll();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves a list of all recent bills from the database.
	 *
	 * @return A List of Bill objects representing recent bills.
	 * @throws ServiceException If there's an issue with the database operation or a
	 *                          service-level error occurs.
	 */
	public List<Bill> getAllRecentbills() throws ServiceException {

		try {
			return billDAO.findAllRecentBills();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves a list of all bills associated with a user's phone number.
	 *
	 * @param phoneNumber The phone number of the user for whom bills are being
	 *                    retrieved.
	 * @return A List of Bill objects representing the user's bills.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public List<Bill> getAllUserbills(long phoneNumber) throws ServiceException, ValidationException {

		try {

			UserService userservice = new UserService();

			User user = userservice.getByPhoneNumber(phoneNumber);

			if (user == null) {
				throw new ServiceException("User Not Found or Invalid Phone Number");
			}
			BillValidator.validateBillId(user.getId());

			return billDAO.findByUserId(user.getId());
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * Retrieves and returns details of a graph as a mapping of node names to corresponding weights.
	 *
	 * @return A {@code Map} containing node names as keys and their associated weights as values.
	 * @throws ServiceException If there is an issue while fetching graph details, a {@code ServiceException} is thrown.
	 */
	public static Map<String, Double> getGraphDetails() throws ServiceException{
		GraphDAO graphDAO = new GraphDAO();
		try {
			
			return graphDAO.findGraphDetails();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

}

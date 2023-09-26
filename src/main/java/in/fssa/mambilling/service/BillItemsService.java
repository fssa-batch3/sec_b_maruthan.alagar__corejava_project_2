package in.fssa.mambilling.service;

import java.util.List;

import in.fssa.mambilling.dao.BillItemsDAO;
import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.exception.ServiceException;
import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.BillItems;
import in.fssa.mambilling.validator.BillItemsValidator;
import in.fssa.mambilling.validator.BillValidator;

/**
 * The BillItemsService class provides a service layer for managing bill items,
 * including creation and retrieval operations.
 */
public class BillItemsService {

	BillItemsDAO billitemsDAO = new BillItemsDAO();

	/**
	 * Creates bill items associated with a bill.
	 *
	 * @param billId    The ID of the bill to which the items will be added.
	 * @param billItems A list of BillItems representing the items to be added to
	 *                  the bill.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public void createBillItems(int billId, List<BillItems> billItems) throws ValidationException, ServiceException {

		try {
			BillItemsValidator.validate(billId, billItems);
			billitemsDAO.create(billId, billItems); 
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves bill items associated with a bill.
	 *
	 * @param newId The ID of the bill for which items are being retrieved.
	 * @return A List of BillItems representing the items associated with the bill.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */

	public List<BillItems> findByBillId(int newId) throws ValidationException, ServiceException {

		try {
			BillValidator.validateBillId(newId);
			return billitemsDAO.findAllByBillId(newId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Removes a bill Items from the database.
	 *
	 * @param billId The ID of the bill to be removed.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 * @throws ValidationException If validation of input parameters fails.
	 */
	public void deleteBillItems(int billId) throws ServiceException, ValidationException {
	    try {
	        BillValidator.validateBillId(billId);
	        billitemsDAO.deleteBillItem(billId);
	    } catch (PersistanceException e) {
	        throw new ServiceException(e.getMessage());
	    }
	}
}

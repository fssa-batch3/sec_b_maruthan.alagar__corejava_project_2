package in.fssa.mambilling.service;

import in.fssa.mambilling.dao.ShopDAO;
import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.exception.ServiceException;
import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Shop;
import in.fssa.mambilling.util.PasswordUtil;
import in.fssa.mambilling.validator.ShopValidator;

/**
 * The ShopService class provides a service layer for managing shops, including
 * shop creation, retrieval, updating, and fetching shop details.
 */
public class ShopService {

	private ShopDAO shopDAO = new ShopDAO();
	private PasswordUtil encoderDecoder = new PasswordUtil();

	/**
	 * Creates a new shop.
	 *
	 * @param shop The Shop object representing the new shop to be created.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public void createShop(Shop shop) throws ValidationException, ServiceException {
		try {
			ShopValidator.validate(shop);
			shop.setPassword(encoderDecoder.encodePassword(shop.getPassword()));
			ShopDAO.dropRow();
			shopDAO.createShop(shop);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates an existing shop's information.
	 *
	 * @param phoneNumber The phone number of the shop to update.
	 * @param newShop     The Shop object representing the updated shop information.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */

	public void updateShop(Shop newShop) throws ValidationException, ServiceException {
		try {
			ShopValidator.validateUpdate(newShop);
			shopDAO.updateShop(newShop);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a shop by its unique ID.
	 *
	 * @param id The ID of the shop to retrieve.
	 * @return A Shop object representing the shop with the specified ID.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */

	public Shop getByShopId(int id) throws ValidationException, ServiceException {
		try {
			ShopValidator.validateShopId(id);
			Shop newShop = shopDAO.findShopById(id);
			return newShop;
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Attempts to log in a shop using the provided email and password.
	 *
	 * @param email    The email of the shop.
	 * @param password The password of the shop.
	 * @return True if the login is successful, false otherwise.
	 * @throws ValidationException If there is a validation error with the password.
	 * @throws ServiceException   If there is an issue with the service operation.
	 */
	public boolean getShopForLogin(String email, String password) throws ValidationException, ServiceException {
		try {

			Shop newShop = shopDAO.findShopDetailsForLogin();
			ShopValidator.validatePassword(password);
			String encodedPassword = encoderDecoder.encodePassword(password);
			return ShopValidator.validateLoginDetails(email, encodedPassword, newShop.getEmail(),newShop.getPassword());
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Updates the password for a shop in the database.
	 *
	 * @param shopId      The ID of the shop to update.
	 * @param newPassword The new password to set for the shop.
	 * @throws ServiceException If there is an issue with the service operation.
	 * @throws ValidationException 
	 */
	public void updateShopPassword(String email, String newPassword) throws ServiceException, ValidationException {
	    try {
	    	ShopValidator.validatePassword(newPassword);
	    	ShopValidator.validateEmail(email, "Shop Email");
			String encodedPassword = encoderDecoder.encodePassword(newPassword);
	        shopDAO.updateShopPassword(email, encodedPassword);
	    } catch (PersistanceException e) {
	        throw new ServiceException(e.getMessage());
	    }
	}
	

}

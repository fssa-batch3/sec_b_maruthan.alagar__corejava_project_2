package in.fssa.mambilling.service;

import java.util.List;

import in.fssa.mambilling.dao.PriceDAO;
import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.exception.ServiceException;
import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.validator.PriceValidator;
import in.fssa.mambilling.validator.ProductValidator;

/**
 * The PriceService class provides a service layer for managing prices,
 * including creation, retrieval, and updating operations, as well as fetching
 * lists of prices.
 */
public class PriceService {

	PriceDAO priceDAO = new PriceDAO();

	/**
	 * Creates a new price for a product.
	 *
	 * @param newPrice  The Price object representing the new price to be created.
	 * @param productId The ID of the product for which the price is being created.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public void createPrice(Price newPrice, int productId) throws ValidationException, ServiceException {

		try {
			ProductValidator.validateProductId(productId);
			PriceValidator.validate(newPrice);
			priceDAO.create(newPrice, productId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Updates the price of a product.
	 *
	 * @param newPrice  The Price object representing the updated price.
	 * @param productId The ID of the product for which the price is being updated.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public void updatePrice(Price newPrice, int productId) throws ValidationException, ServiceException {
		try {
			ProductValidator.validateProductId(productId);
			PriceValidator.validate(newPrice);
			priceDAO.update(newPrice, productId);

		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves a list of all prices from the database.
	 *
	 * @return A List of Price objects representing all prices in the database.
	 * @throws ServiceException If there's an issue with the database operation or a
	 *                          service-level error occurs.
	 */
	public List<Price> getAllPrice() throws ServiceException {
		try {
			return priceDAO.findAll();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves the price by its unique ID.
	 *
	 * @param newId The ID of the price to retrieve.
	 * @return A Price object representing the price with the specified ID.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public Price findById(int newId) throws ValidationException, ServiceException {

		try {
			PriceValidator.validatePriceId(newId);
			return priceDAO.findById(newId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves the price associated with a specific product.
	 *
	 * @param newId The ID of the product for which to retrieve the price.
	 * @return A Price object representing the price associated with the specified
	 *         product ID.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public Price getByProductId(int newId) throws ValidationException, ServiceException {

		try {
			PriceValidator.validatePriceId(newId);
			return priceDAO.findByProductId(newId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}
}

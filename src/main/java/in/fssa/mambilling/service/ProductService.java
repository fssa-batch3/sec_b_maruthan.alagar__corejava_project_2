package in.fssa.mambilling.service;

import java.util.List;

import in.fssa.mambilling.dao.ProductDAO;
import in.fssa.mambilling.dto.ProductDTO;
import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.exception.ServiceException;
import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.validator.ProductValidator;

/**
 * The ProductService class provides a Service layer for managing products,
 * including creation, retrieval, updating, and deletion operations, as well as
 * fetching lists of products and their details.
 */
public class ProductService {

	ProductDAO productDAO = new ProductDAO();
	PriceService priceService = new PriceService();

	/**
	 * Creates a new product.
	 *
	 * @param newProduct The Product object representing the new product to be
	 *                   created.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a Service-level error occurs.
	 */
	public void createProduct(Product newProduct) throws ValidationException, ServiceException {

		int productId = 0;
		try {
			ProductValidator.validateCreate(newProduct);
			productId = productDAO.create(newProduct);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

		try {
			priceService.createPrice(newProduct.getPrice(), productId);
		} catch (ServiceException e) {
			System.out.println("Failed to create product price");
			removeRow(productId);

		} catch (ValidationException e) {
			removeRow(productId);
			throw new ValidationException(e.getMessage());

		}

	}

	/**
	 * Updates an existing product.
	 *
	 * @param newProduct The Product object representing the updated product.
	 * @param id         The ID of the product to update.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a Service-level error occurs.
	 */
	public void updateProduct(Product newProduct, int id) throws ValidationException, ServiceException {

		try {
			ProductValidator.validateUpdate(newProduct, id);
			priceService.updatePrice(newProduct.getPrice(), id);
			productDAO.update(newProduct, id);

		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves a list of all products from the database.
	 *
	 * @return A List of Product objects representing all products in the database.
	 * @throws ServiceException If there's an issue with the database operation or a
	 *                          Service-level error occurs.
	 */
	public List<Product> getAllProducts() throws ServiceException {

		try {
			return productDAO.findAll();
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Deletes a product by its unique ID.
	 *
	 * @param productId The ID of the product to delete.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a Service-level error occurs.
	 */
	public void deleteProduct(int productId) throws ValidationException, ServiceException {
		try {

			ProductValidator.validateProductId(productId);
			productDAO.delete(productId);

		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves detailed information about a product.
	 *
	 * @param id The ID of the product for which to retrieve details.
	 * @return A ProductDTO object representing detailed information about the
	 *         product.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a Service-level error occurs.
	 */
	public ProductDTO getProductDetail(int id) throws ValidationException, ServiceException {
		try {
			ProductValidator.validateProductId(id);
			return productDAO.findProductDetail(id);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a product by its unique ID.
	 *
	 * @param newId The ID of the product to retrieve.
	 * @return A Product object representing the product with the specified ID.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a Service-level error occurs.
	 */

	public Product findById(int newId) throws ValidationException, ServiceException {

		try {
			ProductValidator.validateProductId(newId);
			return productDAO.findById(newId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Removes a product and its associated price from the database.
	 *
	 * @param productId The ID of the product to remove.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a Service-level error occurs.
	 * @throws ValidationException If validation of input parameters fails.
	 */
	public void removeRow(int productId) throws ServiceException, ValidationException {

		try {
			ProductValidator.validateProductId(productId);
			productDAO.dropRow(productId);
		} catch (PersistanceException e) {
			throw new ServiceException("Failed to Delete row");
		}

	}

}

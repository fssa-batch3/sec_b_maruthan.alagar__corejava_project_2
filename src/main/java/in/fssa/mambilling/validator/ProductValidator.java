package in.fssa.mambilling.validator;

import in.fssa.mambilling.dao.ProductDAO;
import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.util.StringUtil;

/**
 * The ProductValidator class provides methods for validating product-related
 * operations.
 */
public class ProductValidator {
	/**
	 * Validates the creation of a new product.
	 *
	 * @param newProduct The new product to validate.
	 * @throws ValidationException If any validation errors are encountered.
	 */
	public static void validateCreate(Product newProduct) throws ValidationException {

		if (newProduct == null) {
			throw new ValidationException("Invalid Product Input");
		}
		if(newProduct.getPrice()==null) {
			throw new ValidationException("Invalid Price Details");
		}

		StringUtil.rejectIfInvalidString(newProduct.getProductName(), "Product Name");

		if (newProduct.getSpecialName() != null) {
			StringUtil.rejectIfInvalidString(newProduct.getSpecialName(), "Special Name");
		}

		if (newProduct.getQuantity() < 1) {
			throw new ValidationException("Invalid Product Quantity");
		}

		if (newProduct.getQuantityType() == null) {
			throw new ValidationException("Invalid Product Quantity Type");
		}

		ProductDAO productDAO = new ProductDAO();

		try {
			Product prod = productDAO.isProductAlreadyExistsCreate(newProduct);
			if (prod != null) {
				throw new ValidationException("Product Already Exists");
			}
		} catch (PersistanceException e) {
			throw new ValidationException("Failed to check if the product exists during create.");
		}

	}

	/**
	 * Validates the update of an existing product.
	 *
	 * @param newProduct The updated product data.
	 * @param id         The ID of the product being updated.
	 * @throws ValidationException If any validation errors are encountered.
	 */
	public static void validateUpdate(Product newProduct, int id) throws ValidationException {

		if (newProduct == null) {
			throw new ValidationException("Invalid Product Input");
		}
		
		if(newProduct.getPrice()==null) {
			throw new ValidationException("Invalid Price Details");
		}

		if (id <= 0) {
			throw new ValidationException("Invalid Product ID");
		}

		StringUtil.rejectIfInvalidString(newProduct.getProductName(), "Product Name");

		if (newProduct.getSpecialName() != null) {
			StringUtil.rejectIfInvalidString(newProduct.getSpecialName(), "Special Name");
		}
		if (newProduct.getQuantity() <= 0) {
			throw new ValidationException("Invalid Product Quantity");
		}

		if (newProduct.getQuantityType() == null) {
			throw new ValidationException("Invalid Product Quantity Type");
		}

		try {
			ProductDAO productDAO = new ProductDAO();
			Product prod = productDAO.isProductAlreadyExists(id);

			if (prod == null) {
				throw new ValidationException("Product Doesn't Exists");
			}
			Product prod1 = productDAO.isProductAlreadyExistsCreate(newProduct);

			if (prod1 != null) {
				throw new ValidationException("Product Already Exists");
			}

		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * Validates a product ID.
	 *
	 * @param id The ID of the product to validate.
	 * @throws ValidationException If the product ID is invalid.
	 */
	public static void validateProductId(int id) throws ValidationException {
		if (id < 1) {
			throw new ValidationException("Invalid Product ID");
		}

	}

}

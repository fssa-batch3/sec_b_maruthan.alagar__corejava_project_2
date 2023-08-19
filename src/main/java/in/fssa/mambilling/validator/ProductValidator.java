package in.fssa.mambilling.validator;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.dao.ProductDAO;
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

		ProductDAO productdao = new ProductDAO();

		try {
			Product prod = productdao.isProductAlreadyExistsCreate(newProduct);
			if (prod != null) {
				throw new ValidationException("Product Already Exists");
			}
		} catch (PersistanceException e) {
			throw new ValidationException("Failed to Check product is exists or not in create");
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
			ProductDAO productdao = new ProductDAO();
			Product prod = productdao.isProductAlreadyExists(id);

			if (prod == null) {
				throw new ValidationException("Product Doesn't Exists");
			}
			Product prod1 = productdao.isProductAlreadyExistsCreate(newProduct);

			if (prod1 != null) {
				throw new ValidationException("Product Already Exists");
			}

		} catch (PersistanceException e) {
			throw new ValidationException("Failed to Check product is exists or not in update");
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

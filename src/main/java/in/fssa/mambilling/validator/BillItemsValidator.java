package in.fssa.mambilling.validator;

import java.util.List;

import in.fssa.mambilling.exception.ServiceException;
import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.BillItems;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.service.PriceService;
import in.fssa.mambilling.service.ProductService;

/**
 * The BillItemsValidator class provides methods for validating bill items.
 */
public class BillItemsValidator {

	/**
	 * Validates a list of bill items for a given bill ID.
	 *
	 * @param billId    The ID of the bill to which the items belong.
	 * @param billItems The list of bill items to validate.
	 * @throws ValidationException If any validation errors are encountered.
	 * @throws ServiceException    If there is a Service-related issue during
	 *                             validation.
	 */
	public static void validate(int billId, List<BillItems> billItems) throws ValidationException, ServiceException {

		if (billId < 1) {
			throw new ValidationException("Invalid Bill ID");
		}

		if (billItems == null) {
			throw new ValidationException("Invalid Product Details");
		}

		for (BillItems billItem : billItems) {

			try {
				ProductService productService = new ProductService();
				PriceService priceService = new PriceService();
				Product product = productService.findById(billItem.getProductId());
				Price price = priceService.findById(billItem.getPriceId());

				if (product == null) {
					throw new ValidationException("Product Not found with this ID");

				}

				if (price == null) {
					throw new ValidationException("Price Not found with this ID");

				}
			} catch (ServiceException e) {
				throw new ValidationException(e.getMessage());
			}

			if (billItem.getQuantity() < 1) {
				throw new ValidationException(
						"Invalid product quantity for this product id " + billItem.getProductId());
			}

		}

	}

}

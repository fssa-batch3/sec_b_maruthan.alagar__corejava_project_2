package in.fssa.mambilling.service;

import java.util.List;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.Exception.ServiceException;
import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.dao.ProductDAO;
import in.fssa.mambilling.dto.ProductDTO;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.validator.PriceValidator;
import in.fssa.mambilling.validator.ProductValidator;

public class ProductService {

	ProductDAO productdao = new ProductDAO();
	PriceService priceservice = new PriceService();

	public void create(Product newProduct) throws ValidationException, ServiceException {

		int productId = 0;
		try {
			ProductValidator.validateCreate(newProduct);
			productId = productdao.create(newProduct);
		} catch (PersistanceException e) {
			throw new ServiceException("Failed to create product");
		}

		try {
			System.out.println(productId);
			priceservice.create(newProduct.getPrice(), productId);
		} catch (ServiceException e) {
			System.out.println("Failed to create product price");
			removeRow(productId);

		} catch (ValidationException e) {
			removeRow(productId);
			throw new ValidationException(e.getMessage());

		}

	}

	public void update(Product newProduct, int id) throws ValidationException, ServiceException {

		try {
			ProductValidator.validateUpdate(newProduct, id);
			priceservice.update(newProduct.getPrice(), id);
			productdao.update(newProduct, id);

		} catch (PersistanceException e) {
			throw new ServiceException("Failed to update product");
		}

	}

	public List<Product> getAll() throws ServiceException {

		try {
			return productdao.findAll();
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to get Product Details");
		}

	}

	public void delete(int productId) throws ValidationException, ServiceException {
		try {

			PriceValidator.validateId(productId);
			productdao.delete(productId);

		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public ProductDTO getProductDetail(int id) throws ValidationException, ServiceException {
		try {
			PriceValidator.validateId(id);
			return productdao.findProductDetail(id);
		} catch (PersistanceException e) {
			throw new ServiceException("Failed to create Price");
		}
	}

	public void removeRow(int productId) throws ServiceException, ValidationException {

		try {
			PriceValidator.validateId(productId);
			productdao.dropRow(productId);
		} catch (PersistanceException e) {
			throw new ServiceException("Failed to create Price");
		}

	}

}

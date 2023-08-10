package in.fssa.mambilling.service;

import java.util.List;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.Exception.ServiceException;
import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.dao.PriceDAO;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.validator.PriceValidator;

public class PriceService {

	PriceDAO pricedao = new PriceDAO();

	public void create(Price newPrice, int productId) throws ValidationException, ServiceException {

		try {
			PriceValidator.validateId(productId);
			PriceValidator.validate(newPrice);
			pricedao.create(newPrice, productId);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to create Price");
		}

	}

	public void update(Price newPrice, int productId) throws ValidationException, ServiceException {
		try {
			PriceValidator.validateId(productId);
			PriceValidator.validate(newPrice);
			pricedao.update(newPrice, productId);

		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to update Price");
		}

	}

	public List<Price> getAll() throws ServiceException {
		try {
			return pricedao.findAll();
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}

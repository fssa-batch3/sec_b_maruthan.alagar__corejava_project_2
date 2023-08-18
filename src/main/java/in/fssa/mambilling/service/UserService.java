package in.fssa.mambilling.service;

import java.util.List;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.Exception.ServiceException;
import in.fssa.mambilling.Exception.ValidationException;
import in.fssa.mambilling.dao.UserDAO;
import in.fssa.mambilling.model.User;
import in.fssa.mambilling.validator.UserValidator;

public class UserService {

	UserDAO userdao = new UserDAO();

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<User> getAllUsers() throws ServiceException {

		try {
			return userdao.findAll();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}
	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void create(User user) throws ValidationException, ServiceException {
		try {
			UserValidator.validate(user);
			userdao.create(user);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}
	/**
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public User findByPhoneNumber(long phoneNumber) throws ValidationException, ServiceException {

		try {
			UserValidator.validatePhoneNumber(phoneNumber, "Phone Number");
			return userdao.findByPhoneNumber(phoneNumber);

		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}
	/**
	 * 
	 * @param phoneNumber
	 * @param newUser
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void update(long phoneNumber, User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validateUpdate(phoneNumber, newUser);
			userdao.update(phoneNumber, newUser);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

}

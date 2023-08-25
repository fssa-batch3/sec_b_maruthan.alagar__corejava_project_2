package in.fssa.mambilling.service;

import java.util.List;

import in.fssa.mambilling.dao.UserDAO;
import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.exception.ServiceException;
import in.fssa.mambilling.exception.ValidationException;
import in.fssa.mambilling.model.User;
import in.fssa.mambilling.validator.UserValidator;

/**
 * The UserService class provides a service layer for managing users, including
 * user creation, retrieval, updating, and fetching user details.
 */
public class UserService {

	UserDAO userDAO = new UserDAO();

	/**
	 * Retrieves a list of all users from the database.
	 *
	 * @return A List of User objects representing all users in the database.
	 * @throws ServiceException If there's an issue with the database operation or a
	 *                          service-level error occurs.
	 */
	public List<User> getAllUsers() throws ServiceException {

		try {
			return userDAO.findAll();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Creates a new user.
	 *
	 * @param user The User object representing the new user to be created.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public void createUser(User user) throws ValidationException, ServiceException {
		try {
			UserValidator.validate(user);
			userDAO.create(user);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves a user by their phone number.
	 *
	 * @param phoneNumber The phone number of the user to retrieve.
	 * @return A User object representing the user with the specified phone number.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public User getByPhoneNumber(long phoneNumber) throws ValidationException, ServiceException {

		try {
			UserValidator.validatePhoneNumber(phoneNumber, "Phone Number");
			return userDAO.findByPhoneNumber(phoneNumber);

		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Updates an existing user's information.
	 *
	 * @param phoneNumber The phone number of the user to update.
	 * @param newUser     The User object representing the updated user information.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public void updateUser(long phoneNumber, User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validateUpdate(phoneNumber, newUser);
			userDAO.update(phoneNumber, newUser);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves a user by their unique ID.
	 *
	 * @param newId The ID of the user to retrieve.
	 * @return A User object representing the user with the specified ID.
	 * @throws ValidationException If validation of input parameters fails.
	 * @throws ServiceException    If there's an issue with the database operation
	 *                             or a service-level error occurs.
	 */
	public User getByUserId(int newId) throws ValidationException, ServiceException {

		try {
			UserValidator.validateUserId(newId);
			return userDAO.findById(newId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

}

package in.fssa.mambilling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.model.User;
import in.fssa.mambilling.util.ConnectionUtil;

/**
 * The UserDAO class provides methods for interacting with a database to perform
 * operations related to user management.
 */

public class UserDAO {

	/**
	 * Retrieves a list of all users from the database.
	 *
	 * @return A List of User objects representing all users in the database.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public List<User> findAll() throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<User> userList = new ArrayList<User>();
		try {
			String query = "SELECT * FROM users";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				User newUser = new User(rs.getString("name"), rs.getString("email"), rs.getLong("phone_number"),
						rs.getString("address"));
				userList.add(newUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return userList;

	}

	/**
	 * Creates a new user entry in the database.
	 *
	 * @param user The User object representing the new user to be created.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public void create(User user) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT into users ( name , email , phone_number , address ) VALUES ( ?, ?, ?, ? );";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setLong(3, user.getPhoneNumber());
			ps.setString(4, user.getAddress());

			ps.executeUpdate();

			System.out.println("User Successfully Created :)");

		} catch (SQLException e) {
			// e.printStackTrace();
			if (e.getMessage().contains("Duplicate entry")) {
				throw new PersistanceException("Duplicate constraint");
			} else {
				System.out.println(e.getMessage());
				throw new PersistanceException(e.getMessage());
			}

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Retrieves a user by their phone number.
	 *
	 * @param phoneNumber The phone number of the user to be retrieved.
	 * @return A User object representing the user with the specified phone number,
	 *         if found, otherwise null.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public User findByPhoneNumber(long phoneNumber) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT * FROM users  WHERE phone_number = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, phoneNumber);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setAddress(rs.getString("address"));
				user.setId(rs.getInt("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

	/**
	 * Updates an existing user entry in the database based on their phone number.
	 *
	 * @param phoneNumber The phone number of the user to be updated.
	 * @param newUser     The User object representing the updated user information.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public void update(long phoneNumber, User newUser) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET name = ?, phone_number = ? , address = ? , email = ? Where phone_number = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newUser.getName());
			ps.setLong(2, newUser.getPhoneNumber());
			ps.setString(3, newUser.getAddress());
			ps.setString(4, newUser.getEmail());
			ps.setLong(5, phoneNumber);

			ps.executeUpdate();

			System.out.println("User Successfully Updated :)");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Retrieves user information by their user ID.
	 *
	 * @param userId The ID of the user for which information is being retrieved.
	 * @return A User object representing the user information.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */

	public User findById(int userId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT * FROM users  WHERE id = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setAddress(rs.getString("address"));
				user.setId(rs.getInt("id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

}

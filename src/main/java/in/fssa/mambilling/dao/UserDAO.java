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


public class UserDAO {
	
	/**
	 * 
	 * @return
	 * @throws PersistanceException
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
				User newUser = new User(rs.getString("name"),rs.getString("email"),rs.getLong("phone_number"),rs.getString("address"));
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
	 * 
	 * @param user
	 * @throws PersistanceException
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
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws PersistanceException
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
	 * 
	 * @param phoneNumber
	 * @param newUser
	 * @throws PersistanceException
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
			ps.setLong(5 ,phoneNumber);

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
	 * 
	 * @param userId
	 * @return
	 * @throws PersistanceException
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

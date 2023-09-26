package in.fssa.mambilling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.model.Shop;
import in.fssa.mambilling.util.ConnectionUtil;

/**
 * Data Access Object (DAO) for managing Shop data in the database.
 */
public class ShopDAO {

	/**
	 * Creates a new shop record in the database.
	 *
	 * @param shop The Shop object to be created.
	 * @throws PersistanceException If there is an issue with database insertion or
	 *                              a duplicate entry is detected.
	 */
	public void createShop(Shop shop) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO shop (shop_name, licence_number, gstn_number, phone_number, email, address, print_name, owner_name, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, shop.getShopName());
			ps.setString(2, shop.getLicenseNumber());
			ps.setString(3, shop.getGSTNNumber());
			ps.setLong(4, shop.getPhoneNumber());
			ps.setString(5, shop.getEmail());
			ps.setString(6, shop.getAddress());
			ps.setString(7, shop.getPrintName());
			ps.setString(8, shop.getOwnerName());
			ps.setString(9, shop.getPassword());

			ps.executeUpdate();


		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry")) {
				throw new PersistanceException("Duplicate constraint");
			} else {
				throw new PersistanceException(e.getMessage());
			}

		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Updates an existing shop record in the database based on the shop's ID.
	 *
	 * @param shop The Shop object with updated data.
	 * @throws PersistanceException If there is an issue with database update or the
	 *                              shop is not found.
	 */
	public void updateShop(Shop shop) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE shop SET shop_name=?, licence_number=?, gstn_number=?, phone_number=? ,email=?, address=?, print_name=?, owner_name=? WHERE id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, shop.getShopName());
			ps.setString(2, shop.getLicenseNumber());
			ps.setString(3, shop.getGSTNNumber());
			ps.setLong(4, shop.getPhoneNumber());
			ps.setString(5, shop.getEmail());
			ps.setString(6, shop.getAddress());
			ps.setString(7, shop.getPrintName());
			ps.setString(8, shop.getOwnerName());

			ps.setInt(9, 1);

			int updatedRows = ps.executeUpdate();

			if (updatedRows == 0) {
				throw new PersistanceException("Shop not found");
			}


		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Retrieves a shop record from the database based on the shop's ID.
	 *
	 * @param id The ID of the shop to search for.
	 * @return The Shop object if found, or null if not found.
	 * @throws PersistanceException If there is an issue with the database query.
	 */
	public Shop findShopById(int id) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Shop shop = null;

		try {
			String query = "SELECT id,shop_name,licence_number,gstn_number,phone_number, email ,address,print_name ,owner_name FROM shop WHERE id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				shop = new Shop();
				shop.setId(rs.getInt("id"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setLicenseNumber(rs.getString("licence_number"));
				shop.setGSTNNumber(rs.getString("gstn_number"));
				shop.setPhoneNumber(rs.getLong("phone_number"));
				shop.setEmail(rs.getString("email"));
				shop.setAddress(rs.getString("address"));
				shop.setPrintName(rs.getString("print_name"));
				shop.setOwnerName(rs.getString("owner_name"));
			}

		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return shop;

	}

	/**
	 * Deletes a product with the specified ID from the database.
	 *
	 * @param newId The ID of the product to be deleted.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public static void dropRow() throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "truncate table shop;";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.executeUpdate();
;

		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Retrieves shop details for a given shop ID from the database.
	 *
	 * @param id The ID of the shop to retrieve details for.
	 * @return A Shop object containing the shop's email and password.
	 * @throws PersistanceException If there is an issue with database connectivity
	 *                              or SQL execution.
	 */
	public Shop findShopDetailsForLogin() throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Shop shop = new Shop();

		try {
			String query = "SELECT  email , password FROM shop WHERE id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, 1);
			rs = ps.executeQuery();

			if (rs.next()) {

				shop.setEmail(rs.getString("email"));
				shop.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return shop;

	}
	
	
	
	/**
	 * Updates the password for a shop in the database.
	 *
	 * @param shopId     The ID of the shop to update.
	 * @param newPassword The new password to set for the shop.
	 * @throws PersistanceException If there is an issue with the database connection or the update operation.
	 */
	public void updateShopPassword(String email, String newPassword) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        String query = "UPDATE shop SET password=? WHERE email=?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setString(1, newPassword);
	        ps.setString(2, email);

	        int rowsUpdated = ps.executeUpdate();

	        if (rowsUpdated == 0) {
	            throw new PersistanceException("Shop not found");
	        }

	    } catch (SQLException e) {
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, null);
	    }
	}

}

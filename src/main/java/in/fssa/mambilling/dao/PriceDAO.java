package in.fssa.mambilling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.util.ConnectionUtil;

/**
 * The PriceDAO class provides methods for interacting with a database to
 * perform operations related to product prices.
 */
public class PriceDAO {

	/**
	 * Creates a new price entry for a product in the database.
	 *
	 * @param newPrice  The Price object representing the new price to be created.
	 * @param productID The ID of the product for which the price is being created.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public void create(Price newPrice, int productID) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String pricequery = "INSERT INTO price (product_id, mrp, tax, discount) VALUES (?, ?, ?, ?);";
			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(pricequery);
			ps.setInt(1, productID);
			ps.setDouble(2, newPrice.getMrp());
			ps.setDouble(3, newPrice.getTax());
			ps.setDouble(4, newPrice.getDiscount());
			ps.executeUpdate();


		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Updates the price entry for a product in the database.
	 *
	 * @param newPrice  The Price object representing the updated price.
	 * @param productID The ID of the product for which the price is being updated.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public void update(Price newPrice, int productID) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String pricequery = "UPDATE price SET mrp = ?, tax = ?, discount = ? WHERE product_id = ?;";
			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(pricequery);

			ps.setDouble(1, newPrice.getMrp());
			ps.setDouble(2, newPrice.getTax());
			ps.setDouble(3, newPrice.getDiscount());
			ps.setInt(4, productID);
			ps.executeUpdate();


		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Retrieves a list of all product prices from the database.
	 *
	 * @return A List of Price objects representing all product prices in the
	 *         database.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public List<Price> findAll() throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Price> priceList = null;
		try {
			String query = "SELECT discount , mrp , tax  FROM price";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			priceList = new ArrayList<>();
			while (rs.next()) {
				Price newPrice = new Price();
				newPrice.setDiscount(rs.getDouble("discount"));
				newPrice.setMrp(rs.getDouble("mrp"));
				newPrice.setTax(rs.getDouble("tax"));

				priceList.add(newPrice);
			}
		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return priceList;

	}

	/**
	 * Retrieves the price information for a specific price ID.
	 *
	 * @param priceId The ID of the price for which information is being retrieved.
	 * @return A Price object representing the price information.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public Price findById(int priceId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Price price = null;

		try {
			String query = "SELECT discount , mrp , tax , id FROM price  WHERE id = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, priceId);
			rs = ps.executeQuery();

			if (rs.next()) {
				price = new Price();
				price.setDiscount(rs.getDouble("discount"));
				price.setMrp(rs.getDouble("mrp"));
				price.setTax(rs.getDouble("tax"));
				price.setId(rs.getInt("id"));

			}

		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return price;
	}

	/**
	 * Retrieves the price information for a specific product ID.
	 *
	 * @param productId The ID of the product for which price information is being
	 *                  retrieved.
	 * @return A Price object representing the price information for the product.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public Price findByProductId(int productId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Price price = null;

		try {
			String query = "SELECT id FROM  price  WHERE product_id = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();

			if (rs.next()) {
				price = new Price();

				price.setId(rs.getInt("id"));
			}

		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return price;
	}

}

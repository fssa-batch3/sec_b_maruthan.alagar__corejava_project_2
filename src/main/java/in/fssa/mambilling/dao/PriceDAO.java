package in.fssa.mambilling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.model.Price;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.model.Product.QuantityType;
import in.fssa.mambilling.util.ConnectionUtil;

public class PriceDAO {

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

			System.out.println("Price Successfully Created :)");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

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

			System.out.println("Price Successfully Updated :)");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	public List<Price> findAll() throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Price> priceList = null;
		try {
			String query = "SELECT * FROM price";
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
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return priceList;

	}
}

package in.fssa.mambilling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.dto.ProductDTO;
import in.fssa.mambilling.model.Product;
import in.fssa.mambilling.model.Product.QuantityType;
import in.fssa.mambilling.util.ConnectionUtil;

public class ProductDAO {
	/**
	 * 
	 * @return
	 * @throws PersistanceException
	 */
	public List<Product> findAll() throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Product> productList = new ArrayList<Product>();
		try {
			String query = "SELECT * FROM products";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Product newProduct = new Product();
				newProduct.setProductName(rs.getString("product_name"));
				newProduct.setQuantity(rs.getInt("quantity"));
				newProduct.setSpecialName(rs.getString("special_name"));
				newProduct.setQuantityType(QuantityType.valueOf(rs.getString("quantity_type")));
				productList.add(newProduct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return productList;

	}
	/**
	 * 
	 * @param newProduct
	 * @return
	 * @throws PersistanceException
	 */
	public int create(Product newProduct) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int productId = 0;

		try {
			String productquery = "INSERT INTO products (product_name, quantity, special_name, quantity_type) VALUES (?, ?, ?, ?);";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(productquery, java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, newProduct.getProductName());
			ps.setInt(2, newProduct.getQuantity());
			ps.setString(3, newProduct.getSpecialName());
			ps.setString(4, newProduct.getQuantityType().toString());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				productId = rs.getInt(1);
			}

			System.out.println("Product Successfully Created :)");

		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("Duplicate entry")) {
				throw new PersistanceException("Duplicate constraint");
			} else {
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productId;
	}
	/**
	 * 
	 * @param newProduct
	 * @param id
	 * @throws PersistanceException
	 */
	public void update(Product newProduct, int id) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products SET product_name = ?,special_name = ?,quantity = ?,quantity_type = ? Where id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newProduct.getProductName());
			ps.setString(2, newProduct.getSpecialName());
			ps.setInt(3, newProduct.getQuantity());
			ps.setString(4, newProduct.getQuantityType().toString());
			ps.setInt(5, id);

			ps.executeUpdate();

			System.out.println("Product Successfully Updated :)");

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
	 * @param newProduct
	 * @return
	 * @throws PersistanceException
	 */
	public Product isProductAlreadyExistsCreate(Product newProduct) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;

		try {
			String query = "SELECT * FROM products WHERE product_name = ? and quantity = ? and quantity_type = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newProduct.getProductName());
			ps.setInt(2, newProduct.getQuantity());
			ps.setString(3, newProduct.getQuantityType().toString());

			rs = ps.executeQuery();

			if (rs.next()) {
				product = new Product();

				product.setProductName(rs.getString("product_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setQuantityType(QuantityType.valueOf(rs.getString("quantity_type")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return product;

	}
	/**
	 * 
	 * @param productId
	 * @return
	 * @throws PersistanceException
	 */
	public Product isProductAlreadyExists(int productId) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;

		try {
			String query = "SELECT * FROM products WHERE id = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, productId);

			rs = ps.executeQuery();

			if (rs.next()) {
				product = new Product();

				product.setProductName(rs.getString("product_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setQuantityType(QuantityType.valueOf(rs.getString("quantity_type")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return product;

	}
	/**
	 * 
	 * @param productId
	 * @return
	 * @throws PersistanceException
	 */
	public ProductDTO findProductDetail(int productId) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductDTO product = null;

		try {
			String query = "SELECT p.product_name, p.quantity, p.special_name, p.quantity_type, pr.mrp, pr.tax, pr.discount FROM products p JOIN price pr ON p.id = pr.product_id;";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

			if (rs.next()) {
				product = new ProductDTO();

				product.setProductName(rs.getString("product_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setQuantityType(QuantityType.valueOf(rs.getString("quantity_type")));
				product.setSpecialName(rs.getString("special_name"));
				product.setDiscount(rs.getDouble("discount"));
				product.setMrp(rs.getDouble("mrp"));
				product.setTax(rs.getDouble("tax"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return product;

	}
	/**
	 * 
	 * @param newId
	 * @throws PersistanceException
	 */
	public void delete(int newId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products SET is_active = false Where id = ? and is_active = true ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, newId);

			ps.executeUpdate();

			System.out.println("Product Successfully Deactivated :)");

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
	 * @param newId
	 * @throws PersistanceException
	 */
	public void dropRow(int newId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "DELETE FROM products WHERE (id = ?);";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, newId);

			ps.executeUpdate();

			System.out.println("Product Successfully Deleted :)");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

}

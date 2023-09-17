package in.fssa.mambilling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.util.ConnectionUtil;

/**
 * This class provides data access methods for retrieving graph details related
 * to bills.
 */
public class GraphDAO {

	/**
	 * Retrieves and returns a mapping of bill dates to their respective total
	 * amounts.
	 *
	 * @return A {@code Map} containing bill dates as keys and their associated
	 *         total amounts as values.
	 * @throws PersistanceException If there is an issue while fetching graph
	 *                              details, a {@code PersistanceException} is
	 *                              thrown.
	 */
	public Map<String, Double> findGraphDetails() throws PersistanceException {
	    Map<String, Double> dateTotalAmounts = new HashMap<>();
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String sql = "SELECT DISTINCT DATE(timestamp) AS bill_date FROM bills";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String dateToQuery = rs.getString("bill_date");
	            double totalAmount = 0.0;
	            PreparedStatement billIdsStmt = null;
	            ResultSet billIdsResult = null;

	            try {
	                // Retrieve bill_ids for the current date
	                String billIdsSql = "SELECT id FROM bills WHERE DATE(timestamp) = ?";
	                billIdsStmt = con.prepareStatement(billIdsSql);
	                billIdsStmt.setString(1, dateToQuery);
	                billIdsResult = billIdsStmt.executeQuery();

	                while (billIdsResult.next()) {
	                    String billId = billIdsResult.getString("id");
	                    PreparedStatement billItemsStmt = null;
	                    ResultSet billItemsResult = null;

	                    try {
	                        // Retrieve bill_items and calculate total amount for each bill
	                        String billItemsSql = "SELECT bi.quantity, p.mrp, p.tax, p.discount FROM bill_items bi INNER JOIN price p ON bi.product_id = p.product_id WHERE bi.bill_id = ?";
	                        billItemsStmt = con.prepareStatement(billItemsSql);
	                        billItemsStmt.setString(1, billId);
	                        billItemsResult = billItemsStmt.executeQuery();

	                        while (billItemsResult.next()) {
	                            int quantity = billItemsResult.getInt("quantity");
	                            double mrp = billItemsResult.getDouble("mrp");
	                            double tax = billItemsResult.getDouble("tax");
	                            double discount = billItemsResult.getDouble("discount");

	                            // Calculate the price for each product and add to total
	                            double taxAmount = (mrp * tax / 100.0);
	                            double discountAmount = (mrp * discount / 100.0);
	                            double price = (mrp + taxAmount - discountAmount) * quantity;
	                            totalAmount += price;
	                        }
	                    } finally {
	                        if (billItemsResult != null) {
	                            billItemsResult.close();
	                        }
	                        if (billItemsStmt != null) {
	                            billItemsStmt.close();
	                        }
	                    }
	                }
	            } finally {
	                if (billIdsResult != null) {
	                    billIdsResult.close();
	                }
	                if (billIdsStmt != null) {
	                    billIdsStmt.close();
	                }
	            }

	            // Store the total amount for the current date
	            dateTotalAmounts.put(dateToQuery, totalAmount);
	        }

	        for (Map.Entry<String, Double> entry : dateTotalAmounts.entrySet()) {
	            System.out.println("Date: " + entry.getKey() + ", Total Amount: " + entry.getValue());
	        }

	        return dateTotalAmounts;
	    } catch (SQLException e) {
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	}


}

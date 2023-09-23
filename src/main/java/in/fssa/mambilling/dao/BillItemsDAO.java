package in.fssa.mambilling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.model.BillItems;
import in.fssa.mambilling.util.ConnectionUtil;

/**
 * The BillItemsDAO class provides methods for interacting with a database to
 * perform operations related to bill items.
 */
public class BillItemsDAO {

	/**
	 * Creates bill items for a specific bill in the database.
	 *
	 * @param billId    The ID of the bill for which bill items are being created.
	 * @param billItems A List of BillItems objects representing the items to be
	 *                  added to the bill.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public void create(int billId, List<BillItems> billItems) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO bill_items ( bill_id , product_id , price_id , quantity ) VALUES ( ?, ?, ?, ? );";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query); 

			for (BillItems item : billItems) {

				ps.setInt(1, billId);
				ps.setInt(2, item.getProductId());
				ps.setInt(3, item.getPriceId());
				ps.setInt(4, item.getQuantity());

				ps.executeUpdate();
			}

			System.out.println("Bill Items Successfully Created :)");

		} catch (SQLException e) {

			throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Retrieves a list of bill items associated with a specific bill.
	 *
	 * @param billId The ID of the bill for which bill items are being retrieved.
	 * @return A List of BillItems objects representing bill items associated with
	 *         the bill.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public List<BillItems> findAllByBillId(int billId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<BillItems> billItemsList = null;

		try {
			String query = "SELECT product_id , price_id , quantity FROM bill_items WHERE bill_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, billId);
			rs = ps.executeQuery();

			billItemsList = new ArrayList<BillItems>();

			while (rs.next()) {

				BillItems billItem = new BillItems(rs.getInt("product_id"), rs.getInt("price_id"),
						rs.getInt("quantity"));
				billItemsList.add(billItem);
			}
		} catch (SQLException e) {

			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return billItemsList;
	}
	
	/**
	 * Deletes a bill item from the database.
	 *
	 * @param billItemId The ID of the bill item to be deleted.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public void deleteBillItem(int billItemId) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        String deleteQuery = "DELETE FROM bill_items WHERE bill_id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(deleteQuery);
	        ps.setInt(1, billItemId);
	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new PersistanceException("Bill item not found .");
	        }

	        System.out.println("Bill item Successfully deleted :)");

	    } catch (SQLException e) {
	        throw new PersistanceException(e.getMessage());

	    } finally {
	        ConnectionUtil.close(con, ps);
	    }
	}


}

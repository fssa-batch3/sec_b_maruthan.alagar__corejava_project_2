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

        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT DISTINCT DATE(timestamp) AS bill_date FROM bills");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String dateToQuery = rs.getString("bill_date");
                double totalAmount = 0.0;

                try (PreparedStatement billIdsStmt = con.prepareStatement("SELECT id FROM bills WHERE DATE(timestamp) = ?");
                     PreparedStatement billItemsStmt = con.prepareStatement("SELECT bi.quantity, p.mrp, p.tax, p.discount FROM bill_items bi INNER JOIN price p ON bi.product_id = p.product_id WHERE bi.bill_id = ?")) {

                    billIdsStmt.setString(1, dateToQuery);
                    ResultSet billIdsResult = billIdsStmt.executeQuery();

                    while (billIdsResult.next()) {
                        String billId = billIdsResult.getString("id");

                        billItemsStmt.setString(1, billId);
                        ResultSet billItemsResult = billItemsStmt.executeQuery();

                        while (billItemsResult.next()) {
                            int quantity = billItemsResult.getInt("quantity");
                            double mrp = billItemsResult.getDouble("mrp");
                            double tax = billItemsResult.getDouble("tax");
                            double discount = billItemsResult.getDouble("discount");

                            double taxAmount = (mrp * tax / 100.0);
                            double discountAmount = (mrp * discount / 100.0);
                            double price = (mrp + taxAmount - discountAmount) * quantity;
                            if (price % 1 >= 0.4) {
                            	price = Math.ceil(price);
                				}
                            totalAmount += price;
                        }
                    }
                }

                dateTotalAmounts.put(dateToQuery, totalAmount);
            }
        } catch (SQLException e) {
            throw new PersistanceException(e.getMessage());
        }

        return dateTotalAmounts;
    }
}

package in.fssa.mambilling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import in.fssa.mambilling.exception.PersistanceException;
import in.fssa.mambilling.model.Bill;
import in.fssa.mambilling.util.ConnectionUtil;

/**
 * The BillDAO class provides methods for interacting with a database to perform
 * operations related to bills.
 */
public class BillDAO {

	/**
	 * Retrieves a list of all bills from the database.
	 *
	 * @return A List of Bill objects representing all bills in the database.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public List<Bill> findAll() throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Bill> billList = null;
		try {
			String query = "SELECT timeStamp , id, user_id FROM bills";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			billList = new ArrayList<>();
			while (rs.next()) {

				Timestamp timestamp = rs.getTimestamp("timeStamp");
				Instant instant = timestamp.toInstant();
				ZoneId zoneId = ZoneId.systemDefault();
				LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
				
				Bill newbill = new Bill(localDateTime, rs.getInt("id"),rs.getInt("user_id"));
				billList.add(newbill);
			}
		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return billList;

	}

	/**
	 * Creates a new bill in the database for a given user.
	 *
	 * @param userId The ID of the user for whom the bill is being created.
	 * @return The ID of the newly created bill.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */

	public int create(int userId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int billId = 0;

		try {
			String billquery = "INSERT INTO bills (user_id) VALUES (?);";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(billquery, java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userId);
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				billId = rs.getInt(1);
			}

			System.out.println("Bill Successfully Created :)");

		} catch (SQLException e) {
				throw new PersistanceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return billId;
	}

	/**
	 * Deletes a bill with the specified ID from the database.
	 *
	 * @param newId The ID of the bill to be deleted.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public void dropRow(int newId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "DELETE FROM bills WHERE (id = ?);";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, newId);

			ps.executeUpdate();

			System.out.println("Bill Successfully Deleted :)");

		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Retrieves a list of bills that were created within the last 10 minutes.
	 *
	 * @return A List of Bill objects representing recent bills.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public List<Bill> findAllRecentBills() throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Bill> recentBillList = null;

		try {

			String query = "SELECT timeStamp ,id ,user_id FROM bills WHERE timestamp >= NOW() - INTERVAL 10 MINUTE ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			recentBillList = new ArrayList<>();

			while (rs.next()) {
				Timestamp timestamp = rs.getTimestamp("timeStamp");
				Instant instant = timestamp.toInstant();
				ZoneId zoneId = ZoneId.systemDefault();
				LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

				Bill newbill = new Bill(localDateTime, rs.getInt("id"),rs.getInt("user_id"));
				recentBillList.add(newbill);
			}
		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return recentBillList;
	}

	/**
	 * Retrieves a list of bills associated with a specific user.
	 *
	 * @param userId The ID of the user for whom bills are being retrieved.
	 * @return A List of Bill objects representing bills associated with the user.
	 * @throws PersistanceException If there's an issue with the database connection
	 *                              or query execution.
	 */
	public List<Bill> findByUserId(int userId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Bill> userBillList = null;

		try {
			String query = "SELECT timeStamp, id ,user_id FROM bills  WHERE user_id = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			userBillList = new ArrayList<>();

			while (rs.next()) {
				Timestamp timestamp = rs.getTimestamp("timeStamp");
				Instant instant = timestamp.toInstant();
				ZoneId zoneId = ZoneId.systemDefault();
				LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

				Bill newbill = new Bill(localDateTime, rs.getInt("id"),rs.getInt("user_id"));
				userBillList.add(newbill);
			}

		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return userBillList;
	}

}

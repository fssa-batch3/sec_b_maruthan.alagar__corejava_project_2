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

import in.fssa.mambilling.Exception.PersistanceException;
import in.fssa.mambilling.model.Bill;
import in.fssa.mambilling.model.User;
import in.fssa.mambilling.util.ConnectionUtil;

public class BillDAO {
	/**
	 * 
	 * @return
	 * @throws PersistanceException
	 */
	public List<Bill> findAll() throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Bill> billList = null;
		try {
			String query = "SELECT * FROM bill";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			billList = new ArrayList<Bill>();
			while (rs.next()) {
				
				Timestamp timestamp = rs.getTimestamp("time_Stamp");
                 Instant instant = timestamp.toInstant();
                 ZoneId zoneId = ZoneId.systemDefault(); 
                 LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
				Bill newbill = new Bill(localDateTime,rs.getInt("id"));
				billList.add(newbill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return billList;

	}
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws PersistanceException
	 */
	
	public int create(int userId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int billId = 0;

		try {
			String billquery = "INSERT INTO bill (user_id) VALUES (?);";
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
			e.printStackTrace();
			if (e.getMessage().contains("Duplicate entry")) {
				throw new PersistanceException("Duplicate constraint");
			} else {
				System.out.println(e.getMessage());
				throw new PersistanceException(e.getMessage());
			}

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return billId;
	}

}

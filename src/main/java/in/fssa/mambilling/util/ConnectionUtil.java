package in.fssa.mambilling.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {

		Dotenv env = Dotenv.load();

		String url = env.get("DATABASE_HOSTNAME");
		String username = env.get("DATABASE_USERNAME");
		String password = env.get("DATABASE_PASSWORD");

		Connection connection = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new SQLException(e);
		}

		return connection;

	}

	public static void close(Connection newConnection, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (newConnection != null) {
				newConnection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection newConnection, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (newConnection != null) {
				newConnection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

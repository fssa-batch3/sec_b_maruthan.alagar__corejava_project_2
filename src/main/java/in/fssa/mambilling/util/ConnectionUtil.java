package in.fssa.mambilling.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * The ConnectionUtil class provides utility methods for managing database
 * connections.
 */
public class ConnectionUtil {
	/**
	 * Establishes a connection to the database using environment variables or
	 * configuration.
	 *
	 * @return A Connection object representing the database connection.
	 * @throws SQLException If a database connection cannot be established.
	 */
	public static Connection getConnection() throws SQLException {

		String url;
		String userName;
		String passWord;

		if (System.getenv("CI") != null) {
			url = System.getenv("DATABASE_HOSTNAME");
			userName = System.getenv("DATABASE_USERNAME");
			passWord = System.getenv("DATABASE_PASSWORD");
		} else {
			Dotenv env = Dotenv.load();
			url = env.get("DATABASE_HOSTNAME");
			userName = env.get("DATABASE_USERNAME");
			passWord = env.get("DATABASE_PASSWORD");
		}

		Connection connection = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, passWord);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new SQLException(e);
		}

		return connection;

	}

	/**
	 * Closes a database connection and a prepared statement.
	 *
	 * @param newConnection The Connection object to close.
	 * @param ps            The PreparedStatement object to close.
	 */
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

	/**
	 * Closes a database connection, a prepared statement, and a result set.
	 *
	 * @param newConnection The Connection object to close.
	 * @param ps            The PreparedStatement object to close.
	 * @param rs            The ResultSet object to close.
	 */
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

package in.fssa.mambilling.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {


		String url;
        String userName;
        String passWord;

        System.out.println(System.getenv("CI"));
        
        if (System.getenv("CI") != null) {
        	System.out.println("Consuming System.getenv() method");
            url = System.getenv("DATABASE_HOSTNAME");
            userName = System.getenv("DATABASE_USERNAME");
            passWord = System.getenv("DATABASE_PASSWORD");
        } else {
        	System.out.println("Consuming Dotenv.load() method");
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

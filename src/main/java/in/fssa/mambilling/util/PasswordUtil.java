package in.fssa.mambilling.util;

import java.util.Base64;

public class PasswordUtil {

	private static final String KEY = System.getenv("DATABASE_PASSWORD");

	public String encodePassword(String password) {
		try {
			// Convert the password and key to byte arrays
			byte[] passwordBytes = password.getBytes("UTF-8");
			byte[] keyBytes = KEY.getBytes("UTF-8");

			// Perform XOR operation
			for (int i = 0; i < passwordBytes.length; i++) {
				passwordBytes[i] ^= keyBytes[i % keyBytes.length];
			}
			// Encode the result to Base64
			byte[] encodedBytes = Base64.getEncoder().encode(passwordBytes);
			return new String(encodedBytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

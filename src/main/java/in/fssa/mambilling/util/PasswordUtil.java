package in.fssa.mambilling.util;

import java.util.Base64;

public class PasswordUtil {

	private static final String KEY = "mam_billing_secret_key";


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

	public String decodePassword(String encodedPassword) {
		try {
			// Decode the Base64-encoded password to byte array
			byte[] encodedBytes = encodedPassword.getBytes("UTF-8");
			byte[] passwordBytes = Base64.getDecoder().decode(encodedBytes);
			byte[] keyBytes = KEY.getBytes("UTF-8");

			// Perform XOR operation to decode
			for (int i = 0; i < passwordBytes.length; i++) {
				passwordBytes[i] ^= keyBytes[i % keyBytes.length];
			}

			// Convert byte array back to string
			return new String(passwordBytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

package in.fssa.mambilling.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
			Logger.error(e);
			return null;
		}
	}

	
	
	 public static String getSecurePassword(String password, byte[] salt) {

	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            md.update(salt);
	            byte[] bytes = md.digest(password.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < bytes.length; i++) {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	        	Logger.error(e);
	        }
	        return generatedPassword;
	    }

	    private static byte[] getSalt() throws NoSuchAlgorithmException {
	        SecureRandom random = new SecureRandom();
	        byte[] salt = new byte[16];
	        random.nextBytes(salt);
	        return salt;
	    }
	    
	    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
	    	byte[] salt = getSalt();
	    	String newPass = getSecurePassword(password, salt);
			return newPass;
	    	
	    }
}

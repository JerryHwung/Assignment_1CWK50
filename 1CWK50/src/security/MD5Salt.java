package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

// Reference to https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
public class MD5Salt {
	 
	public static String hashIt(String oriPword, byte[] salt)
	{
		String hashedPword = null;
		try {
			// create MessageDigest instance
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(salt);
			//Get the hash's bytes 
			byte[] bytes = md.digest(oriPword.getBytes());
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			 hashedPword = sb.toString();
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashedPword;
	}
	// Add salt
    public static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        // Use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        // Create array for salt
        byte[] salt = new byte[16];
        // Get a random salt
        sr.nextBytes(salt);
        // return salt
        return salt;
    }
}

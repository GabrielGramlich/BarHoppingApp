import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class DontHopUnlessISaySo {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException { }

    public static String getSecurePassword(String passwordToHash, String seasoning)
    {
        String generatedPassword = null;
        try {
            byte[] salt = seasoning.getBytes();

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static String getSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            String seasoning = new String(salt);
            return seasoning;
        }
        catch (NoSuchAlgorithmException ex) {
        System.out.println("Uh, oh... " + ex);
        }
        catch (NoSuchProviderException ex) {
        System.out.println("Uh, oh... " + ex);
        }
    }
}

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import static input.InputUtils.stringInput;

public class DontHopUnlessISaySo {
    public static void main(String[] args) { }

    public static boolean haveWeMet() {
        //TODO check if user is already signed in

        return false;
    }

    public static boolean whosThere (String username, String potatoes) {
        boolean iBelieveYou;

        String seasoning = AllYourDatabaseAreBelongToDrunks.needThatSalt(username);
        String databaseCredentials = AllYourDatabaseAreBelongToDrunks.needThatPassword(username);

        String saltyHashBrowns = nothingCuresAHangoverLikeATastyPassword(potatoes, seasoning);

        if (saltyHashBrowns.equals(databaseCredentials)) {
            iBelieveYou = true;
        } else {
            iBelieveYou = false;
        }

        return iBelieveYou;
    }

    public static String nothingCuresAHangoverLikeATastyPassword(String potatoes, String seasoning) {
        String hashbrowns = null;
        try {
            byte[] salt = seasoning.getBytes();

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(potatoes.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashbrowns = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashbrowns;
    }

    public static String worthYourWeightInEncryption() {
        try {
            SecureRandom sr = SecureRandom.getInstanceStrong();
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            String seasoning = new String(salt);
            return seasoning;
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Uh, oh... " + ex);
            String failure = "";
            return failure;
        }
    }
}

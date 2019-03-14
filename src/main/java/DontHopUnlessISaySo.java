import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import static input.InputUtils.stringInput;

public class DontHopUnlessISaySo {
    public static void main(String[] args) { }

//    public static boolean haveWeMet() {
//        //TODO check if user is already signed in
//    }
//    public static boolean whosThere() {
//        //TODO let them sign in
//        String username = stringInput("Enter your username:");
//        String password = stringInput("Enter your password:");
//
//        boolean youToldTheTruth = youLiar(username, password);
//
//        return youToldTheTruth;
//    }
//    public static boolean youLiar (String username, String potatoes) {
//        //TODO get salt from db
//        //TODO get hashed password from db
//        //TODO check credentials from user database
//        boolean iBelieveYou;
//
//        String saltyHashBrowns = nothingCuresAHangoverLikeATastyPassword(potatoes, seasoning);
//
//        if (saltyHashBrowns == databaseCredentials) {
//            iBelieveYou = true;
//        } else {
//            iBelieveYou = false;
//        }
//
//        return iBelieveYou;
//    }
//    public static String nothingCuresAHangoverLikeATastyPassword(String potatoes, String seasoning)
//    {
//        String hashbrowns = null;
//        try {
//            byte[] salt = seasoning.getBytes();
//
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(salt);
//            byte[] bytes = md.digest(potatoes.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (byte i : bytes)
//            {
//                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//            }
//            hashbrowns = sb.toString();
//        }
//        catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return hashbrowns;
//    }
//    public static String worthYourWeightInEncryption() {
//        try {
//            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
//            byte[] salt = new byte[16];
//            sr.nextBytes(salt);
//            String seasoning = new String(salt);
//            return seasoning;
//        }
//        catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
//        System.out.println("Uh, oh... " + ex);
//        }
//    }
}

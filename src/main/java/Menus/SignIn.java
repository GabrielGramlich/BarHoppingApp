package Menus;

import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.selectIntegerWithString;
import static Menus.CreateAccount.createAccountLogin;
import static Menus.OwnerMenu.ownerMenu;
import static Menus.PatronMenu.userMenu;
import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

public class SignIn {
    //TODO Comment your code, you dick

    public static String username;
    public static Integer loginID;
    public static Integer ownerID;
    public static String password;
    public static String salt;
    public static String email;
    public static String locationName;
    public static Integer locationID;
    public static Integer drinkID;
    public static String drinkSelection;
    public static boolean hold = true;
    public static boolean go = false;

    public static void main(String[] args) {
        MainMenu mainGui = new MainMenu();

        while (hold) { }

        while (go) {
            go = DontHopUnlessISaySo.whosThere(username, password);
            if (go) {
                SignInMenu signInGui = new SignInMenu();
            }
        }

        loginID = selectIntegerWithString("Login_Credential_ID",
                "Login_Credentials", "Username", username);
        System.out.println("success.");
        userOrOwner();
    }

    public static void userOrOwner() {
        Integer accountType = selectIntegerWithString("Account_Type",
                "Login_Credentials", "Username", username);

        if (accountType == 1) {
            ownerMenu();
        } else if (accountType == 0) {
            userMenu();
        }
    }
}

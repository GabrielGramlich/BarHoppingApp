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

    public static void main(String[] args) {
        boolean weHave = DontHopUnlessISaySo.haveWeMet();
        boolean quit = true;
        username = "";
        password = "";
        // TODO get username and password if already signed in #this will happen when it's a real app

        boolean newUser = yesNoInput("Are you a new member?");

        if (newUser) {
            quit = createAccountLogin();
        }

        while (!weHave) {
            while (quit) {
                username = stringInput("Enter your username:");
                password = stringInput("Enter your password:");
                quit = DontHopUnlessISaySo.whosThere(username, password);
                if (quit) {
                    boolean signUp = yesNoInput("Username doesn't exit. Would you like to sign up?");
                    if (signUp) {
                        quit = createAccountLogin();
                    }
                }
            }
            weHave = true;
        }

        loginID = selectIntegerWithString("Login_Credential_ID",
                "Login_Credentials", "Username", username);
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

package Menus;

import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.*;
import static AllYourDatabaseAreBelongToDrunks.UpdateStatementCreation.updateString;
import static GetDrink.DrinkingFunTime.letsGetLit;
import static Menus.CreateAccount.getUserPreferences;
import static Menus.DeleteAccount.deleteAccount;
import static Menus.SignIn.loginID;
import static Menus.SignIn.username;
import static Menus.SignIn.salt;
import static Menus.ValidateData.isValidEmail;
import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

public class PatronMenu {
    public static void main(String[] args) { }

    public static void userMenu() {
        boolean go = true;
        boolean leave = false;

        while (go) {
            String decision = stringInput("What you want? Drink or account?");
            if (decision.equals("drink")) {
                Integer userID = selectInteger("User_ID", "Users",
                        "Login_Credential_ID", loginID);
                letsGetLit(userID);
            } else if (decision.equals("account")) {
                String otherDecision = stringInput("Change it or delete it?");
                if (otherDecision.equals("change")) {
                    changeUserSettings();
                } else if (otherDecision.equals("delete")) {
                    deleteAccount();
                }
            } else {
                leave = yesNoInput("You done yet?");
            }
            if (leave) {
                go = false;
            }
        }
    }

    public static void changeUserSettings() {
        do {
            String toBeUpdated = stringInput("Change email, name, username, password, or preferences?");
            String newInfo = "";

            if (toBeUpdated.equals("preferences")) {
                getUserPreferences(false);
            } else if (toBeUpdated.equals("email")) {
                newInfo = stringInput("New email?");
                while (!isValidEmail(newInfo)) {
                    newInfo = stringInput("Wrong. Try again.");
                }
                toBeUpdated = "Email_Address";
            } else if (toBeUpdated.equals("name")) {
                newInfo = stringInput("New name?");
            } else if (toBeUpdated.equals("username")) {
                newInfo = stringInput("New username?");
            } else if (toBeUpdated.equals("password")) {
                String newPassword = stringInput("New password?");

                salt = selectStringWithString("Salt",
                        "Login_Credentials", "Username", username);
                newInfo = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(newPassword, salt);
            }

            if (toBeUpdated.equals("name")) {
                loginID = selectIntegerWithString("Login_Credential_ID",
                        "Login_Credentials", "Username", username);
                Integer userID = selectInteger("User_ID", "Users",
                        "Login_Credential_ID", loginID);
                Integer spaceIndex = newInfo.indexOf(" ");
                String first = newInfo.substring(0, spaceIndex);
                String last = newInfo.substring(spaceIndex + 1);

                updateString("Users", "First_Name", first,
                        "User_ID", userID);
                updateString("Users", "Last_Name", last,
                        "User_ID", userID);
            } else {
                Integer loginID = selectIntegerWithString(
                        "Login_Credential_ID", "Login_Credentials", "Username", username);
                updateString("Login_Credentials", toBeUpdated, newInfo,
                        "Login_Credential_ID", loginID);
            }
        } while (!yesNoInput("You done yet?"));

        System.out.println("Bout time.");
    }
}

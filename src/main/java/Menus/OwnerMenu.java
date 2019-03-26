package Menus;

import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.*;
import static AllYourDatabaseAreBelongToDrunks.UpdateStatementCreation.updateString;
import static Menus.DeleteAccount.deleteAccount;
import static Menus.DrinkMenu.ownerDrinksMenu;
import static Menus.LocationMenu.ownerLocationsMenu;
import static Menus.SignIn.loginID;
import static Menus.SignIn.ownerID;
import static Menus.SignIn.username;
import static Menus.SignIn.salt;
import static Menus.ValidateData.isValidEmail;
import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

public class OwnerMenu {
    public static void main(String[] args) { }

    public static void ownerMenu() {
        boolean go = true;
        boolean leave = false;

        while (go) {
            loginID = selectIntegerWithString("Login_Credential_ID",
                    "Login_Credentials", "Username", username);
            ownerID = selectInteger("Owner_ID", "Owners",
                    "Login_Credential_ID", loginID);
            String decision = stringInput("Does master want the account, location or drink settings?");

            if (decision.equals("account")) {
                String otherDecision = stringInput("Delete or change account?");
                if (otherDecision.equals("delete")) {
                    deleteAccount();
                } else if (otherDecision.equals("change")) {
                    changeOwnerSettings();
                }
            } else if (decision.equals("location")) {
                ownerLocationsMenu();
            } else if (decision.equals("drink")) {
                ownerDrinksMenu();
            } else {
                leave = yesNoInput("Would you like to leave?");
            }
            if (leave) {
                go = false;
            }
        }
    }

    public static void changeOwnerSettings() {
        do {
            String toBeUpdated = stringInput("Change email, username, password, name, contact number or " +
                    "contact email?");
            String newInfo = "";

            if (toBeUpdated.equals("email")) {
                newInfo = stringInput("New email?");
                toBeUpdated = "Email_Address";
            } else if (toBeUpdated.equals("username")) {
                newInfo = stringInput("New username?");
            } else if (toBeUpdated.equals("password")) {
                String newPassword = stringInput("New password?");
                salt = selectStringWithString("Salt",
                        "Login_Credentials", "Username", username);
                newInfo = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(newPassword, salt);
            } else if (toBeUpdated.equals("name")) {
                newInfo = stringInput("New name?");
            } else if (toBeUpdated.equals("contact number")) {
                newInfo = stringInput("New contact number?");
                toBeUpdated = "Contact_Number";
            } else if (toBeUpdated.equals("contact email")) {
                newInfo = stringInput("New contact email?");
                toBeUpdated = "Contact_Email";
                while (!isValidEmail(newInfo)) {
                    newInfo = stringInput("Invalid email address. Please enter another.");
                }
            }

            loginID = selectIntegerWithString("Login_Credential_ID",
                    "Login_Credentials", "Username", username);
            Integer ownerID = selectInteger("Owner_ID", "Owners",
                    "Login_Credential_ID", loginID);

            if (toBeUpdated.equals("name")) {
                Integer spaceIndex = newInfo.indexOf(" ");
                String first = newInfo.substring(0, spaceIndex);
                String last = newInfo.substring(spaceIndex + 1);

                updateString("Owners", "First_Name", first,
                        "Owner_ID", ownerID);
                updateString("Owners", "Last_Name", last,
                        "Owner_ID", ownerID);
            } else if (toBeUpdated.equals("Contact_Number")) {
                updateString("Owners", "Contact_Number", newInfo,
                        "Owner_ID", ownerID);
            } else if (toBeUpdated.equals("Contact_Email")) {
                updateString("Owners", "Contact_Email", newInfo,
                        "Owner_ID", ownerID);
            } else {
                updateString("Owners", toBeUpdated, newInfo,
                        "Owner_ID", ownerID);
            }
        } while (yesNoInput("Do you still require assistance, sir?"));

        System.out.println("It's been a pleasure.");
    }
}

package Menus;

import java.util.ArrayList;

import static AllYourDatabaseAreBelongToDrunks.InsertStatementCreation.*;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.selectInteger;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.selectIntegerWithString;
import static Menus.SignIn.email;
import static Menus.SignIn.loginID;
import static Menus.SignIn.username;
import static Menus.SignIn.password;
import static Menus.SignIn.salt;
import static Menus.ValidateData.isValidEmail;
import static input.InputUtils.*;

public class CreateAccount {
    public static void main(String[] args) { }

    public static boolean createAccountLogin() {
        Integer alreadyChosen = 666;
        password = "";
        boolean owner = yesNoInput("You a corporate fat cat?");
        boolean quit = false;

        while (alreadyChosen != 0) {
            username = stringInput("Username. Gimme.");
            password = stringInput("Oy. Password, guvna!");
            alreadyChosen = selectIntegerWithString("Account_Type",
                    "Login_Credentials", "Username", username);
            if (alreadyChosen != 0) {
                boolean decision = yesNoInput("Username already chosen. Would you like to sign in?");
                if (decision) {
                    alreadyChosen = 0;
                    quit = true;
                }
            }
        }

        if (!quit) {
            email = stringInput("Now the email.");

            while (!isValidEmail(email)) {
                email = stringInput("Wrong. Try again.");
            }

            salt = DontHopUnlessISaySo.worthYourWeightInEncryption();
            String hashedPassword = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(password, salt);

            loginID = whoAreYou(owner, username, email, hashedPassword, salt);

            if (owner) {
                createOwnerAccount();
            } else {
                createUserAccount();
            }
        }
        return quit;
    }

    public static void createUserAccount() {
        String firstName = stringInput("What's your first name? It's Douche, isn't it?");
        String lastName = stringInput("Last name, Bag.");

        greetingsFriend(firstName, lastName, loginID);

        getUserPreferences(true);
        setBaseUserPreferences();
    }

    public static void createOwnerAccount() {
        String firstName = stringInput("What's your first, sir");
        String lastName = stringInput("And your surname?");
        Double contactNumber = Double.valueOf(stringInput(("What number can we contact you at? (0005550000)")));
        String contactEmail = email;
        if (!yesNoInput("Can we contact you at the email address previously provided?")) {
            contactEmail = stringInput("What email address would you like to use for correspondence?");
            while (!isValidEmail(contactEmail)) {
                contactEmail = stringInput("Invalid email address. Please enter another.");
            }
        }
        helloBoss(loginID, firstName, lastName, contactNumber, contactEmail);
    }

    public static void getUserPreferences(boolean firstTime) {
        ArrayList<String> allergies = new ArrayList<>();

        if (firstTime) {
            if (yesNoInput("Any allergies?")) {
                allergies.add(stringInput("What'll kill ya?"));
                while (yesNoInput("Anything else?")) {
                    allergies.add(stringInput("What'll kill ya?"));
                }
            }
        }

        String topShelf = stringInput("Favorite spirit?");
        boolean weakOrStrong = yesNoInput("Do you have hair on your chest?");
        boolean deepPockets = !yesNoInput("You broke, homie?");
        boolean youFancy = yesNoInput("You a fan of mixology?");
        Integer importantPreference = intInput("What's most important, strength (1), price (2), " +
                "complexity (3)?");
        Integer unimportantPreference = intInput("What's least important, strength (1), price (2), " +
                "complexity (3)?");

        Integer userID = selectInteger("User_ID",
                "Users", "Login_Credential_ID", loginID);
        youGotSomeWeirdKinks(userID, allergies, topShelf, weakOrStrong,
                deepPockets, youFancy, importantPreference, unimportantPreference);
    }

    public static void setBaseUserPreferences() {
        checkPreferenceSetup();
        Integer userID = selectInteger("User_ID", "Users",
                "Login_Credential_ID", loginID);
        for (int i = 1; i < 12; i++) {
            youHaveNoPersonalityWhatsoever(i, userID);
        }
    }

    public static void checkPreferenceSetup() {
        Integer preferenceID = selectIntegerWithString("Preference_ID",
                "Preferences", "Name", "Alcohol content");
        if (preferenceID == 0) {
            youreSayingPeopleLikeThat("Alcohol content", "drink");
            youreSayingPeopleLikeThat("Price", "drink");
            youreSayingPeopleLikeThat("Complexity", "drink");
            youreSayingPeopleLikeThat("Spirit forward", "drink");
            youreSayingPeopleLikeThat("Refreshing", "drink");
            youreSayingPeopleLikeThat("Type 1.1", "drink");
            youreSayingPeopleLikeThat("Type 1.2", "drink");
            youreSayingPeopleLikeThat("Type 1.3", "drink");
            youreSayingPeopleLikeThat("Type 2.1", "drink");
            youreSayingPeopleLikeThat("Type 2.2", "drink");
            youreSayingPeopleLikeThat("Type 2.3", "drink");
        }
    }
}

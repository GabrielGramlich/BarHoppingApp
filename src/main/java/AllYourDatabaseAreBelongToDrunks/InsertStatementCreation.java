package AllYourDatabaseAreBelongToDrunks;

import java.util.ArrayList;

import static AllYourDatabaseAreBelongToDrunks.GenericStatementExecution.dontNeedThat;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.selectIntegerWithString;
import static input.InputUtils.stringInput;

public class InsertStatementCreation {
    public static void main(String[] args) { }

    public static void helloBoss(Integer loginID, String first, String last, Double number, String email) {
        // Creating a new user
        String insertDataSql = "INSERT INTO Owners (First_Name, Last_Name, Contact_Number, Contact_Email, " +
                "Login_Credential_ID) VALUES(\"" + first + "\", \"" + last + "\", " + number + ", \"" + email + "\", "
                + loginID + ");";
        dontNeedThat(insertDataSql);
    }

    public static void welcomeToYourKingdom(Integer ownerID, String name, Double number, String street, String city,
                                            String state, Double zip) {
        // Creating a new location
        String insertDataSql = "INSERT INTO Locations (Owner_ID, Name, Phone_Number, Street, City, State, Zip) " +
                "VALUES (" + ownerID + ", \"" + name + "\", " + number + ", \"" + street + "\", \"" + city + "\", \""
                + state + "\", " + zip + ");";
        dontNeedThat(insertDataSql);
    }

    public static void IDidntKnowKingdomsHadHours(Integer locationID, ArrayList<String> days,
                                                  ArrayList<String> openHours, ArrayList<String> closeHours,
                                                  ArrayList<String> speHoursStart, ArrayList<String> speHoursEnd,
                                                  ArrayList<Double> speHoursDiscount) {
        // Creating a new calendar entry for a location
        for (int i = 0; i < days.size(); i++) {
            String insertDataSql = "INSERT INTO Calendar (Location_ID, Day_of_Week, Time_Open, Time_Close, " +
                    "Specialty_Hour_Start, Specialty_Hour_End, Specialty_Discount) VALUES (" + locationID + ", \""
                    + days.get(i) + "\", TIME_FORMAT(CONVERT(\"" + openHours.get(i) + "\", TIME), \"%H:%i\"), " +
                    "TIME_FORMAT(CONVERT(\"" + closeHours.get(i) + "\", TIME), \"%H:%i\"), TIME_FORMAT(CONVERT(\""
                    + speHoursStart.get(i) + "\", TIME), \"%H:%i\"), TIME_FORMAT(CONVERT(\"" + speHoursEnd.get(i)
                    + "\", TIME), \"%H:%i\"), " + speHoursDiscount.get(i) + ");";
            dontNeedThat(insertDataSql);
        }
    }

    public static Integer whoAreYou(Boolean accountType, String username, String emailAddress, String password,
                                    String salt) {
        // Storing log in credentials
        String insertDataSql = "INSERT INTO Login_Credentials (Account_Type, Username, Email_Address, Password, " +
                "Salt) VALUES(" + accountType + ", \"" + username + "\", \"" + emailAddress + "\", \"" + password
                + "\", \"" + salt + "\");";
        dontNeedThat(insertDataSql);

        Integer credentialsID = selectIntegerWithString("Login_Credential_ID",
                "Login_Credentials", "Username", username);
        return credentialsID;
    }

    public static void greetingsFriend(String first, String last, Integer loginID) {
        // Creating a new user
        String insertDataSql = "INSERT INTO Users (First_Name, Last_Name, Login_Credential_ID) VALUES(\"" + first
                + "\", \"" + last + "\", " + loginID + ");";
        dontNeedThat(insertDataSql);
    }

    public static void thatSoundsDelicious(String name, String startDate, String endDate, Integer strength,
                                           Double price, Integer complexity, boolean spiritForwardOrRefreshing,
                                           Integer type) {
        // Creating a new drink
        String insertDataSql = "INSERT INTO Drinks (Name, Availability_Start, Availability_End, Alcohol_Content, " +
                "Price, Complexity, Spirit_Forward_or_Refreshing, Type) VALUES(\"" + name + "\", STR_TO_DATE(\""
                + startDate + "\", \"%m/%d/%y\"), STR_TO_DATE(\"" + endDate + "\", \"%m/%d/%y\"), " + strength + ", "
                + price + ", " + complexity + ", " + spiritForwardOrRefreshing + ", " + type + ");";
        dontNeedThat(insertDataSql);
    }

    public static void whatsInItThough(ArrayList<String> ingredients, Integer drinkID) {
        // Creating a new recipe entry for all ingredients in a drink
        for (String ingredient : ingredients) {
            Integer ingredientID = selectIntegerWithString("Ingredient_Name", "Ingredients",
                    "Name", ingredient);
            if (ingredientID == 0) {
                String ingredientType = stringInput("Is this ingredient a liquor, liqueur, mixer or garnish?");
                String sqlStatement = "INSERT INTO Ingredients (Name, Type) VALUES(\"" + ingredient + "\", \""
                        + ingredientType + "\");";
                dontNeedThat(sqlStatement);
                ingredientID = selectIntegerWithString("Ingredient_Name", "Ingredients",
                        "Name", ingredient);
            }
            String sqlStatement = "INSERT INTO Recipes (Ingredient_ID, Drink_ID) VALUES(" + ingredientID + ", "
                    + drinkID + ");";
            dontNeedThat(sqlStatement);
        }
    }

    public static void youGotSomeWeirdKinks(Integer userID, ArrayList<String> allergies, String topShelf,
                                            boolean weakOrStrong, boolean deepPockets, boolean youFancy,
                                            Integer importantPreference, Integer unimportantPreference) {
        // Storing user defined preferences
        if (!allergies.isEmpty()) {
            for (String allergy : allergies) {
                Integer allergyID = selectIntegerWithString("Allergy_ID", "Allergies",
                        "Name", allergy);

                if (allergyID == 0) {
                    String sqlStatement = "INSERT INTO Allergies (Name) VALUES(\"" + allergy + "\");";
                    dontNeedThat(sqlStatement);
                    allergyID = selectIntegerWithString("Allergy_ID", "Allergies",
                            "Name", allergy);
                }

                String sqlStatement = "INSERT INTO User_Allergies (Allergy_ID, User_ID) VALUES(" + allergyID + ", "
                        + userID + ");";
                dontNeedThat(sqlStatement);
            }
        }

        String sqlStatement = "INSERT INTO User_Defined_Preferences (User_ID, Preferred_Liquor, " +
                "Weak_or_Strong, Cheap_or_Pricey, Simple_or_Complex, Important_Preference, Unimportant_Preference) " +
                "VALUES(" + userID + ", \"" + topShelf + "\", " + weakOrStrong + ", " + deepPockets + ", "
                + youFancy + ", " + importantPreference + ", " + unimportantPreference + ");";
        dontNeedThat(sqlStatement);
    }

    public static void wasItGood(Integer userID, Integer drinkID, Integer rating) {
        // Storing drink rating
        String sqlStatement = "INSERT INTO Drink_Preferences (User_ID, Drink_ID, Rating) VALUES(" + userID + ", "
                + drinkID + ", " + rating + ");";
        dontNeedThat(sqlStatement);
    }

    public static void youreSayingPeopleLikeThat(String name, String type) {
        // Creating a new preference
        String sqlStatement = "INSERT INTO Preferences (Name, Type) VALUES(\"" + name + "\", \"" + type + "\");";
        dontNeedThat(sqlStatement);
    }

    public static void yourOpinionIsWrong(Integer preferenceID, Integer userID, Double variable) {
        // Creating a new system defined preference
        String variableString = String.valueOf(String.format("%.3f", variable));
        String sqlStatement = "INSERT INTO System_Defined_Preferences (Preference_ID, User_ID, Variable) VALUES("
                + preferenceID + ", " + userID + ", " + variableString + ");";
        dontNeedThat(sqlStatement);
    }

    public static void youHaveNoPersonalityWhatsoever(Integer preferenceID, Integer userID) {
        // Creating a new blank user
        String sqlStatement = "INSERT INTO System_Defined_Preferences (Preference_ID, User_ID, Variable) VALUES("
                + preferenceID + ", " + userID + ", 5.000);";
        dontNeedThat(sqlStatement);
    }
}

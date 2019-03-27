package GetDrink;

import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static AllYourDatabaseAreBelongToDrunks.InsertStatementCreation.wasItGood;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.*;
import static GetDrink.CheckIntakeLevels.checkingYourIntake;
import static GetDrink.CheckIntakeLevels.cuttingYouOff;
import static GetDrink.FindDrink.pickingYourPoison;
import static GetDrink.UpdateBasePreferences.*;
import static GetDrink.UpdateBasePreferences.updateRatingType;
import static GetDrink.UpdateIngredientPreferences.youreSoPicky;
import static input.InputUtils.intInput;
import static input.InputUtils.yesNoInput;

public class DrinkingFunTime {
    //TODO check for time with drink selection and factor in specialty price with that #this will happen when it's a
    // real app
    //TODO Comment your code, you dick

    public static Integer howMuchHaveYouHad = 0;
    public static LocalDateTime startGettingDrunkTime = null;
    public static Integer LIMIT = 3;
    public static Integer userID;
    public static ArrayList<String> drinks = new ArrayList<>();
    public static ArrayList<String> locations = new ArrayList<>();
    public static String preferredLiquor;
    public static boolean strongPreference;
    public static boolean priceyPreference;
    public static boolean complexPreference;
    public static Integer importantPreference;
    public static Integer unimportantPreference;
    public static Double alcoholContentLow;
    public static Double alcoholContentHigh;
    public static Double priceLow;
    public static Double priceHigh;
    public static Double complexityLow;
    public static Double complexityHigh;
    public static boolean spiritForwardOrRefreshing;
    public static Integer type;
    public static Integer rating;
    public static Integer drinkID;

    public static void main(String[] args) { }

    public static void letsGetLit(Integer ID) {
        userID = ID;
        boolean go = checkingYourIntake();

        if (go) {
            getUserPreferrences();

            spiritForwardOrRefreshing = yesNoInput("Would you like something spirit forward (N), " +
                    "or refreshing (Y)?");
            if (spiritForwardOrRefreshing) {
                type = intInput("Would you prefer type1 (1), type2 (2), or type3 (3)?");
            } else {
                type = intInput("Would you prefer type1 (1), type2 (2), or type3 (3)?");
            }

            pickingYourPoison();
        }
    }

    public static void getUserPreferrences() {
        preferredLiquor = selectString("Preferred_Liquor",
                "User_Defined_Preferences", "User_ID", userID);
        strongPreference = selectBoolean("Weak_or_Strong",
                "User_Defined_Preferences", "User_ID", userID);
        priceyPreference = selectBoolean("Cheap_or_Pricey",
                "User_Defined_Preferences", "User_ID", userID);
        complexPreference = selectBoolean("Simple_or_Complex",
                "User_Defined_Preferences", "User_ID", userID);
        importantPreference = selectInteger("Important_Preference",
                "User_Defined_Preferences", "User_ID", userID);
        unimportantPreference = selectInteger("Unimportant_Preference",
                "User_Defined_Preferences", "User_ID", userID);
    }

    public static void letMeCheckInTheBack(String name) {
        Integer locationID = selectInteger("Location_ID",
                "Drink_Locations", "Drink_ID", drinkID);
        String street = selectString("Street", "Locations",
                "Location_ID", locationID);
        String city = selectString("City", "Locations",
                "Location_ID", locationID);
        String state = selectString("State", "Locations",
                "Location_ID", locationID);
        String zip = selectString("Zip", "Locations",
                "Location_ID", locationID);
        String drinkAddress = street + ", " + city + ", " + state + " " + zip;

        turnLeftAtTheOakTree(drinkAddress, name, locationID);
    }

    public static void turnLeftAtTheOakTree(String address, String drinkName, Integer locationID) {
        String locationName = selectString("Name", "Locations",
                "Location_ID", locationID);
        System.out.println("Here's your drink: " + drinkName);
        System.out.println("You can find it at: " + locationName);

        String map = "http://maps.google.co.in/maps?q=" + address;
        map = map.replace(" ", "+");
        try {
            Desktop.getDesktop().browse(new URL(map).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkPlease(drinkName, locationName);
    }

    public static void checkPlease(String name, String locationName) {
        drinks.add(name);
        locations.add(locationName);

        //TODO wait for users return #this will happen when it's a real app
        boolean stillDrinking = true;

        do {
            stillDrinking = yesNoInput("Are you still drinking?");
        } while (stillDrinking);

        keepEmComingBarkeep(name);
    }


    public static void keepEmComingBarkeep(String name) {
        rating = intInput("On a scale from 1-5, how did you like " + name + "?");
        drinkID = selectIntegerWithString("Drink_ID", "Drinks",
                "Name", name);
        wasItGood(userID, drinkID, rating);

        whatDoYouThinkYoureBetterThanMe();
        youreSoPicky();

        boolean keepEmComing = yesNoInput("Another round?");
        if (keepEmComing) {
            letsGetLit(userID);
        } else {
            cuttingYouOff();
        }
    }

    public static void whatDoYouThinkYoureBetterThanMe() {
        updateRatingAlcoholContent();
        updateRatingPrice(true);
        updateRatingComplexity();
        updateRatingSpiritForwardOrRefreshing();
        updateRatingType();
    }

    public static void theHangoverThisAppEdition() {
        //TODO display the course of the user's night #this will happen when it's a real app
    }
}

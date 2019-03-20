import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

import static input.InputUtils.*;

public class HopThoseBars {
    public static Integer howMuchHaveYouHad = 0;
    public static LocalDateTime startGettingDrunkTime = null;
    public static Integer LIMIT = 3;

    public static Integer userID;
    public static ArrayList<String> drinks = new ArrayList<>();
    public static ArrayList<Integer> locations = new ArrayList<>();

    public static String preferredLiquor;
    public static String nonpreferredLiquor;
    public static boolean strongPreference;
    public static boolean priceyPreference;
    public static boolean complexPreference;

    public static boolean spiritForwardOrRefreshing;
    public static Integer type;

    public static void main(String[] args) { }

    public static void letsGetLit(Integer ID) {
        userID = ID;
        boolean go = checkingYourIntake();

        if (go) {
            getUserPreferrences();

            spiritForwardOrRefreshing = yesNoInput("Would you like something spirit forward (N), " +
                    "or refreshing (Y)?");
            if (spiritForwardOrRefreshing) {
                type = intInput("Would you prefer type (1), type2 (2), or type3 (3)?");
            } else {
                type = intInput("Would you prefer type (1), type2 (2), or type3 (3)?");
            }

            pickingYourPoison();
            howMuchHaveYouHad++;
        }
    }

    public static void getUserPreferrences() {
        preferredLiquor = AllYourDatabaseAreBelongToDrunks.selectString("Preferred_Liquor",
                "User_Defined_Preferences", "User_ID", userID);
        nonpreferredLiquor = AllYourDatabaseAreBelongToDrunks.selectString("Nonpreferred_Liquor",
                "User_Defined_Preferences", "User_ID", userID);
        strongPreference = AllYourDatabaseAreBelongToDrunks.selectBoolean("Weak_or_Strong",
                "User_Defined_Preferences", "User_ID", userID);
        priceyPreference = AllYourDatabaseAreBelongToDrunks.selectBoolean("Cheap_or_Pricey",
                "User_Defined_Preferences", "User_ID", userID);
        complexPreference = AllYourDatabaseAreBelongToDrunks.selectBoolean("Simple_or_Complex",
                "User_Defined_Preferences", "User_ID", userID);
    }

    public static boolean checkingYourIntake() {
        if (startGettingDrunkTime == null) {
            startGettingDrunkTime = LocalDateTime.now();
        }

        LocalDateTime currentTime = LocalDateTime.now();
        double hours = startGettingDrunkTime.until(currentTime, ChronoUnit.HOURS);

        double howMuchHaveYouReallyHad = howMuchHaveYouHad / hours;
        if (hours >= 2 && howMuchHaveYouReallyHad >= LIMIT) {
            cuttingYouOff();
            return false;
        } else {
            return true;
        }
    }

    public static void pickingYourPoison() {
        //TODO figure out how you want to add in user preferences

        ArrayList<Integer> almostRightDrinks = AllYourDatabaseAreBelongToDrunks.selectIntegerArrayListWithBoolean(
                "Drink_ID", "Drinks", "Spirit_Forward_or_Refreshing",
                spiritForwardOrRefreshing);
        ArrayList<Integer> rightDrinks = new ArrayList<>();

        for (Integer drink : almostRightDrinks) {
            rightDrinks.add(AllYourDatabaseAreBelongToDrunks.selectIntegerWithSecondKey("Drink_ID",
                    "Drinks", "Drink_ID", drink, "Type", type));
        }

        Random rand = new Random();
        int randInt = rand.nextInt(rightDrinks.size());

        Integer drinkID = rightDrinks.get(randInt);
        String drinkName = AllYourDatabaseAreBelongToDrunks.selectString("Name", "Drinks",
                "Drink_ID", drinkID);

        letMeCheckInTheBack(drinkName, drinkID);
    }

    public static void letMeCheckInTheBack(String name, Integer ID) {
        Integer locationID = AllYourDatabaseAreBelongToDrunks.selectInteger("Location_ID",
                "Drink_Locations", "Drink_ID", ID);
        String street = AllYourDatabaseAreBelongToDrunks.selectString("Street", "Locations",
                "Location_ID", locationID);
        String city = AllYourDatabaseAreBelongToDrunks.selectString("City", "Locations",
                "Location_ID", locationID);
        String state = AllYourDatabaseAreBelongToDrunks.selectString("State", "Locations",
                "Location_ID", locationID);
        String zip = AllYourDatabaseAreBelongToDrunks.selectString("Zip", "Locations",
                "Location_ID", locationID);
        String drinkAddress = street + ", " + city + ", " + state + " " + zip;

        turnLeftAtTheOakTree(drinkAddress, name, locationID);
    }

    public static void turnLeftAtTheOakTree(String address, String name, Integer locationID) {
        System.out.println("Here's your drink: " + name);

        String map = "http://maps.google.co.in/maps?q=" + address;
        map = map.replace(" ", "+");
        try {
            Desktop.getDesktop().browse(new URL(map).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkPlease(name, locationID);
    }

    public static void checkPlease(String name, Integer locationID) {
        drinks.add(name);
        locations.add(locationID);

        //TODO wait for users return #this will happen when it's a real app
        boolean stillDrinking = true;

        do {
            stillDrinking = yesNoInput("Are you still drinking?");
        } while (stillDrinking);

        keepEmComingBarkeep(name);
    }


    public static void keepEmComingBarkeep(String name) {
        Integer rating = intInput("On a scale from 1-5, how did you like " + name + "?");
        Integer drinkID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Drink_ID", "Drinks", "Name", name);
        AllYourDatabaseAreBelongToDrunks.wasItGood(userID, drinkID, rating);

        //TODO get ingredients and shit and add them to the system defined preferences table

        boolean keepEmComing = yesNoInput("Another round?");
        if (keepEmComing) {
            letsGetLit(userID);
        } else {
            cuttingYouOff();
        }
    }

    public static void cuttingYouOff() {
        //TODO recommend a ride service #this will happen when it's a real app
        try {
            Desktop.getDesktop().browse(new URL("uber.com").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }

        theHangoverThisAppEdition();
    }

    public static void theHangoverThisAppEdition() {
        //TODO display the course of the user's night #this will happen when it's a real app
    }
}

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

        drinkID = rightDrinks.get(randInt);
        String drinkName = AllYourDatabaseAreBelongToDrunks.selectString("Name", "Drinks",
                "Drink_ID", drinkID);

        letMeCheckInTheBack(drinkName);
    }

    public static void letMeCheckInTheBack(String name) {
        Integer locationID = AllYourDatabaseAreBelongToDrunks.selectInteger("Location_ID",
                "Drink_Locations", "Drink_ID", drinkID);
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
        rating = intInput("On a scale from 1-5, how did you like " + name + "?");
        drinkID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Drink_ID", "Drinks",
                "Name", name);
        AllYourDatabaseAreBelongToDrunks.wasItGood(userID, drinkID, rating);

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
        updateRatingPrice();
        updateRatingComplexity();
        updateRatingSpiritForwardOrRefreshing();
        updateRatingType();
    }

    public static void updateRatingAlcoholContent() {
        Integer alcoholContent = AllYourDatabaseAreBelongToDrunks.selectInteger("Alcohol_Content",
                "Drinks", "Drink_ID", drinkID);

        updateRatingInteger(alcoholContent, 1);
    }

    public static void updateRatingPrice() {
        Double price = AllYourDatabaseAreBelongToDrunks.selectDouble("Price", "Drinks",
                "Drink_ID", drinkID);
        Double lowestPrice = AllYourDatabaseAreBelongToDrunks.selectLowestDouble("Price", "Drinks");
        Double highestPrice = AllYourDatabaseAreBelongToDrunks.selectHighestDouble("Price", "Drinks");
        Double averagePrice = AllYourDatabaseAreBelongToDrunks.selectAverageDouble("Price", "Drinks");

        Integer priceVariable = 5;
        if (price < averagePrice) {
            Double highDifference = averagePrice - price;
            Double lowDifference = price - lowestPrice;
            if (highDifference > lowDifference) {
                priceVariable = 1;
            } else {
                priceVariable = 3;
            }
        } else if (price > averagePrice) {
            Double highDifference = highestPrice - price;
            Double lowDifference = price - averagePrice;
            if (highDifference < lowDifference) {
                priceVariable = 9;
            } else {
                priceVariable = 7;
            }
        }

        updateRatingInteger(priceVariable, 2);
    }

    public static void updateRatingComplexity() {
        Integer complexity = AllYourDatabaseAreBelongToDrunks.selectInteger("Complexity", "Drinks",
                "Drink_ID", drinkID);

        updateRatingInteger(complexity, 3);
    }

    public static void updateRatingSpiritForwardOrRefreshing() {
        boolean spiritForwardOrRefreshing = AllYourDatabaseAreBelongToDrunks.selectBoolean(
                "Spirit_Forward_or_Refreshing", "Drinks", "Drink_ID", drinkID);
        Integer preferenceID;
        if (!spiritForwardOrRefreshing) {
            preferenceID = 4;
        } else {
            preferenceID = 5;
        }

        Double currentRating = AllYourDatabaseAreBelongToDrunks.selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", preferenceID,
                "User_ID", userID);
        Double newRating = getNewSimpleRating(currentRating);

        Integer sdpID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithSecondKey(
                "System_Defined_Preference_ID", "System_Defined_Preferences",
                "Preference_ID", preferenceID, "User_ID", userID);
        AllYourDatabaseAreBelongToDrunks.updateDouble("System_Defined_Preferences", "Variable", newRating,
                "System_Defined_Preference_ID", sdpID);
    }

    public static void updateRatingType() {
        boolean spiritForwardOrRefreshing = AllYourDatabaseAreBelongToDrunks.selectBoolean(
                "Spirit_Forward_or_Refreshing", "Drinks", "Drink_ID", drinkID);
        Integer type = AllYourDatabaseAreBelongToDrunks.selectInteger("Type", "Drinks",
                "Drink_ID", drinkID);
        if (spiritForwardOrRefreshing) {
            type +=3;
        }

        Double currentRating = AllYourDatabaseAreBelongToDrunks.selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", (type + 5),
                "User_ID", userID);
        Double newRating = getNewSimpleRating(currentRating);

        Integer sdpID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithSecondKey(
                "System_Defined_Preference_ID", "System_Defined_Preferences",
                "Preference_ID", 1, "User_ID", userID);
        AllYourDatabaseAreBelongToDrunks.updateDouble("System_Defined_Preferences", "Variable", newRating,
                "System_Defined_Preference_ID", sdpID);
    }

    public static void updateRatingInteger(Integer variable, Integer primaryKeyID) {
        int minusOrPlus = 1;
        int difference = 0;
        if (variable < 5) {
            minusOrPlus = 0;
            difference = (5 - variable);
        } else if (variable > 5) {
            minusOrPlus = 2;
            difference = variable - 5;
        }
        Double currentRating = AllYourDatabaseAreBelongToDrunks.selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", primaryKeyID,
                "User_ID", userID);
        Double newRating = getNewComplexRating(difference, minusOrPlus, currentRating);

        Integer sdpID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithSecondKey(
                "System_Defined_Preference_ID", "System_Defined_Preferences",
                "Preference_ID", primaryKeyID, "User_ID", userID);
        AllYourDatabaseAreBelongToDrunks.updateDouble("System_Defined_Preferences", "Variable", newRating,
                "System_Defined_Preference_ID", sdpID);
    }

    public static Double getNewSimpleRating(Double currentRating) {
        if (rating == 2) {
            if (currentRating >= 1.25) {
                currentRating -= .25;
            }
        } else if (rating == 1) {
            if (currentRating >= 1.5) {
                currentRating -= .5;
            }
        } else if (rating == 4) {
            if (currentRating <= 8.75) {
                currentRating += .25;
            }
        } else if (rating == 5) {
            if (currentRating <= 8.5) {
                currentRating += .5;
            }
        }
        return currentRating;
    }

    public static Double getNewComplexRating(Integer difference, Integer minusOrPlus, Double currentRating) {
        if (rating == 2) {
            if (difference == 1 || difference == 2) {
                if (minusOrPlus == 0) {
                    if (currentRating <= 8.875) {
                        currentRating += .125;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating >= 1.125) {
                        currentRating -= .125;
                    }
                }
            } else if (difference == 3 || difference == 4) {
                if (minusOrPlus == 0) {
                    if (currentRating <= 8.75) {
                        currentRating += .25;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating >= 1.25) {
                        currentRating -= .25;
                    }
                }
            }
        } else if (rating == 1) {
            if (difference == 1 || difference == 2) {
                if (minusOrPlus == 0) {
                    if (currentRating <= 8.75) {
                        currentRating += .25;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating >= 1.25) {
                        currentRating -= .25;
                    }
                }
            } else if (difference == 3 || difference == 4) {
                if (minusOrPlus == 0) {
                    if (currentRating <= 8.5) {
                        currentRating += .5;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating >= 1.5) {
                        currentRating -= .5;
                    }
                }
            }
        } else if (rating == 4) {
            if (difference == 1 || difference == 2) {
                if (minusOrPlus == 0) {
                    if (currentRating >= 1.125) {
                        currentRating -= .125;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating <= 8.875) {
                        currentRating += .125;
                    }
                }
            } else if (difference == 3 || difference == 4) {
                if (minusOrPlus == 0) {
                    if (currentRating >= 1.25) {
                        currentRating -= .25;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating <= 8.75) {
                        currentRating += .25;
                    }
                }
            }
        } else if (rating == 5) {
            if (difference == 1 || difference == 2) {
                if (minusOrPlus == 0) {
                    if (currentRating >= 1.25) {
                        currentRating -= .25;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating <= 8.75) {
                        currentRating += .25;
                    }
                }
            } else if (difference == 3 || difference == 4) {
                if (minusOrPlus == 0) {
                    if (currentRating >= 1.5) {
                        currentRating -= .5;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating <= 8.5) {
                        currentRating += .5;
                    }
                }
            }
        }

        return currentRating;
    }

    public static void youreSoPicky() {
        ArrayList<Integer> ingredientIDs = AllYourDatabaseAreBelongToDrunks.selectIntegerArrayList(
                "Ingredient_ID", "Recipes", "Drink_ID", drinkID);
        ArrayList<String> ingredients = new ArrayList<>();

        for (Integer ID: ingredientIDs) {
            ingredients.add(AllYourDatabaseAreBelongToDrunks.selectString("Name", "Ingredients",
                    "Ingredient_ID", ID));
        }

        for (String ingredient : ingredients) {
            Integer sdpID = haveYouEvenTriedIt(ingredient);
            Double currentRating = AllYourDatabaseAreBelongToDrunks.selectDouble("Variable",
                    "System_Defined_Preferences", "System_Defined_Preferences_ID", sdpID);

            Double newRating = getNewSimpleRating(currentRating);
            AllYourDatabaseAreBelongToDrunks.updateDouble("System_Defined_Preferences", "Variable",
                    newRating, "System_Defined_Preferences_ID", sdpID);
        }
    }

    public static Integer haveYouEvenTriedIt(String ingredient) {
        Integer preferenceID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Preference_ID",
                "Preferences", "Name", ingredient);
        if (preferenceID == 0) {
            AllYourDatabaseAreBelongToDrunks.youreSayingPeopleLikeThat(ingredient, "ingredient");
            preferenceID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Preference_ID",
                    "Preferences", "Name", ingredient);
        }

        Integer sdpID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithSecondKey(
                "System_Defined_Preference_ID", "System_Defined_Preferences",
                "Preference_ID", preferenceID, "User_ID", userID);
        if (sdpID == 0) {
            AllYourDatabaseAreBelongToDrunks.youreOpinionIsWrong(preferenceID, userID, 5.0);
            sdpID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithSecondKey("System_Defined_Preference_ID",
                    "System_Defined_Preferences", "Preference_ID", preferenceID,
                    "User_ID", userID);
        }

        return sdpID;
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

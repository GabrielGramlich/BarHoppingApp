import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

import static input.InputUtils.*;

public class HopThoseBars {
    //TODO Comment your code, you dick

    public static Integer howMuchHaveYouHad = 0;
    public static LocalDateTime startGettingDrunkTime = null;
    public static Integer LIMIT = 3;
    public static Integer userID;
    public static ArrayList<String> drinks = new ArrayList<>();
    public static ArrayList<Integer> locations = new ArrayList<>();
    public static String preferredLiquor;
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
                type = intInput("Would you prefer type1 (1), type2 (2), or type3 (3)?");
            } else {
                type = intInput("Would you prefer type1 (1), type2 (2), or type3 (3)?");
            }

            pickingYourPoison();
            howMuchHaveYouHad++;
        }
    }

    public static void getUserPreferrences() {
        preferredLiquor = AllYourDatabaseAreBelongToDrunks.selectString("Preferred_Liquor",
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
//        ArrayList<Integer> localDrinks = getLocalDrinks();
        ArrayList<Integer> startingDrinks = getStartingDrinks();
        ArrayList<Integer> allergenFreeDrinks = getAllergenFreeDrinks(startingDrinks);
        ArrayList<Integer> almostRightDrinks = getAlmostRightDrinks(allergenFreeDrinks);
        ArrayList<Integer> rightDrinks = getRightDrinks(almostRightDrinks);

        Random rand = new Random();
        int randInt = rand.nextInt(rightDrinks.size());

        drinkID = rightDrinks.get(randInt);
        String drinkName = AllYourDatabaseAreBelongToDrunks.selectString("Name", "Drinks",
                "Drink_ID", drinkID);

        letMeCheckInTheBack(drinkName);
    }

//    public static ArrayList<Integer> getLocalDrinks() {
//        //TODO get input on where the user is drinking
//        //TODO get drinks in that area
//    }

    public static ArrayList<Integer> getStartingDrinks() {
        ArrayList<Integer> tempDrinksBoolean = AllYourDatabaseAreBelongToDrunks.selectIntegerArrayListWithBoolean(
                "Drink_ID", "Drinks", "Spirit_Forward_or_Refreshing",
                spiritForwardOrRefreshing);
        ArrayList<Integer> tempDrinksType = AllYourDatabaseAreBelongToDrunks.selectIntegerArrayList("Drink_ID", "Drinks", "Type", type);
        ArrayList<Integer> startingDrinks = new ArrayList<>();

        for (Integer drinkBool : tempDrinksBoolean) {
            for (Integer drinkType : tempDrinksType) {
                if (drinkBool.equals(drinkType)) {
                    startingDrinks.add(drinkBool);
                }
            }
        }

        return startingDrinks;
    }

    public static ArrayList<Integer> getAllergenFreeDrinks(ArrayList<Integer> startingDrinks) {
        ArrayList<Integer> allergenFreeDrinks = new ArrayList<>();
        ArrayList<Integer> allergies = AllYourDatabaseAreBelongToDrunks.selectIntegerArrayList("Allergy_ID", "User_Allergies", "User_ID", userID);
        if (!allergies.isEmpty()) {
            ArrayList<String> allergens = new ArrayList<>();

            for (Integer allergy : allergies) {
                allergens.add(AllYourDatabaseAreBelongToDrunks.selectString("Name", "Allergies", "Allergy_ID", allergy));
            }

            for (Integer drink : startingDrinks) {
                boolean hasAllergen = false;
                ArrayList<Integer> recipe = AllYourDatabaseAreBelongToDrunks.selectIntegerArrayList("Ingredient_ID", "Recipes", "Drink_ID", drink);
                for (Integer ingredient : recipe) {
                    String name = AllYourDatabaseAreBelongToDrunks.selectString("Name", "Ingredients", "Ingredient_ID", ingredient);
                    for (String allergen : allergens) {
                        if (name.equals(allergen)) {
                            hasAllergen = true;
                        }
                    }
                }
                if (!hasAllergen) {
                    allergenFreeDrinks.add(drink);
                }
            }
            return allergenFreeDrinks;
        } else {
            return startingDrinks;
        }
    }

    public static ArrayList<Integer> getAlmostRightDrinks(ArrayList<Integer> allergenFreeDrinks) {
        Double sdpAlcoholContent = AllYourDatabaseAreBelongToDrunks.selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", 1,
                "User_ID", userID);
        Double sdpPrice = AllYourDatabaseAreBelongToDrunks.selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", 2,
                "User_ID", userID);
        Double sdpComplexity = AllYourDatabaseAreBelongToDrunks.selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", 3,
                "User_ID", userID);
        if (strongPreference) {
            sdpAlcoholContent++;
        } else {
            sdpAlcoholContent--;
        }
        if (priceyPreference) {
            sdpPrice++;
        } else {
            sdpPrice--;
        }
        if (complexPreference) {
            sdpComplexity++;
        } else {
            sdpComplexity--;
        }

        Integer allowedVariation = 3;
        Double alcoholContentLow = sdpAlcoholContent - allowedVariation;
        Double alcoholContentHigh = sdpAlcoholContent + allowedVariation;
        Double priceLow = sdpPrice - allowedVariation;
        Double priceHigh = sdpPrice + allowedVariation;
        Double complexityLow = sdpComplexity - allowedVariation;
        Double complexityHigh = sdpComplexity + allowedVariation;

        ArrayList<Integer> almostRightDrinks = new ArrayList<>();
        for (Integer drink : allergenFreeDrinks) {
            drinkID = drink;
            Integer drinkAlcoholContent = AllYourDatabaseAreBelongToDrunks.selectInteger("Alcohol_Content", "Drinks", "Drink_ID", drinkID);
            Integer drinkPrice = updateRatingPrice(false);
            Integer drinkComplexity = AllYourDatabaseAreBelongToDrunks.selectInteger("Complexity", "Drinks", "Drink_ID", drinkID);
            if (drinkAlcoholContent < alcoholContentHigh && drinkAlcoholContent > alcoholContentLow) {
                if (drinkPrice < priceHigh && drinkPrice > priceLow) {
                    if (drinkComplexity < complexityHigh && drinkComplexity > complexityLow) {
                        almostRightDrinks.add(drink);
                    }
                }
            }
        }

        return almostRightDrinks;
    }

    public static ArrayList<Integer> getRightDrinks(ArrayList<Integer> almostRightDrinks) {
        ArrayList<Integer> tempRightDrinks = new ArrayList<>();
        ArrayList<Integer> rightDrinks = new ArrayList<>();
        Integer ingredientID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Ingredient_ID", "Ingredients", "Name", preferredLiquor);
        ArrayList<Integer> drinksWithLiquor = AllYourDatabaseAreBelongToDrunks.selectIntegerArrayList("Drink_ID", "Recipes", "Ingredient_ID", ingredientID);

        for (Integer drink : almostRightDrinks) {
            for (Integer otherDrink : drinksWithLiquor) {
                if (drink.equals(otherDrink)) {
                    tempRightDrinks.add(drink);
                }
            }
        }
        if (tempRightDrinks.isEmpty()) {
            rightDrinks.addAll(almostRightDrinks);
        } else {
            rightDrinks.addAll(tempRightDrinks);
        }

        return rightDrinks;
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
        updateRatingPrice(true);
        updateRatingComplexity();
        updateRatingSpiritForwardOrRefreshing();
        updateRatingType();
    }

    public static void updateRatingAlcoholContent() {
        Integer alcoholContent = AllYourDatabaseAreBelongToDrunks.selectInteger("Alcohol_Content",
                "Drinks", "Drink_ID", drinkID);

        updateRatingInteger(alcoholContent, 1);
    }

    public static Integer updateRatingPrice(boolean actuallyUpdating) {
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

        if (actuallyUpdating) {
            updateRatingInteger(priceVariable, 2);
        }

        return priceVariable;
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
        Double updateValue = .125;
        if (rating == 2) {
            if (difference == 1 || difference == 2) {
                if (minusOrPlus == 0) {
                    if (currentRating <= 9 - updateValue) {
                        currentRating += updateValue;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating >= 1 + updateValue) {
                        currentRating -= updateValue;
                    }
                }
            } else if (difference == 3 || difference == 4) {
                updateValue *= updateValue * 2;
                if (minusOrPlus == 0) {
                    if (currentRating <= 9 - updateValue) {
                        currentRating += updateValue;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating >= 1 + updateValue) {
                        currentRating -= updateValue;
                    }
                }
            }
        } else if (rating == 1) {
            if (difference == 1 || difference == 2) {
                updateValue *= updateValue * 2;
                if (minusOrPlus == 0) {
                    if (currentRating <= 9 - updateValue) {
                        currentRating += updateValue;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating >= 1 + updateValue) {
                        currentRating -= updateValue;
                    }
                }
            } else if (difference == 3 || difference == 4) {
                updateValue *= updateValue * 4;
                if (minusOrPlus == 0) {
                    if (currentRating <= 9 - updateValue) {
                        currentRating += updateValue;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating >= 1 + updateValue) {
                        currentRating -= updateValue;
                    }
                }
            }
        } else if (rating == 4) {
            if (difference == 1 || difference == 2) {
                if (minusOrPlus == 0) {
                    if (currentRating >= 1 + updateValue) {
                        currentRating -= updateValue;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating <= 9 - updateValue) {
                        currentRating += updateValue;
                    }
                }
            } else if (difference == 3 || difference == 4) {
                updateValue *= updateValue * 2;
                if (minusOrPlus == 0) {
                    if (currentRating >= 1 + updateValue) {
                        currentRating -= updateValue;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating <= 9 - updateValue) {
                        currentRating += updateValue;
                    }
                }
            }
        } else if (rating == 5) {
            if (difference == 1 || difference == 2) {
                updateValue *= updateValue * 2;
                if (minusOrPlus == 0) {
                    if (currentRating >= 1 + updateValue) {
                        currentRating -= updateValue;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating <= 9 - updateValue) {
                        currentRating += updateValue;
                    }
                }
            } else if (difference == 3 || difference == 4) {
                updateValue *= updateValue * 4;
                if (minusOrPlus == 0) {
                    if (currentRating >= 1 + updateValue) {
                        currentRating -= updateValue;
                    }
                } else if (minusOrPlus == 2) {
                    if (currentRating <= 9 - updateValue) {
                        currentRating += updateValue;
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

package GetDrink;

import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.*;
import static AllYourDatabaseAreBelongToDrunks.UpdateStatementCreation.updateDouble;
import static GetDrink.DrinkingFunTime.drinkID;
import static GetDrink.DrinkingFunTime.userID;
import static GetDrink.GetRatings.getNewComplexRating;
import static GetDrink.GetRatings.getNewSimpleRating;

public class UpdateBasePreferences {
    public static void main(String[] args) { }

    public static void updateRatingAlcoholContent() {
        Integer alcoholContent = selectInteger("Alcohol_Content",
                "Drinks", "Drink_ID", drinkID);

        updateRatingInteger(alcoholContent, 1);
    }

    public static Integer updateRatingPrice(boolean actuallyUpdating) {
        Double price = selectDouble("Price", "Drinks",
                "Drink_ID", drinkID);
        Double lowestPrice = selectLowestDouble("Price", "Drinks");
        Double highestPrice = selectHighestDouble("Price", "Drinks");
        Double averagePrice = selectAverageDouble("Price", "Drinks");

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
        Integer complexity = selectInteger("Complexity", "Drinks",
                "Drink_ID", drinkID);

        updateRatingInteger(complexity, 3);
    }

    public static void updateRatingSpiritForwardOrRefreshing() {
        boolean spiritForwardOrRefreshing = selectBoolean(
                "Spirit_Forward_or_Refreshing", "Drinks", "Drink_ID", drinkID);
        Integer preferenceID;
        if (!spiritForwardOrRefreshing) {
            preferenceID = 4;
        } else {
            preferenceID = 5;
        }

        Double currentRating = selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", preferenceID,
                "User_ID", userID);
        Double newRating = getNewSimpleRating(currentRating);

        Integer sdpID = selectIntegerWithSecondKey(
                "System_Defined_Preference_ID", "System_Defined_Preferences",
                "Preference_ID", preferenceID, "User_ID", userID);
        updateDouble("System_Defined_Preferences", "Variable", newRating,
                "System_Defined_Preference_ID", sdpID);
    }

    public static void updateRatingType() {
        boolean spiritForwardOrRefreshing = selectBoolean(
                "Spirit_Forward_or_Refreshing", "Drinks", "Drink_ID", drinkID);
        Integer type = selectInteger("Type", "Drinks",
                "Drink_ID", drinkID);
        if (spiritForwardOrRefreshing) {
            type +=3;
        }

        Double currentRating = selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", (type + 5),
                "User_ID", userID);
        Double newRating = getNewSimpleRating(currentRating);

        Integer sdpID = selectIntegerWithSecondKey(
                "System_Defined_Preference_ID", "System_Defined_Preferences",
                "Preference_ID", 1, "User_ID", userID);
        updateDouble("System_Defined_Preferences", "Variable", newRating,
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
        Double currentRating = selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", primaryKeyID,
                "User_ID", userID);
        Double newRating = getNewComplexRating(difference, minusOrPlus, currentRating);

        Integer sdpID = selectIntegerWithSecondKey(
                "System_Defined_Preference_ID", "System_Defined_Preferences",
                "Preference_ID", primaryKeyID, "User_ID", userID);
        updateDouble("System_Defined_Preferences", "Variable", newRating,
                "System_Defined_Preference_ID", sdpID);
    }
}

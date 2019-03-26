package GetDrink;

import java.util.ArrayList;

import static AllYourDatabaseAreBelongToDrunks.InsertStatementCreation.yourOpinionIsWrong;
import static AllYourDatabaseAreBelongToDrunks.InsertStatementCreation.youreSayingPeopleLikeThat;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.*;
import static AllYourDatabaseAreBelongToDrunks.UpdateStatementCreation.updateDouble;
import static GetDrink.DrinkingFunTime.drinkID;
import static GetDrink.DrinkingFunTime.userID;
import static GetDrink.GetRatings.getNewSimpleRating;

public class UpdateIngredientPreferences {
    public static void main(String[] args) { }

    public static void youreSoPicky() {
        ArrayList<Integer> ingredientIDs = selectIntegerArrayList(
                "Ingredient_ID", "Recipes", "Drink_ID", drinkID);
        ArrayList<String> ingredients = new ArrayList<>();

        for (Integer ID: ingredientIDs) {
            ingredients.add(selectString("Name", "Ingredients",
                    "Ingredient_ID", ID));
        }

        for (String ingredient : ingredients) {
            Integer sdpID = haveYouEvenTriedIt(ingredient);
            Double currentRating = selectDouble("Variable",
                    "System_Defined_Preferences", "System_Defined_Preference_ID", sdpID);

            Double newRating = getNewSimpleRating(currentRating);
            updateDouble("System_Defined_Preferences", "Variable",
                    newRating, "System_Defined_Preference_ID", sdpID);
        }
    }

    public static Integer haveYouEvenTriedIt(String ingredient) {
        Integer preferenceID = selectIntegerWithString("Preference_ID",
                "Preferences", "Name", ingredient);
        if (preferenceID == 0) {
            youreSayingPeopleLikeThat(ingredient, "ingredient");
            preferenceID = selectIntegerWithString("Preference_ID",
                    "Preferences", "Name", ingredient);
        }

        Integer sdpID = selectIntegerWithSecondKey(
                "System_Defined_Preference_ID", "System_Defined_Preferences",
                "Preference_ID", preferenceID, "User_ID", userID);
        if (sdpID == 0) {
            yourOpinionIsWrong(preferenceID, userID, 5.000);
            sdpID = selectIntegerWithSecondKey("System_Defined_Preference_ID",
                    "System_Defined_Preferences", "Preference_ID", preferenceID,
                    "User_ID", userID);
        }

        return sdpID;
    }
}

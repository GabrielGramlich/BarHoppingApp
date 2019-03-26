package Menus;

import java.util.ArrayList;

import static AllYourDatabaseAreBelongToDrunks.DeleteStatementCreation.delete;
import static AllYourDatabaseAreBelongToDrunks.DeleteStatementCreation.deleteWithSecondKey;
import static AllYourDatabaseAreBelongToDrunks.InsertStatementCreation.thatSoundsDelicious;
import static AllYourDatabaseAreBelongToDrunks.InsertStatementCreation.whatsInItThough;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.*;
import static AllYourDatabaseAreBelongToDrunks.UpdateStatementCreation.*;
import static Menus.SignIn.*;
import static input.InputUtils.*;

public class DrinkMenu {
    public static void main(String[] args) { }

    public static void ownerDrinksMenu() {
        String decision = stringInput("Create, update, delete or review drinks?");
        if (decision.equals("create")) {
            createDrink();
        } else if (decision.equals("update")) {
            updateDrink();
        } else if (decision.equals("delete")) {
            deleteDrink();
        } else if (decision.equals("review")) {
            reviewDrink();
        }
    }

    public static void createDrink() {
        String name = stringInput("What is this tasty concoction you have for us?");
        String startDate;
        String endDate;

        if (yesNoInput("Is it available for a limited time?")) {
            startDate = stringInput("When will it become available? (MM/DD/YY)");
            endDate = stringInput("When will it stop being available? (MM/DD/YY)");
        } else {
            startDate = "01/01/00";
            endDate = "01/01/00";
        }

        Integer strength = intInput("On a scale from 1-9, how strong is this drink?");
        Double price = doubleInput("How much does it cost?");
        Integer complexity = intInput("On a scale from 1-9, how complex is this drink?");
        boolean spiritForwardOrRefreshing = yesNoInput("Is it spirit forward (N), or refreshing (Y)?");
        Integer type;
        if (spiritForwardOrRefreshing) {
            type = intInput("Is it type (1), type2 (2), or type3 (3)?");
        } else {
            type = intInput("Is it type (1), type2 (2), or type3 (3)?");
        }

        ArrayList<String> ingredients = new ArrayList<>();
        do {
            ingredients.add(stringInput("What's in this thingamadrink?"));
        } while (yesNoInput("Is there another ingredient?"));

        thatSoundsDelicious(name, startDate, endDate, strength, price, complexity,
                spiritForwardOrRefreshing, type);
        drinkID = selectIntegerWithString("Drink_ID", "Drinks",
                "Name", name);
        whatsInItThough(ingredients, drinkID);
    }

    public static void updateDrink() {
        boolean optionChosen = getDrinkChoice();

        if (optionChosen) {
            String otherDecision = stringInput("Would you like to update " + drinkSelection + "'s " +
                    "ingredients, description or availability?");
            drinkID = selectIntegerWithString("Drink_ID", "Drinks",
                    "Name", drinkSelection);
            if (otherDecision.equals("ingredients")) {
                String finalDecision = stringInput("Would you like to change or add an ingredient)");
                if (finalDecision.equals("change")) {
                    displayRecipe();

                    String toUpdate = stringInput("Which ingredient would you like to change?");
                    String newData = stringInput("What would you like to change it to?");

                    Integer oldIngredientID = selectIntegerWithString(
                            "Ingredient_Name", "Ingredients", "Name", toUpdate);
                    Integer newIngredientID = selectIntegerWithString(
                            "Ingredient_Name", "Ingredients", "Name", newData);

                    updateIntegerWithSecondaryID("Recipes",
                            "Ingredient_ID", newIngredientID, "Drink_ID", drinkID,
                            "Ingredient_ID", oldIngredientID);
                } else if (finalDecision.equals("add")) {
                    ArrayList<String> ingredients = new ArrayList<>();
                    ingredients.add(stringInput("What would you like to add to " + drinkSelection + "?"));
                    whatsInItThough(ingredients, drinkID);
                }

            } else if (otherDecision.equals("description")) {
                updateDrinkDescription();
            } else if (otherDecision.equals("availability")) {
                updateDrinkAvailability();
            }
        }
    }

    public static void updateDrinkDescription() {
        displayDrinkData();

        String finalDecision = stringInput("Update alcohol_content, price, specialty_price, complexity, " +
                "spirit_forward_or_refreshing, or type?");

        if (finalDecision.equals("alcohol_content")) {
            Integer newData = intInput("Enter new strength on a scale of 1-9.");
            updateInteger("Drinks", finalDecision, newData,
                    "Drink_ID", drinkID);
        } else if (finalDecision.equals("price")) {
            Double newData = doubleInput("Enter new price.");
            updateDouble("Drinks", finalDecision, newData,
                    "Drink_ID", drinkID);
        } else if (finalDecision.equals("specialty_price")) {
            Double newData = doubleInput("Enter new specialty price.");
            updateDouble("Drinks", finalDecision, newData,
                    "Drink_ID", drinkID);
        } else if (finalDecision.equals("complexity")) {
            Integer newData = intInput("Enter new complexity on a scale of 1-9.");
            updateInteger("Drinks", finalDecision, newData,
                    "Drink_ID", drinkID);
        } else if (finalDecision.equals("spirit_forward_or_refreshing")) {
            boolean newData = yesNoInput("Is it spirit forward (N), or refreshing (Y)?");
            updateBoolean("Drinks", finalDecision, newData,
                    "Drink_ID", drinkID);
        } else if (finalDecision.equals("type")) {
            boolean spiritForwardOrRefreshing = selectBoolean(
                    "Spirit_Forward_or_Refreshing", "Drinks", "Drink_ID", drinkID);
            if (spiritForwardOrRefreshing) {
                Integer newData = intInput("Enter new drink type: type1 (1), type2 (2), type3 (3).");
                updateInteger("Drinks", finalDecision, newData,
                        "Drink_ID", drinkID);
            } else {
                Integer newData = intInput("Enter new drink type: type1 (1), type2 (2), type3 (3).");
                updateInteger("Drinks", finalDecision, newData,
                        "Drink_ID", drinkID);
            }
        }
    }

    public static void updateDrinkAvailability() {
        System.out.println("Availability Start: " + selectString(
                "Availability_Start", "Drinks", "Drink_ID", drinkID));
        System.out.println("Availability End: " + selectString(
                "Availability_End", "Drinks", "Drink_ID", drinkID));
        String finalDecision = stringInput("Update availability_start or availability_end?");
        if (finalDecision.equals("availability_start")) {
            String newData = stringInput("Enter new start date (MM/DD/YY).");
            updateDate("Drinks", finalDecision, newData,
                    "Drink_ID", drinkID);
        } else if (finalDecision.equals("availability_end")) {
            String newData = stringInput("Enter new start date (MM/DD/YY).");
            updateDate("Drinks", finalDecision, newData,
                    "Drink_ID", drinkID);
        }
    }

    public static void deleteDrink() {
        String otherDecision = stringInput("Would you like to delete entire drink, or ingredients " +
                "(drink or ingredient)?");
        boolean optionChosen = getDrinkChoice();

        drinkID = selectIntegerWithString("Drink_ID", "Drinks",
                "Name", drinkSelection);

        if (otherDecision.equals("drink")) {
            if (optionChosen) {
                delete("Drinks", "Drink_ID", drinkID);
                delete("Recipes", "Drink_ID", drinkID);
            }
        } else if (otherDecision.equals("ingredient")) {
            if (optionChosen) {
                displayRecipe();

                String toBeDeleted = stringInput("Which ingredient would you like to delete?");
                Integer ingredientID = selectIntegerWithString(
                        "Ingredient_Name", "Ingredients", "Name", toBeDeleted);
                deleteWithSecondKey("Recipes", "Drink_ID",
                        drinkID, "Ingredient_ID", ingredientID);
            }
        }
    }

    public static void reviewDrink() {
        boolean optionChosen = getDrinkChoice();

        drinkID = selectIntegerWithString("Drink_ID", "Drinks",
                "Name", drinkSelection);
        if (optionChosen) {
            String finalDecision = stringInput("Would you like to review ingredients or data?");
            if (finalDecision.equals("ingredients")) {
                displayRecipe();
            } else if (finalDecision.equals("data")) {
                System.out.println("Drink data for " + drinkSelection + ":");
                displayDrinkData();
            }
        }
    }

    public static void displayDrinkData() {
        System.out.println("Alcohol content, scale from 1-9: " + selectInteger(
                "Alcohol_Content", "Drinks", "Drink_ID", drinkID));
        System.out.println("Price: " + selectDouble("Price", "Drinks",
                "Drink_ID", drinkID));
        System.out.println("Specialty Price: " + selectDouble(
                "Specialty_Price", "Drinks", "Drink_ID", drinkID));
        System.out.println("Complexity, scale from 1-9: " + selectInteger(
                "Complexity", "Drinks", "Drink_ID", drinkID));
        boolean spiritForwardOrRefreshing = selectBoolean(
                "Spirit_Forward_or_Refreshing", "Drinks", "Drink_ID", drinkID);
        System.out.println("Spirit forward(false) or refreshing(true): " + spiritForwardOrRefreshing);
        if (spiritForwardOrRefreshing) {
            System.out.println("Drink type, type1 (1), type2 (2), type3 (3): "
                    + selectInteger("Type", "Drinks",
                    "Drink_ID", drinkID));
        } else {
            System.out.println("Drink type, type1 (1), type2 (2), type3 (3): "
                    + selectInteger("Type", "Drinks",
                    "Drink_ID", drinkID));
        }
    }

    public static void displayRecipe() {
        Integer recipeID = selectInteger("Recipe_ID", "Recipes",
                "Drink_ID", drinkID);
        ArrayList<Integer> ingredientIDs = selectIntegerArrayList(
                "Ingredient_ID", "Recipes", "Recipe_ID", recipeID);
        ArrayList<String> ingredients = new ArrayList<>();

        for (Integer ID : ingredientIDs) {
            ingredients.add(selectString("Name", "Ingredients",
                    "Ingredient_ID", ID));
        }

        System.out.println("Ingredients in " + drinkSelection + ":");
        for (String ingredient : ingredients) {
            System.out.println(ingredient);
        }
    }

    public static boolean getDrinkChoice() {
        ArrayList<String> locations = selectStringArrayList("Name",
                "Locations", "Owner_ID", ownerID);
        for (String location : locations) {
            System.out.println(location);
        }
        locationName = stringInput("Which location would you like?");
        locationID = selectIntegerWithString("Location_ID", "Locations",
                "Name", locationName);

        ArrayList<Integer> drinks = selectIntegerArrayList("Drink_ID",
                "Drink_Locations", "Location_ID", locationID);
        ArrayList<String> drinkNames = new ArrayList<>();

        for (Integer drink : drinks) {
            drinkNames.add(selectString("Name", "Drinks",
                    "Drink_ID", drink));
        }

        boolean optionChosen = false;

        for (String drinkName : drinkNames) {
            boolean doIt = yesNoInput("Would you like the drink named " + drinkName + "?");
            if (doIt) {
                drinkSelection = drinkName;
                optionChosen = true;
                break;
            }
        }

        return optionChosen;
    }
}

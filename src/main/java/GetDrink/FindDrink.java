package GetDrink;

import java.util.ArrayList;
import java.util.Random;

import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.*;
import static GetDrink.DrinkingFunTime.*;
import static GetDrink.UpdateBasePreferences.updateRatingPrice;

public class FindDrink {
    public static void main(String[] args) { }

    public static void pickingYourPoison() {
//        ArrayList<Integer> localDrinks = getLocalDrinks();
        ArrayList<Integer> startingDrinks = getStartingDrinks();
        ArrayList<Integer> allergenFreeDrinks = getAllergenFreeDrinks(startingDrinks);
        ArrayList<Integer> almostRightDrinks = getAlmostRightDrinks(allergenFreeDrinks);
        ArrayList<Integer> rightDrinks = getRightDrinks(almostRightDrinks);
        ArrayList<Integer> youLikeDrinks = getYouLikeDrinks(rightDrinks);
        ArrayList<Integer> everyoneLikesDrinks = getEveryoneLikesDrinks(youLikeDrinks);

        Random rand = new Random();
        int randInt = rand.nextInt(everyoneLikesDrinks.size());

        drinkID = everyoneLikesDrinks.get(randInt);
        String drinkName = selectString("Name", "Drinks",
                "Drink_ID", drinkID);

        letMeCheckInTheBack(drinkName);
    }

//    public static ArrayList<Integer> getLocalDrinks() {
//        //TODO get input on where the user is drinking #this will happen when it's a real app
//        //TODO get drinks in that area #this will happen when it's a real app
//    }

    public static ArrayList<Integer> getStartingDrinks() {
        ArrayList<Integer> tempDrinksBoolean = selectIntegerArrayListWithBoolean(
                "Drink_ID", "Drinks", "Spirit_Forward_or_Refreshing",
                spiritForwardOrRefreshing);
        ArrayList<Integer> tempDrinksType = selectIntegerArrayList("Drink_ID",
                "Drinks", "Type", type);
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
        ArrayList<Integer> allergies = selectIntegerArrayList("Allergy_ID",
                "User_Allergies", "User_ID", userID);
        if (!allergies.isEmpty()) {
            ArrayList<String> allergens = new ArrayList<>();

            for (Integer allergy : allergies) {
                allergens.add(selectString("Name", "Allergies",
                        "Allergy_ID", allergy));
            }

            for (Integer drink : startingDrinks) {
                boolean hasAllergen = false;
                ArrayList<Integer> recipe = selectIntegerArrayList(
                        "Ingredient_ID", "Recipes", "Drink_ID", drink);
                for (Integer ingredient : recipe) {
                    String name = selectString("Name", "Ingredients",
                            "Ingredient_ID", ingredient);
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
        Double sdpAlcoholContent = selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", 1,
                "User_ID", userID);
        Double sdpPrice = selectDoubleWithSecondaryKey("Variable",
                "System_Defined_Preferences", "Preference_ID", 2,
                "User_ID", userID);
        Double sdpComplexity = selectDoubleWithSecondaryKey("Variable",
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

        getVariation(sdpAlcoholContent, sdpPrice, sdpComplexity);

        ArrayList<Integer> almostRightDrinks = new ArrayList<>();
        for (Integer drink : allergenFreeDrinks) {
            drinkID = drink;
            Integer drinkAlcoholContent = selectInteger("Alcohol_Content",
                    "Drinks", "Drink_ID", drinkID);
            Integer drinkPrice = updateRatingPrice(false);
            Integer drinkComplexity = selectInteger("Complexity",
                    "Drinks", "Drink_ID", drinkID);
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

    public static void getVariation(Double alcoholContent, Double price, Double complexity) {
        Double importantAllowedVariation = 2d;
        Double allowedVariation = 3.5;
        Double unimportantAllowedVariation = 5d;

        if (importantPreference.equals(1)) {
            alcoholContentLow = alcoholContent - importantAllowedVariation;
            alcoholContentHigh = alcoholContent + importantAllowedVariation;
            if (unimportantPreference.equals(2)) {
                priceLow = price - unimportantAllowedVariation;
                priceHigh = price + unimportantAllowedVariation;
                complexityLow = complexity - allowedVariation;
                complexityHigh = complexity + allowedVariation;
            } else if (unimportantPreference.equals(3)) {
                complexityLow = complexity - unimportantAllowedVariation;
                complexityHigh = complexity + unimportantAllowedVariation;
                priceLow = price - allowedVariation;
                priceHigh = price + allowedVariation;
            }
        } else if (importantPreference.equals(2)) {
            priceLow = price - importantAllowedVariation;
            priceHigh = price + importantAllowedVariation;
            if (unimportantPreference.equals(1)) {
                alcoholContentLow = alcoholContent - unimportantAllowedVariation;
                alcoholContentHigh = alcoholContent + unimportantAllowedVariation;
                complexityLow = complexity - allowedVariation;
                complexityHigh = complexity + allowedVariation;
            } else if (unimportantPreference.equals(3)) {
                complexityLow = complexity - unimportantAllowedVariation;
                complexityHigh = complexity + unimportantAllowedVariation;
                alcoholContentLow = alcoholContent - allowedVariation;
                alcoholContentHigh = alcoholContent + allowedVariation;
            }
        } else if (importantPreference.equals(3)) {
            complexityLow = complexity - importantAllowedVariation;
            complexityHigh = complexity + importantAllowedVariation;
            if (unimportantPreference.equals(1)) {
                alcoholContentLow = alcoholContent - unimportantAllowedVariation;
                alcoholContentHigh = alcoholContent + unimportantAllowedVariation;
                priceLow = price - allowedVariation;
                priceHigh = price + allowedVariation;
            } else if (unimportantPreference.equals(2)) {
                priceLow = price - unimportantAllowedVariation;
                priceHigh = price + unimportantAllowedVariation;
                alcoholContentLow = alcoholContent - allowedVariation;
                alcoholContentHigh = alcoholContent + allowedVariation;
            }
        }
    }

    public static ArrayList<Integer> getRightDrinks(ArrayList<Integer> almostRightDrinks) {
        ArrayList<Integer> tempRightDrinks = new ArrayList<>();
        ArrayList<Integer> rightDrinks = new ArrayList<>();
        Integer ingredientID = selectIntegerWithString("Ingredient_ID",
                "Ingredients", "Name", preferredLiquor);
        ArrayList<Integer> drinksWithLiquor = selectIntegerArrayList(
                "Drink_ID", "Recipes", "Ingredient_ID", ingredientID);

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

    public static ArrayList<Integer> getYouLikeDrinks(ArrayList<Integer> rightDrinks) {
        ArrayList<Integer> youLikeDrinks = new ArrayList<>();
        ArrayList<Integer> likedDrinks = selectIntegerArrayListForAll("Drink_ID", "Drink_Preferences");
        for (Integer drink : rightDrinks) {
            boolean happened = false;
            for (Integer otherDrink : likedDrinks) {
                Double drinkRating = selectAverageDoubleWithKey("Rating", "Drink_Preferences",
                        "Drink_ID", otherDrink);
                if (drinkRating != null) {
                    happened = true;
                    if (drink.equals(otherDrink)) {
                        Random random = new Random();
                        int go = random.nextInt(5);

                        int j = 1;
                        for (int i = 5 ; i > 0 ; i--) {
                            if (drinkRating >= i) {
                                if (go >= j) {
                                    youLikeDrinks.add(drink);
                                }
                            }
                            j++;
                        }
                    }
                }
            }
            if (!happened) {
                youLikeDrinks.add(drink);
            }
        }

        if (youLikeDrinks.isEmpty()) {
            return rightDrinks;
        } else {
            return youLikeDrinks;
        }
    }

    public static ArrayList<Integer> getEveryoneLikesDrinks(ArrayList<Integer> youLikeDrinks) {
        ArrayList<Integer> everyoneLikesDrinks = new ArrayList<>();
        ArrayList<Integer> likedDrinks = selectIntegerArrayListForAll("Drink_ID", "Drink_Preferences");
        for (Integer drink : youLikeDrinks) {
            boolean happened = false;
            for (Integer otherDrink : likedDrinks) {
                Double drinkRating = selectAverageDoubleWithKey("Rating", "Drink_Preferences",
                        "Drink_ID", otherDrink);
                if (drinkRating != null) {
                    happened = true;
                    if (drink.equals(otherDrink)) {
                        Random random = new Random();
                        int go = random.nextInt(5);

                        int j = 1;
                        for (int i = 5 ; i > 0 ; i--) {
                            if (drinkRating >= i) {
                                if (go >= j) {
                                    everyoneLikesDrinks.add(drink);
                                }
                            }
                            j++;
                        }
                    }
                }
            }
            if (!happened) {
                everyoneLikesDrinks.add(drink);
            }
        }

        if (everyoneLikesDrinks.isEmpty()) {
            return youLikeDrinks;
        } else {
            return everyoneLikesDrinks;
        }
    }
}

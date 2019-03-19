import java.util.ArrayList;
import java.util.regex.Pattern;

import static input.InputUtils.*;

public class WhoAreYouAndWhatDoYouWant {
    private static String username;
    private static Integer loginID;
    private static Integer ownerID;
    private static String password;
    private static String salt;
    private static String email;
    private static String locationName;
    private static Integer locationID;
    private static Integer drinkID;
    private static String drinkSelection;

    public static void main(String[] args) {
        boolean weHave = DontHopUnlessISaySo.haveWeMet();
        boolean quit = true;
        username = "";
        password = "";
        // TODO get username and password if already signed in

        boolean newUser = yesNoInput("Are you a new member?");

        if (newUser) {
            quit = createAccountLogin();
        }

        if (quit) {
            while (!weHave) {
                username = stringInput("Enter your username:");
                password = stringInput("Enter your password:");
                weHave = DontHopUnlessISaySo.whosThere(username, password);
            }
        }

        userOrOwner();
    }

    private static void userOrOwner() {
        Integer accountType = AllYourDatabaseAreBelongToDrunks.needThatAccountType(username);

        if (accountType == 2) {
            ownerMenu();
        } else if (accountType == 1) {
            userMenu();
        }
    }

    private static void deleteAccount() {
        loginID = AllYourDatabaseAreBelongToDrunks.needThatCredentialID(username);
        Integer userID = AllYourDatabaseAreBelongToDrunks.needThatUserID(username);
        boolean response = yesNoInput("Awh, you wanna leave? Big baby got his feewings hurt?");
        if (response) {
            AllYourDatabaseAreBelongToDrunks.throwThatInTheTrash(loginID, userID);
        }
    }

    private static boolean createAccountLogin() {
        Integer alreadyChosen = 666;
        password = "";
        boolean owner = yesNoInput("You a corporate fat cat?");
        boolean quit = false;

        while (alreadyChosen != 0) {
            username = stringInput("Username. Gimme.");
            password = stringInput("Oy. Password, guvna!");
            alreadyChosen = AllYourDatabaseAreBelongToDrunks.needThatAccountType(username);
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

            loginID = AllYourDatabaseAreBelongToDrunks.whoAreYou(owner, username, email, hashedPassword, salt);

            if (owner) {
                createOwnerAccount();
            } else {
                createUserAccount();
            }
        }
        return quit;
    }

    private static void createUserAccount() {
        String firstName = stringInput("What's your first name? It's Douche, isn't it?");
        String lastName = stringInput("Last name, Bag.");

        AllYourDatabaseAreBelongToDrunks.greetingsFriend(firstName, lastName, loginID);

        getUserPreferences(true);
    }

    private static void createOwnerAccount() {
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
        AllYourDatabaseAreBelongToDrunks.helloBoss(loginID, firstName, lastName, contactNumber, contactEmail);

        ownerMenu();
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private static void getUserPreferences(boolean firstTime) {
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
        String bottomShelf = stringInput("Least favorite?");
        boolean weakOrStrong = yesNoInput("Do you have hair on your chest?");
        boolean deepPockets = !yesNoInput("You broke, homie?");
        boolean youFancy = yesNoInput("You a fan of mixology?");

        AllYourDatabaseAreBelongToDrunks.youGotSomeWeirdKinks(username, allergies, topShelf, bottomShelf, weakOrStrong, deepPockets, youFancy);
    }

    private static void userMenu() {
        //TODO finish user menu
        String decision = stringInput("What you want? Drink, account, home?");
        if (decision.equals("drink")) {
//            HopThoseBars.letsGetLit();
        } else if (decision.equals("account")) {
            String otherDecision = stringInput("Change it or delete it?");
            if (otherDecision.equals("change")) {
                changeUserSettings();
            } else if (otherDecision.equals("delete")) {
                deleteAccount();
            }
        } else if (decision.equals("home")) {
//            HopThoseBars.seeYouAround();
        }
        //TODO loop the menu
    }

    private static void changeUserSettings() {
        do {
            String toBeUpdated = stringInput("Change email, name, username, password, or preferences?");
            String newInfo = "";

            if (toBeUpdated.equals("preferences")) {
                getUserPreferences(false);
            } else if (toBeUpdated.equals("email")) {
                newInfo = stringInput("New email?");
                while (!isValidEmail(newInfo)) {
                    newInfo = stringInput("Wrong. Try again.");
                }
                toBeUpdated = "Email_Address";
            } else if (toBeUpdated.equals("name")) {
                newInfo = stringInput("New name?");
            } else if (toBeUpdated.equals("username")) {
                newInfo = stringInput("New username?");
            } else if (toBeUpdated.equals("password")) {
                String newPassword = stringInput("New password?");
                salt = AllYourDatabaseAreBelongToDrunks.needThatSalt(username);
                newInfo = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(newPassword, salt);
            }

            if (toBeUpdated.equals("name")) {
                Integer userID = AllYourDatabaseAreBelongToDrunks.needThatUserID(username);
                Integer spaceIndex = newInfo.indexOf(" ");
                String first = newInfo.substring(0, spaceIndex);
                String last = newInfo.substring(spaceIndex + 1);

                AllYourDatabaseAreBelongToDrunks.updateString("Users", "First_Name", first, "User_ID", userID);
                AllYourDatabaseAreBelongToDrunks.updateString("Users", "Last_Name", last, "User_ID", userID);
            } else {
                Integer loginID = AllYourDatabaseAreBelongToDrunks.needThatCredentialID(username);
                AllYourDatabaseAreBelongToDrunks.updateString("Login_Credentials", toBeUpdated, newInfo, "Login_ID", loginID);
            }
        } while (!yesNoInput("You done yet?"));

        System.out.println("Bout time.");
    }

    private static void ownerMenu() {
        loginID = AllYourDatabaseAreBelongToDrunks.needThatOwnerLoginID(username);
        ownerID = AllYourDatabaseAreBelongToDrunks.needThatOwnerID(loginID);
        String decision = stringInput("Does master want the account, location or drink settings?");
        if (decision.equals("account")) {
            String otherDecision = stringInput("Delete or change account?");
            if (otherDecision.equals("delete")) {
                deleteAccount();
            } else if (otherDecision.equals("change")) {
                changeOwnerSettings();
            }
        } else if (decision.equals("location")) {
            ownerLocationsMenu();
        } else if (decision.equals("drink")) {
            ownerDrinksMenu();
        }
    }

    private static void changeOwnerSettings() {
        do {
            String toBeUpdated = stringInput("Change email, username, password, name, contact number or contact email?");
            String newInfo = "";

            if (toBeUpdated.equals("email")) {
                newInfo = stringInput("New email?");
                toBeUpdated = "Email_Address";
            } else if (toBeUpdated.equals("username")) {
                newInfo = stringInput("New username?");
            } else if (toBeUpdated.equals("password")) {
                String newPassword = stringInput("New password?");
                salt = AllYourDatabaseAreBelongToDrunks.needThatSalt(username);
                newInfo = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(newPassword, salt);
            } else if (toBeUpdated.equals("name")) {
                newInfo = stringInput("New name?");
            } else if (toBeUpdated.equals("contact number")) {
                newInfo = stringInput("New contact number?");
                toBeUpdated = "Contact_Number";
            } else if (toBeUpdated.equals("contact email")) {
                newInfo = stringInput("New contact email?");
                toBeUpdated = "Contact_Email";
                while (!isValidEmail(newInfo)) {
                    newInfo = stringInput("Invalid email address. Please enter another.");
                }
            }

            loginID = AllYourDatabaseAreBelongToDrunks.needThatOwnerLoginID(username);
            Integer ownerID = AllYourDatabaseAreBelongToDrunks.needThatOwnerID(loginID);

            if (toBeUpdated.equals("name")) {
                Integer spaceIndex = newInfo.indexOf(" ");
                String first = newInfo.substring(0, spaceIndex);
                String last = newInfo.substring(spaceIndex + 1);

                AllYourDatabaseAreBelongToDrunks.updateString("Owners", "First_Name", first, "Owner_ID", ownerID);
                AllYourDatabaseAreBelongToDrunks.updateString("Owners", "Last_Name", last, "Owner_ID", ownerID);
            } else if (toBeUpdated.equals("Contact_Number")) {
                AllYourDatabaseAreBelongToDrunks.updateString("Owners", "Contact_Number", newInfo, "Owner_ID", ownerID);
            } else if (toBeUpdated.equals("Contact_Email")) {
                AllYourDatabaseAreBelongToDrunks.updateString("Owners", "Contact_Email", newInfo, "Owner_ID", ownerID);
            } else {
                AllYourDatabaseAreBelongToDrunks.updateString("Owners", toBeUpdated, newInfo, "Owner_ID", ownerID);
            }



        } while (yesNoInput("Do you still require assistance, sir?"));

        System.out.println("It's been a pleasure.");
    }



    private static void ownerLocationsMenu() {
        String decision = stringInput("Create, update, delete or review locations?");

        if (decision.equals("create")) {
            createLocation();
            createLocationCalendar();
        } else {
            String otherDecision = stringInput("Would you like to " + decision + " location information or hours of operation (location or hours).");
            ArrayList<String> locations = AllYourDatabaseAreBelongToDrunks.needThoseLocations(ownerID);
            for (String location : locations) {
                System.out.println(location);
            }
            locationName = stringInput("Which location would you like to " + decision + "?");
            locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(locationName);

            if (decision.equals("update")) {
                if (otherDecision.equals("location")) {
                    updateLocation();
                } else if (otherDecision.equals("hours")) {
                    updateHours();
                }
            } else if (decision.equals("delete")) {
                deleteLocation();
            } else if (decision.equals("review")) {
                reviewLocationData(otherDecision);
            }
        }
    }

    private static void createLocation() {
        locationName = stringInput("Location name?");
        Double number = Double.valueOf(stringInput("Location phone number?"));
        String street = stringInput("Location street address?");
        String city = stringInput("Location city?");
        String state = stringInput("Location state?");
        Double zip = Double.valueOf(stringInput("Location zip code?"));

        AllYourDatabaseAreBelongToDrunks.welcomeToYourKingdom(ownerID, locationName, number, street, city, state, zip);
    }

    private static void createLocationCalendar() {
        ArrayList<String> days = new ArrayList<>();
        ArrayList<String> openHours = new ArrayList<>();
        ArrayList<String> closeHours = new ArrayList<>();
        ArrayList<String> speHoursStart = new ArrayList<>();
        ArrayList<String> speHoursEnd = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        for (String day : days) {
            String openHoursInput = stringInput("When do you open on " + day + "? (HH:MM)");
            String closeHoursInput = stringInput("When do you close on " + day + "? (HH:MM)");
            openHours.add(openHoursInput);
            closeHours.add(closeHoursInput);

            String speHoursStartInput;
            String speHoursEndInput;
            boolean go = yesNoInput("Do you have specialty hours on " + day + "?");
            if (go) {
                speHoursStartInput = stringInput("When do they start? (HH:MM)");
                speHoursEndInput = stringInput("When do they end? (HH:MM)");
                speHoursStart.add(speHoursStartInput);
                speHoursEnd.add(speHoursEndInput);
            } else {
                speHoursStartInput = "00:00";
                speHoursEndInput = "00:00";
                speHoursStart.add(speHoursStartInput);
                speHoursEnd.add(speHoursEndInput);
            }
        }

        locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(locationName);
        AllYourDatabaseAreBelongToDrunks.IDidntKnowKingdomsHadHours(locationID, days, openHours, closeHours, speHoursStart, speHoursEnd);
    }

    private static void updateLocation() {
        AllYourDatabaseAreBelongToDrunks.needThatLocationData(locationID);

        String finalDecision = stringInput("Would you like to update, name, phone_number, street, city, state or zip?");
        String newStringData = stringInput("New " + finalDecision + "?");
        Double newDoubleData;

        if (finalDecision.equals("phone_number") || finalDecision.equals("zip")) {
            //TODO provide formatting if entering number or zip
            newDoubleData = Double.valueOf(newStringData);
            AllYourDatabaseAreBelongToDrunks.updateDouble("Locations", finalDecision, newDoubleData, "Location_ID", locationID);
        } else {
            AllYourDatabaseAreBelongToDrunks.updateString("Locations", finalDecision, newStringData, "Location_ID", locationID);
        }
    }

    private static void updateHours() {
        AllYourDatabaseAreBelongToDrunks.needThatHourData(locationID);

        String finalDecision = stringInput("Would you like to update time_open, time_close, specialty_hour_start or specialty_hour_end?");
        String dayOfWeek = stringInput("For which day of the week?");
        String newData = stringInput("New " + finalDecision + " for " + dayOfWeek + "? (HH:MM)");

        AllYourDatabaseAreBelongToDrunks.updateTime("Calendar", finalDecision, newData, "Location_ID", locationID, "Day_of_Week", dayOfWeek);
    }

    private static void deleteLocation() {
        boolean confirmed = yesNoInput("Are you sure you want to delete this location and its corresponding data?");
        if (confirmed) {
            AllYourDatabaseAreBelongToDrunks.thanksForStoppingBy(locationID);
        }
    }

    private static void reviewLocationData(String otherDecision) {
        if (otherDecision.equals("location")) {
            AllYourDatabaseAreBelongToDrunks.needThatLocationData(locationID);
        } else if (otherDecision.equals("hours")) {
            AllYourDatabaseAreBelongToDrunks.needThatHourData(locationID);
        }
    }

    private static void ownerDrinksMenu() {
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

    private static void createDrink() {
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

        Integer strength = intInput("On a scale from 1-5, how strong is this drink?");
        Double price = doubleInput("How much does it cost?");
        Double specialtyPrice;
        if (yesNoInput("Is there a specialty price for it during certain hours?")) {
            specialtyPrice = doubleInput("What is that price?");
        } else {
            specialtyPrice = 0.00;
        }
        Integer complexity = intInput("On a scale from 1-5, how complex is this drink?");
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

        AllYourDatabaseAreBelongToDrunks.thatSoundsDelicious(name, startDate, endDate, strength, price, specialtyPrice, complexity, spiritForwardOrRefreshing, type);
        drinkID = AllYourDatabaseAreBelongToDrunks.needThatDrinkID(name);
        AllYourDatabaseAreBelongToDrunks.whatsInItThough(ingredients, drinkID);
    }

    private static void updateDrink() {
        boolean optionChosen = getDrinkChoice();

        if (optionChosen) {
            String otherDecision = stringInput("Would you like to update " + drinkSelection + "'s ingredients, description or availability?");
            drinkID = AllYourDatabaseAreBelongToDrunks.needThatDrinkID(drinkSelection);
            if (otherDecision.equals("ingredients")) {
                String finalDecision = stringInput("Would you like to change or add an ingredient)");
                if (finalDecision.equals("change")) {
                    ArrayList<String> ingredients = AllYourDatabaseAreBelongToDrunks.needThatRecipe(drinkID);
                    System.out.println("Drink contains:");
                    for (String ingredient : ingredients) {
                        System.out.println(ingredient);
                    }
                    String toUpdate = stringInput("Which ingredient would you like to change?");
                    String newData = stringInput("What would you like to change it to?");

                    Integer oldIngredientID = AllYourDatabaseAreBelongToDrunks.needThatIngredientID(toUpdate);
                    Integer newIngredientID = AllYourDatabaseAreBelongToDrunks.needThatIngredientID(newData);

                    AllYourDatabaseAreBelongToDrunks.updateIntegerWithSecondaryID("Recipes", "Ingredients_Ingredient_ID", newIngredientID, "Drink_ID", drinkID, "Ingredients_Ingredient_ID", oldIngredientID);
                } else if (finalDecision.equals("add")) {
                    String newData = stringInput("What would you like to add to " + drinkSelection + "?");
                    AllYourDatabaseAreBelongToDrunks.evenBetter(drinkID, newData);
                }

            } else if (otherDecision.equals("description")) {
                updateDrinkDescription();
            } else if (otherDecision.equals("availability")) {
                updateDrinkAvailability();
            }
        }
    }

    private static void updateDrinkDescription() {
        displayDrinkData();

        String finalDecision = stringInput("Update alcohol_content, price, specialty_price, complexity, spirit_forward_or_refreshing, or type?");

        if (finalDecision.equals("alcohol_content")) {
            Integer newData = intInput("Enter new strength on a scale of 1-5.");
            AllYourDatabaseAreBelongToDrunks.updateInteger("Drinks", finalDecision, newData, "Drink_ID", drinkID);
        } else if (finalDecision.equals("price")) {
            Double newData = doubleInput("Enter new price.");
            AllYourDatabaseAreBelongToDrunks.updateDouble("Drinks", finalDecision, newData, "Drink_ID", drinkID);
        } else if (finalDecision.equals("specialty_price")) {
            Double newData = doubleInput("Enter new specialty price.");
            AllYourDatabaseAreBelongToDrunks.updateDouble("Drinks", finalDecision, newData, "Drink_ID", drinkID);
        } else if (finalDecision.equals("complexity")) {
            Integer newData = intInput("Enter new complexity on a scale of 1-5.");
            AllYourDatabaseAreBelongToDrunks.updateInteger("Drinks", finalDecision, newData, "Drink_ID", drinkID);
        } else if (finalDecision.equals("spirit_forward_or_refreshing")) {
            boolean newData = yesNoInput("Is it spirit forward (N), or refreshing (Y)?");
            AllYourDatabaseAreBelongToDrunks.updateBoolean("Drinks", finalDecision, newData, "Drink_ID", drinkID);
        } else if (finalDecision.equals("type")) {
            boolean spiritForwardOrRefreshing = AllYourDatabaseAreBelongToDrunks.needThatSpiritForwardOrRefreshing(drinkID);
            if (spiritForwardOrRefreshing) {
                Integer newData = intInput("Enter new drink type: type1 (1), type2 (2), type3 (3).");
                AllYourDatabaseAreBelongToDrunks.updateInteger("Drinks", finalDecision, newData, "Drink_ID", drinkID);
            } else {
                Integer newData = intInput("Enter new drink type: type1 (1), type2 (2), type3 (3).");
                AllYourDatabaseAreBelongToDrunks.updateInteger("Drinks", finalDecision, newData, "Drink_ID", drinkID);
            }
        }
    }

    private static void updateDrinkAvailability() {
        System.out.println("Availability Start: " + AllYourDatabaseAreBelongToDrunks.needThatAvailabilityStart(drinkID));
        System.out.println("Availability End: " + AllYourDatabaseAreBelongToDrunks.needThatAvailabilityEnd(drinkID));
        String finalDecision = stringInput("Update availability_start or availability_end?");
        if (finalDecision.equals("availability_start")) {
            String newData = stringInput("Enter new start date (MM/DD/YY).");
            AllYourDatabaseAreBelongToDrunks.updateDate("Drinks", finalDecision, newData, "Drink_ID", drinkID);
        } else if (finalDecision.equals("availability_end")) {
            String newData = stringInput("Enter new start date (MM/DD/YY).");
            AllYourDatabaseAreBelongToDrunks.updateDate("Drinks", finalDecision, newData, "Drink_ID", drinkID);
        }
    }

    private static void deleteDrink() {
        String otherDecision = stringInput("Would you like to delete entire drink, or ingredients (drink or ingredient)?");
        boolean optionChosen = getDrinkChoice();

        drinkID = AllYourDatabaseAreBelongToDrunks.needThatDrinkID(drinkSelection);

        if (otherDecision.equals("drink")) {
            if (optionChosen) {
                AllYourDatabaseAreBelongToDrunks.itWasDeliciousWhileItLasted(drinkID);
            }
        } else if (otherDecision.equals("ingredient")) {
            if (optionChosen) {
                ArrayList<String> ingredients = AllYourDatabaseAreBelongToDrunks.needThatRecipe(drinkID);
                for (String ingredient : ingredients) {
                    System.out.println(ingredient);
                }

                String toBeDeleted = stringInput("Which ingredient would you like to delete?");
                Integer ingredientID = AllYourDatabaseAreBelongToDrunks.needThatIngredientID(toBeDeleted);
                AllYourDatabaseAreBelongToDrunks.thatTastesGrossInThis(drinkID, ingredientID);
            }
        }
    }

    private static void reviewDrink() {
        boolean optionChosen = getDrinkChoice();

        drinkID = AllYourDatabaseAreBelongToDrunks.needThatDrinkID(drinkSelection);
        if (optionChosen) {
            String finalDecision = stringInput("Would you like to review ingredients or data?");
            if (finalDecision.equals("ingredients")) {
                ArrayList<String> ingredients = AllYourDatabaseAreBelongToDrunks.needThatRecipe(drinkID);
                System.out.println("Ingredients in " + drinkSelection + ":");
                for (String ingredient : ingredients) {
                    System.out.println(ingredient);
                }
            } else if (finalDecision.equals("data")) {
                System.out.println("Drink data for " + drinkSelection + ":");
                displayDrinkData();
            }
        }
    }

    private static boolean getDrinkChoice() {
        ArrayList<String> locations = AllYourDatabaseAreBelongToDrunks.needThoseLocations(ownerID);
        for (String location : locations) {
            System.out.println(location);
        }
        locationName = stringInput("Which location would you like?");
        locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(locationName);

        AllYourDatabaseAreBelongToDrunks.needThoseDrinks(locationID);
        ArrayList<String> drinks = AllYourDatabaseAreBelongToDrunks.needThoseDrinks(locationID);
        boolean optionChosen = false;

        for (String drink : drinks) {
            boolean doIt = yesNoInput("Would you like the drink named " + drink + "?");
            if (doIt) {
                drinkSelection = drink;
                optionChosen = true;
                break;
            }
        }

        return optionChosen;
    }

    private static void displayDrinkData() {
        System.out.println("Alcohol content, scale from 1-5: " + AllYourDatabaseAreBelongToDrunks.needThatAlcoholContent(drinkID));
        System.out.println("Price: " + AllYourDatabaseAreBelongToDrunks.needThatPrice(drinkID));
        System.out.println("Specialty Price: " + AllYourDatabaseAreBelongToDrunks.needThatSpecialtyPrice(drinkID));
        System.out.println("Complexity, scale from 1-5: " + AllYourDatabaseAreBelongToDrunks.needThatComplexity(drinkID));
        boolean spiritForwardOrRefreshing = AllYourDatabaseAreBelongToDrunks.needThatSpiritForwardOrRefreshing(drinkID);
        System.out.println("Spirit forward(false) or refreshing(true): " + spiritForwardOrRefreshing);
        if (spiritForwardOrRefreshing) {
            System.out.println("Drink type, type1 (1), type2 (2), type3 (3): " + AllYourDatabaseAreBelongToDrunks.needThatType(drinkID));
        } else {
            System.out.println("Drink type, type1 (1), type2 (2), type3 (3): " + AllYourDatabaseAreBelongToDrunks.needThatType(drinkID));
        }
    }
}

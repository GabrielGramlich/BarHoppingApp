import java.util.ArrayList;
import java.util.regex.Pattern;

import static input.InputUtils.*;

public class WhoAreYouAndWhatDoYouWant {
    public static String username;
    public static Integer loginID;
    public static Integer ownerID;
    public static String password;
    public static String salt;
    public static String email;
    public static String locationName;
    public static Integer locationID;
    public static Integer drinkID;
    public static String drinkSelection;

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

    public static void userOrOwner() {
        Integer accountType = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Account_Type", "Login_Credentials", "Username", username);

        if (accountType == 1) {
            ownerMenu();
        } else if (accountType == 0) {
            userMenu();
        }
    }

    public static void deleteAccount() {
        loginID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Login_ID", "Login_Credentials", "Username", username);
        Integer userID = AllYourDatabaseAreBelongToDrunks.selectInteger("User_ID", "Users", "Login_Credentials_Login_ID", loginID);
        boolean response = yesNoInput("Awh, you wanna leave? Big baby got his feewings hurt?");
        if (response) {
            AllYourDatabaseAreBelongToDrunks.delete("Users", "User_ID", userID);
            AllYourDatabaseAreBelongToDrunks.delete("Login_Credentials", "Login_ID", loginID);
        }
    }

    public static boolean createAccountLogin() {
        Integer alreadyChosen = 666;
        password = "";
        boolean owner = yesNoInput("You a corporate fat cat?");
        boolean quit = false;

        while (alreadyChosen != 0) {
            username = stringInput("Username. Gimme.");
            password = stringInput("Oy. Password, guvna!");
            alreadyChosen = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Account_Type", "Login_Credentials", "Username", username);
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

    public static void createUserAccount() {
        String firstName = stringInput("What's your first name? It's Douche, isn't it?");
        String lastName = stringInput("Last name, Bag.");

        AllYourDatabaseAreBelongToDrunks.greetingsFriend(firstName, lastName, loginID);

        getUserPreferences(true);
    }

    public static void createOwnerAccount() {
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

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static void getUserPreferences(boolean firstTime) {
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

    public static void userMenu() {
        //TODO finish user menu
        String decision = stringInput("What you want? Drink, account, home?");
        if (decision.equals("drink")) {
            HopThoseBars.letsGetLit();
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

    public static void changeUserSettings() {
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

                salt = AllYourDatabaseAreBelongToDrunks.selectStringWithString("Salt", "Login_Credentials", "Username", username);
                newInfo = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(newPassword, salt);
            }

            if (toBeUpdated.equals("name")) {
                loginID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Login_ID", "Login_Credentials", "Username", username);
                Integer userID = AllYourDatabaseAreBelongToDrunks.selectInteger("User_ID", "Users", "Login_Credentials_Login_ID", loginID);
                Integer spaceIndex = newInfo.indexOf(" ");
                String first = newInfo.substring(0, spaceIndex);
                String last = newInfo.substring(spaceIndex + 1);

                AllYourDatabaseAreBelongToDrunks.updateString("Users", "First_Name", first, "User_ID", userID);
                AllYourDatabaseAreBelongToDrunks.updateString("Users", "Last_Name", last, "User_ID", userID);
            } else {
                Integer loginID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Login_ID", "Login_Credentials", "Username", username);
                AllYourDatabaseAreBelongToDrunks.updateString("Login_Credentials", toBeUpdated, newInfo, "Login_ID", loginID);
            }
        } while (!yesNoInput("You done yet?"));

        System.out.println("Bout time.");
    }

    public static void ownerMenu() {
        loginID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Login_ID", "Login_Credentials", "Username", username);
        ownerID = AllYourDatabaseAreBelongToDrunks.selectInteger("Owner_ID", "Owners", "Login_Credentials_Login_ID", loginID);
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

    public static void changeOwnerSettings() {
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
                salt = AllYourDatabaseAreBelongToDrunks.selectStringWithString("Salt", "Login_Credentials", "Username", username);
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

            loginID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Login_ID", "Login_Credentials", "Username", username);
            Integer ownerID = AllYourDatabaseAreBelongToDrunks.selectInteger("Owner_ID", "Owners", "Login_Credentials_Login_ID", loginID);

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



    public static void ownerLocationsMenu() {
        String decision = stringInput("Create, update, delete or review locations?");

        if (decision.equals("create")) {
            createLocation();
            createLocationCalendar();
        } else {
            String otherDecision = stringInput("Would you like to " + decision + " location information or hours of operation (location or hours).");
            ArrayList<String> locations = AllYourDatabaseAreBelongToDrunks.selectStringArrayList("Name", "Locations", "Owners_Owner_ID", ownerID);
            for (String location : locations) {
                System.out.println(location);
            }
            locationName = stringInput("Which location would you like to " + decision + "?");
            locationID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Location_ID", "Locations", "Name", locationName);

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

    public static void createLocation() {
        locationName = stringInput("Location name?");
        Double number = Double.valueOf(stringInput("Location phone number?"));
        String street = stringInput("Location street address?");
        String city = stringInput("Location city?");
        String state = stringInput("Location state?");
        Double zip = Double.valueOf(stringInput("Location zip code?"));

        AllYourDatabaseAreBelongToDrunks.welcomeToYourKingdom(ownerID, locationName, number, street, city, state, zip);
    }

    public static void createLocationCalendar() {
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

        locationID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Location_ID", "Locations", "Name", locationName);
        AllYourDatabaseAreBelongToDrunks.IDidntKnowKingdomsHadHours(locationID, days, openHours, closeHours, speHoursStart, speHoursEnd);
    }

    public static void updateLocation() {
        displayLocationData();

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

    public static void updateHours() {
        displayHours();

        String finalDecision = stringInput("Would you like to update time_open, time_close, specialty_hour_start or specialty_hour_end?");
        String dayOfWeek = stringInput("For which day of the week?");
        String newData = stringInput("New " + finalDecision + " for " + dayOfWeek + "? (HH:MM)");

        AllYourDatabaseAreBelongToDrunks.updateTime("Calendar", finalDecision, newData, "Location_ID", locationID, "Day_of_Week", dayOfWeek);
    }

    public static void deleteLocation() {
        boolean confirmed = yesNoInput("Are you sure you want to delete this location and its corresponding data?");
        if (confirmed) {
            AllYourDatabaseAreBelongToDrunks.delete("Locations", "Location_ID", locationID);
            AllYourDatabaseAreBelongToDrunks.delete("Calendar", "Locations_Location_ID", locationID);

        }
    }

    public static void reviewLocationData(String otherDecision) {
        if (otherDecision.equals("location")) {
            displayLocationData();
        } else if (otherDecision.equals("hours")) {
            displayHours();
        }
    }

    public static void displayLocationData() {
        System.out.println("Name:\t" + AllYourDatabaseAreBelongToDrunks.selectString("Name", "Locations","Location_ID", locationID));
        System.out.println("Number:\t" + AllYourDatabaseAreBelongToDrunks.selectString("Phone_Number", "Locations","Location_ID", locationID));
        System.out.println("Street:\t" + AllYourDatabaseAreBelongToDrunks.selectString("Street", "Locations","Location_ID", locationID));
        System.out.println("City:\t" + AllYourDatabaseAreBelongToDrunks.selectString("City", "Locations","Location_ID", locationID));
        System.out.println("State:\t" + AllYourDatabaseAreBelongToDrunks.selectString("State", "Locations","Location_ID", locationID));
        System.out.println("Zip:\t" + AllYourDatabaseAreBelongToDrunks.selectString("Zip", "Locations","Location_ID", locationID));
    }

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
        drinkID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Drink_ID", "Drinks", "Name", name);
        AllYourDatabaseAreBelongToDrunks.whatsInItThough(ingredients, drinkID);
    }

    public static void updateDrink() {
        boolean optionChosen = getDrinkChoice();

        if (optionChosen) {
            String otherDecision = stringInput("Would you like to update " + drinkSelection + "'s ingredients, description or availability?");
            drinkID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Drink_ID", "Drinks", "Name", drinkSelection);
            if (otherDecision.equals("ingredients")) {
                String finalDecision = stringInput("Would you like to change or add an ingredient)");
                if (finalDecision.equals("change")) {
                    displayRecipe();

                    String toUpdate = stringInput("Which ingredient would you like to change?");
                    String newData = stringInput("What would you like to change it to?");

                    Integer oldIngredientID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Ingredient_Name", "Ingredients", "Name", toUpdate);
                    Integer newIngredientID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Ingredient_Name", "Ingredients", "Name", newData);

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

    public static void updateDrinkDescription() {
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
            boolean spiritForwardOrRefreshing = AllYourDatabaseAreBelongToDrunks.selectBoolean("Spirit_Forward_or_Refreshing", "Drinks", "Drink_ID", drinkID);
            if (spiritForwardOrRefreshing) {
                Integer newData = intInput("Enter new drink type: type1 (1), type2 (2), type3 (3).");
                AllYourDatabaseAreBelongToDrunks.updateInteger("Drinks", finalDecision, newData, "Drink_ID", drinkID);
            } else {
                Integer newData = intInput("Enter new drink type: type1 (1), type2 (2), type3 (3).");
                AllYourDatabaseAreBelongToDrunks.updateInteger("Drinks", finalDecision, newData, "Drink_ID", drinkID);
            }
        }
    }

    public static void updateDrinkAvailability() {
        System.out.println("Availability Start: " + AllYourDatabaseAreBelongToDrunks.selectString("Availability_Start", "Drinks", "Drink_ID", drinkID));
        System.out.println("Availability End: " + AllYourDatabaseAreBelongToDrunks.selectString("Availability_End", "Drinks", "Drink_ID", drinkID));
        String finalDecision = stringInput("Update availability_start or availability_end?");
        if (finalDecision.equals("availability_start")) {
            String newData = stringInput("Enter new start date (MM/DD/YY).");
            AllYourDatabaseAreBelongToDrunks.updateDate("Drinks", finalDecision, newData, "Drink_ID", drinkID);
        } else if (finalDecision.equals("availability_end")) {
            String newData = stringInput("Enter new start date (MM/DD/YY).");
            AllYourDatabaseAreBelongToDrunks.updateDate("Drinks", finalDecision, newData, "Drink_ID", drinkID);
        }
    }

    public static void deleteDrink() {
        String otherDecision = stringInput("Would you like to delete entire drink, or ingredients (drink or ingredient)?");
        boolean optionChosen = getDrinkChoice();

        drinkID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Drink_ID", "Drinks", "Name", drinkSelection);

        if (otherDecision.equals("drink")) {
            if (optionChosen) {
                AllYourDatabaseAreBelongToDrunks.delete("Drinks", "Drink_ID", drinkID);
                AllYourDatabaseAreBelongToDrunks.delete("Recipes", "Drinks_Drink_ID", drinkID);
            }
        } else if (otherDecision.equals("ingredient")) {
            if (optionChosen) {
                displayRecipe();

                String toBeDeleted = stringInput("Which ingredient would you like to delete?");
                Integer ingredientID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Ingredient_Name", "Ingredients", "Name", toBeDeleted);
                AllYourDatabaseAreBelongToDrunks.deleteWithSecondKey("Recipes", "Drinks_Drink_ID", drinkID, "Ingredients_Ingredient_ID", ingredientID);
            }
        }
    }

    public static void reviewDrink() {
        boolean optionChosen = getDrinkChoice();

        drinkID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Drink_ID", "Drinks", "Name", drinkSelection);
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
        System.out.println("Alcohol content, scale from 1-5: " + AllYourDatabaseAreBelongToDrunks.selectInteger("Alcohol_Content", "Drinks", "Drink_ID", drinkID));
        System.out.println("Price: " + AllYourDatabaseAreBelongToDrunks.selectDouble("Price", "Drinks", "Drink_ID", drinkID));
        System.out.println("Specialty Price: " + AllYourDatabaseAreBelongToDrunks.selectDouble("Specialty_Price", "Drinks", "Drink_ID", drinkID));
        System.out.println("Complexity, scale from 1-5: " + AllYourDatabaseAreBelongToDrunks.selectInteger("Complexity", "Drinks", "Drink_ID", drinkID));
        boolean spiritForwardOrRefreshing = AllYourDatabaseAreBelongToDrunks.selectBoolean("Spirit_Forward_or_Refreshing", "Drinks", "Drink_ID", drinkID);
        System.out.println("Spirit forward(false) or refreshing(true): " + spiritForwardOrRefreshing);
        if (spiritForwardOrRefreshing) {
            System.out.println("Drink type, type1 (1), type2 (2), type3 (3): " + AllYourDatabaseAreBelongToDrunks.selectInteger("Type", "Drinks", "Drink_ID", drinkID));
        } else {
            System.out.println("Drink type, type1 (1), type2 (2), type3 (3): " + AllYourDatabaseAreBelongToDrunks.selectInteger("Type", "Drinks", "Drink_ID", drinkID));
        }
    }

    public static void displayRecipe() {
        Integer recipeID = AllYourDatabaseAreBelongToDrunks.selectInteger("Recipe_ID", "Recipes", "Drink_ID", drinkID);
        ArrayList<Integer> ingredientIDs = AllYourDatabaseAreBelongToDrunks.selectIntegerArrayList("Ingredient_ID", "Recipes", "Recipe_ID", recipeID);
        ArrayList<String> ingredients = new ArrayList<>();

        for (Integer ID : ingredientIDs) {
            ingredients.add(AllYourDatabaseAreBelongToDrunks.selectString("Name", "Ingredients", "Ingredient_ID", ID));
        }

        System.out.println("Ingredients in " + drinkSelection + ":");
        for (String ingredient : ingredients) {
            System.out.println(ingredient);
        }
    }

    public static void displayHours() {
        System.out.println("Day of week:\t\t" + AllYourDatabaseAreBelongToDrunks.selectString("Day_of_Week", "Locations", "Location_ID", locationID));
        System.out.println("Time open:\t\t" + AllYourDatabaseAreBelongToDrunks.selectString("Time_Open", "Locations", "Location_ID", locationID));
        System.out.println("Time close:\t\t" + AllYourDatabaseAreBelongToDrunks.selectString("Time_Close", "Locations", "Location_ID", locationID));
        System.out.println("Specialty hour start:\t" + AllYourDatabaseAreBelongToDrunks.selectString("Specialty_Hour_Start", "Locations", "Location_ID", locationID));
        System.out.println("Specialty hour end:\t" + AllYourDatabaseAreBelongToDrunks.selectString("Specialty_Hour_End", "Locations", "Location_ID", locationID));
    }

    public static boolean getDrinkChoice() {
        ArrayList<String> locations = AllYourDatabaseAreBelongToDrunks.selectStringArrayList("Name", "Locations", "Owners_Owner_ID", ownerID);
        for (String location : locations) {
            System.out.println(location);
        }
        locationName = stringInput("Which location would you like?");
        locationID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Location_ID", "Locations", "Name", locationName);

        ArrayList<Integer> drinks = AllYourDatabaseAreBelongToDrunks.selectIntegerArrayList("Drinks_Drink_ID", "Drink_Locations", "Location_ID", locationID);
        ArrayList<String> drinkNames = new ArrayList<>();

        for (Integer drink : drinks) {
            drinkNames.add(AllYourDatabaseAreBelongToDrunks.selectString("Name", "Drinks", "Drink_ID", drink));
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

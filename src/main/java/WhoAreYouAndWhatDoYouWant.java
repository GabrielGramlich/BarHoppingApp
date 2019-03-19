import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import static input.InputUtils.*;

public class WhoAreYouAndWhatDoYouWant {
    //TODO break methods down into more manageable units

    public static String USERNAME;

    public static void main(String[] args) {
        boolean weHave = DontHopUnlessISaySo.haveWeMet();
        boolean quit = true;
        USERNAME = "";
        String password = "";
        // TODO get username and password if already signed in

        boolean newUser = yesNoInput("Are you a new member?");

        if (newUser) {
            quit = pullUpAStool();
        }

        if (quit) {
            while (!weHave) {
                USERNAME = stringInput("Enter your username:");
                password = stringInput("Enter your password:");
                weHave = DontHopUnlessISaySo.whosThere(USERNAME, password);
            }
        }

        whatreYouHaving();
    }

    public static void whatreYouHaving() {
        String pubCrawl = doINeedATie();
        if (pubCrawl.equals("pub")) {
            whatCanIDoForYouMaster();
        } else if (pubCrawl.equals("crawl")) {
            whatDoYouWantPeasant();
        }
    }

    public static String doINeedATie() {
        Integer pubcrawl = AllYourDatabaseAreBelongToDrunks.needThatAccountType(USERNAME);

        if (pubcrawl == 2) {
            return "pub";
        } else if (pubcrawl == 1) {
            return "crawl";
        } else {
            return "failureasfsdf";
        }
    }

    public static void IHateYou() {
        Integer loginID = AllYourDatabaseAreBelongToDrunks.needThatCredentialID(USERNAME);
        Integer userID = AllYourDatabaseAreBelongToDrunks.needThatUserID(USERNAME);
        boolean response = yesNoInput("Awh, you wanna leave? Big baby got his feewings hurt?");
        if (response) {
            AllYourDatabaseAreBelongToDrunks.throwThatInTheTrash(loginID, userID);
        }
    }

    public static boolean pullUpAStool() {
        Integer alreadyChosen = 666;
        String potatoes = "";
        boolean hotOrCold = yesNoInput("You a corporate fat cat?");
        boolean quit = false;

        while (alreadyChosen != 0) {
            USERNAME = stringInput("Username. Gimme.");
            potatoes = stringInput("Oy. Password, guvna!");
            alreadyChosen = AllYourDatabaseAreBelongToDrunks.needThatAccountType(USERNAME);
            if (alreadyChosen != 0) {
                boolean decision = yesNoInput("Username already chosen. Would you like to sign in?");
                if (decision) {
                    alreadyChosen = 0;
                    quit = true;
                }
            }
        }

        if (!quit) {
            String email = stringInput("Now the email.");

            while (!itsCalledEmailGrandpa(email)) {
                email = stringInput("Wrong. Try again.");
            }

            String seasoning = DontHopUnlessISaySo.worthYourWeightInEncryption();
            String saltyHashBrowns = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(potatoes, seasoning);

            Integer ID = AllYourDatabaseAreBelongToDrunks.whoAreYou(hotOrCold, USERNAME, email, saltyHashBrowns, seasoning);

            if (!hotOrCold) {
                String firstName = stringInput("What's your first name? It's Douche, isn't it?");
                String lastName = stringInput("Last name, Bag.");

                AllYourDatabaseAreBelongToDrunks.greetingsFriend(firstName, lastName, ID);

                yourePrettyPickyArentYou(true);
            } else {
                String firstName = stringInput("What's your first, sir");
                String lastName = stringInput("And your surname?");
                Double contactNumber = Double.valueOf(stringInput(("What number can we contact you at? (0005550000)")));
                String contactEmail = email;
                if (!yesNoInput("Can we contact you at the email address previously provided?")) {
                    contactEmail = stringInput("What email address would you like to use for correspondence?");
                    while (!itsCalledEmailGrandpa(contactEmail)) {
                        contactEmail = stringInput("Invalid email address. Please enter another.");
                    }
                }
                AllYourDatabaseAreBelongToDrunks.helloBoss(ID, firstName, lastName, contactNumber, contactEmail);

                whatCanIDoForYouMaster();
            }
        }
        return quit;
    }

    public static boolean itsCalledEmailGrandpa(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static void yourePrettyPickyArentYou(boolean firstTime) {
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

        AllYourDatabaseAreBelongToDrunks.youGotSomeWeirdKinks(USERNAME, allergies, topShelf, bottomShelf, weakOrStrong, deepPockets, youFancy);
    }

    public static void whatCanIDoForYouMaster() {
        Integer loginID = AllYourDatabaseAreBelongToDrunks.needThatOwnerLoginID(USERNAME);
        Integer ownerID = AllYourDatabaseAreBelongToDrunks.needThatOwnerID(loginID);
        String decision = stringInput("Does master want the account, location or drink settings?");
        if (decision.equals("account")) {
            String otherDecision = stringInput("Delete or change account?");
            if (otherDecision.equals("delete")) {
                IHateYou();
            } else if (otherDecision.equals("change")) {
                youveLostWeight();
            }
        } else if (decision.equals("location")) {
            whereAmI(ownerID);
        } else if (decision.equals("drink")) {
            whatWasThat(ownerID);
        }
    }

    public static void youveLostWeight() {
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
                String salt = AllYourDatabaseAreBelongToDrunks.needThatSalt(USERNAME);
                newInfo = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(newPassword, salt);
            } else if (toBeUpdated.equals("name")) {
                newInfo = stringInput("New name?");
            } else if (toBeUpdated.equals("contact number")) {
                newInfo = stringInput("New contact number?");
                toBeUpdated = "Contact_Number";
            } else if (toBeUpdated.equals("contact email")) {
                newInfo = stringInput("New contact email?");
                toBeUpdated = "Contact_Email";
                while (!itsCalledEmailGrandpa(newInfo)) {
                    newInfo = stringInput("Invalid email address. Please enter another.");
                }
            }

            AllYourDatabaseAreBelongToDrunks.itsAnHonorToGetToKnowYou(toBeUpdated, newInfo, USERNAME);
        } while (yesNoInput("Do you still require assistance, sir?"));

        System.out.println("It's been a pleasure.");
    }



    public static void whereAmI(Integer ownerID) {
        String decision = stringInput("Create, update, delete or review locations?");

        if (decision.equals("create")) {
            locationCreation(ownerID);
        } else {
            String otherDecision = stringInput(decision + " location information or hours of operation (location or hours).");
            ArrayList<String> locations = AllYourDatabaseAreBelongToDrunks.needThoseLocations(ownerID);
            for (String location : locations) {
                System.out.println(location);
            }
            String locationName = stringInput("Which location would you like to " + decision + "?");
            Integer locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(locationName);

            if (decision.equals("update")) {
                locationUpdation(otherDecision, locationID);
            } else if (decision.equals("delete")) {
                boolean confirmed = yesNoInput("Are you sure you want to delete this location and its corresponding data?");
                if (confirmed) {
                    AllYourDatabaseAreBelongToDrunks.thanksForStoppingBy(locationID);
                }
            } else if (decision.equals("review")) {
                if (otherDecision.equals("location")) {
                    AllYourDatabaseAreBelongToDrunks.needThatLocationData(locationID);
                } else if (otherDecision.equals("hours")) {
                    AllYourDatabaseAreBelongToDrunks.needThatHourData(locationID);
                }
            }
        }
    }

    public static void locationCreation(Integer ownerID) {
        String name = stringInput("Location name?");
        Double number = Double.valueOf(stringInput("Location phone number?"));
        String street = stringInput("Location street address?");
        String city = stringInput("Location city?");
        String state = stringInput("Location state?");
        Double zip = Double.valueOf(stringInput("Location zip code?"));

        AllYourDatabaseAreBelongToDrunks.welcomeToYourKingdom(ownerID, name, number, street, city, state, zip);

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

        Integer locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(name);
        AllYourDatabaseAreBelongToDrunks.IDidntKnowKingdomsHadHours(locationID, days, openHours, closeHours, speHoursStart, speHoursEnd);
    }

    public static void locationUpdation(String otherDecision, Integer locationID) {
        if (otherDecision.equals("location")) {
            AllYourDatabaseAreBelongToDrunks.needThatLocationData(locationID);

            String informationType = stringInput("Would you like to update, name, phone_number, street, city, state or zip?");
            String newStringData = stringInput("New " + informationType + "?");
            Double newDoubleData;

            if (informationType.equals("phone_number") || informationType.equals("zip")) {
                //TODO provide formatting if entering number or zip
                newDoubleData = Double.valueOf(newStringData);
                AllYourDatabaseAreBelongToDrunks.thanksForKeepingUsMovingDouble(informationType, newDoubleData, locationID);
            } else {
                AllYourDatabaseAreBelongToDrunks.thanksForKeepingUsMovingString(informationType, newStringData, locationID);
            }
        } else if (otherDecision.equals("hours")) {
            AllYourDatabaseAreBelongToDrunks.needThatHourData(locationID);

            String informationType = stringInput("Would you like to update time_open, time_close, specialty_hour_start or specialty_hour_end?");
            String dayData = stringInput("For which day of the week?");
            String newData = "";

            newData = stringInput("New " + informationType + " for " + dayData + "? (HH:MM)");

            AllYourDatabaseAreBelongToDrunks.thanksForStayingOpen(dayData, informationType, newData, locationID);
        }
    }

    public static void whatWasThat(Integer ownerID) {
        String decision = stringInput("Create, update, delete or review drinks?");
        if (decision.equals("create")) {
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
            Integer drinkID = AllYourDatabaseAreBelongToDrunks.needThatDrinkID(name);
            AllYourDatabaseAreBelongToDrunks.whatsInItThough(ingredients, drinkID);
        } else if (decision.equals("update")) {
            ArrayList<String> locations = AllYourDatabaseAreBelongToDrunks.needThoseLocations(ownerID);
            for (String location : locations) {
                System.out.println(location);
            }
            String locationName = stringInput("Which location would you like to update drinks for?");
            Integer locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(locationName);

            AllYourDatabaseAreBelongToDrunks.needThoseDrinks(locationID);
            ArrayList<String> drinks = AllYourDatabaseAreBelongToDrunks.needThoseDrinks(locationID);
            String drinkToUpdate = "";
            boolean optionChosen = false;

            for (String drink : drinks) {
                boolean doIt = yesNoInput("Would you like to update the drink named " + drink + "?");
                if (doIt) {
                    drinkToUpdate = drink;
                    optionChosen = true;
                    break;
                }
            }

            if (optionChosen) {
                String otherDecision = stringInput("Would you like to update " + drinkToUpdate + "'s ingredients, description or availability?");
                Integer drinkID = AllYourDatabaseAreBelongToDrunks.needThatDrinkID(drinkToUpdate);
                if (otherDecision.equals("ingredients")) {
                    String finaldecision = stringInput("Would you like to change or add an ingredient)");
                    if (finaldecision.equals("change")) {
                        ArrayList<String> ingredients = AllYourDatabaseAreBelongToDrunks.needThatRecipe(drinkID);
                        System.out.println("Drink contains:");
                        for (String ingredient : ingredients) {
                            System.out.println(ingredient);
                        }
                        String toUpdate = stringInput("Which ingredient would you like to change?");
                        String newData = stringInput("What would you like to change it to?");
                        AllYourDatabaseAreBelongToDrunks.thatllTasteBetter(drinkID, toUpdate, newData);
                    } else if (finaldecision.equals("add")) {
                        String newData = stringInput("What would you like to add to " + drinkToUpdate + "?");
                        AllYourDatabaseAreBelongToDrunks.evenBetter(drinkID, newData);
                    }

                } else if (otherDecision.equals("description")) {
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

                    String finalDecision = stringInput("Update alcohol_content, price, specialty_price, complexity, spirit_forward_or_refreshing, or type?");
                    if (finalDecision.equals("alcohol_content")) {
                        Integer newData = intInput("Enter new strength on a scale of 1-5.");
                        AllYourDatabaseAreBelongToDrunks.thatsGonnaFuckMeUp(drinkID, newData);
                    } else if (finalDecision.equals("price")) {
                        Double newData = doubleInput("Enter new price.");
                        AllYourDatabaseAreBelongToDrunks.thatsSoExpensive(drinkID, newData);
                    } else if (finalDecision.equals("specialty_price")) {
                        Double newData = doubleInput("Enter new specialty price.");
                        AllYourDatabaseAreBelongToDrunks.thatsSoCheap(drinkID, newData);
                    } else if (finalDecision.equals("complexity")) {
                        Integer newData = intInput("Enter new complexity on a scale of 1-5.");
                        AllYourDatabaseAreBelongToDrunks.thatsSoComplex(drinkID, newData);
                    } else if (finalDecision.equals("spirit_forward_or_refreshing")) {
                        boolean newData = yesNoInput("Is it spirit forward (N), or refreshing (Y)?");
                        AllYourDatabaseAreBelongToDrunks.thatsSoOneOrTheOther(drinkID, newData);
                    } else if (finalDecision.equals("type")) {
                        if (spiritForwardOrRefreshing) {
                            Integer newData = intInput("Enter new drink type: type1 (1), type2 (2), type3 (3).");
                            AllYourDatabaseAreBelongToDrunks.thatsSoWhatThatIs(drinkID, newData);
                        } else {
                            Integer newData = intInput("Enter new drink type: type1 (1), type2 (2), type3 (3).");
                            AllYourDatabaseAreBelongToDrunks.thatsSoWhatThatIs(drinkID, newData);
                        }
                    }
                } else if (otherDecision.equals("availability")) {
                    System.out.println("Availability Start: " + AllYourDatabaseAreBelongToDrunks.needThatAvailabilityStart(drinkID));
                    System.out.println("Availability End: " + AllYourDatabaseAreBelongToDrunks.needThatAvailabilityEnd(drinkID));
                    String finalDecision = stringInput("Update availability_start or availability_end?");
                    if (finalDecision.equals("availability_start")) {
                        String newData = stringInput("Enter new start date (MM/DD/YY).");
                        AllYourDatabaseAreBelongToDrunks.soThatsWhenYouStart(drinkID, newData);
                    } else if (finalDecision.equals("availability_end")) {
                        String newData = stringInput("Enter new start date (MM/DD/YY).");
                        AllYourDatabaseAreBelongToDrunks.soThatsWhenYouEnd(drinkID, newData);
                    }
                }
            }
        } else if (decision.equals("delete")) {
            String otherDecision = stringInput("Would you like to delete entire drink, or ingredients (drink or ingredient)?");
            ArrayList<String> locations = AllYourDatabaseAreBelongToDrunks.needThoseLocations(ownerID);
            for (String location : locations) {
                System.out.println(location);
            }
            String locationName = stringInput("Which location would you like to delete a drink from?");
            Integer locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(locationName);

            AllYourDatabaseAreBelongToDrunks.needThoseDrinks(locationID);
            ArrayList<String> drinks = AllYourDatabaseAreBelongToDrunks.needThoseDrinks(locationID);
            String drinkToDelete = "";
            boolean optionChosen = false;

            for (String drink : drinks) {
                boolean doIt = yesNoInput("Would you like to delete the " + otherDecision + " for " + drink + "?");
                if (doIt) {
                    drinkToDelete = drink;
                    optionChosen = true;
                    break;
                }
            }
            Integer drinkID = AllYourDatabaseAreBelongToDrunks.needThatDrinkID(drinkToDelete);

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
        } else if (decision.equals("review")) {
            //TODO print all drinks
            ArrayList<String> locations = AllYourDatabaseAreBelongToDrunks.needThoseLocations(ownerID);
            for (String location : locations) {
                System.out.println(location);
            }
            String locationName = stringInput("Which location would you like to delete a drink from?");
            Integer locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(locationName);

            AllYourDatabaseAreBelongToDrunks.needThoseDrinks(locationID);
            ArrayList<String> drinks = AllYourDatabaseAreBelongToDrunks.needThoseDrinks(locationID);
            String drinkToReview = "";
            boolean optionChosen = false;

            for (String drink : drinks) {
                boolean doIt = yesNoInput("Would you like to review the drink named " + drink + "?");
                if (doIt) {
                    drinkToReview = drink;
                    optionChosen = true;
                    break;
                }
            }
            Integer drinkID = AllYourDatabaseAreBelongToDrunks.needThatDrinkID(drinkToReview);
            if (optionChosen) {
                String finalDecision = stringInput("Would you like to review ingredients or data?");
                if (finalDecision.equals("ingredients")) {
                    ArrayList<String> ingredients = AllYourDatabaseAreBelongToDrunks.needThatRecipe(drinkID);
                    System.out.println("Ingredients in " + drinkToReview + ":");
                    for (String ingredient : ingredients) {
                        System.out.println(ingredient);
                    }
                } else if (finalDecision.equals("data")) {
                    System.out.println("Drink data for " + drinkToReview + ":");
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
        }
    }

    public static void whatDoYouWantPeasant() {
        //TODO finish user menu
        String decision = stringInput("What you want? Drink, account, home?");
        if (decision.equals("drink")) {
//            HopThoseBars.letsGetLit();
        } else if (decision.equals("account")) {
            String otherDecision = stringInput("Change it or delete it?");
            if (otherDecision.equals("change")) {
                youvePutOnAFewPounds();
            } else if (otherDecision.equals("delete")) {
                IHateYou();
            }
        } else if (decision.equals("home")) {
//            HopThoseBars.seeYouAround();
        }
        //TODO loop the menu
    }

    public static void youvePutOnAFewPounds() {
        do {
            String toBeUpdated = stringInput("Change email, name, username, password, or preferences?");
            String newInfo = "";

            if (toBeUpdated.equals("preferences")) {
                yourePrettyPickyArentYou(false);
            } else if (toBeUpdated.equals("email")) {
                newInfo = stringInput("New email?");
                while (!itsCalledEmailGrandpa(newInfo)) {
                    newInfo = stringInput("Wrong. Try again.");
                }
                toBeUpdated = "Email_Address";
            } else if (toBeUpdated.equals("name")) {
                newInfo = stringInput("New name?");
            } else if (toBeUpdated.equals("username")) {
                newInfo = stringInput("New username?");
            } else if (toBeUpdated.equals("password")) {
                String newPassword = stringInput("New password?");
                String salt = AllYourDatabaseAreBelongToDrunks.needThatSalt(USERNAME);
                newInfo = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(newPassword, salt);
            }

            AllYourDatabaseAreBelongToDrunks.makeUpYourMindAlready(toBeUpdated, newInfo, USERNAME);
        } while (!yesNoInput("You done yet?"));

        System.out.println("Bout time.");
    }
}

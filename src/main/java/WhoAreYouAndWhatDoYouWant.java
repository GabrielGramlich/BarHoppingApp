import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import static input.InputUtils.*;

public class WhoAreYouAndWhatDoYouWant {
    //TODO create global variable for username. You're using that way too much, bruh.
    //TODO lines 236 and 297

    public static void main(String[] args) {
        boolean weHave = DontHopUnlessISaySo.haveWeMet();
        boolean quit = true;
        String username = "";
        String password = "";
        // TODO get username and password if already signed in

        boolean newUser = yesNoInput("Are you a new member?");

        if (newUser) {
            quit = pullUpAStool();
        }

        if (quit) {
            while (!weHave) {
                username = stringInput("Enter your username:");
                password = stringInput("Enter your password:");
                weHave = DontHopUnlessISaySo.whosThere(username, password);
            }
        }

        whatreYouHaving(username);
    }

    public static void whatreYouHaving(String username) {
        String pubCrawl = doINeedATie(username);
        if (pubCrawl.equals("pub")) {
            whatCanIDoForYouMaster(username);
        } else if (pubCrawl.equals("crawl")) {
            whatDoYouWantPeasant(username);
        }
    }

    public static String doINeedATie(String username) {
        Integer pubcrawl = AllYourDatabaseAreBelongToDrunks.needThatAccountType(username);

        if (pubcrawl == 2) {
            return "pub";
        } else if (pubcrawl == 1) {
            return "crawl";
        } else {
            return "failureasfsdf";
        }
    }

    public static void IHateYou(String username) {
        Integer loginID = AllYourDatabaseAreBelongToDrunks.needThatCredentialID(username);
        Integer userID = AllYourDatabaseAreBelongToDrunks.needThatUserID(username);
        boolean response = yesNoInput("Awh, you wanna leave? Big baby got his feewings hurt?");
        if (response) {
            AllYourDatabaseAreBelongToDrunks.throwThatInTheTrash(loginID, userID);
        }
    }

    public static boolean pullUpAStool() {
        Integer alreadyChosen = 666;
        String recipe = "";
        String potatoes = "";
        boolean hotOrCold = yesNoInput("You a corporate fat cat?");
        boolean quit = false;

        while (alreadyChosen != 0) {
            recipe = stringInput("Username. Gimme.");
            potatoes = stringInput("Oy. Password, guvna!");
            alreadyChosen = AllYourDatabaseAreBelongToDrunks.needThatAccountType(recipe);
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

            Integer ID = AllYourDatabaseAreBelongToDrunks.whoAreYou(hotOrCold, recipe, email, saltyHashBrowns, seasoning);

            if (!hotOrCold) {
                String firstName = stringInput("What's your first name? It's Douche, isn't it?");
                String lastName = stringInput("Last name, Bag.");

                AllYourDatabaseAreBelongToDrunks.greetingsFriend(firstName, lastName, ID);

                yourePrettyPickyArentYou(recipe, true);
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

                whatCanIDoForYouMaster(recipe);
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

    public static void yourePrettyPickyArentYou(String username, boolean firstTime) {
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

    public static void whatCanIDoForYouMaster(String username) {
        String decision = stringInput("Does master want the account, location or drink settings?");
        if (decision.equals("account")) {
            String otherDecision = stringInput("Delete or change account?");
            if (otherDecision.equals("delete")) {
                IHateYou(username);
            } else if (otherDecision.equals("change")) {
                youveLostWeight(username);
            }
        } else if (decision.equals("location")) {
            whereAmI(username);
        } else if (decision.equals("drink")) {
            whatWasThat();
        }
    }

    public static void youveLostWeight(String username) {
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
                String salt = AllYourDatabaseAreBelongToDrunks.needThatSalt(username);
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

            AllYourDatabaseAreBelongToDrunks.itsAnHonorToGetToKnowYou(toBeUpdated, newInfo, username);
        } while (yesNoInput("Do you still require assistance, sir?"));

        System.out.println("It's been a pleasure.");
    }



    public static void whereAmI(String username) {
        try {
            String decision = stringInput("Create, update, delete or review locations?");
            Integer loginID = AllYourDatabaseAreBelongToDrunks.needThatOwnerLoginID(username);
            Integer ownerID = AllYourDatabaseAreBelongToDrunks.needThatOwnerID(loginID);

            if (decision.equals("create")) {
                String name = stringInput("Location name?");
                Double number = Double.valueOf(stringInput("Location phone number?"));
                String street = stringInput("Location street address?");
                String city = stringInput("Location city?");
                String state = stringInput("Location state?");
                Double zip = Double.valueOf(stringInput("Location zip code?"));

                AllYourDatabaseAreBelongToDrunks.welcomeToYourKingdom(ownerID, name, number, street, city, state, zip);

                ArrayList<String> days = new ArrayList<>();
                ArrayList<java.sql.Date> openHours = new ArrayList<>();
                ArrayList<java.sql.Date> closeHours = new ArrayList<>();
                ArrayList<java.sql.Date> speHoursStart = new ArrayList<>();
                ArrayList<java.sql.Date> speHoursEnd = new ArrayList<>();
                days.add("Monday");
                days.add("Tuesday");
                days.add("Wednesday");
                days.add("Thursday");
                days.add("Friday");
                days.add("Saturday");
                days.add("Sunday");

                for (String day : days) {
                    //TODO figure out how to work with time
                    SimpleDateFormat format = new SimpleDateFormat("hh:mm a");

                    String openHoursInput = stringInput("When do you open on " + day + "? (hh:mm)");
                    String closeHoursInput = stringInput("When do you close on " + day + "? (hh:mm)");

                    java.sql.Date openHoursDate = new java.sql.Date(format.parse(openHoursInput).getTime());
                    java.sql.Date closeHoursDate = new java.sql.Date(format.parse(closeHoursInput).getTime());

                    openHours.add(openHoursDate);
                    closeHours.add(closeHoursDate);

                    boolean go = yesNoInput("Do you have specialty hours on " + day + "?");
                    if (go) {
                        String startHoursInput = stringInput("When do specialty hours start on " + day + "? (hh:mm)");
                        String endHoursInput = stringInput("When do specialty hours end on " + day + "? (hh:mm)");

                        java.sql.Date startHoursDate = new java.sql.Date(format.parse(startHoursInput).getTime());
                        java.sql.Date endHoursDate = new java.sql.Date(format.parse(endHoursInput).getTime());

                        speHoursStart.add(startHoursDate);
                        speHoursEnd.add(endHoursDate);
                    } else {
                        String startHoursInput = stringInput("00:00");
                        String endHoursInput = stringInput("00:00");

                        java.sql.Date startHoursDate = new java.sql.Date(format.parse(startHoursInput).getTime());
                        java.sql.Date endHoursDate = new java.sql.Date(format.parse(endHoursInput).getTime());

                        speHoursStart.add(startHoursDate);
                        speHoursEnd.add(endHoursDate);
                    }
                }

                Integer locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(name);
                AllYourDatabaseAreBelongToDrunks.IDidntKnowKingdomsHadHours(locationID, days, openHours, closeHours, speHoursStart, speHoursEnd);

            } else {
                String otherDecision = stringInput(decision + " location information or hours of operation (location or hours).");
                ArrayList<String> locations = AllYourDatabaseAreBelongToDrunks.needThoseLocations(ownerID);
                for (String location : locations) {
                    System.out.println(location);
                }
                String locationName = stringInput("Which location would you like to " + decision + "?");
                Integer locationID = AllYourDatabaseAreBelongToDrunks.needThatLocationID(locationName);

                if (decision.equals("update")) {

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
                        //TODO figure out how to work with time
                        AllYourDatabaseAreBelongToDrunks.needThatHourData(locationID);

                        String informationType = stringInput("Would you like to update time_open, time_close, specialty_hour_start or specialty_hour_end?");
                        String dayData = stringInput("For which day of the week?");
                        String newData = "";

                        newData = stringInput("New " + informationType + " in hours for " + dayData + "?");
                        newData += ":";
                        newData += stringInput("New " + informationType + " in minutes for " + dayData + "?");

                        AllYourDatabaseAreBelongToDrunks.thanksForStayingOpen(dayData, informationType, newData, locationID);
                    }
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
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
    }

    public static void whatWasThat() {
//        String decision = stringInput("Create, update, delete or review drinks?");
//        if (decision.equals("create")) {
//            //TODO get drink name and availability
//            //TODO get drink descriptions
//            //TODO get drink ingredient array
//            //TODO push all data
//        } else if (decision.equals("update")) {
//            //TODO print all drinks
//            //TODO get drink selection
//            //TODO get decision for updating ingredients, description, or availability
//            //TODO print corresponding data
//            //TODO get new data
//            //TODO push new data
//        } else if (decision.equals("delete")) {
//            //TODO print all drinks
//            //TODO get drink selection
//            //TODO delete selection
//        } else if (decision.equals("review")) {
//            //TODO print all drinks
//            //TODO get drink selection
//            //TODO get decision for reviewing ingredients, description, or availability
//            //TODO print corresponding data
//        }
    }

    public static void whatDoYouWantPeasant(String username) {
        //TODO finish user menu
        String decision = stringInput("What you want? Drink, account, home?");
        if (decision.equals("drink")) {
//            HopThoseBars.letsGetLit();
        } else if (decision.equals("account")) {
            String otherDecision = stringInput("Change it or delete it?");
            if (otherDecision.equals("change")) {
                youvePutOnAFewPounds(username);
            } else if (otherDecision.equals("delete")) {
                IHateYou(username);
            }
        } else if (decision.equals("home")) {
//            HopThoseBars.seeYouAround();
        }
        //TODO loop the menu
    }

    public static void youvePutOnAFewPounds(String username) {
        do {
            String toBeUpdated = stringInput("Change email, name, username, password, or preferences?");
            String newInfo = "";

            if (toBeUpdated.equals("preferences")) {
                yourePrettyPickyArentYou(username, false);
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
                String salt = AllYourDatabaseAreBelongToDrunks.needThatSalt(username);
                newInfo = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(newPassword, salt);
            }

            AllYourDatabaseAreBelongToDrunks.makeUpYourMindAlready(toBeUpdated, newInfo, username);
        } while (!yesNoInput("You done yet?"));

        System.out.println("Bout time.");
    }
}

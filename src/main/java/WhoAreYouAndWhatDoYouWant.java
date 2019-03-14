import java.util.ArrayList;
import java.util.regex.Pattern;

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

public class WhoAreYouAndWhatDoYouWant {
    //TODO create global variable for username. You're using that way too much, bruh.

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

            String firstName = stringInput("What's your first name? It's Douche, isn't it?");
            String lastName = stringInput("Last name, Bag.");

            AllYourDatabaseAreBelongToDrunks.greetingsFriend(firstName, lastName, ID);

            yourePrettyPickyArentYou(recipe, true);
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
            whereAmI();
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
            }

            AllYourDatabaseAreBelongToDrunks.makeUpYourMindAlready(toBeUpdated, newInfo, username);
        } while (!yesNoInput("Do you still require assistance, sir?"));

        System.out.println("It's been a pleasure.");
    }

    public static void whereAmI() {
        String decision = stringInput("Create, update, delete or review locations?");
        String otherDecision;
        if (decision.equals("create")) {
            //TODO get all location data
            //TODO push all location data
            //TODO get all hours data
            //TODO push all hours data
        } else if (decision.equals("update")) {
            otherDecision = stringInput("Location information or hours of operation (decision or hours).");
            if (otherDecision.equals("location")) {
                //TODO print location information
                //TODO get new information type #street, name, whatever
                //TODO get new data
                //TODO push new data
            } else if (otherDecision.equals("hours")) {
                //TODO print location information
                //TODO get selection for location
                //TODO print hour information for selected location
                //TODO get new information type #hours, days, specialty hours, whatever
                //TODO get new data
                //TODO push new data
            }
        } else if (decision.equals("delete")) {
            //TODO print location information
            //TODO get selection for location
            //TODO delete selection
        } else if (decision.equals("review")) {
            otherDecision = stringInput("Location information or hours of operation (decision or hours).");
            if (otherDecision.equals("location")) {
                //TODO print location information
            } else if (otherDecision.equals("hours")) {
                //TODO print location information
                //TODO get selection for location
                //TODO print hour information for selected location
            }
        }
    }

    public static void whatWasThat() {
        String decision = stringInput("Create, update, delete or review drinks?");
        if (decision.equals("create")) {
            //TODO get drink name and availability
            //TODO get drink descriptions
            //TODO get drink ingredient array
            //TODO push all data
        } else if (decision.equals("update")) {
            //TODO print all drinks
            //TODO get drink selection
            //TODO get decision for updating ingredients, description, or availability
            //TODO print corresponding data
            //TODO get new data
            //TODO push new data
        } else if (decision.equals("delete")) {
            //TODO print all drinks
            //TODO get drink selection
            //TODO delete selection
        } else if (decision.equals("review")) {
            //TODO print all drinks
            //TODO get drink selection
            //TODO get decision for reviewing ingredients, description, or availability
            //TODO print corresponding data
        }
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

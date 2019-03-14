import java.util.ArrayList;

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

public class WhoAreYouAndWhatDoYouWant {
    public static void main(String[] args) {
        boolean newUser = yesNoInput("Are you a new member?");

        if (newUser) {
            pullUpAStool();
        } else {
            boolean weHave = DontHopUnlessISaySo.haveWeMet();
            String username = "";
            String password = "";
            // TODO get username and password if already signed in

            while (!weHave) {
                username = stringInput("Enter your username:");
                password = stringInput("Enter your password:");
                weHave = DontHopUnlessISaySo.whosThere(username, password);
            }

            whatreYouHaving(username);
        }
    }

    public static void whatreYouHaving(String username) {
        String pubCrawl = doINeedATie(username);
        if (pubCrawl.equals("pub")) {
            System.out.println("Calling whatCanIDoForYouMaster");
            whatCanIDoForYouMaster();
        } else if (pubCrawl.equals("crawl")) {
            System.out.println("Calling whatDoYouWantPeasant");
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

//    public static void IHateYou() {
//        //TODO allow user to delete profile
//        nevermind();
//    }

//    public static void nevermind() {
//        //TODO discard changes or save to database
//        AllYourDatabaseAreBelongToDrunks.thisOrThat();
//    }

    public static void pullUpAStool() {
        Integer alreadyChosen = 1;
        String recipe = "";
        String potatoes = "";
        boolean hotOrCold = yesNoInput("You a corporate fat cat?");

        while (alreadyChosen != 0) {
            recipe = stringInput("Username. Gimme.");
            potatoes = stringInput("Oy. Password, guvna!");
            alreadyChosen = AllYourDatabaseAreBelongToDrunks.needThatAccountType(recipe);
            if (alreadyChosen != 0) {
                System.out.println("Already chosen. Pick a different username.");
            }
        }

        String email = stringInput("Now the email.");
        // TODO check formatting for input

        String seasoning = DontHopUnlessISaySo.worthYourWeightInEncryption();
        String saltyHashBrowns = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(potatoes, seasoning);

        Integer ID = AllYourDatabaseAreBelongToDrunks.whoAreYou(hotOrCold, recipe, email, saltyHashBrowns, seasoning);

        String firstName = stringInput("What's your first name? It's Douche, isn't it?");
        String lastName = stringInput("Last name, Bag.");

        AllYourDatabaseAreBelongToDrunks.greetingsFriend(firstName, lastName, ID);

        yourePrettyPickyArentYou(recipe, true);

        whatreYouHaving(recipe);
    }

    public static void yourePrettyPickyArentYou(String username, boolean firstTime) {
        //TODO set user preferences

        if (firstTime) {
            ArrayList<String> allergies = new ArrayList<>();

            while (yesNoInput("Any (more) allergies?")) {
                allergies.add(stringInput("What'll kill ya?"));
            }
            //TODO put allergies in database
        }

        String topShelf = stringInput("Favorite spirit?");
        String bottomShelf = stringInput("Least favorite?");
        boolean strongOrWeak = yesNoInput("Do you have hair on your chest?");
        boolean deepPockets = yesNoInput("You're not broke, right?");
        boolean youFancy = yesNoInput("You a fan of mixology?");

        //TODO put user defined preferences in database
    }

    public static void whatCanIDoForYouMaster() {
//        //TODO create user menu
//        youveLostWeight();
//        IHateYou();
    }

//    public static void youveLostWeight() {
//        //TODO allow user to change account settings
//        whereAmI();
//        whatWasThat();
//        nevermind();
//    }

//    public static void whereAmI() {
//        //TODO allowing updating of locations and hours
//        AllYourDatabaseAreBelongToDrunks.thisOrThat();
//    }

//    public static void whatWasThat() {
//        //TODO allowing updating of products and ingredients
//        AllYourDatabaseAreBelongToDrunks.thisOrThat();
//    }

    public static void whatDoYouWantPeasant(String username) {
        //TODO I mean nothing new really, but I left off here.
        //TODO create user menu
//        HopThoseBars.letsGetLit();
        youvePutOnAFewPounds(username);
//        IHateYou();
//        HopThoseBars.seeYouAround();
    }

    public static void youvePutOnAFewPounds(String username) {
        //TODO allow user to change account settings
        while (yesNoInput("Wanna change something in your account?")) {
            String toBeUpdated = stringInput("Change email, name, username, password, or preferences?");
            String newInfo = "";

            if (toBeUpdated.equals("preferences")) {
                yourePrettyPickyArentYou(username, false);
            } else if (toBeUpdated.equals("email")) {
                newInfo = stringInput("New email?");
            } else if (toBeUpdated.equals("name")) {
                newInfo = stringInput("New name?");
            } else if (toBeUpdated.equals("username")) {
                newInfo = stringInput("New username?");
            } else if (toBeUpdated.equals("password")) {
                newInfo = stringInput("New password?");
            }

            AllYourDatabaseAreBelongToDrunks.makeUpYourMindAlready(toBeUpdated, newInfo, username);
        }
    }
}

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

public class WhoAreYouAndWhatDoYouWant {
    public static void main(String[] args) {
        boolean newUser = yesNoInput("Are you a new member?");

        if (newUser) {
            pullUpAStool();
        }
//        } else {
//            boolean youreIn;
//            boolean weHave = DontHopUnlessISaySo.haveWeMet();
//
//            if (!weHave) {
//                youreIn = DontHopUnlessISaySo.whosThere();
//            } else {
//                youreIn = true;
//            }
//            if (youreIn) {
//                whatreYouHaving();
//            }
//        }
    }
    public static void whatreYouHaving(String username, String password) {
        String pubCrawl = doINeedATie(username, password);
        if (pubCrawl.equals("pub")) {
            whatCanIDoForYouMaster();
        } else if (pubCrawl.equals("crawl")) {
            whatDoYouWantPeasant();
        }
    }

    public static String doINeedATie(String username, String password) {
        //TODO determine if end user is on the professional end, or consumer end

        Integer pubcrawl = AllYourDatabaseAreBelongToDrunks.needThatAccountType(username, password);

        if (pubcrawl == 2) {
            return "pub";
        } else if (pubcrawl == 1) {
            return "crawl";
        } else {
            return "failure";
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
            // TODO fix this. Take out the password in the called method. It's not really necessary anyway.
            alreadyChosen = AllYourDatabaseAreBelongToDrunks.needThatAccountType(recipe, potatoes);
            if (alreadyChosen != 0) {
                System.out.println("Already chosen. Pick a different username.");
            }
        }

        String email = stringInput("Now the email.");
        // TODO check formatting for input

        String seasoning = DontHopUnlessISaySo.worthYourWeightInEncryption();
        String saltyHashBrowns = DontHopUnlessISaySo.nothingCuresAHangoverLikeATastyPassword(potatoes, seasoning);

        AllYourDatabaseAreBelongToDrunks.whoAreYou(hotOrCold, recipe, email, saltyHashBrowns, seasoning);

        whatreYouHaving(recipe, saltyHashBrowns);
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

    public static void whatDoYouWantPeasant() {
//        //TODO create user menu
//        HopThoseBars.letsGetLit();
//        youvePutOnAFewPounds();
//        IHateYou();
//        HopThoseBars.seeYouAround();
    }
//    public static void youvePutOnAFewPounds() {
//        //TODO allow user to change account settings
//        AllYourDatabaseAreBelongToDrunks.thisOrThat();
//        nevermind();
//    }
}

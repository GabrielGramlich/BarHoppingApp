import static input.InputUtils.stringInput;

public class HopThoseBars {
    public static void main(String[] args) {
        //TODO separate into different classes
        if (newUser) {
            pullUpAStool();
        } else {
            boolean weHave = haveWeMet();
            boolean youreIn;

            if (!weHave) {
                youreIn = whosThere();
            } else {
                youreIn = true;
            }
            if (youreIn) {
                whatreYouHaving();
            }
        }
    }
    //Following methods are for all users types
    public static void pullUpAStool() {
        //TODO get input about account type, username, password, all that fun stuff
        String potatoes = stringInput("Oy. Password, guvna!");
        String seasoning = DontHopUnlessISaySo.getSalt();
        String saltyHashBrowns = DontHopUnlessISaySo.getSecurePassword(potatoes, seasoning);
        thisOrThat();
    }
    public static void whatreYouHaving() {
        int howMuchHaveYouHad;
        int tooMuch;
        String pubCrawl;

        pubCrawl = doINeedATie();
        if (pubCrawl.equals("pub")) {
            whatCanIDoForYouMaster();
        } else if (pubCrawl.equals("crawl")) {
            whatDoYouWantPeasant();
            if (howMuchHaveYouHad >= tooMuch) {
                cuttingYouOff();
            }
        }
    }
    public static boolean haveWeMet() {
        //TODO check if user is already signed in
    }
    public static boolean whosThere() {
        //TODO let them sign in
        String username = stringInput("Enter your username:");
        String password = stringInput("Enter your password:");

        boolean youToldTheTruth = youLiar(username, password);

        return youToldTheTruth;
    }
    public static boolean youLiar (String username, String potatoes) {
        //TODO get salt from db
        //TODO get hashed password from db
        //TODO check credentials from user database
        boolean iBelieveYou;

        String saltyHashBrowns = DontHopUnlessISaySo.getSecurePassword(potatoes, seasoning);

        if (saltyHashBrowns == databaseCredentials) {
            iBelieveYou = true;
        } else {
            iBelieveYou = false;
        }

        return iBelieveYou;
    }
    public static String doINeedATie() {
        //TODO determine if end user is on the professional end, or consumer end
        if (professional) {
            return "pub";
        } else if (consumer) {
            return "crawl";
        }
    }
    public static void IHateYou() {
        //TODO allow user to delete profile
        nevermind();
    }
    public static void nevermind() {
        //TODO discard changes or save to database
        thisOrThat();
    }

    //Following methods are for professional users
    public static void whatCanIDoForYouMaster() {
        //TODO create user menu
        youveLostWeight();
        IHateYou();
    }
    public static void youveLostWeight() {
        //TODO allow user to change account settings
        whereAmI();
        whatWasThat();
        nevermind();
    }
    public static void whereAmI() {
        //TODO allowing updating of locations and hours
        thisOrThat();
    }
    public static void whatWasThat() {
        //TODO allowing updating of products and ingredients
        thisOrThat();
    }

    //Following methods are for consumer users
    public static void whatDoYouWantPeasant() {
        //TODO create user menu
        letsGetLit();
        youvePutOnAFewPounds();
        IHateYou();
        seeYouAround();
    }
    public static void youvePutOnAFewPounds() {
        //TODO allow user to change account settings
        thisOrThat();
        nevermind();
    }
    public static void letsGetLit() {
        //TODO gather user input about what drink they'll be wanting
        //TODO get user preferences from user database
        pickingYourPoison();
        thisOrThat();
    }
    public static void pickingYourPoison() {
        //TODO create algorithm for finding the right drink
        //TODO confer with user database
        letMeCheckInTheBack();
    }
    public static void letMeCheckInTheBack() {
        //TODO use result of pickingYourPoison to pull the right drink from drink database
        String IGotThis;
        turnLeftAtTheOakTree(IGotThis);
        thisOrThat();
    }
    public static void turnLeftAtTheOakTree(String directions) {
        //TODO pull location from drink database
        //TODO find it via GPS or send to google maps
        thisOrThat();
    }
    public static void seeYouAround() {
        //TODO store users last trip
        //TODO wait for users return
        checkPlease();
        keepEmComingBarkeep();
        theHangoverThisAppEdition();
    }
    public static void checkPlease() {
        //TODO get results from user and send them to ML database
        thisOrThat();
    }
    public static void keepEmComingBarkeep() {
        //TODO allow the user to choose a next round
        //TODO determine if last results were faulty
        thisOrThat();
    }
    public static void cuttingYouOff() {
        //TODO keep track of user's alcohol intake and recommend a ride service
    }
    public static void theHangoverThisAppEdition() {
        //TODO display the course of the user's night
    }
    //Following methods are for the databases
    public static void thisOrThat() {
        //TODO determine what database information to access
        if (gettingData) {
            needThis();
        } else if (sendingData) {
            dontNeedThat();
        }
    }
    public static void needThis() {
        //TODO pull data from database
    }
    public static void dontNeedThat() {
        //TODO push data to database
    }
}

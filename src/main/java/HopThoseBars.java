import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static input.InputUtils.intInput;
import static input.InputUtils.yesNoInput;

public class HopThoseBars {
    public static Integer howMuchHaveYouHad = 0;
    public static LocalDateTime startGettingDrunkTime = null;
    public static Integer LIMIT = 3;

    public static Integer userID;
    public static String preferredLiquor;
    public static String nonpreferredLiquor;
    public static boolean strongPreferrence;
    public static boolean priceyPreferrence;
    public static boolean complexPreferrence;

    public static boolean spiritForwardOrRefreshing;
    public static Integer type;

    public static void main(String[] args) { }

    public static void letsGetLit(Integer ID) {
        userID = ID;
        boolean go = checkingYourIntake();

        if (go) {
            getUserPreferrences();

            spiritForwardOrRefreshing = yesNoInput("Would you like something spirit forward (N), or refreshing (Y)?");
            if (spiritForwardOrRefreshing) {
                type = intInput("Would you prefer type (1), type2 (2), or type3 (3)?");
            } else {
                type = intInput("Would you prefer type (1), type2 (2), or type3 (3)?");
            }

            pickingYourPoison();
            howMuchHaveYouHad++;
        }
    }

    public static void getUserPreferrences() {
        preferredLiquor = AllYourDatabaseAreBelongToDrunks.selectString("Preferred_Liquor", "User_Defined_Preferences", "Users_User_ID", userID);
        nonpreferredLiquor = AllYourDatabaseAreBelongToDrunks.selectString("Nonpreferred_Liquor", "User_Defined_Preferences", "Users_User_ID", userID);
        strongPreferrence = AllYourDatabaseAreBelongToDrunks.selectBoolean("Weak_or_Strong", "User_Defined_Preferences", "Users_User_ID", userID);
        priceyPreferrence = AllYourDatabaseAreBelongToDrunks.selectBoolean("Cheap_or_Pricey", "User_Defined_Preferences", "Users_User_ID", userID);
        complexPreferrence = AllYourDatabaseAreBelongToDrunks.selectBoolean("Simple_or_Complex", "User_Defined_Preferences", "Users_User_ID", userID);
    }

    public static boolean checkingYourIntake() {
        if (startGettingDrunkTime == null) {
            startGettingDrunkTime = LocalDateTime.now();
        }

        LocalDateTime currentTime = LocalDateTime.now();
        double hours = startGettingDrunkTime.until(currentTime, ChronoUnit.HOURS);

        double howMuchHaveYouReallyHad = howMuchHaveYouHad / hours;
        if (hours >= 2 && howMuchHaveYouReallyHad >= LIMIT) {
            cuttingYouOff();
            return false;
        } else {
            return true;
        }
    }

    public static void pickingYourPoison() {
        //TODO create algorithm for finding the right drink
        //TODO confer with user database
//        letMeCheckInTheBack();
    }

//    public static void letMeCheckInTheBack() {
//        //TODO use result of pickingYourPoison to pull the right drink from drink database
//        String IGotThis;
//        turnLeftAtTheOakTree(IGotThis);
//        AllYourDatabaseAreBelongToDrunks.thisOrThat();
//    }

//    public static void turnLeftAtTheOakTree(String directions) {
//        //TODO pull location from drink database
//        //TODO find it via GPS or send to google maps
//        AllYourDatabaseAreBelongToDrunks.thisOrThat();
//    }

//    public static void seeYouAround() {
//        //TODO store users last trip
//        //TODO wait for users return
//        checkPlease();
//        keepEmComingBarkeep();
//        theHangoverThisAppEdition();
//    }

//    public static void checkPlease() {
//        //TODO get results from user and send them to ML database
//        AllYourDatabaseAreBelongToDrunks.thisOrThat();
//    }

//    public static void keepEmComingBarkeep() {
//        //TODO allow the user to choose a next round
//        //TODO determine if last results were faulty
//        AllYourDatabaseAreBelongToDrunks.thisOrThat();
//        if (keepGoing) {
//            letsGetLit();
//        } else {
//            cuttingYouOff();
//        }
//    }

    public static void cuttingYouOff() {
        //TODO keep track of user's alcohol intake and recommend a ride service #this will be for a future update
    }

//    public static void theHangoverThisAppEdition() {
//        //TODO display the course of the user's night #this will be for a future update.
//    }
}

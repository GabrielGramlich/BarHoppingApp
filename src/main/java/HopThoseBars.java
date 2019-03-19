import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class HopThoseBars {
    public static Integer howMuchHaveYouHad = 0;
    public static LocalDateTime startGettingDrunkTime = null;

    public static void main(String[] args) { }

    public static void letsGetLit() {
        boolean go = checkingYourIntake();

        if (go) {
            //TODO gather user input about what drink they'll be wanting
//            pickingYourPoison();
            //TODO get user preferences from user database
            howMuchHaveYouHad++;
        }
    }

    public static boolean checkingYourIntake() {
        if (startGettingDrunkTime == null) {
            startGettingDrunkTime = LocalDateTime.now();
        }

        LocalDateTime currentTime = LocalDateTime.now();
        double hours = startGettingDrunkTime.until(currentTime, ChronoUnit.HOURS);

        double howMuchHaveYouReallyHad = howMuchHaveYouHad / hours;
        if (howMuchHaveYouReallyHad >= 3) {
            cuttingYouOff();
            return false;
        } else {
            return true;
        }
    }

//    public static void pickingYourPoison() {
//        //TODO create algorithm for finding the right drink
//        //TODO confer with user database
//        letMeCheckInTheBack();
//    }

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

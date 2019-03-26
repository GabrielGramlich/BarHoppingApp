package Menus;

import java.util.ArrayList;

import static AllYourDatabaseAreBelongToDrunks.DeleteStatementCreation.delete;
import static AllYourDatabaseAreBelongToDrunks.InsertStatementCreation.IDidntKnowKingdomsHadHours;
import static AllYourDatabaseAreBelongToDrunks.InsertStatementCreation.welcomeToYourKingdom;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.*;
import static AllYourDatabaseAreBelongToDrunks.UpdateStatementCreation.*;
import static Menus.SignIn.*;
import static Menus.SignIn.locationID;
import static input.InputUtils.*;

public class LocationMenu {
    public static void main(String[] args) { }

    public static void ownerLocationsMenu() {
        String decision = stringInput("Create, update, delete or review locations?");

        if (decision.equals("create")) {
            createLocation();
            createLocationCalendar();
        } else {
            String otherDecision = stringInput("Would you like to " + decision + " location information or " +
                    "hours of operation (location or hours).");
            ArrayList<String> locations = selectStringArrayList("Name",
                    "Locations", "Owner_ID", ownerID);
            for (String location : locations) {
                System.out.println(location);
            }
            locationName = stringInput("Which location would you like to " + decision + "?");
            locationID = selectIntegerWithString("Location_ID",
                    "Locations", "Name", locationName);

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

        welcomeToYourKingdom(ownerID, locationName, number, street, city, state, zip);
    }

    public static void createLocationCalendar() {
        ArrayList<String> days = new ArrayList<>();
        ArrayList<String> openHours = new ArrayList<>();
        ArrayList<String> closeHours = new ArrayList<>();
        ArrayList<String> speHoursStart = new ArrayList<>();
        ArrayList<String> speHoursEnd = new ArrayList<>();
        ArrayList<Double> speHoursDiscount = new ArrayList<>();
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
                speHoursDiscount.add(doubleInput(
                        "What percent discount is offered during these hours?") / 100);
            } else {
                speHoursStartInput = "00:00";
                speHoursEndInput = "00:00";
                speHoursStart.add(speHoursStartInput);
                speHoursEnd.add(speHoursEndInput);
                speHoursDiscount.add(.00);
            }
        }

        locationID = selectIntegerWithString("Location_ID", "Locations",
                "Name", locationName);
        IDidntKnowKingdomsHadHours(locationID, days, openHours, closeHours,
                speHoursStart, speHoursEnd, speHoursDiscount);
    }

    public static void updateLocation() {
        displayLocationData();

        String finalDecision = stringInput("Would you like to update, name, phone_number, street, city, " +
                "state or zip?");
        String newStringData = stringInput("New " + finalDecision + "?");
        Double newDoubleData;

        if (finalDecision.equals("phone_number")) {
            //TODO provide formatting for number
            newDoubleData = Double.valueOf(newStringData);
            updateDouble("Locations", finalDecision, newDoubleData,
                    "Location_ID", locationID);
        } else if (finalDecision.equals("zip")) {
            //TODO provide formatting for zip
            newDoubleData = Double.valueOf(newStringData);
            updateDouble("Locations", finalDecision, newDoubleData,
                    "Location_ID", locationID);
        } else {
            updateString("Locations", finalDecision, newStringData,
                    "Location_ID", locationID);
        }
    }

    public static void updateHours() {
        displayHours();

        String finalDecision = stringInput("Would you like to update time_open, time_close, " +
                "specialty_hour_start or specialty_hour_end?");
        String dayOfWeek = stringInput("For which day of the week?");
        String newData = stringInput("New " + finalDecision + " for " + dayOfWeek + "? (HH:MM)");

        updateTime("Calendar", finalDecision, newData,
                "Location_ID", locationID, "Day_of_Week", dayOfWeek);
    }

    public static void deleteLocation() {
        boolean confirmed = yesNoInput("Are you sure you want to delete this location and its " +
                "corresponding data?");
        if (confirmed) {
            delete("Locations", "Location_ID", locationID);
            delete("Calendar", "Location_ID", locationID);
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
        System.out.println("Name:\t" + selectString("Name",
                "Locations","Location_ID", locationID));
        System.out.println("Number:\t" + selectString("Phone_Number",
                "Locations","Location_ID", locationID));
        System.out.println("Street:\t" + selectString("Street",
                "Locations","Location_ID", locationID));
        System.out.println("City:\t" + selectString("City",
                "Locations","Location_ID", locationID));
        System.out.println("State:\t" + selectString("State",
                "Locations","Location_ID", locationID));
        System.out.println("Zip:\t" + selectString("Zip",
                "Locations","Location_ID", locationID));
    }

    public static void displayHours() {
        System.out.println("Day of week:\t\t" + selectString(
                "Day_of_Week", "Locations", "Location_ID", locationID));
        System.out.println("Time open:\t\t" + selectString(
                "Time_Open", "Locations", "Location_ID", locationID));
        System.out.println("Time close:\t\t" + selectString(
                "Time_Close", "Locations", "Location_ID", locationID));
        System.out.println("Specialty hour start:\t" + selectString(
                "Specialty_Hour_Start", "Locations", "Location_ID", locationID));
        System.out.println("Specialty hour end:\t" + selectString(
                "Specialty_Hour_End", "Locations", "Location_ID", locationID));
    }
}

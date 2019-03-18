import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static input.InputUtils.yesNoInput;

public class AllYourDatabaseAreBelongToDrunks {
    public static void main(String[] args) { }
    //TODO make generic sql insert statement creation method
    //TODO make generic sql update statement creation method
    //TODO make generic sql select statement creation method
    //TODO make generic sql delete statement creation method


    /*******************
    ***Insert methods***
    *******************/



    public static void helloBoss(Integer loginID, String first, String last, Double number, String email) {
        // Creating a new user
        String insertDataSql = "INSERT INTO Owners (First_Name, Last_Name, Contact_Number, Contact_Email, Login_Credentials_Login_ID) VALUES(\"" + first + "\", \"" + last + "\", " + number + ", \"" + email + "\", " + loginID + ");";
        dontNeedThat(insertDataSql);
    }

    public static void welcomeToYourKingdom(Integer ownerID, String name, Double number, String street, String city, String state, Double zip) {
        String insertDataSql = "INSERT INTO Locations (Owners_Owner_ID, Name, Phone_Number, Street, City, State, Zip) VALUES (" + ownerID + ", \"" + name + "\", " + number + ", \"" + street + "\", \"" + city + "\", \"" + state + "\", " + zip + ");";
        dontNeedThat(insertDataSql);
    }

    public static void IDidntKnowKingdomsHadHours(Integer locationID, ArrayList<String> days, ArrayList<String> openHours, ArrayList<String> closeHours, ArrayList<String> speHoursStart, ArrayList<String> speHoursEnd) {
        for (int i = 0; i < days.size(); i++) {
            String insertDataSql = "INSERT INTO Calendar (Locations_Location_ID, Day_of_Week, Time_Open, Time_Close, Specialty_Hour_Start, Specialty_Hour_End) VALUES (" + locationID + ", \"" + days.get(i) + "\", TIME_FORMAT(CONVERT(\"" + openHours.get(i) + "\", TIME), \"%H:%i\"), TIME_FORMAT(CONVERT(\"" + closeHours.get(i) + "\", TIME), \"%H:%i\"), TIME_FORMAT(CONVERT(\"" + speHoursStart.get(i) + "\", TIME), \"%H:%i\"), TIME_FORMAT(CONVERT(\"" + speHoursEnd.get(i) + "\", TIME), \"%H:%i\"));";
            dontNeedThat(insertDataSql);
        }
    }

    public static Integer whoAreYou(Boolean accountType, String username, String emailAddress, String password, String salt) {
        // Storing log in credentials
        String insertDataSql = "INSERT INTO Login_Credentials (Account_Type, Username, Email_Address, Password, Salt) VALUES(" + accountType + ", \"" + username + "\", \"" + emailAddress + "\", \"" + password + "\", \"" + salt + "\");";
        dontNeedThat(insertDataSql);

        Integer credentialsID = needThatCredentialID(username);
        return credentialsID;
    }

    public static void greetingsFriend(String first, String last, Integer loginID) {
        // Creating a new user
        String insertDataSql = "INSERT INTO Users (First_Name, Last_Name, Login_Credentials_Login_ID) VALUES(\"" + first + "\", \"" + last + "\", " + loginID + ");";
        dontNeedThat(insertDataSql);
    }

    public static void thatSoundsDelicious(String name, String startDate, String endDate, Integer strength, Double price, Double specialtyPrice, Integer complexity, boolean spiritForwardOrRefreshing, Integer type) {
        String insertDataSql = "INSERT INTO Drinks (Name, Availability_Start, Availability_End, Alcohol_Content, Price, Specialty_Price, Complexity, Spirit_Forward_or_Refreshing, Type) VALUES(\"" + name + "\", STR_TO_DATE(\"" + startDate + "\", \"%m/%d/%y\"), STR_TO_DATE(\"" + endDate + "\", \"%m/%d/%y\"), " + strength + ", " + price + ", " + specialtyPrice + ", " + complexity + ", " + spiritForwardOrRefreshing + ", " + type + ");";
        dontNeedThat(insertDataSql);
    }

    public static void whatsInItThough(ArrayList<String> ingredients, Integer drinkID) {
        for (String ingredient : ingredients) {
            Integer ingredientID = needThatIngredientID(ingredient);
            String sqlStatement = "INSERT INTO Recipes (Drinks_Drink_ID, Ingredients_Ingredient_ID) VALUES(" + drinkID + ", " + ingredientID + ");";
            dontNeedThat(sqlStatement);
        }
    }

    public static void evenBetter(Integer drinkID, String newData) {
        String sqlStatement = "INSERT INTO Recipes (Drinks_Drink_ID, Ingredients_Ingredient_ID) Values(" + drinkID + ", " + newData + ");";
        dontNeedThat(sqlStatement);
    }


    /*******************
    ***Update methods***
    *******************/


    public static void thanksForKeepingUsMovingDouble(String column, Double newData, Integer locationID) {
        String sqlStatement = "UPDATE Locations SET " + column + " = " + newData + " WHERE Location_ID = " + locationID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void thanksForKeepingUsMovingString(String column, String newData, Integer locationID) {
        String sqlStatement = "UPDATE Locations SET " + column + " = \"" + newData + "\" WHERE Location_ID = " + locationID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void thanksForStayingOpen(String day, String column, String newData, Integer locationID) {
        String sqlStatement = "UPDATE Calendar SET " + column + " = TIME_FORMAT(CONVERT(\"" + newData + "\", TIME), \"%H:%i\") WHERE Location_ID = " + locationID + " AND Day_of_Week = \"" + day + "\";";
        dontNeedThat(sqlStatement);
    }

    public static void makeUpYourMindAlready(String column, String data, String username) {
        String updateDataSql;

        if (column.equals("name")) {
            Integer userID = needThatUserID(username);
            Integer spaceIndex = data.indexOf(" ");
            String first = data.substring(0, spaceIndex);
            String last = data.substring(spaceIndex + 1);

            updateDataSql = "UPDATE Users SET First_Name = \"" + first + "\" WHERE User_ID = " + userID + ";";
            dontNeedThat(updateDataSql);

            updateDataSql = "UPDATE Users SET Last_Name = \"" + last + "\" WHERE User_ID = " + userID + ";";
            dontNeedThat(updateDataSql);
        } else {
            Integer loginID = needThatCredentialID(username);
            updateDataSql = "UPDATE Login_Credentials SET " + column + " = \"" + data + "\" WHERE Login_ID = " + loginID + ";";
            dontNeedThat(updateDataSql);
        }
    }

    public static void itsAnHonorToGetToKnowYou(String column, String data, String username) {
        String updateDataSql;
        Integer loginID = needThatOwnerLoginID(username);
        Integer ownerID = needThatOwnerID(loginID);

        if (column.equals("name")) {
            Integer spaceIndex = data.indexOf(" ");
            String first = data.substring(0, spaceIndex);
            String last = data.substring(spaceIndex + 1);

            updateDataSql = "UPDATE Owners SET First_Name = \"" + first + "\" WHERE Owner_ID = " + ownerID + ";";
            dontNeedThat(updateDataSql);

            updateDataSql = "UPDATE Owners SET Last_Name = \"" + last + "\" WHERE Owner_ID = " + ownerID + ";";
            dontNeedThat(updateDataSql);
        } else if (column.equals("Contact_Number")) {
            updateDataSql = "UPDATE Owners SET Contact_Number = " + Double.valueOf(data) + " WHERE Owner_ID = " + ownerID + ";";
            dontNeedThat(updateDataSql);
        } else if (column.equals("Contact_Email")) {
            updateDataSql = "UPDATE Owners SET Contact_Email = \"" + data + "\" WHERE Owner_ID = " + ownerID + ";";
            dontNeedThat(updateDataSql);
        } else {
            updateDataSql = "UPDATE Login_Credentials SET " + column + " = \"" + data + "\" WHERE Login_ID = " + loginID + ";";
            dontNeedThat(updateDataSql);
        }
    }

    public static void thatllTasteBetter(Integer drinkID, String oldIngredient, String newIngredient) {
        Integer oldIngredientID = needThatIngredientID(oldIngredient);
        Integer newIngredientID = needThatIngredientID(newIngredient);
        String sqlStatement = "UPDATE Recipes SET Ingredients_Ingredient_ID = " + newIngredientID + " WHERE Drink_ID = " + drinkID + " AND Ingredients_Ingredient_ID = " + oldIngredientID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void thatsGonnaFuckMeUp(Integer drinkID, Integer newData) {
        String sqlStatement = "UPDATE Drinks SET Alcohol_Content = " + newData + " WHERE Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void thatsSoExpensive(Integer drinkID, Double newData) {
        String sqlStatement = "UPDATE Drinks SET Price = " + newData + " WHERE Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void thatsSoCheap(Integer drinkID, Double newData) {
        String sqlStatement = "UPDATE Drinks SET Specialty_Price = " + newData + " WHERE Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void thatsSoComplex(Integer drinkID, Integer newData) {
        String sqlStatement = "UPDATE Drinks SET Complexity = " + newData + " WHERE Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void thatsSoOneOrTheOther(Integer drinkID, boolean newData) {
        String sqlStatement = "UPDATE Drinks SET Spirit_Forward_or_Refreshing = " + newData + " WHERE Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void thatsSoWhatThatIs(Integer drinkID, Integer newData) {
        String sqlStatement = "UPDATE Drinks SET Type = " + newData + " WHERE Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void soThatsWhenYouStart(Integer drinkID, String newData) {
        String sqlStatement = "UPDATE Drinks SET Availability_Start = STR_TO_DATE(\"" + newData + "\", \"%m/%d/%y\") WHERE Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void soThatsWhenYouEnd(Integer drinkID, String newData) {
        String sqlStatement = "UPDATE Drinks SET Availability_End = STR_TO_DATE(\"" + newData + "\", \"%m/%d/%y\") WHERE Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);
    }


    /****************************
    ***Data collection methods***
    ****************************/


    public static Integer needThatOwnerLoginID(String username) {
        String sqlStatement = "SELECT Login_ID FROM Login_Credentials WHERE Username = \"" + username + "\";";
        Integer loginID = needThatInteger("Login_ID", sqlStatement);
        return loginID;
    }

    public static Integer needThatOwnerID(Integer loginID) {
        String sqlStatement = "SELECT Owner_ID FROM Owners WHERE Login_Credentials_Login_ID = " + loginID + ";";
        Integer ownerID = needThatInteger("Owner_ID", sqlStatement);
        return ownerID;
    }

    public static ArrayList<String> needThoseLocations(Integer ownerID) {
        String sqlStatement = "SELECT * FROM Locations WHERE Owners_Owner_ID = " + ownerID + ";";
        ArrayList<String> locations = needThatArrayListString("Name", sqlStatement);
        return locations;
    }

    public static Integer needThatLocationID(String locationName) {
        String sqlStatement = "SELECT Location_ID FROM Locations WHERE Name = \"" + locationName + "\";";
        Integer locationID = needThatInteger("Location_ID", sqlStatement);
        return locationID;
    }

    public static void needThatLocationData(Integer locationID) {
        String sqlStatement = "SELECT * FROM Locations WHERE Location_ID = " + locationID + ";";
        displayLocationData(sqlStatement);
    }

    public static void needThatHourData(Integer locationID) {
        String sqlStatement = "SELECT * FROM Locations WHERE Location_ID = " + locationID + ";";
        displayHourData(sqlStatement);
    }

    public static Integer needThatAccountType(String username) {
        String sqlStatement = "SELECT Account_Type FROM Login_Credentials WHERE Username = \"" + username + "\";";
        Integer accountType = needThatBoolean("Account_Type", sqlStatement);
        return accountType;
    }

    public static Integer needThatCredentialID(String username) {
        String sqlStatement = "SELECT Login_ID FROM Login_Credentials WHERE Username = \"" + username + "\";";
        Integer loginID = needThatInteger("Login_ID", sqlStatement);
        return loginID;
    }

    public static String needThatSalt(String username) {
        String sqlStatement = "SELECT Salt FROM Login_Credentials WHERE Username = \"" + username + "\";";
        String salt = needThatString("Salt", sqlStatement);
        return salt;
    }

    public static String needThatPassword(String username) {
        String sqlStatement = "SELECT Password FROM Login_Credentials WHERE Username = \"" + username + "\";";
        String password = needThatString("Password", sqlStatement);
        return password;
    }

    public static Integer needThatUserID(String username) {
        Integer loginID = needThatCredentialID(username);
        String sqlStatement = "SELECT User_ID FROM Users WHERE Login_Credentials_Login_ID = " + loginID + ";";
        Integer userID = needThatInteger("User_ID", sqlStatement);
        return userID;
    }

    public static Integer needThatIngredientID(String ingredient) {
        String sqlStatement = "SELECT Ingredient_ID FROM Ingredients WHERE Name = \"" + ingredient + "\";";
        Integer ingredientID = needThatInteger("Ingredient_ID", sqlStatement);
        return ingredientID;
    }

    public static Integer needThatDrinkID(String name) {
        //TODO add in locationID to database for drinks table as a combined key and add it to this method
        String sqlStatement = "SELECT Drink_ID FROM Drinks WHERE Name = \"" + name + "\";";
        Integer drinkID = needThatInteger("Drink_ID", sqlStatement);
        return drinkID;
    }

    public static ArrayList<String> needThoseDrinks(Integer locationID) {
        String sqlStatement = "SELECT * FROM Drink_Locations WHERE Location_ID = " + locationID + ";";
        ArrayList<Integer> drinks = needThatArrayListInteger("Drinks_Drink_ID", sqlStatement);

        ArrayList<String> drinkNames = new ArrayList<>();

        for (Integer drink : drinks) {
            drinkNames.add(needThatDrinkName(drink));
        }

        return drinkNames;
    }

    public static String needThatDrinkName(Integer drinkID) {
        String sqlStatement = "SELECT Name FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        String drink = needThatString("Name", sqlStatement);
        return drink;
    }

    public static ArrayList<String> needThatRecipe(Integer drinkID) {
        Integer recipeID = needThatRecipeID(drinkID);
        String sqlStatement = "SELECT Ingredient_ID FROM Recipes WHERE Recipe_ID = " + recipeID + ";";
        ArrayList<Integer> ingredientIDs = needThatArrayListInteger("Ingredient_ID", sqlStatement);
        ArrayList<String> ingredients = new ArrayList<>();

        for (Integer ID : ingredientIDs) {
            ingredients.add(needThatIngredientName(ID));
        }

        return ingredients;
    }

    public static Integer needThatRecipeID(Integer drinkID) {
        String sqlstatement = "SELECT Recipe_ID FROM Recipes WHERE Drink_ID = " + drinkID + ";";
        Integer recipeID = needThatInteger("Recipe_ID", sqlstatement);
        return recipeID;
    }

    public static String needThatIngredientName(Integer ingredientID) {
        String sqlStatement = "SELECT Name FROM Ingredients WHERE Ingredient_ID = " + ingredientID + ";";
        String ingredient = needThatString("Name", sqlStatement);
        return ingredient;
    }

    public static Integer needThatAlcoholContent(Integer drinkID) {
        String sqlStatement = "SELECT Alcohol_Content FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Integer alcoholContent = needThatInteger("Alcohol_Content", sqlStatement);
        return alcoholContent;
    }

    public static Double needThatPrice(Integer drinkID) {
        String sqlStatement = "SELECT Price FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Double price = needThatDouble("Price", sqlStatement);
        return price;
    }

    public static Double needThatSpecialtyPrice(Integer drinkID) {
        String sqlStatement = "SELECT Specialty_Price FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Double specialtyPrice = needThatDouble("Specialty_Price", sqlStatement);
        return specialtyPrice;
    }

    public static Integer needThatComplexity(Integer drinkID) {
        String sqlStatement = "SELECT Complexity FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Integer complexity = needThatInteger("Complexity", sqlStatement);
        return complexity;
    }

    public static boolean needThatSpiritForwardOrRefreshing(Integer drinkID) {
        String sqlStatement = "SELECT Spirit_Forward_or_Refreshing FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Integer spiritForwardOrRefreshing = needThatBoolean("Spirit_Forward_or_Refreshing", sqlStatement);
        boolean returnBool = false;
        if (spiritForwardOrRefreshing == 2) {
            returnBool = true;
        }
        return returnBool;
    }

    public static Integer needThatType(Integer drinkID) {
        String sqlStatement = "SELECT Type FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Integer Type = needThatInteger("Type", sqlStatement);
        return Type;
    }

    public static String needThatAvailabilityStart(Integer drinkID) {
        String sqlStatement = "SELECT Availability_Start FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        String startDate = needThatString("Availability_Start", sqlStatement);
        return startDate;
    }

    public static String needThatAvailabilityEnd(Integer drinkID) {
        String sqlStatement = "SELECT Availability_End FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        String endDate = needThatString("Availability_End", sqlStatement);
        return endDate;
    }


    /*********************
        ***Deletion methods***
        *********************/


    public static void throwThatInTheTrash(Integer loginID, Integer userID) {
        String insertDataSql = "DELETE FROM Users WHERE User_ID = " + userID + ";";
        dontNeedThat(insertDataSql);

        insertDataSql = "DELETE FROM Login_Credentials WHERE Login_ID = " + loginID + ";";
        dontNeedThat(insertDataSql);
    }

    public static void thanksForStoppingBy(Integer locationID) {
        String deleteDataSql = "DELETE FROM Locations WHERE Location_ID + " + locationID + ";";
        dontNeedThat(deleteDataSql);

        deleteDataSql = "DELETE FROM Calendar WHERE Locations_Location_ID + " + locationID + ";";
        dontNeedThat(deleteDataSql);
    }


    /*********************************
    ***Generic data storage methods***
    *********************************/


    public static void dontNeedThat(String sqlStatement) {
        // Generic means of pushing data
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();
            statement.execute(sqlStatement);

            statement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    /************************************
    ***Generic data collection methods***
    ************************************/


    public static String needThatString(String column, String sqlStatement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            ResultSet retrievedData = statement.executeQuery(sqlStatement);

            String returnString = "";
            while (retrievedData.next()) {
                returnString = retrievedData.getString(column);
            }

            statement.close();
            connection.close();


            return returnString;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static Integer needThatInteger(String column, String sqlStatement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            ResultSet retrievedData = statement.executeQuery(sqlStatement);

            Integer returnInteger = 0;
            while (retrievedData.next()) {
                returnInteger = retrievedData.getInt(column);
            }

            statement.close();
            connection.close();


            return returnInteger;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static Double needThatDouble(String column, String sqlStatement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            ResultSet retrievedData = statement.executeQuery(sqlStatement);

            Double returnDouble = 0.0;
            while (retrievedData.next()) {
                returnDouble = retrievedData.getDouble(column);
            }

            statement.close();
            connection.close();


            return returnDouble;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return 0.0;
        }
    }

    public static Integer needThatBoolean(String column, String sqlStatement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            ResultSet retrievedData = statement.executeQuery(sqlStatement);

            Integer returnInteger = 0;
            boolean type;
            while (retrievedData.next()) {
                type = retrievedData.getBoolean(column);
                if (type) {
                    returnInteger = 2;
                } else {
                    returnInteger = 1;
                }
            }

            statement.close();
            connection.close();

            return returnInteger;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static ArrayList<String> needThatArrayListString(String column, String sqlStatement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            ResultSet retrievedData = statement.executeQuery(sqlStatement);

            ArrayList<String> returnArrayList = new ArrayList<>();

            while (retrievedData.next()) {
                returnArrayList.add(retrievedData.getString(column));
            }

            statement.close();
            connection.close();

            return returnArrayList;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            ArrayList<String> returnArrayList = new ArrayList<>();
            return returnArrayList;
        }
    }

    public static ArrayList<Integer> needThatArrayListInteger(String column, String sqlStatement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            ResultSet retrievedData = statement.executeQuery(sqlStatement);

            ArrayList<Integer> returnArrayList = new ArrayList<>();

            while (retrievedData.next()) {
                returnArrayList.add(retrievedData.getInt(column));
            }

            statement.close();
            connection.close();

            return returnArrayList;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            ArrayList<Integer> returnArrayList = new ArrayList<>();
            return returnArrayList;
        }
    }


    /********************
    ***Display methods***
    ********************/


    public static void displayLocationData(String sqlStatement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            ResultSet retrievedData = statement.executeQuery(sqlStatement);

            String labelString = "Name\tPhone Number\tStreet\tCity\tState\tZip";
            String infoString = "";

            while (retrievedData.next()) {
                String name = retrievedData.getString("Name");
                String number = String.valueOf(retrievedData.getInt("Phone_Number"));
                String street = retrievedData.getString("Street");
                String city = retrievedData.getString("City");
                String state = retrievedData.getString("State");
                String zip = String.valueOf(retrievedData.getInt("Zip"));
                infoString = name + "\t" + number + "\t" + street + "\t" + city  + "\t" + state  + "\t" + zip;
            }

            statement.close();
            connection.close();

            System.out.println(labelString);
            System.out.println(infoString);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayHourData(String sqlStatement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            ResultSet retrievedData = statement.executeQuery(sqlStatement);

            String labelString = "Day_of_Week\tTime_Open\tTime_Close\tSpecialty_Hour_Start\tSpecialty_Hour_End";
            String infoString = "";

            while (retrievedData.next()) {
                String day = retrievedData.getString("Day_of_Week");
                String open = String.valueOf(retrievedData.getDate("Time_Open"));
                String close = String.valueOf(retrievedData.getDate("Time_Close"));
                String start = String.valueOf(retrievedData.getDate("Specialty_Hour_Start"));
                String end = String.valueOf(retrievedData.getDate("Specialty_Hour_End"));
                infoString = day + "\t" + open + "\t" + close + "\t" + start  + "\t" + end;
            }

            statement.close();
            connection.close();

            System.out.println(labelString);
            System.out.println(infoString);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    /***********************
    ***To be done methods***
    ***********************/


    public static void youGotSomeWeirdKinks(String username, ArrayList allergies, String topShelf, String bottomShelf, boolean weakOrStrong, boolean deepPockets, boolean youFancy) {
        //TODO put user defined preferences in database
    }

//    public static void nevermind() {
//        //TODO discard changes or save to database
//        boolean response = yesNoInput("Are you sure you want to save your changes?");
//    }
}

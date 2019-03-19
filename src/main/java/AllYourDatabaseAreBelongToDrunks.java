import java.sql.*;
import java.util.ArrayList;

public class AllYourDatabaseAreBelongToDrunks {
    public static void main(String[] args) { }
    //TODO rework specific sql select statement creation method

    //TODO make generic sql delete statement creation method
    //TODO rework specific sql delete statement creation method


    /***********************************************
    ***Specific insert statement creation methods***
    ***********************************************/


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


    /**********************************************
    ***Generic update statement creation methods***
    **********************************************/


    public static void updateString(String table, String column, String newData, String primaryKeyName, Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = \"" + newData + "\" WHERE \"" + primaryKeyName + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateTime(String table, String column, String newData, String primaryKeyName, Integer primaryKeyID, String secondaryKeyName, String secondaryKeyData) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = TIME_FORMAT(CONVERT(\"" + newData + "\", TIME), \"%H:%i\") WHERE \"" + primaryKeyName + "\" = " + primaryKeyID + " AND \"" + secondaryKeyName + "\" = " + secondaryKeyData + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateDate(String table, String column, String newData, String primaryKeyName, Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = STR_TO_DATE(\"" + newData + "\", \"%m/%d/%y\") WHERE \"" + primaryKeyName + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateDouble(String table, String column, Double newData, String primaryKeyName, Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE \"" + primaryKeyName + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateInteger(String table, String column, Integer newData, String primaryKeyName, Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE \"" + primaryKeyName + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateIntegerWithSecondaryID(String table, String column, Integer newData, String primaryKeyName, Integer primaryKeyID, String secondaryKeyName, Integer secondaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE \"" + primaryKeyName + "\" = " + primaryKeyID + " AND \"" + secondaryKeyName + "\" = " + secondaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateBoolean(String table, String column, boolean newData, String primaryKeyName, Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE \"" + primaryKeyName + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }


    /***********************************************
    ***Specific select statement creation methods***
    ***********************************************/


    public static String selectString(String column, String table, String primaryKeyName, String primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = " + primaryKeyID + ";";
        String returnString = needThatString(column, sqlStatement);
        return returnString;
    }

    public static Double selectDouble(String column, String table, String primaryKeyName, String primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = " + primaryKeyID + ";";
        Double returnDouble = needThatDouble(column, sqlStatement);
        return returnDouble;
    }

    public static Integer selectInteger(String column, String table, String primaryKeyName, String primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = " + primaryKeyID + ";";
        Integer returnInteger = needThatInteger(column, sqlStatement);
        return returnInteger;
    }

    public static Integer selectBoolean(String column, String table, String primaryKeyName, String primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = " + primaryKeyID + ";";
        Integer returnInteger = needThatBoolean(column, sqlStatement);
        return returnInteger;
    }

    public static ArrayList<String> selectStringArrayList(String column, String table, String primaryKeyName, String primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = " + primaryKeyID + ";";
        ArrayList<String> returnArrayListString = needThatArrayListString(column, sqlStatement);
        return returnArrayListString;
    }


    /***********************************************
    ***Specific select statement creation methods***
    ***********************************************/


    public static String needThatDrinkName(Integer drinkID) {
        String sqlStatement = "SELECT Name FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        String drink = needThatString("Name", sqlStatement);
        return drink;
    }

    public static String needThatIngredientName(Integer ingredientID) {
        String sqlStatement = "SELECT Name FROM Ingredients WHERE Ingredient_ID = " + ingredientID + ";";
        String ingredient = needThatString("Name", sqlStatement);
        return ingredient;
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

    public static Integer needThatRecipeID(Integer drinkID) {
        String sqlstatement = "SELECT Recipe_ID FROM Recipes WHERE Drink_ID = " + drinkID + ";";
        Integer recipeID = needThatInteger("Recipe_ID", sqlstatement);
        return recipeID;
    }

    public static Integer needThatAlcoholContent(Integer drinkID) {
        String sqlStatement = "SELECT Alcohol_Content FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Integer alcoholContent = needThatInteger("Alcohol_Content", sqlStatement);
        return alcoholContent;
    }

    public static Integer needThatComplexity(Integer drinkID) {
        String sqlStatement = "SELECT Complexity FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Integer complexity = needThatInteger("Complexity", sqlStatement);
        return complexity;
    }

    public static Integer needThatType(Integer drinkID) {
        String sqlStatement = "SELECT Type FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Integer Type = needThatInteger("Type", sqlStatement);
        return Type;
    }

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

    public static Integer needThatLocationID(String locationName) {
        String sqlStatement = "SELECT Location_ID FROM Locations WHERE Name = \"" + locationName + "\";";
        Integer locationID = needThatInteger("Location_ID", sqlStatement);
        return locationID;
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

    public static boolean needThatSpiritForwardOrRefreshing(Integer drinkID) {
        String sqlStatement = "SELECT Spirit_Forward_or_Refreshing FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        Integer spiritForwardOrRefreshing = needThatBoolean("Spirit_Forward_or_Refreshing", sqlStatement);
        boolean returnBool = false;
        if (spiritForwardOrRefreshing == 2) {
            returnBool = true;
        }
        return returnBool;
    }

    public static ArrayList<String> needThoseLocations(Integer ownerID) {
        String sqlStatement = "SELECT * FROM Locations WHERE Owners_Owner_ID = " + ownerID + ";";
        ArrayList<String> locations = needThatArrayListString("Name", sqlStatement);
        return locations;
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


    /***********************************************
    ***Specific delete statement creation methods***
    ***********************************************/


    public static void throwThatInTheTrash(Integer loginID, Integer userID) {
        String insertDataSql = "DELETE FROM Users WHERE User_ID = " + userID + ";";
        dontNeedThat(insertDataSql);

        insertDataSql = "DELETE FROM Login_Credentials WHERE Login_ID = " + loginID + ";";
        dontNeedThat(insertDataSql);
    }

    public static void thanksForStoppingBy(Integer locationID) {
        String deleteDataSql = "DELETE FROM Locations WHERE Location_ID = " + locationID + ";";
        dontNeedThat(deleteDataSql);

        deleteDataSql = "DELETE FROM Calendar WHERE Locations_Location_ID = " + locationID + ";";
        dontNeedThat(deleteDataSql);
    }

    public static void itWasDeliciousWhileItLasted(Integer drinkID) {
        String sqlStatement = "Delete FROM Drinks WHERE Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);

        sqlStatement = "DELETE FROM Recipes WHERE Drinks_Drink_ID = " + drinkID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void thatTastesGrossInThis(Integer drinkID, Integer ingredientID) {
        String sqlStatement = "Delete FROM Recipes WHERE Drinks_Drink_ID = " + drinkID + " AND Ingredients_Ingredient_ID = " + ingredientID + ";";
        dontNeedThat(sqlStatement);
    }


    /***********************************************************
    ***Generic input/update/delete statement execution method***
    ***********************************************************/


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


    /***********************************************
    ***Generic select statement execution methods***
    ***********************************************/


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


    public static void displayLocationData(Integer locationID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            String sqlStatement = "SELECT * FROM Locations WHERE Location_ID = " + locationID + ";";

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

    public static void displayHourData(Integer locationID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            String sqlStatement = "SELECT * FROM Locations WHERE Location_ID = " + locationID + ";";
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

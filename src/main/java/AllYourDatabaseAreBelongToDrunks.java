import java.sql.*;
import java.util.ArrayList;

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

public class AllYourDatabaseAreBelongToDrunks {
    public static void main(String[] args) { }
    //TODO Comment your code, you dick


    /***********************************************
    ***Specific insert statement creation methods***
    ***********************************************/


    public static void helloBoss(Integer loginID, String first, String last, Double number, String email) {
        // Creating a new user
        String insertDataSql = "INSERT INTO Owners (First_Name, Last_Name, Contact_Number, Contact_Email, " +
                "Login_Credential_ID) VALUES(\"" + first + "\", \"" + last + "\", " + number + ", \"" + email + "\", "
                + loginID + ");";
        dontNeedThat(insertDataSql);
    }

    public static void welcomeToYourKingdom(Integer ownerID, String name, Double number, String street, String city,
                                            String state, Double zip) {
        String insertDataSql = "INSERT INTO Locations (Owner_ID, Name, Phone_Number, Street, City, State, Zip) " +
                "VALUES (" + ownerID + ", \"" + name + "\", " + number + ", \"" + street + "\", \"" + city + "\", \""
                + state + "\", " + zip + ");";
        dontNeedThat(insertDataSql);
    }

    public static void IDidntKnowKingdomsHadHours(Integer locationID, ArrayList<String> days,
                                                  ArrayList<String> openHours, ArrayList<String> closeHours,
                                                  ArrayList<String> speHoursStart, ArrayList<String> speHoursEnd,
                                                  ArrayList<Double> speHoursDiscount) {
        for (int i = 0; i < days.size(); i++) {
            String insertDataSql = "INSERT INTO Calendar (Location_ID, Day_of_Week, Time_Open, Time_Close, " +
                    "Specialty_Hour_Start, Specialty_Hour_End, Specialty_Discount) VALUES (" + locationID + ", \""
                    + days.get(i) + "\", TIME_FORMAT(CONVERT(\"" + openHours.get(i) + "\", TIME), \"%H:%i\"), " +
                    "TIME_FORMAT(CONVERT(\"" + closeHours.get(i) + "\", TIME), \"%H:%i\"), TIME_FORMAT(CONVERT(\""
                    + speHoursStart.get(i) + "\", TIME), \"%H:%i\"), TIME_FORMAT(CONVERT(\"" + speHoursEnd.get(i)
                    + "\", TIME), \"%H:%i\"), " + speHoursDiscount.get(i) + ");";
            dontNeedThat(insertDataSql);
        }
    }

    public static Integer whoAreYou(Boolean accountType, String username, String emailAddress, String password,
                                    String salt) {
        // Storing log in credentials
        String insertDataSql = "INSERT INTO Login_Credentials (Account_Type, Username, Email_Address, Password, " +
                "Salt) VALUES(" + accountType + ", \"" + username + "\", \"" + emailAddress + "\", \"" + password
                + "\", \"" + salt + "\");";
        dontNeedThat(insertDataSql);

        Integer credentialsID = AllYourDatabaseAreBelongToDrunks.selectIntegerWithString("Login_Credential_ID",
                "Login_Credentials", "Username", username);
        return credentialsID;
    }

    public static void greetingsFriend(String first, String last, Integer loginID) {
        // Creating a new user
        String insertDataSql = "INSERT INTO Users (First_Name, Last_Name, Login_Credential_ID) VALUES(\"" + first
                + "\", \"" + last + "\", " + loginID + ");";
        dontNeedThat(insertDataSql);
    }

    public static void thatSoundsDelicious(String name, String startDate, String endDate, Integer strength,
                                           Double price, Integer complexity, boolean spiritForwardOrRefreshing,
                                           Integer type) {
        String insertDataSql = "INSERT INTO Drinks (Name, Availability_Start, Availability_End, Alcohol_Content, " +
                "Price, Complexity, Spirit_Forward_or_Refreshing, Type) VALUES(\"" + name + "\", STR_TO_DATE(\""
                + startDate + "\", \"%m/%d/%y\"), STR_TO_DATE(\"" + endDate + "\", \"%m/%d/%y\"), " + strength + ", "
                + price + ", " + complexity + ", " + spiritForwardOrRefreshing + ", " + type + ");";
        dontNeedThat(insertDataSql);
    }

    public static void whatsInItThough(ArrayList<String> ingredients, Integer drinkID) {
        for (String ingredient : ingredients) {
            Integer ingredientID = selectIntegerWithString("Ingredient_Name", "Ingredients",
                    "Name", ingredient);
            if (ingredientID == 0) {
                String ingredientType = stringInput("Is this ingredient a liquor, liqueur, mixer or garnish?");
                String sqlStatement = "INSERT INTO Ingredients (Name, Type) VALUES(\"" + ingredient + "\", \""
                        + ingredientType + "\");";
                dontNeedThat(sqlStatement);
                ingredientID = selectIntegerWithString("Ingredient_Name", "Ingredients",
                        "Name", ingredient);
            }
            String sqlStatement = "INSERT INTO Recipes (Ingredient_ID, Drink_ID) VALUES(" + ingredientID + ", "
                    + drinkID + ");";
            dontNeedThat(sqlStatement);
        }
    }

    public static void youGotSomeWeirdKinks(Integer userID, ArrayList<String> allergies, String topShelf,
                                            boolean weakOrStrong, boolean deepPockets, boolean youFancy) {
        if (!allergies.isEmpty()) {
            for (String allergy : allergies) {
                Integer allergyID = selectIntegerWithString("Allergy_ID", "Allergies",
                        "Name", allergy);

                if (allergyID == 0) {
                    String sqlStatement = "INSERT INTO Allergies (Name) VALUES(\"" + allergy + "\");";
                    dontNeedThat(sqlStatement);
                    allergyID = selectIntegerWithString("Allergy_ID", "Allergies",
                            "Name", allergy);
                }

                String sqlStatement = "INSERT INTO User_Allergies (Allergy_ID, User_ID) VALUES(" + allergyID + ", "
                        + userID + ");";
                dontNeedThat(sqlStatement);
            }
        }

        String sqlStatement = "INSERT INTO User_Defined_Preferences (User_ID, Preferred_Liquor, " +
                "Weak_or_Strong, Cheap_or_Pricey, Simple_or_Complex) VALUES(" + userID + ", \"" + topShelf + "\", "
                + weakOrStrong + ", " + deepPockets + ", " + youFancy + ");";
        dontNeedThat(sqlStatement);
    }

    public static void wasItGood(Integer userID, Integer drinkID, Integer rating) {
        String sqlStatement = "INSERT INTO Drink_Preferences (User_ID, Drink_ID, Rating) VALUES(" + userID + ", "
                + drinkID + ", " + rating + ");";
        dontNeedThat(sqlStatement);
    }

    public static void youreSayingPeopleLikeThat(String name, String type) {
        String sqlStatement = "INSERT INTO Preferences (Name, Type) VALUES(\"" + name + "\", \"" + type + "\");";
        dontNeedThat(sqlStatement);
    }

    public static void youreOpinionIsWrong(Integer preferenceID, Integer userID, Double variable) {
        String sqlStatement = "INSERT INTO System_Defined_Preferences (Preference_ID, User_ID, Variable) VALUES("
                + preferenceID + ", " + userID + ", " + variable + ");";
        dontNeedThat(sqlStatement);
    }

    public static void youHaveNoPersonalityWhatsoever(Integer preferenceID, Integer userID) {
        String sqlStatement = "INSERT INTO System_Defined_Preferences (Preference_ID, User_ID, Variable) VALUES("
                + preferenceID + ", " + userID + ", 5.0);";
        dontNeedThat(sqlStatement);
    }


    /**********************************************
    ***Generic update statement creation methods***
    **********************************************/


    public static void updateString(String table, String column, String newData, String primaryKeyName,
                                    Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = \"" + newData + "\" WHERE \""
                + primaryKeyName + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateTime(String table, String column, String newData, String primaryKeyName,
                                  Integer primaryKeyID, String secondaryKeyName, String secondaryKeyData) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = TIME_FORMAT(CONVERT(\"" + newData
                + "\", TIME), \"%H:%i\") WHERE \"" + primaryKeyName + "\" = " + primaryKeyID + " AND \""
                + secondaryKeyName + "\" = " + secondaryKeyData + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateDate(String table, String column, String newData, String primaryKeyName,
                                  Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = STR_TO_DATE(\"" + newData
                + "\", \"%m/%d/%y\") WHERE \"" + primaryKeyName + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateDouble(String table, String column, Double newData, String primaryKeyName,
                                    Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE \"" + primaryKeyName
                + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateInteger(String table, String column, Integer newData, String primaryKeyName,
                                     Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE \"" + primaryKeyName
                + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateIntegerWithSecondaryID(String table, String column, Integer newData,
                                                    String primaryKeyName, Integer primaryKeyID,
                                                    String secondaryKeyName, Integer secondaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE \"" + primaryKeyName
                + "\" = " + primaryKeyID + " AND \"" + secondaryKeyName + "\" = " + secondaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateBoolean(String table, String column, boolean newData, String primaryKeyName,
                                     Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE \"" + primaryKeyName
                + "\" = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }


    /***********************************************
    ***Specific select statement creation methods***
    ***********************************************/


    public static String selectString(String column, String table, String primaryKeyName, Integer primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = "
                + primaryKeyID + ";";
        String returnString = needThatString(column, sqlStatement);
        return returnString;
    }

    public static String selectStringWithString(String column, String table, String primaryKeyName,
                                                String primaryKeyData) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = \""
                + primaryKeyData + "\";";
        String returnString = needThatString(column, sqlStatement);
        return returnString;
    }

    public static Double selectDouble(String column, String table, String primaryKeyName, Integer primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = "
                + primaryKeyID + ";";
        Double returnDouble = needThatDouble(column, sqlStatement);
        return returnDouble;
    }

    public static Double selectDoubleWithSecondaryKey(String column, String table, String primaryKeyName,
                                                      Integer primaryKeyID, String secondaryKeyName,
                                                      Integer secondaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = "
                + primaryKeyID + " AND " + secondaryKeyName + " = " + secondaryKeyID + ";";
        Double returnDouble = needThatDouble(column, sqlStatement);
        return returnDouble;
    }

    public static Integer selectInteger(String column, String table, String primaryKeyName, Integer primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = "
                + primaryKeyID + ";";
        Integer returnInteger = needThatInteger(column, sqlStatement);
        return returnInteger;
    }

    public static Integer selectIntegerWithString(String column, String table, String primaryKeyName,
                                                  String primaryKeyData) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = \""
                + primaryKeyData + "\";";
        Integer returnInteger = needThatInteger(column, sqlStatement);
        return returnInteger;
    }

    public static Double selectLowestDouble(String column, String table) {
        String sqlStatement = "SELECT MIN(" + column + ") FROM " + table + ";";
        Double minValue = needThatDouble("MIN(" + column + ")", sqlStatement);
        return minValue;
    }

    public static Double selectHighestDouble(String column, String table) {
        String sqlStatement = "SELECT MAX(" + column + ") FROM " + table + ";";
        Double maxValue = needThatDouble("MAX(" + column + ")", sqlStatement);
        return maxValue;
    }

    public static Double selectAverageDouble(String column, String table) {
        String sqlStatement = "SELECT AVG(" + column + ") FROM " + table + ";";
        Double maxValue = needThatDouble("AVG(" + column + ")", sqlStatement);
        return maxValue;
    }

    public static Integer selectIntegerWithSecondKey(String column, String table, String primaryKeyName,
                                                     Integer primaryKeyID, String secondaryKeyName,
                                                     Integer secondaryKeyData) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = "
                + primaryKeyID + " AND " + secondaryKeyName + " = " + secondaryKeyData + ";";
        Integer returnInteger = needThatInteger(column, sqlStatement);
        return returnInteger;
    }

    public static boolean selectBoolean(String column, String table, String primaryKeyName, Integer primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = "
                + primaryKeyID + ";";
        Integer returnInteger = needThatBoolean(column, sqlStatement);
        boolean returnBoolean = false;
        if (returnInteger == 2) {
            returnBoolean = true;
        }
        return returnBoolean;
    }

    public static ArrayList<String> selectStringArrayList(String column, String table, String primaryKeyName,
                                                          Integer primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = "
                + primaryKeyID + ";";
        ArrayList<String> returnArrayListString = needThatArrayListString(column, sqlStatement);
        return returnArrayListString;
    }

    public static ArrayList<Integer> selectIntegerArrayList(String column, String table, String primaryKeyName,
                                                            Integer primaryKeyID) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = "
                + primaryKeyID + ";";
        ArrayList<Integer> returnArrayListInteger = needThatArrayListInteger(column, sqlStatement);
        return returnArrayListInteger;
    }

    public static ArrayList<Integer> selectIntegerArrayListWithBoolean(String column, String table,
                                                                       String primaryKeyName, boolean primaryKeyBool) {
        String sqlStatement = "SELECT " + column + " FROM " + table + " WHERE " + primaryKeyName + " = "
                + primaryKeyBool + ";";
        ArrayList<Integer> returnArrayListInteger = needThatArrayListInteger(column, sqlStatement);
        return returnArrayListInteger;
    }


    /***********************************************
    ***Generic delete statement creation methods***
    ***********************************************/


    public static void delete(String table, String primaryKeyName, Integer primaryKeyID) {
        String insertDataSql = "DELETE FROM " + table + " WHERE " + primaryKeyName + " = " + primaryKeyID + ";";
        dontNeedThat(insertDataSql);
    }

    public static void deleteWithSecondKey(String table, String primaryKeyName, Integer primaryKeyID,
                                           String secondaryKeyName, Integer secondaryKeyID) {
        String insertDataSql = "DELETE FROM " + table + " WHERE " + primaryKeyName + " = " + primaryKeyID + " AND "
                + secondaryKeyName + " = " + secondaryKeyID + ";";
        dontNeedThat(insertDataSql);
    }


    /***********************************************************
    ***Generic input/update/delete statement execution method***
    ***********************************************************/


    public static void dontNeedThat(String sqlStatement) {
        // Generic means of pushing data
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bar_Database?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "root", "whopps666");
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
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bar_Database?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "root", "whopps666");
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
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bar_Database?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "root", "whopps666");
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
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bar_Database?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "root", "whopps666");
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
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bar_Database?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "root", "whopps666");
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
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bar_Database?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "root", "whopps666");
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
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bar_Database?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "root", "whopps666");
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


    /***********************
    ***To be done methods***
    ***********************/


    public static boolean doIt() {
        boolean response = yesNoInput("Are you sure you want to save your changes?");
        return response;
    }
}

package AllYourDatabaseAreBelongToDrunks;

import java.sql.*;
import java.util.ArrayList;

public class SelectStatementExecution {
    public static void main(String[] args) { }

    public static String needThatString(String column, String sqlStatement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bar_Database?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "BarHopApp", "imissfroggerthatgamewasawesome");
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
                    "BarHopApp", "imissfroggerthatgamewasawesome");
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
                    "BarHopApp", "imissfroggerthatgamewasawesome");
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
                    "BarHopApp", "imissfroggerthatgamewasawesome");
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
                    "BarHopApp", "imissfroggerthatgamewasawesome");
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
                    "BarHopApp", "imissfroggerthatgamewasawesome");
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
}

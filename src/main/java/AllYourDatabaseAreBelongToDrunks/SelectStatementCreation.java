package AllYourDatabaseAreBelongToDrunks;

import java.util.ArrayList;

import static AllYourDatabaseAreBelongToDrunks.SelectStatementExecution.*;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementExecution.needThatArrayListInteger;

public class SelectStatementCreation {
    public static void main(String[] args) { }

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
}

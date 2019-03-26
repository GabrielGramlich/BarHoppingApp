package AllYourDatabaseAreBelongToDrunks;

import static AllYourDatabaseAreBelongToDrunks.GenericStatementExecution.dontNeedThat;
import static input.InputUtils.yesNoInput;

public class UpdateStatementCreation {
    public static void main(String[] args) { }

    public static void updateString(String table, String column, String newData, String primaryKeyName,
                                    Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = \"" + newData + "\" WHERE "
                + primaryKeyName + " = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateTime(String table, String column, String newData, String primaryKeyName,
                                  Integer primaryKeyID, String secondaryKeyName, String secondaryKeyData) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = TIME_FORMAT(CONVERT(\"" + newData
                + "\", TIME), \"%H:%i\") WHERE " + primaryKeyName + " = " + primaryKeyID + ", "
                + secondaryKeyName + " = " + secondaryKeyData + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateDate(String table, String column, String newData, String primaryKeyName,
                                  Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = STR_TO_DATE(\"" + newData
                + "\", \"%m/%d/%y\") WHERE " + primaryKeyName + " = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateDouble(String table, String column, Double newData, String primaryKeyName,
                                    Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE " + primaryKeyName
                + " = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateInteger(String table, String column, Integer newData, String primaryKeyName,
                                     Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE " + primaryKeyName
                + " = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateIntegerWithSecondaryID(String table, String column, Integer newData,
                                                    String primaryKeyName, Integer primaryKeyID,
                                                    String secondaryKeyName, Integer secondaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE " + primaryKeyName
                + " = " + primaryKeyID + ", " + secondaryKeyName + " = " + secondaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static void updateBoolean(String table, String column, boolean newData, String primaryKeyName,
                                     Integer primaryKeyID) {
        String sqlStatement = "UPDATE " + table + " SET " + column + " = " + newData + " WHERE " + primaryKeyName
                + " = " + primaryKeyID + ";";
        dontNeedThat(sqlStatement);
    }

    public static boolean doIt() {
        boolean response = yesNoInput("Are you sure you want to save your changes?");
        return response;
    }
}

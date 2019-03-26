package AllYourDatabaseAreBelongToDrunks;

import static AllYourDatabaseAreBelongToDrunks.GenericStatementExecution.dontNeedThat;
import static input.InputUtils.yesNoInput;

public class DeleteStatementCreation {
    public static void main(String[] args) { }

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

    public static boolean doIt() {
        boolean response = yesNoInput("Are you sure you want to save your changes?");
        return response;
    }
}

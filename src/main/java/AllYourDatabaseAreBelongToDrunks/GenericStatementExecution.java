package AllYourDatabaseAreBelongToDrunks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericStatementExecution {
    public static void main(String[] args) { }

    public static void dontNeedThat(String sqlStatement) {
        // Generic means of pushing data
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bar_Database?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "BarHopApp", "imissfroggerthatgamewasawesome");
            Statement statement = connection.createStatement();
            statement.execute(sqlStatement);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

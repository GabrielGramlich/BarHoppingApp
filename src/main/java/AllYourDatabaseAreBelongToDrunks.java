import java.sql.*;

public class AllYourDatabaseAreBelongToDrunks {
    public static void main(String[] args) {
        // Testing creation of a new account
        try {
            String accountType = "owner";
            Boolean userType;
            if (accountType == "user") {
                userType = false;
            } else {
                userType = true;
            }

            Integer credentialID = whoAreYou(userType, "johndoe69", "jonathon@doe.org", "poopityscoop", "RN32");

            greetingsFriend("John", "Doe", credentialID);
        } catch (NullPointerException npe) { }
    }

    public static Integer whoAreYou(Boolean accountType, String username, String emailAddress, String password, String salt) {
        // Storing log in credentials
        String insertDataSql = "INSERT INTO Login_Credentials (Account_Type, Username, Email_Address, Password, Salt) VALUES(" + accountType + ", \"" + username + "\", \"" + emailAddress + "\", \"" + password + "\", \"" + salt + "\");";
        dontNeedThat(insertDataSql);

        Integer credentialsID = yourNumberIsUp(username, password);
        return credentialsID;
    }

    public static Integer yourNumberIsUp(String username, String password) {
        // getting login ID
        try {
            String getCredentialID = "SELECT Login_ID FROM Login_Credentials WHERE Username = \"" + username + "\" AND Password = \"" + password + "\";";
            ResultSet credentialResult = needThat(getCredentialID);

            Integer credentialID = credentialResult.getInt("Login_ID");

            return credentialID;
        } catch (SQLException sqle) {
            return 0;
        }
    }

    public static void greetingsFriend(String first, String last, Integer loginID) {
        // Creating a new user
        String insertDataSql = "INSERT INTO Users (First_Name, Last_Name, Login_Credentials_Login_ID) VALUES(\"" + first + "\", \"" + last + "\", " + loginID + ");";
        dontNeedThat(insertDataSql);
    }

    public static void dontNeedThat(String insertDataSql) {
        // Generic means of pushing data
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();
            statement.execute(insertDataSql);

            statement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet needThat(String getDataSql) {
        // Generic means of retrieving data
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            ResultSet retrievedData = statement.executeQuery(getDataSql);

            statement.close();
            connection.close();

            return retrievedData;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            ResultSet retrievedData = null;
            return retrievedData;
        }
    }
}

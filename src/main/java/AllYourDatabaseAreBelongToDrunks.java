import java.sql.*;

public class AllYourDatabaseAreBelongToDrunks {
    public static void main(String[] args) { }

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

    public static void makeUpYourMindAlready(String column, String data, String username) {
        String updateDataSql = "";
        Integer userID = needThatUserID(username);

        if (column.equals("name")) {
            Integer spaceIndex = data.indexOf(" ");
            String first = data.substring(0, spaceIndex);
            String last = data.substring(spaceIndex + 1);

            updateDataSql = "UPDATE Users SET First_Name = \"" + first + "\" WHERE User_ID = " + userID + ";";
            dontNeedThat(updateDataSql);

            updateDataSql = "UPDATE Users SET Last_Name = \"" + last + "\" WHERE User_ID = " + userID + ";";
            dontNeedThat(updateDataSql);
        } else {
            updateDataSql = "UPDATE Login_Credentials SET " + column + " = \"" + data + "\" WHERE User_ID = " + userID + ";";
            dontNeedThat(updateDataSql);
        }
    }

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

    public static Integer needThatCredentialID(String username) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            String getCredentialID = "SELECT Login_ID FROM Login_Credentials WHERE Username = \"" + username + "\";";
            ResultSet retrievedData = statement.executeQuery(getCredentialID);

            Integer credentialID = null;
            while (retrievedData.next()) {
                credentialID = retrievedData.getInt("Login_ID");
            }

            statement.close();
            connection.close();

            return credentialID;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static Integer needThatAccountType(String username) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            String getAccountType = "SELECT Account_Type FROM Login_Credentials WHERE Username = \"" + username + "\";";
            ResultSet retrievedData = statement.executeQuery(getAccountType);

            Integer accountType = 0;
            while (retrievedData.next()) {
                boolean type = retrievedData.getBoolean("Account_Type");
                if (type) {
                    accountType = 2;
                } else {
                    accountType = 1;
                }
            }

            statement.close();
            connection.close();

            return accountType;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static String needThatSalt(String username) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            String getSalt = "SELECT Salt FROM Login_Credentials WHERE Username = \"" + username + "\";";
            ResultSet retrievedData = statement.executeQuery(getSalt);

            String salt = "";
            while (retrievedData.next()) {
                salt = retrievedData.getString("Salt");
            }

            statement.close();
            connection.close();

            return salt;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String needThatPassword(String username) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            String getPassword = "SELECT Password FROM Login_Credentials WHERE Username = \"" + username + "\";";
            ResultSet retrievedData = statement.executeQuery(getPassword);

            String password = "";
            while (retrievedData.next()) {
                password = retrievedData.getString("Password");
            }

            statement.close();
            connection.close();

            return password;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static Integer needThatUserID(String username) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bar_DB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "whopps666");
            Statement statement = connection.createStatement();

            Integer loginID = needThatCredentialID(username);

            String getUserID = "SELECT User_ID FROM Users WHERE Login_Credentials_Login_ID = " + loginID + ";";
            ResultSet retrievedData = statement.executeQuery(getUserID);

            Integer userID = 0;
            while (retrievedData.next()) {
                userID = retrievedData.getInt("User_ID");
            }

            statement.close();
            connection.close();

            return userID;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static Object needThat(String username) {
        //TODO make generic query method
    }
}

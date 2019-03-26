package Menus;

import static AllYourDatabaseAreBelongToDrunks.DeleteStatementCreation.delete;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.selectInteger;
import static AllYourDatabaseAreBelongToDrunks.SelectStatementCreation.selectIntegerWithString;
import static Menus.SignIn.loginID;
import static Menus.SignIn.username;
import static input.InputUtils.yesNoInput;

public class DeleteAccount {
    public static void main(String[] args) { }

    public static void deleteAccount() {
        loginID = selectIntegerWithString("Login_Credential_ID",
                "Login_Credentials", "Username", username);
        Integer userID = selectInteger("User_ID", "Users",
                "Login_Credential_ID", loginID);
        boolean response = yesNoInput("Awh, you wanna leave? Big baby got his feewings hurt?");
        if (response) {
            delete("Users", "User_ID", userID);
            delete("Login_Credentials", "Login_Credential_ID",
                    loginID);
        }
    }
}

package controller;

import model.SQL;
import java.sql.SQLException;

public class Login
{
    public static String login (String username,String password) throws SQLException
    {
       int ID = SQL.getIDByUserName(username);
       if (ID == -1) return "Username doesn't exist!";
       if (!SQL.getPasswordByID(ID).equals(password)) return "Password is wrong!";
       else return  "You logged in successfully!";
    }
    public static int showSecurityQuestion (String username) throws SQLException
    {
        int ID = SQL.getIDByUserName(username);
        if (ID==-1) return -1;
        return SQL.getSQNumberByID(ID);
    }
    public static boolean checkAnswer (String username,String answer) throws SQLException
    {
        int ID = SQL.getIDByUserName(username);
        return SQL.getAnswerByID(ID).equals(answer);
    }
    public static String changePasswordByUserName (String username,String newPassword) throws SQLException
    {
        int ID = SQL.getIDByUserName(username);
        String checkedPassword = SignUp.checkPassword(newPassword);
        if (checkedPassword.isEmpty())
        {
            SQL.setNewPasswordByID(ID, newPassword);
            return "Password changed successfully!";
        }
        else
            return checkedPassword;
    }
}

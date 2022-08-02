package controller;

import model.SQL;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp
{
    public static String signup (String username,String password,String password2,int SQNumber,String answer,String accountType) throws SQLException {
        String result = "";
        int ID = SQL.getIDByUserName(username);
        if (ID!=-1)
        {
            result = "Username already exists!";
            return result;
        }
        if (!password.equals(password2))
        {
            result = "Passwords are not same!";
            return result;
        }
        result = checkPassword(password);
        if (result.isEmpty())
        {
            SQL.insertNewUser(username,password,SQNumber,answer,accountType);
            result = "Signed up successfully!";
        }
        return result;
    }
    protected static String checkPassword (String password)
    {
        Pattern pattern ;
        Matcher matcher;
        String[] check = {"[A-Z]","[a-z]","[0-9]"};
        String[] message = {"Password must include uppercase!","Password must include lowercase!","Password must include number!","Password must be at least 8 characters!"};
        StringBuilder result = new StringBuilder();
        for (int i=0;i<3;i++)
        {
            pattern = Pattern.compile(check[i]);
            matcher = pattern.matcher(password);
            if (!matcher.find())
            {
                result.append(message[i]);
                return result.toString();
            }
        }
        if (password.length()<8)
        {
            result.append(message[3]);
        }
        return result.toString();
    }
}

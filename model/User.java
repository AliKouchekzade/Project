package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    private static final Statement statement = Repository.statement;

    public static void insertNewUser (String username,String password,String Gender,String Email,int BirthYear,int SQNumber,String answer,String accountType) throws SQLException {
        String sql = "INSERT INTO users (UserName, Password, Gender, Email, BirthYear, SQNumber, Answer, AccountType) values ('" + username+"','"+
                password + "','" + Gender +"','" + Email + "','" + BirthYear + "','" + SQNumber + "','" + answer + "','" + accountType + "')";
        statement.execute(sql);
    }

    public static int getIDByUserName (String username) throws SQLException {
        String sql = "SELECT ID from users where UserName = '" + username + "';";
        ResultSet resultSet = statement.executeQuery(sql);

        if (!resultSet.next()) {
            return -1;
        }

        return resultSet.getInt("ID");
    }

    public static String getPasswordByID (int ID) throws SQLException {
        String sql = "SELECT Password from users where ID = '" + ID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getString("Password");
    }

    public static int getSQNumberByID (int ID) throws SQLException {
        String sql = "SELECT SQNumber FROM users WHERE ID = '" + ID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt("SQNumber");
    }

    public static String getAnswerByID (int ID) throws SQLException {
        String sql = "SELECT answer FROM users WHERE ID = '" + ID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getString("answer");
    }

    public static void setNewPasswordByID (int ID,String newPassword) throws SQLException {
        String sql = "UPDATE users SET Password = '" +newPassword + "' WHERE ID = '" + ID + "';" ;
        statement.execute(sql);
    }
}

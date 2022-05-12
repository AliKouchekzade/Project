package controller;

import model.User;

import java.sql.SQLException;

public class Login {

    public static String login (String username,String password) throws SQLException {
       int ID = User.getIDByUserName(username);
       if (ID == -1) return "username doesn't exist";
       if (!User.getPasswordByID(ID).equals(password)) return "password is wrong";
       else return  "login successfully";
    }

}

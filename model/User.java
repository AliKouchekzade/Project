package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    private static final Statement statement = Repository.statement;

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

}

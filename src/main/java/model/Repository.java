package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {

    private static Connection connection;
    protected static Statement statement;

    static {
        try {
            connection = Repository.connectToDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assert false;
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectToDataBase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Project",user = "root", password = "M2815165a#100%";
        return DriverManager.getConnection(url,user,password);
    }
}

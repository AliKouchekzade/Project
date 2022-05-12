package view;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    static Scanner scanner = new Scanner(System.in);

    protected static void login() throws SQLException {
        System.out.println("enter your username if you dont have account enter signup");
        String username = scanner.next();
        if (username.equals("signup")) {Manager.LoginBool=false; return;}
        System.out.println("enter your password");
        String password = scanner.next();
        String result = controller.Login.login(username, password);
        System.out.println(result);
        if (result.equals("login successfully")) {
            Home.myUserName = username;
            Manager.LoginBool=false;
            Manager.HomeBool = true;
        }
    }

}

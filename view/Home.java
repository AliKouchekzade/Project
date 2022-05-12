package view;

import java.util.Scanner;

public class Home {

    protected static String myUserName;
    static Scanner scanner = new Scanner(System.in);

    public static void home () {
        System.out.println("you logged in as " + myUserName);
        while (true) {
            System.out.println("for logout enter logout");
            String input = scanner.next();
            if (input.equals("logout")) {
                myUserName = null;
                Manager.HomeBool = false;
                Manager.LoginBool = true;
                System.out.println("myUserName = " + myUserName);
                break;
            }
        }
    }
}

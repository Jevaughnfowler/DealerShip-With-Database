package com.pluralsight;

import com.pluralsight.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <dbUser> <dbPassword> <dbUrl>");
            return;
        }

        System.setProperty("dbUser", args[0]);
        System.setProperty("dbPassword", args[1]);
        System.setProperty("dbUrl", args[2]);

        UserInterface ui = new UserInterface();
        ui.display();
    }
}
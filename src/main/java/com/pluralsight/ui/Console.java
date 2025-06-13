package com.pluralsight.ui;

import java.util.Scanner;

public class Console {

    private Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }


    public int promptForInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again.");
            }
        }
    }


    public double promptForDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again.");
            }
        }
    }



    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
package com.trzewik.userInputProvider;

import com.trzewik.converter.enums.ConvertType;

import java.util.Scanner;

public class UserInputProvider {

    public static ConvertType collectProperConvertType(String message) {
        while (true) {
            try {
                return ConvertType.convertTypeMatcher(collectString(message));
            } catch (IllegalArgumentException e) {
                MessagePrinter.printErrorMessage("Input must be convert type!");
            }
        }
    }

    public static String collectString(String message) {
        while (true) {
            try {
                Scanner userInput = getMessage(message);
                return userInput.nextLine();
            } catch (Exception e) {
                MessagePrinter.printErrorMessage("Input must be a string! Try again: ");
            }
        }
    }

    public static Integer collectInteger(String message) {
        while (true) {
            try {
                Scanner userInput = getMessage(message);
                return userInput.nextInt();
            } catch (Exception e) {
                MessagePrinter.printErrorMessage("Input must be an integer! Try again: ");
            }
        }
    }

    private static Scanner getMessage(String message) {
        Scanner userInput = new Scanner(System.in);
        MessagePrinter.printMessage(message);
        return userInput;
    }
}
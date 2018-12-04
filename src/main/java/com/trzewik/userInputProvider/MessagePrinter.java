package com.trzewik.userInputProvider;

public class MessagePrinter {

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String errorMessage) {
        System.err.println(errorMessage);
    }
}
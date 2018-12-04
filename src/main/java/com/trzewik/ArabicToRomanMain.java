package com.trzewik;

import com.trzewik.converter.Converter;
import com.trzewik.userInputProvider.MessagePrinter;
import com.trzewik.userInputProvider.UserInputProvider;

import static com.trzewik.userInputProvider.MessageProvider.CHOOSE_ARABIC_TO_CONVERT;

public class ArabicToRomanMain {
    public static void main(String[] args) {
        Converter converter = new Converter();
        while (true) {
            try {
                int arabicNumeral = UserInputProvider.collectInteger(CHOOSE_ARABIC_TO_CONVERT);
                String roman = converter.convertArabicToRoman(arabicNumeral);
                MessagePrinter.printMessage(roman);
                return;
            } catch (IllegalArgumentException e) {
                MessagePrinter.printErrorMessage(e.getMessage());
            }
        }
    }
}

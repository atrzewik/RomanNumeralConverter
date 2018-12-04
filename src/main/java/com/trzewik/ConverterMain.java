package com.trzewik;

import com.trzewik.converter.Converter;
import com.trzewik.converter.enums.ConvertType;
import com.trzewik.userInputProvider.MessagePrinter;
import com.trzewik.userInputProvider.MessageProvider;
import com.trzewik.userInputProvider.UserInputProvider;

import static com.trzewik.userInputProvider.MessageProvider.CHOOSE_ARABIC_TO_CONVERT;
import static com.trzewik.userInputProvider.MessageProvider.CHOOSE_ROMAN_TO_CONVERT;

public class ConverterMain {

    public static void main(String[] args) {
        Converter converter = new Converter();
        ConvertType userChoice = UserInputProvider.collectProperConvertType(MessageProvider.CONVERT_TYPE_CHOICE);
        while (true) {
            try {
                switch (userChoice) {
                    case ARABIC_TO_ROMAN:
                        int arabicNumeral = UserInputProvider.collectInteger(CHOOSE_ARABIC_TO_CONVERT);
                        String roman = converter.convertArabicToRoman(arabicNumeral);
                        MessagePrinter.printMessage(roman);
                        return;
                    case ROMAN_TO_ARABIC:
                        String romanNumeral = UserInputProvider.collectString(CHOOSE_ROMAN_TO_CONVERT);
                        int arabic = converter.convertRomanToArabic(romanNumeral);
                        MessagePrinter.printMessage(Integer.toString(arabic));
                        return;
                }
            } catch (IllegalArgumentException e) {
                MessagePrinter.printErrorMessage(e.getMessage());
            }
        }
    }
}
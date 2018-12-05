package com.trzewik.converter;

import com.trzewik.converter.enums.RomanNumeral;

import static com.trzewik.userInputProvider.MessageProvider.*;

public class Converter {

    public String convertArabicToRoman(int arabicNumeral) throws IllegalArgumentException {
        return conversionArabicToRoman(arabicNumeral).toString();
    }

    public int convertRomanToArabic(String romanNumeral) throws IllegalArgumentException {
        return conversionRomanToArabic(romanNumeral);
    }

    private StringBuilder conversionArabicToRoman(int arabicNumeral) {
        validateOfArabicNumeral(arabicNumeral);
        StringBuilder arabicNumeralRomanValue = new StringBuilder();
        for (RomanNumeral romanNumeral : RomanNumeral.values()) {
            int arabicValueOfRomanNumeral = romanNumeral.getArabicValue();
            int numberOfRomanNumeral = arabicNumeral / arabicValueOfRomanNumeral;
            /*
            Adding to arabicNumeralRomanValue romanNumeral as many times as it fits in arabicNumeral
            */
            for (int i = 0; i < numberOfRomanNumeral; i++) {
                if (arabicNumeral >= arabicValueOfRomanNumeral) {
                    arabicNumeralRomanValue.append(romanNumeral);
                    arabicNumeral -= arabicValueOfRomanNumeral;
                }
            }
            RomanNumeral subtrahendOfRomanNumeral = romanNumeral.getSubtrahend();
            int valueOfRomanAndSubtrahendDifference = getValueOfRomanAndSubtrahendDifference(subtrahendOfRomanNumeral,
                    arabicValueOfRomanNumeral);
            /*
            Checking if arabic numeral divided by getValueOfRomanAndSubtrahendDifference(900, 400, etc.) returns
            value bigger than 0
            */
            if (arabicNumeral / valueOfRomanAndSubtrahendDifference > 0) {
                arabicNumeralRomanValue.append(subtrahendOfRomanNumeral);
                arabicNumeralRomanValue.append(romanNumeral);
                arabicNumeral -= valueOfRomanAndSubtrahendDifference;
            }
        }
        return arabicNumeralRomanValue;
    }

    private void validateOfArabicNumeral(int arabicNumeral) {
        /*
         Roman numerals can't be negative and the same roman numerals can't be
         next to each other more than 3 times in a row
         */
        int maximumRomanValue = RomanNumeral.values()[0].getArabicValue();
        if (arabicNumeral / maximumRomanValue > 3) {
            throw new IllegalArgumentException(TO_BIG_ARABIC + maximumRomanValue * 4);
        } else if (arabicNumeral <= 0) {
            throw new IllegalArgumentException(TO_SMALL_ARABIC);
        }
    }

    private int getValueOfRomanAndSubtrahendDifference(RomanNumeral romanNumeralSubtrahend, int arabicValueOfRomanNumeral) {
        if (romanNumeralSubtrahend != null) {
            return arabicValueOfRomanNumeral - romanNumeralSubtrahend.getArabicValue();
        } else {
            return arabicValueOfRomanNumeral;
        }
    }

    private int conversionRomanToArabic(String romanNumeral) {
        validateOfRomanNumeral(romanNumeral);
        int arabicNumeral = 0;
        int romanNumeralLength = romanNumeral.length();
        for (int i = 0; i < romanNumeralLength; i++) {
            RomanNumeral currentRomanNumeral = RomanNumeral.parse(romanNumeral.substring(i, i + 1));
            int currentRomanNumeralValue = currentRomanNumeral.getArabicValue();
            int lastRomanNumeralIndex = romanNumeralLength - 1;
            /*
            Checking if it possible to get next roman numeral index
             */
            if (i != lastRomanNumeralIndex) {
                RomanNumeral nextRomanNumeral = RomanNumeral.parse(romanNumeral.substring(i + 1, i + 2));
                int nextRomanNumeralValue = nextRomanNumeral.getArabicValue();
                if (currentRomanNumeralValue < nextRomanNumeralValue) {
                    arabicNumeral -= currentRomanNumeralValue;
                } else {
                    arabicNumeral += currentRomanNumeralValue;
                }
            } else {
                arabicNumeral += currentRomanNumeralValue;
            }
        }
        return arabicNumeral;
    }

    private void validateOfRomanNumeral(String romanNumeral) throws IllegalArgumentException {
        int romanNumeralLength = romanNumeral.length();
        int count = 0;
        for (int i = 0; i < romanNumeralLength; i++) {
            RomanNumeral biggestValue;
            char currentRoman = romanNumeral.charAt(i);
            int lastRomanNumeralIndex = romanNumeralLength - 1;
            /*
            Throwing exception if roman numerals sequence is invalid
             */
            RomanNumeral currentRomanNumeral = RomanNumeral.parse(Character.toString(currentRoman));
            if (i > 1) {
                char previousRoman = romanNumeral.charAt(i - 1);
                char previousSecondRoman = romanNumeral.charAt(i - 2);
                RomanNumeral previousRomanNumeral = RomanNumeral.parse(Character.toString(previousRoman));
                RomanNumeral previousSecondRomanNumeral = RomanNumeral.parse(Character.toString(previousSecondRoman));
                if (previousSecondRomanNumeral == previousRomanNumeral.getSubtrahend()) {
                    biggestValue = previousRomanNumeral;
                    if (i>2){
                        char previousThirdRoman = romanNumeral.charAt(i - 3);
                        RomanNumeral previousThirdRomanNumeral = RomanNumeral.parse(Character.toString(previousThirdRoman));
                        if (previousThirdRomanNumeral == currentRomanNumeral){
                            throw new IllegalArgumentException(WRONG_CHAR_SEQ);
                        }
                    }
                } else {
                    biggestValue = previousSecondRomanNumeral;
                }
                if (currentRomanNumeral.getArabicValue() > biggestValue.getArabicValue()) {
                    throw new IllegalArgumentException(WRONG_CHAR_SEQ);
                }
            }
            if (i < lastRomanNumeralIndex) {
                char nextRoman = romanNumeral.charAt(i + 1);
                if (currentRoman == nextRoman) {
                    count += 1;
                } else {
                    count = 0;
                }
                /*
                Throwing exception if same roman numeral is given more than three times in a row
                */
                if (count > 2) {
                    throw new IllegalArgumentException(TO_MANY_SAME_CHARS_IN_ROW);
                }
            }

        }
    }
}
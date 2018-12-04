package com.trzewik.converter.enums;

import static com.trzewik.userInputProvider.MessageProvider.NOT_ROMAN_NUMERAL;

public enum RomanNumeral {
    /*
    In case of implementation roman numerals with a bigger value than 1000
    it should be add to the top of enum RomanNumeral
    */
    M(1000),
    D(500),
    C(100),
    L(50),
    X(10),
    V(5),
    I(1);

    private final int arabicValue;


    RomanNumeral(int value) {
        this.arabicValue = value;
    }

    public static RomanNumeral parse(String inputRomanNumeral) {
        /*
        Throwing exception if input doesn't consist of roman numerals
         */
        for (RomanNumeral romanNumeral : values()) {
            if (romanNumeral.name().equals(inputRomanNumeral)) {
                return romanNumeral;
            }
        }
        throw new IllegalArgumentException(NOT_ROMAN_NUMERAL);
    }

    public int getArabicValue() {
        return this.arabicValue;
    }

    public RomanNumeral getSubtrahend() {
        switch (this) {
            case M:
            case D:
                return C;
            case C:
            case L:
                return X;
            case X:
            case V:
                return I;
            default:
                return null;
        }
    }
}
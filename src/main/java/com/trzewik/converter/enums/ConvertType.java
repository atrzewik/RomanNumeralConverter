package com.trzewik.converter.enums;

import static com.trzewik.userInputProvider.MessageProvider.WRONG_CONVERT_TYPE;

public enum ConvertType {
    ARABIC_TO_ROMAN,
    ROMAN_TO_ARABIC;

    public static ConvertType convertTypeMatcher(String userInput) {
        try {
            return ConvertType.valueOf(userInput);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(WRONG_CONVERT_TYPE);
        }
    }
}
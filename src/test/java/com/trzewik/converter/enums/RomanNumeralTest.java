package com.trzewik.converter.enums;

import com.trzewik.userInputProvider.MessageProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class RomanNumeralTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldParse() {
        assertEquals(RomanNumeral.parse("M"), RomanNumeral.M);
    }

    @Test
    public void shouldNotParse() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(MessageProvider.NOT_ROMAN_NUMERAL);
        RomanNumeral.parse("S");
    }
}
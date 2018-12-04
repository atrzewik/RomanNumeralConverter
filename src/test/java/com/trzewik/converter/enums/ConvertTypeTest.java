package com.trzewik.converter.enums;

import com.trzewik.userInputProvider.MessageProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ConvertTypeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldMatchType() {
        assertEquals(ConvertType.convertTypeMatcher("ARABIC_TO_ROMAN"), ConvertType.ARABIC_TO_ROMAN);
    }

    @Test
    public void shouldNotMatchType() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(MessageProvider.WRONG_CONVERT_TYPE);
        ConvertType.convertTypeMatcher("S");
    }
}
package com.trzewik.converter;

import com.trzewik.converter.enums.RomanNumeral;
import com.trzewik.userInputProvider.MessageProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class ConverterTest {

    @RunWith(Parameterized.class)
    public static class ConverterTestParameterizedShouldPass {

        private Converter converter;
        private int arabic;
        private String roman;

        public ConverterTestParameterizedShouldPass(int arabic, String roman) {
            this.arabic = arabic;
            this.roman = roman;
        }

        @Parameterized.Parameters(name = "arabic: {0} = roman: {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {1, "I"}, {4, "IV"}, {5, "V"}, {9, "IX"}, {10, "X"}, {34, "XXXIV"}, {1999, "MCMXCIX"},
                    {408, "CDVIII"}, {777, "DCCLXXVII"}, {864, "DCCCLXIV"}, {521, "DXXI"}, {3999, "MMMCMXCIX"}
            });
        }

        @Before
        public void initialize() {
            converter = new Converter();
        }

        @Test
        public void shouldConvertArabicToRoman() {
            assertEquals(roman, converter.convertArabicToRoman(arabic));
        }

        @Test
        public void shouldConvertRomanToArabic() {
            assertEquals(arabic, converter.convertRomanToArabic(roman));
        }
    }

    @RunWith(Parameterized.class)
    public static class ConverterTestParameterizedShouldNotPass {

        @Rule
        public ExpectedException thrown = ExpectedException.none();
        private Converter converter;
        private String roman;

        public ConverterTestParameterizedShouldNotPass(String roman) {
            this.roman = roman;
        }

        @Parameterized.Parameters(name = "roman: {0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"MCMXCIXM"}, {"MCMM"}, {"XCM"}, {"DXCXXM"}, {"MXM"},
            });
        }

        @Before
        public void initialize() {
            converter = new Converter();
        }

        @Test
        public void shouldNotConvertRomanToArabicBecauseWrongSequence() throws IllegalArgumentException {
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage(MessageProvider.WRONG_CHAR_SEQ);
            converter.convertRomanToArabic(roman);
        }
    }


    public static class ConverterTestNotParametrized {

        @Rule
        public ExpectedException thrown = ExpectedException.none();
        private Converter converter;

        @Before
        public void initialize() {
            converter = new Converter();
        }

        @Test
        public void shouldNotConvertArabicToRomanBecauseInputToBig() throws IllegalArgumentException {
            int maximumRomanValue = RomanNumeral.values()[0].getArabicValue();
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage(MessageProvider.TO_BIG_ARABIC + maximumRomanValue * 4);
            converter.convertArabicToRoman(maximumRomanValue * 4);
        }

        @Test
        public void shouldNotConvertArabicToRomanBecauseInputIsLessThanZero() throws IllegalArgumentException {
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage(MessageProvider.TO_SMALL_ARABIC);
            converter.convertArabicToRoman(0);
        }

        @Test
        public void shouldNotConvertRomanToArabicBecauseThreeSameRomans() throws IllegalArgumentException {
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage(MessageProvider.TO_MANY_SAME_CHARS_IN_ROW);
            converter.convertRomanToArabic("CMMMMMIX");
        }

        @Test
        public void shouldNotConvertRomanToArabicBecauseInputNotRomanNumeral() throws IllegalArgumentException {
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage(MessageProvider.NOT_ROMAN_NUMERAL);
            converter.convertRomanToArabic("FF23");
        }
    }
}
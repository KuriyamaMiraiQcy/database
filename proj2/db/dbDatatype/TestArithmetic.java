package db.dbDatatype;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by lxjhk on 2/22/17.
 * Email: lxjpub@gmail.com
 */
public class TestArithmetic {

    @Test
    public void testStringConcat() {
        DBString a = new DBString("");
        DBString b = new DBString("NaN");
        System.out.println("testStringConcat");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBString("NaN"), Arithmetic.add(a, b)); // This line tests the Equals method
        assertEquals("NaN", Arithmetic.add(a, b).toString()); // Tests toString
    }

    @Test
    public void testStringConcatWithNaN() {
        DBString a = new DBString("");
        DBString b = new DBString(new NaN());
        System.out.println("testStringConcatWithNaN");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBString(new NaN()), Arithmetic.add(a, b)); // This line tests the Equals method
        assertEquals("NaN", Arithmetic.add(a, b).toString()); // Tests toString
    }

    @Test
    public void testStringConcatWithNOVALUE() {
        DBString a = new DBString(new NOVALUE());
        DBString b = new DBString("ASSAFDG");
        System.out.println("testStringConcatWithNOVALUE");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBString("ASSAFDG"), Arithmetic.add(a, b));
        assertEquals("ASSAFDG", Arithmetic.add(a, b).toString());
    }

    @Test
    public void testIntegerAddition() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(100);
        System.out.println("testIntegerAddition");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(Arithmetic.add(a, b), new DBInteger(200));
        assertEquals("200", Arithmetic.add(a, b).toString());
    }

    @Test
    public void testIntegerAdditionWithNOVALUE() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(new NOVALUE());
        System.out.println("testIntegerAdditionWithNOVALUE");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBInteger(100), Arithmetic.add(a, b));
        assertEquals("100", Arithmetic.add(a, b).toString());
    }

    @Test
    public void testIntegerAdditionWithNaN() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(new NaN());
        System.out.println("testIntegerAdditionWithNaN");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBInteger(new NaN()), Arithmetic.add(a, b));
        assertEquals("NaN", Arithmetic.add(a, b).toString());
    }

    @Test
    public void testIntegerAdditionWithDouble() {
        DBInteger a = new DBInteger(100);
        DBDouble b = new DBDouble(42.0);
        System.out.println("testIntegerAdditionWithDouble");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBDouble(142.0), Arithmetic.add(a, b));
        assertEquals("142.0", Arithmetic.add(a, b).toString());
    }

    @Test
    public void testIntegerAdditionWithString() {
        DBInteger a = new DBInteger(100);
        DBString b = new DBString("42.0");
        System.out.println("testIntegerAdditionWithString");
        try {
            Arithmetic.add(a, b);
        } catch (RuntimeException e) {
            System.out.println("Integer cannot add a String");
        }
    }

    @Test
    public void testDoubleAddition() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(1.123);
        System.out.println("testDoubleAddition");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBDouble(133.123), Arithmetic.add(a, b));
        assertEquals("133.123", Arithmetic.add(a, b).toString());
    }

    @Test
    public void testDoubleAdditionWithNaN() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(new NaN());
        System.out.println("testDoubleAdditionWithNaN");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBDouble(new NaN()), Arithmetic.add(a, b));
        assertEquals("NaN", Arithmetic.add(a, b).toString());
    }

    @Test
    public void testDoubleAdditionWithNOVALUE() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(new NOVALUE());
        System.out.println("testDoubleAdditionWithNOVALUE");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBDouble(132.0), Arithmetic.add(a, b));
        assertEquals("132.0", Arithmetic.add(a, b).toString());
    }

    @Test
    public void testDoubleAdditionWithInteger() {
        DBDouble a = new DBDouble(132.0);
        DBInteger b = new DBInteger(1);
        System.out.println("testDoubleAdditionWithInteger");
        System.out.println(Arithmetic.add(a, b));
        assertEquals(new DBDouble(133.0), Arithmetic.add(a, b));
        assertEquals("133.0", Arithmetic.add(a, b).toString());
    }

    @Test
    public void testDoubleAdditionWithString() {
        DBDouble a = new DBDouble(132.0);
        DBString b = new DBString("1.0");
        System.out.println("testDoubleAdditionWithString");
        try {
            Arithmetic.add(a, b);
        } catch (RuntimeException e) {
            System.out.println("Double cannot add a String");
        }
    }

    @Test
    public void testStringSubtraction() {
        DBString a = new DBString("AERD");
        DBString b = new DBString("AFHHH");
        System.out.println("testStringSubtraction");
        try {
            Arithmetic.sub(a, b);
        } catch (RuntimeException e) {
            System.out.println("StringSubtraction is not allowed");
        }
    }

    @Test
    public void testStringSubtractionWithNaN() {
        DBString a = new DBString("AERD");
        DBString b = new DBString(new NaN());
        System.out.println("testStringSubtractionWithNaN");
        try {
            Arithmetic.sub(a, b);
        } catch (RuntimeException e) {
            System.out.println("StringSubtraction is not allowed");
        }

    }

    @Test
    public void testStringSubtractionWithNOVALUE() {
        DBString a = new DBString("AERD");
        DBString b = new DBString(new NOVALUE());
        System.out.println("testStringSubtractionWithNOVALUE");
        try {
            Arithmetic.sub(a, b);
        } catch (RuntimeException e) {
            System.out.println("StringSubtraction is not allowed");
        }
    }

    @Test
    public void testIntegerSubtraction() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(100);
        System.out.println("testIntegerSubtraction");
        System.out.println(Arithmetic.sub(a, b));
        assertEquals(new DBInteger(0), Arithmetic.sub(a, b));
        assertEquals("0", Arithmetic.sub(a, b).toString());
    }

    @Test
    public void testIntegerSubtractionWithNOVALUE() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(new NOVALUE());
        System.out.println("testIntegerSubtractionWithNOVALUE");
        System.out.println(Arithmetic.sub(a, b));
        assertEquals(new DBInteger(100), Arithmetic.sub(a, b));
        assertEquals("100", Arithmetic.sub(a, b).toString());
    }


    @Test
    public void testIntegerSubtractionWithNaN() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(new NaN());
        System.out.println("testIntegerSubtractionWithNaN");
        System.out.println(Arithmetic.sub(a, b));
        assertEquals("NaN", Arithmetic.sub(a, b).toString());
    }

    @Test
    public void testIntegerSubtractionWithDouble() {
        DBInteger a = new DBInteger(100);
        DBDouble b = new DBDouble(1.0);
        System.out.println("testIntegerSubtractionWithDouble");
        System.out.println(Arithmetic.sub(a, b));
        assertEquals(new DBInteger(99), Arithmetic.sub(a, b));
        assertEquals("99.0", Arithmetic.sub(a, b).toString());
    }

    @Test
    public void testIntegerSubtractionWithString() {
        DBInteger a = new DBInteger(100);
        DBString b = new DBString("2");
        System.out.println("testIntegerSubtractionWithString");
        try {
            Arithmetic.sub(a, b);
        } catch (RuntimeException e) {
            System.out.println("Integer cannot sub a String");
        }
    }

    @Test
    public void testDoubleSubtraction() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(1.5);
        System.out.println("testDoubleSubtraction");
        System.out.println(Arithmetic.sub(a, b));
        assertEquals(new DBDouble(130.5), Arithmetic.sub(a, b));
        assertEquals("130.5", Arithmetic.sub(a, b).toString());
    }

    @Test
    public void testDoubleSubtractionWithNaN() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(new NaN());
        System.out.println("testDoubleSubtractionWithNaN");
        System.out.println(Arithmetic.sub(a, b));
        assertEquals(new DBDouble(new NaN()), Arithmetic.sub(a, b));
        assertEquals("NaN", Arithmetic.sub(a, b).toString());
    }

    @Test
    public void testDoubleSubtractionWithNOVALUE() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(new NOVALUE());
        System.out.println("testDoubleSubtractionWithNOVALUE");
        System.out.println(Arithmetic.sub(a, b));
        assertEquals(new DBDouble(132), Arithmetic.sub(a, b));
        assertEquals("132.0", Arithmetic.sub(a, b).toString());
    }

    @Test
    public void testDoubleSubtractionWithInteger() {
        DBDouble a = new DBDouble(132);
        DBInteger b = new DBInteger(2);
        System.out.println("testDoubleSubtractionWithInteger");
        System.out.println(Arithmetic.sub(a, b));
        assertEquals(new DBDouble(130), Arithmetic.sub(a, b));
        assertEquals("130.0", Arithmetic.sub(a, b).toString());
    }

    @Test
    public void testDoubleSubtractionWithString() {
        DBDouble a = new DBDouble(100);
        DBString b = new DBString("2");
        System.out.println("testDoubleSubtractionWithString");
        try {
            Arithmetic.sub(a, b);
        } catch (RuntimeException e) {
            System.out.println("Double cannot sub a String");
        }
    }

    @Test
    public void testStringMultiply() {
        DBString a = new DBString("AERD");
        DBString b = new DBString("AFHHH");
        System.out.println("testStringMultiply");
        try {
            Arithmetic.mul(a, b);
        } catch (RuntimeException e) {
            System.out.println("StringMultiply is not allowed");
        }

    }

    @Test
    public void testStringMultiplyWithNaN() {
        DBString a = new DBString("AERD");
        DBString b = new DBString(new NaN());
        try {
            Arithmetic.mul(a, b);
        } catch (RuntimeException e) {
            System.out.println("StringMultiply is not allowed");
        }
    }

    @Test
    public void testStringMultiplyWithNOVALUE() {
        DBString a = new DBString("AERD");
        DBString b = new DBString(new NOVALUE());
        System.out.println("testStringMultiplyWithNOVALUE");
        try {
            Arithmetic.mul(a, b);
        } catch (RuntimeException e) {
            System.out.println("StringMultiply is not allowed");
        }
    }

    @Test
    public void testIntegerMultiply() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(100);
        System.out.println("testIntegerMultiply");
        System.out.println(Arithmetic.mul(a, b));
        assertEquals(new DBInteger(10000), Arithmetic.mul(a, b));
        assertEquals("10000", Arithmetic.mul(a, b).toString());
    }

    @Test
    public void testIntegerMultiplyWithNOVALUE() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(new NOVALUE());
        System.out.println("testIntegerMultiplyWithNOVALUE");
        System.out.println(Arithmetic.mul(a, b));
        assertEquals(new DBInteger(0), Arithmetic.mul(a, b));
        assertEquals("0", Arithmetic.mul(a, b).toString());
    }

    @Test
    public void testIntegerMultiplyWithDouble() {
        DBInteger a = new DBInteger(100);
        DBDouble b = new DBDouble(1.0);
        System.out.println("testIntegerMultiplyWithDouble");
        System.out.println(Arithmetic.mul(a, b));
        assertEquals(new DBDouble(100), Arithmetic.mul(a, b));
        assertEquals("100.0", Arithmetic.mul(a, b).toString());
    }

    @Test
    public void testIntegerMultiplyWithNaN() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(new NaN());
        System.out.println("testIntegerMultiplyWithNaN");
        System.out.println(Arithmetic.mul(a, b));
        assertEquals(new DBInteger(new NaN()), Arithmetic.mul(a, b));
        assertEquals("NaN", Arithmetic.mul(a, b).toString());
    }

    @Test
    public void testIntegerMultiplyWithString() {
        DBInteger a = new DBInteger(100);
        DBString b = new DBString("2.0");
        System.out.println("testIntegerMultiplyWithString");
        try {
            Arithmetic.mul(a, b);
        } catch (RuntimeException e) {
            System.out.println("Integer cannot mul a String");
        }
    }

    @Test
    public void testDoubleMultiply() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(1.0);
        System.out.println("testDoubleMultiply");
        System.out.println(Arithmetic.mul(a, b));
        assertEquals(new DBDouble(132.0), Arithmetic.mul(a, b));
        assertEquals("132.0", Arithmetic.mul(a, b).toString());
    }

    @Test
    public void testDoubleMultiplyWithNaN() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(new NaN());
        System.out.println("testDoubleMultiplyWithNaN");
        System.out.println(Arithmetic.mul(a, b));
        assertEquals(new DBDouble(new NaN()), Arithmetic.mul(a, b));
        assertEquals("NaN", Arithmetic.mul(a, b).toString());
    }

    @Test
    public void testDoubleMultiplyWithNOVALUE() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(new NOVALUE());
        System.out.println("testDoubleMultiplyWithNOVALUE");
        System.out.println(Arithmetic.mul(a, b));
        assertEquals(new DBDouble(0), Arithmetic.mul(a, b));
        assertEquals("0.0", Arithmetic.mul(a, b).toString());
    }

    @Test
    public void testDoubleMultiplyWithInteger() {
        DBDouble a = new DBDouble(132);
        DBInteger b = new DBInteger(2);
        System.out.println("testDoubleMultiplyWithInteger");
        System.out.println(Arithmetic.mul(a, b));
        assertEquals(new DBDouble(264), Arithmetic.mul(a, b));
        assertEquals("264.0", Arithmetic.mul(a, b).toString());
    }

    @Test
    public void testDoubleMultiplyWithString() {
        DBDouble a = new DBDouble(100);
        DBString b = new DBString("2.0");
        System.out.println("testDoubleMultiplyWithString");
        try {
            Arithmetic.mul(a, b);
        } catch (RuntimeException e) {
            System.out.println("Double cannot mul a String");
        }
    }

    @Test
    public void testStringDivision() {
        DBString a = new DBString("AERD");
        DBString b = new DBString("AFHHH");
        System.out.println("testStringDivision");
        try {
            Arithmetic.mul(a, b);
        } catch (RuntimeException e) {
            System.out.println("StringDivision is not allowed");
        }

    }

    @Test
    public void testStringDivisionWithNaN() {
        DBString a = new DBString("AERD");
        DBString b = new DBString(new NaN());
        try {
            Arithmetic.mul(a, b);
        } catch (RuntimeException e) {
            System.out.println("StringDivision is not allowed");
        }
    }

    @Test
    public void testStringDivisionWithNOVALUE() {
        DBString a = new DBString("AERD");
        DBString b = new DBString(new NOVALUE());
        System.out.println("testStringDivisionWithNOVALUE");
        try {
            Arithmetic.mul(a, b);
        } catch (RuntimeException e) {
            System.out.println("StringDivision is not allowed");
        }

    }

    @Test
    public void testIntegerDivision() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(100);
        System.out.println("testIntegerDivision");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBInteger(1), Arithmetic.div(a, b));
        assertEquals("1", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testIntegerDivisionWithZero() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(0);
        System.out.println("testIntegerDivisionWithZero");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBInteger(new NaN()), Arithmetic.div(a, b));
        assertEquals("NaN", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testIntegerDivisionWithNOVALUE() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(new NOVALUE());
        System.out.println("testIntegerDivisionWithNOVALUE");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBInteger(new NaN()), Arithmetic.div(a, b));
        assertEquals("NaN", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testIntegerDivisionWithDouble() {
        DBInteger a = new DBInteger(100);
        DBDouble b = new DBDouble(1.0);
        System.out.println("testIntegerDivisionWithDouble");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBInteger(100), Arithmetic.div(a, b));
        assertEquals("100.0", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testIntegerDivisionWithDoubleZero() {
        DBInteger a = new DBInteger(100);
        DBDouble b = new DBDouble(0);
        System.out.println("testIntegerDivisionWithDoubleZero");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBInteger(new NaN()), Arithmetic.div(a, b));
        assertEquals("NaN", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testIntegerDivisionWithNaN() {
        DBInteger a = new DBInteger(100);
        DBInteger b = new DBInteger(new NaN());
        System.out.println("testIntegerDivisionWithNaN");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBInteger(new NaN()), Arithmetic.div(a, b));
        assertEquals("NaN", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testIntegerDivisionWithString() {
        DBInteger a = new DBInteger(100);
        DBString b = new DBString("0");
        System.out.println("testIntegerDivisionWithString");
        try {
            Arithmetic.div(a, b);
        } catch (RuntimeException e) {
            System.out.println("Integer cannot div a String");
        }
    }

    @Test
    public void testDoubleDivision() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(1.0);
        System.out.println("testDoubleDivision");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBDouble(132.0), Arithmetic.div(a, b));
        assertEquals("132.0", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testDoubleDivisionWithZero() {
        DBDouble a = new DBDouble(132);
        DBInteger b = new DBInteger(0);
        System.out.println("testDoubleDivision");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBDouble(new NaN()), Arithmetic.div(a, b));
        assertEquals("NaN", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testDoubleDivisionWithNaN() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(new NaN());
        System.out.println("testDoubleDivisionWithNaN");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBDouble(new NaN()), Arithmetic.div(a, b));
        assertEquals("NaN", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testDoubleDivisionWithNOVALUE() {
        DBDouble a = new DBDouble(132);
        DBDouble b = new DBDouble(new NOVALUE());
        System.out.println("testDoubleDivisionWithNOVALUE");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBDouble(new NaN()), Arithmetic.div(a, b));
        assertEquals("NaN", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testDoubleDivisionWithInteger() {
        DBDouble a = new DBDouble(132);
        DBInteger b = new DBInteger(2);
        System.out.println("testDoubleDivisionWithInteger");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBDouble(66), Arithmetic.div(a, b));
        assertEquals("66.0", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testDoubleDivisionWithIntegerZero() {
        DBDouble a = new DBDouble(132);
        DBInteger b = new DBInteger(0);
        System.out.println("testDoubleDivisionWithIntegerZero");
        System.out.println(Arithmetic.div(a, b));
        assertEquals(new DBDouble(new NaN()), Arithmetic.div(a, b));
        assertEquals("NaN", Arithmetic.div(a, b).toString());
    }

    @Test
    public void testDoubleDivisionWithString() {
        DBDouble a = new DBDouble(132);
        DBString b = new DBString("0");
        System.out.println("testDoubleDivisionWithString");
        try {
            Arithmetic.div(a, b);
        } catch (RuntimeException e) {
            System.out.println("Double cannot div a String");
        }
    }


}
package com.example.unittests;

import com.example.helloworld.HelloWorld;
import com.sun.javafx.binding.BindingHelperObserver;
import com.sun.javafx.runtime.SystemProperties;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Charles on 12/23/2015.
 */
public class HelloWorldTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testCalculateHiddenCubes() throws Exception {
        assertEquals(8, HelloWorld.CalculateHiddenCubes(4));
        assertEquals(0, HelloWorld.CalculateHiddenCubes(2));
        assertEquals(27, HelloWorld.CalculateHiddenCubes(5));
    }

    @org.junit.Test
    public void testValidateEan() throws Exception {
        assertTrue(HelloWorld.ValidateEan("9783815820865"));
        assertFalse(HelloWorld.ValidateEan("9783815820864"));
    }

    @org.junit.Test
    public void testHightAndLow() throws Exception{
        assertEquals("42 -9", HelloWorld.HighAndLow("8 3 -5 42 -1 0 0 -9 4 7 4 -4"));
    }

    @Test
    public void testTwoDecimalPlaces() throws Exception{
        assertEquals(12.23, HelloWorld.twoDecimalPlaces(12.2335435645), 0.00);
    }

    @Test
    public void testIsValid(){
        assertTrue(HelloWorld.isValid("i1"));
        assertTrue(HelloWorld.isValid("i"));
        assertFalse(HelloWorld.isValid("1i"));
        assertFalse(HelloWorld.isValid("i1bvadlkfjgh3%4t837yp8o"));
    }

    @Test
    public void testTriangular(){
        assertEquals(1, HelloWorld.Triangular(1));
        assertEquals(3, HelloWorld.Triangular(2));
        assertEquals(6, HelloWorld.Triangular(3));
    }

    @Test
    public void testeqSumPowDig(){
        assertEquals("[]", Arrays.toString(HelloWorld.eqSumPowDig(100, 2)));
        assertEquals("[153]", Arrays.toString(HelloWorld.eqSumPowDig(200, 3)));
        assertEquals("[153, 370]", Arrays.toString(HelloWorld.eqSumPowDig(370, 3)));
        assertEquals("[153, 370, 371]", Arrays.toString(HelloWorld.eqSumPowDig(400, 3)));
        assertEquals("[153, 370, 371, 407]", Arrays.toString(HelloWorld.eqSumPowDig(500, 3)));
    }

    @Test
    public void testReverse(){
        char[] source = {'a', 'b'};
        char[] result = {'b', 'a'};
        assertArrayEquals(result, HelloWorld.Reverse(source));
    }

    @Test
    public void testFunReverse(){
        assertEquals("201", HelloWorld.funReverse("012"));
        assertEquals("504132", HelloWorld.funReverse("012345"));
        assertEquals("9081726354", HelloWorld.funReverse("0123456789"));
        assertEquals("oHlel", HelloWorld.funReverse("Hello"));
        assertEquals("84rptpzsno51dvmdkjbc59fryj", HelloWorld.funReverse("4ppso1vdjc9rjyf5bkmd5nztr8"));
    }

    @Test
    public void testFunReverse2(){
        assertEquals("201", HelloWorld.funReverse2("012"));
        assertEquals("504132", HelloWorld.funReverse2("012345"));
        assertEquals("9081726354", HelloWorld.funReverse2("0123456789"));
        assertEquals("oHlel", HelloWorld.funReverse2("Hello"));
        assertEquals("84rptpzsno51dvmdkjbc59fryj", HelloWorld.funReverse2("4ppso1vdjc9rjyf5bkmd5nztr8"));
    }

    @Test
    public void testStockSummary(){
        String art[] = new String[]{"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"};
        String cd[] = new String[] {"A", "B", "X"};
        assertEquals("(A : 200) - (B : 1140) - (X : 0)",
                HelloWorld.stockSummary(art, cd));

        art = new String[0];
        cd = new String[]{"B","R","D", "X"};
        assertEquals("",
                HelloWorld.stockSummary(art, cd));
    }

    @Test
    public void testGetMiddle(){
        assertEquals("es", HelloWorld.getMiddle("test"));
        assertEquals("dd", HelloWorld.getMiddle("middle"));
        assertEquals("t", HelloWorld.getMiddle("testing"));
        assertEquals("A", HelloWorld.getMiddle("A"));
    }

    @Test
    public void testMorseCodeDecoder(){
        assertEquals("AAA AAAA", HelloWorld.Decode(".... . -.--   .--- ..- -.. ."));
        assertEquals("A", HelloWorld.Decode("   ."));
    }

    @Test
    public void testBitCleaner(){
        assertEquals("110011011", HelloWorld.CleanBits("0110011011000"));
        assertEquals("110011011", HelloWorld.CleanBits("110011011000"));
        assertEquals("110011011", HelloWorld.CleanBits("0110011011"));
        assertEquals("110011011", HelloWorld.CleanBits("110011011"));
    }

    @Test
    public void testCalcTimeInterval(){
        String bits = "1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011";
        assertEquals(2, HelloWorld.CalculateTimeInterval(bits));
    }

    @Test
    public void testDecodeBits(){
        String bits = "1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011";
        assertEquals(".... . -.--   .--- ..- -.. .", HelloWorld.decodeBits(bits));
        assertEquals(". .", HelloWorld.decodeBits("10001"));
        assertEquals(". .", HelloWorld.decodeBits("111000000000111"));
        assertEquals("..", HelloWorld.decodeBits("111000111"));
    }

    @Test
    public void testDigPow(){
        assertEquals(1L, HelloWorld.digPow(89, 1));
        assertEquals(-1L, HelloWorld.digPow(92, 1));
        assertEquals(2L, HelloWorld.digPow(695, 2));
        assertEquals(51L, HelloWorld.digPow(46288, 3));
    }

    @Test
    public void testRace(){
        Assert.assertArrayEquals(new int[]{0,32,18}, HelloWorld.Race(720, 850, 70));
        Assert.assertArrayEquals(new int[]{3,21,49}, HelloWorld.Race(80, 91, 37));
    }

    @Test
    public void testJosephusSurvivor(){
        assertEquals(4, HelloWorld.josephusSurvivor(7, 3));
        assertEquals(10, HelloWorld.josephusSurvivor(11, 19));
        assertEquals(28, HelloWorld.josephusSurvivor(40, 3));
        assertEquals(13, HelloWorld.josephusSurvivor(14, 2));
        assertEquals(100, HelloWorld.josephusSurvivor(100,1));
    }
}

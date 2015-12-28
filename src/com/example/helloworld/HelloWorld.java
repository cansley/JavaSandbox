package com.example.helloworld;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by Charles on 12/21/2015.
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        for(int x=1;x<10;x++){
            System.out.println(String.format("%d: %d - %d", x, (int)Math.pow(x, 3), CalculateHiddenCubes(x)));
        }

        System.out.println(ValidateEan("9783815820865"));

    }

    public static String getMiddle(String word){
        int startIdx = (int)Math.ceil(word.length()/2.0) - 1;
        int endIdx = (word.length() % 2 == 0) ? startIdx+2 : startIdx+1;
        return word.substring(startIdx, endIdx);
    }

    public static int CalculateHiddenCubes(int cubeSize){
        int val =(int)Math.pow(cubeSize-2, 3);
        return (val>0)? val: 0;
    }

    public static boolean ValidateEan(String eanCode){
        if(eanCode.length() != 13) return false;
        String[] chars = eanCode.split("");
        int sum =0;
        int multiplier = 0;
        try {
            for (int x = 0; x < 12; x++) {
                if(x%2==0){
                    multiplier = 1;
                } else{
                    multiplier = 3;
                }
                int val = Integer.valueOf(chars[x]) * multiplier;
                sum += val;
            }
        } catch (Exception ex){
            return false; // one of the chars must not be a numeric.
        }

        int checksum = (sum % 10 == 0) ? 0 : 10 - (sum % 10);
        return checksum == Integer.valueOf(chars[12]);
    }

    public static String HighAndLow(String numbers){
        List<Integer> val = Arrays.asList(numbers.split(" ")).stream().map(i -> Integer.parseInt(i)).sorted().collect(Collectors.toList());
        return String.format("%d %d", val.get(val.size()-1), val.get(0));
    }

    public static Double twoDecimalPlaces(double number){
        return new BigDecimal(number).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
    }

    public static boolean isValid(String idn){
        boolean val =idn.matches("^[a-zA-Z_$]{1}[a-zA-z0-9_$]*$");
        return val;
    }

    public static int Triangular(int n){
        return (n <= 0) ? 0 : n * (n + 1) / 2;
    }

    public static long[] eqSumPowDig(long hmax, int exp){
        return LongStream.rangeClosed(2, hmax).filter(i->meetsCriteria(i, exp)).toArray();
    }

    private static boolean meetsCriteria(long value, int exp){
        ArrayList<Long> valAsArray = new ArrayList<>();
        long workingVal = value;
        do {
            valAsArray.add(workingVal % 10);
            workingVal /=10;
        } while(workingVal > 0);
        long val = valAsArray.stream().reduce(0l, (a,b)-> a + (long)Math.pow(b, exp));

        return val == value;
    }

    public static String funReverse(String s){
        char[] retVal = new char[s.length()];
        char[] workingArray  = s.toCharArray();
        for(int x=0;x<retVal.length;x++){
            retVal[x] = Reverse(workingArray)[0];
            workingArray = Arrays.copyOfRange(workingArray, 1, workingArray.length);
        }

        return new String(retVal);
    }

    public static char[] Reverse(char[] array){
        int midPoint = array.length / 2;
        char holder;

        for(int x = 0;x <midPoint;x++){
            holder = array[array.length-1-x];
            array[array.length-1-x] = array[x];
            array[x] = holder;
        }

        return array;
    }

    public static String funReverse2(String s){
        return reverseString(s, s.length());
    }

    public static String reverseString(String string, int count){
        String s = new StringBuilder(string).reverse().toString();
        return count == 1 ? s : s.substring(0, 1) + reverseString(s.substring(1), --count);
    }

    public static String stockSummary(String[] lstOfArt, String[] lstOf1stLetter){
        if(lstOfArt == null || lstOfArt.length == 0) return "";
        if(lstOf1stLetter == null || lstOf1stLetter.length == 0) return "";
        String[] counts = new String[lstOf1stLetter.length];

        for(int x=0;x<counts.length;x++){
            String letter = lstOf1stLetter[x];
            int count = Arrays.asList(lstOfArt).stream().map(w-> (!w.startsWith(letter)) ? 0 : Integer.parseInt(w.split(" ")[1])).reduce(0, (a,b)-> a+b);
            counts[x] = String.format("(%s : %d)", letter, count);
        }

        return String.join(" - ", counts);
    }
}

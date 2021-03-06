package com.example.helloworld;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by Charles on 12/21/2015.
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println(String.format("Hello, World!"));

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

    public static String Decode(String morseCode){
        return String.join(" ", Arrays.asList(morseCode.split("   ")).stream().map(w->decodeWord(w)).filter(w-> w.length() > 0).collect(Collectors.toList()));
    }

    private static String decodeWord(String word){
        return (word.length() == 0) ? "" :String.join("", Arrays.asList(word.split(" ")).stream().map(l->CheckNull(MorseCode.get(l))).collect(Collectors.toList()));
    }

    private static String CheckNull(String value){
        if(value == null) return "";
        return value;
    }

    public static String CleanBits(String bits){
        //return bits.substring(bits.indexOf("1", 0), bits.lastIndexOf("1")+1);
        return bits.replaceAll("^0+|0+$", "");
    }

    /*
        Calulates the time interval size give the following rules
        sub pause : character pause : word pause is
        1 : 3 : 7
        For the input string group by zeroes, get min mid max, figure out how many units is a time unit.
     */
    public static int CalculateTimeInterval(String bits) {

        // Old code, left for reference...
        /*
        String[] digits = CleanBits(bits).split("");
        TreeSet<Integer> spaceSizes = new TreeSet<>();
        int findCount = 0;
        String lastDigit = "";
        int firstSetOnesCount = 0;
        boolean foundFirstZero = false;
        for(int x=0;x<digits.length;x++){
            if(digits[x].equals("0") && lastDigit.equals("1")){
                findCount = 1;
                foundFirstZero = true;
            } else if (digits[x].equals("0") && lastDigit.equals("0")){
                findCount++;
            } else if(digits[x].equals("1") && lastDigit.equals("0")){
                if(!spaceSizes.contains(findCount))
                {
                    spaceSizes.add(findCount);
                }
                findCount = 0;
            } else if(!foundFirstZero && digits[x].equals("1")) {
                firstSetOnesCount++;
            }

            lastDigit = digits[x];
        }
        if(findCount > 0 && !spaceSizes.contains(findCount))
        {
            spaceSizes.add(findCount);
        }

        Integer[] sortedSet = new Integer[spaceSizes.size()];
        spaceSizes.toArray(sortedSet);
        int retVal = -1;
        switch (sortedSet.length){
            case 3:
                retVal= sortedSet[0];
                break;
            case 2:
                float ratio = (float)sortedSet[1]/sortedSet[0];
                if(ratio == 3.0) {
                    retVal= sortedSet[0];
                } else if(ratio == 2.5){
                    retVal= (int)(sortedSet[0]/3);
                }else{
                    retVal = -1;
                }
                break;
            case 1:
                if(sortedSet[0]/firstSetOnesCount == 1){
                    retVal = firstSetOnesCount;
                } else if(sortedSet[0]/3 == 1){
                    retVal = 1;
                } else if (sortedSet[0] / firstSetOnesCount == 2.5){
                    retVal = firstSetOnesCount;
                } else if (sortedSet[0] / firstSetOnesCount == 3) {
                    retVal = firstSetOnesCount;
                } else
                {
                    retVal = sortedSet[0];
                }
                break;
            case 0:
                retVal = bits.length();
                break;
            default:
                retVal = -1;
        }
        */
        bits = CleanBits(bits); // just in case...
        int retVal = Integer.MAX_VALUE;
        Matcher matcher = Pattern.compile("1+|0+").matcher(bits);
        while(matcher.find()){
            retVal = Math.min(retVal, matcher.group().length());
        }
        return retVal;
    }

    public static String decodeBits(String bits){
        bits = CleanBits(bits);
        int timeInterval = CalculateTimeInterval(bits);
        bits = bits.replaceAll("0{" + timeInterval * 7 + "}", "   ");
        bits = bits.replaceAll("0{" + timeInterval * 3 + "}", " ");
        bits = bits.replaceAll("1{" + timeInterval * 3 + "}0?", "-");
        bits = bits.replaceAll("1{" + timeInterval + "}0?", ".");
        bits = bits.replaceAll("0", "");

        return bits;
    }

    public static long digPow(int n, int p){
        String val = String.valueOf(n);
        int runningTot = 0;
        for(int x=0;x<val.length();x++){
            runningTot += Math.pow(Integer.decode(val.substring(x, x+1)),(p+x));
        }

        int result = runningTot / n;
        if(result * n == runningTot) return result;

        return (runningTot % n == 0) ? runningTot / n : -1L;
    }

    public static int[] Race(int v1, int v2, int g){
        if (v1 > v2) return null;

        long seconds = (long)Math.floor((double)g/(v2-v1)*3600);

        int[] retVal = new int[3];
        LocalTime tim = LocalTime.ofSecondOfDay(seconds);
        retVal[0] = tim.getHour();
        retVal[1] = tim.getMinute();
        retVal[2] = tim.getSecond();
        return retVal;
    }

    // where n = number of people in circle, k = target interval.
    public static int josephusSurvivor(final int n, final int k){
        return (n>0) ? (josephusSurvivor(n - 1, k) + k-1) % n + 1 : 0;
    }

    public static <T> List<T> josephusSurvivor(final List<T> items, final int k){
        List<T> permutation = new ArrayList<>();
        int position = 0;
        while(items.size()>0){
            position = (position + k -1) % items.size();
            permutation.add(items.remove(position));
        }
        return permutation;
    }

    // leaving this one for posterity
    private static <T> List<T> josephusSurvivor(final List<T> n, final int k, final int s){
        if(n.size() == 1) return n;
        int idx = (k+s-2 < n.size()) ? k+s-2 : (k+s-2) % n.size();
        idx = (idx == n.size()) ? 0: idx;
        List<T> res = new ArrayList<>();
        List<T> newList = new ArrayList<>(n);
        res.add(n.get(idx));
        newList.remove(idx);
        res.addAll(josephusSurvivor(newList, k, (idx+1 == n.size()) ? 1 : idx+1));
        return res;
    }

    private static int[] combineArrays(final int[]a, final int[]b){
        int[] retVal = new int[a.length+b.length];
        System.arraycopy(a, 0, retVal, 0, a.length);
        System.arraycopy(b, 0, retVal, a.length, b.length);
        return retVal;
    }

    /**
     Return value: List of all prime factors of a given number n
     */
    public static Long[] getAllPrimeFactors(long n) {
        if (n==0) return new Long[] {};
        if(n==1) return new Long[] {1l};
        List<Long> factors = new ArrayList<>();
        for(long i=2;i<=n;i++){
            while (n % i == 0){
                factors.add(i);
                n /= i;
            }
        }
        Long[] retVal = new Long[factors.size()];
        factors.toArray(retVal);
        return retVal;
    }

    /**
     Return value: List containing two lists, first containg all prime factors without duplicates,
     second containing the count, how often each prime factor occured.
     Return code always contains two lists.

     e.g.: getUniquePrimeFactorsWithCount(100) = {{2,5},{2,2}) // prime 2 occurs 2 times, prime 2 occurs 5 times,
     */
    public static Long[][] getUniquePrimeFactorsWithCount(long n) {
        if(n == 0) return new Long[][] {{},{}};
        if(n == 1) return new Long[][] {{1l},{1l}};
        Long[] factors = getAllPrimeFactors(n);
        LinkedHashMap<Long, Long> mitCounts = new LinkedHashMap<>();
        for(int x=0;x<factors.length;x++){
            if(mitCounts.containsKey(factors[x])){
                mitCounts.put(factors[x], mitCounts.get(factors[x])+1l);
            } else {
                mitCounts.put(factors[x], 1l);
            }
        }

        return new Long[][]{mitCounts.keySet().toArray(new Long[mitCounts.size()]),
                mitCounts.values().toArray(new Long[mitCounts.size()])};
    }
    /**
     Return value: List containing product of same prime factors,
     e.g.: 45 = 3*3*5 ==>  {3^2,5^1} == {9,5}
     e.g.: getUniquePrimeFactorsWithCount(100) = {{2,5},{2,2}) // prime 2 occurs 2 times, prime 2 occurs 5 times,
     */
    public static Long[] getPrimeFactorPotencies(long n) {
        if (n==0) return new Long[] {};
        if(n==1) return new Long[] {1l};

        Long[][] uFactors = getUniquePrimeFactorsWithCount(n);
        Long[] potencies = new Long[uFactors.length];
        for(int x = 0; x<uFactors.length;x++){
            potencies[x] = (long)Math.pow(uFactors[0][x],uFactors[1][x]);
        }
        return potencies;
    }
}



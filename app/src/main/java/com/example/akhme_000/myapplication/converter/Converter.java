package com.example.akhme_000.myapplication.converter;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.MathContext;

public class Converter {

    final private static boolean debug;
    final private static NumeralSystem[] numeralSystem;
    final private static MathContext mathContext;
    final private static int divisionPrecision;
    
    static {
        debug = true;
        numeralSystem = new NumeralSystem[37];
        for (int i = 0; i < 37; i++) numeralSystem[i] = new NumeralSystem(i);
        mathContext = MathContext.DECIMAL128;
        divisionPrecision = 20;
    }
    
//    /*
//    * @param base - binary or hex number
//    * Only 16 bit fixed point is supported
//    */
//    public static String fixedPointToDouble(String input, int wordSize, int fractionSize, int base) {
//
//        String binary = base == 0 ? input : hexToBin(input);
//        binary = padCharacters(wordSize, binary.length(), binary.charAt(0)) + binary;
//        boolean isNegative = binary.startsWith("1");
//        if (isNegative) binary = addOne(invertBinaryString(binary));
//
//        // common sense checking
//        if (wordSize < 2 || fractionSize < 0 || base < 0 || base > 36) return "Can't be a fixed point number";
//
//        // Check for overflow
//        int integerSize = Math.max(0, wordSize - 1 - fractionSize);
//        BigDecimal upperLimit;
//        if (integerSize > 0)
//            upperLimit = new BigDecimal(power("2", integerSize));
//        else  {
//            BigDecimal currentPower = new BigDecimal("1").divide(new BigDecimal(power("2", fractionSize - wordSize + 2)));
//            upperLimit = currentPower;
//            for (int i = 1; i < wordSize - 1; i++) {
//                currentPower = currentPower.multiply(new BigDecimal("0.5"));
//                upperLimit = upperLimit.add(currentPower);
//            }
//
//        }
//        BigDecimal precision = new BigDecimal("1").divide(new BigDecimal(power("2", fractionSize), mathContext));
//        BigDecimal lowerLimit = upperLimit.add(precision).negate(mathContext);
//
////        p("Upper limit: " + upperLimit.toString());
////        p("Lower limit: " + lowerLimit.toString());
////        p("Precision: " + precision.toString());
//
////        p("inputNumber: " + inputNumber.toString());
//
//        if(input.matches("^0*$")) {
//            return "0.0";
//        }
//        else if(input.matches("^80*$")) {
//            return (lowerLimit.toString());
//        }
//        else if(input.matches("^7[Ff]*$"))   {
//            return (upperLimit.toString());
//        }
//
//        BigDecimal result = new BigDecimal("0");
//        BigDecimal currentPower;
//        if (integerSize > 0) {
//            currentPower = new BigDecimal(power("2", wordSize - fractionSize - 1 ));
//            for (int i = 1; i < wordSize - fractionSize; i++) {
//                currentPower = currentPower.multiply(new BigDecimal("0.5"));
//                result = result.add(currentPower.multiply(new BigDecimal(binary.charAt(i) + ".0")));
//            }
//
//            for (int i = wordSize - fractionSize; i < wordSize - 1; i++) {
//                currentPower = currentPower.multiply(new BigDecimal("0.5"));
//                result = result.add(currentPower.multiply(new BigDecimal(binary.charAt(i) + ".0")));
//            }
//        }
//        else  {
//            currentPower = new BigDecimal("1").divide(new BigDecimal(power("2", fractionSize - wordSize + 1)));
//            for (int i = 1; i < wordSize - 1; i++) {
//                currentPower = currentPower.multiply(new BigDecimal("0.5"));
//                result = result.add(currentPower.multiply(new BigDecimal(binary.charAt(i) + ".0")));
//            }
//        }
//
//        if (result.toString().length() > 20) result = new BigDecimal(result.toString().substring(0,10));
//
//        //return new BigDecimal(signedNumToDec(input, base)).divide(new BigDecimal(2).pow(n), mathContext).toString();
//        return (isNegative ? "-" : "" ) + result.toString();
//    }
   
//    public static String signedBinaryToDec(String binary) {
//        if (binary.charAt(0) == '1')
//            return "-" + new BigInteger(anyToAny(binary.replace('1', '.').replace('0', '1').replace('.', '0'), 2, 10)).add(new BigInteger("1"));
//        else return anyToAny(binary, 2, 10);
//    }
//
//    // base b is two's complement. h is two's complement in hex
//    public static String signedNumToDec(String input, int base) {
//        if (base == 0) return signedBinaryToDec(input);
//        else {
//            String signBit = anyToAny(Character.toString(input.charAt(0)), 16, 2);
//            String binary = anyToAny(input, 16, 2);
//            return signedBinaryToDec(binary.length() < input.length() * 4
//                    ? new String(new char[input.length() * 4 - binary.length()]).replace("\0", signBit) + binary
//                    : binary);
//        }
//    }
    
//    private static String padCharacters(int requiredLength, int currentLength, char characterToPad){
//        return currentLength < requiredLength
//                ? new String(new char[requiredLength - currentLength]).replace("\0", Character.toString(characterToPad)) : "";
//    }
//
//    private static String add(String a, String b) {
//        return new BigInteger(a).add(new BigInteger(b)).toString();
//    }
//    private static String multiply(String a, String b) {
//        return new BigInteger(a).multiply(new BigInteger(b)).toString();
//    }
//    private static String power(String a, int b) {
//        return new BigDecimal(a).pow(b, mathContext).toString();
//    }
//    private static String not(String a) {
//        return new BigInteger(a).not().toString();
//    }
//
//    public static String invertBinaryString(String input) {
//        return input.replace('1', '.').replace('0', '1').replace('.', '0');
//    }
//
//    public static String addOne(String input) {
//
//
//        if (input.charAt(input.length()-1) == '0')
//            return input.substring(0, input.length() - 1) + "1";
//
//        StringBuilder buffer = new StringBuilder(input);
//        for (int i = buffer.length() - 1; i > -1; i--) {
//            if (buffer.charAt(i) == '1')
//                buffer.setCharAt(i, '0');
//            else {
//                buffer.setCharAt(i, '1');
//                return new String(buffer);
//            }
//        }
//        return new String(buffer);
//    }
//
//    // 5E-5 -> 0.00005
//    public static String scientificToDouble(String input) {
//
//        boolean isNegative = input.startsWith("-");
//        if (isNegative) input = input.substring(1);
//
//        int dotPosition = input.indexOf('.');
//        if (dotPosition != -1) input = input.substring(0, dotPosition) + input.substring(dotPosition+1);
//
//        int ePosition = input.indexOf('E');
//        int number_of_zeros = Integer.parseInt(input.substring(ePosition + 2));
//
//        return (isNegative ? "-" : "") + "0." + padCharacters(number_of_zeros, 1, '0') + input.substring(0, ePosition);
//    }
//
//    public static String logicalShiftRight(String input, int shift_amount) {
//        for (int i = 0; i < shift_amount; i++) {
//            input = "0" + input.substring(0, input.length() - 1);
//        }
//        return input;
//    }
//
//    public static String logicalShiftLeft(String input, int shift_amount) {
//        for (int i = 0; i < shift_amount; i++) {
//            input = input.substring(1) + "0";
//        }
//        return input;
//    }
    
//    public static String binToHex(String input) {
//        if (input.length() % 4 != 0) return "This binary number cannot be converted to hex";
//        String result = "";
//        for (int i = 0; i < input.length() / 4; i++) {
//            result += anyToAny(input.substring(4*i, 4*i+4), 2, 16);
//        }
//        return result;
//    }
//    public static String hexToBin(String input) {
//        String result = "";
//        for (int i = 0; i < input.length(); i++) {
//            String currentHex = anyToAny(Character.toString(input.charAt(i)), 16, 2);
//            currentHex = padCharacters(4, currentHex.length(), '0') + currentHex;
//            result += currentHex;
//        }
//        return result;
//    }
    
//    public static String doubleToFixedPoint(String input, int wordSize, int fractionSize, int base) {
//
//        // common sense checking
//        if (wordSize < 2 || fractionSize < 0 || base < 0 || base > 36) return "Can't be a fixed point number";
//
//        // Check for overflow
//        int integerSize = Math.max(0, wordSize - 1 - fractionSize);
//        BigDecimal upperLimit;
//        if (integerSize > 0)
//            upperLimit = new BigDecimal(power("2", integerSize));
//        else  {
//            BigDecimal currentPower = new BigDecimal("1").divide(new BigDecimal(power("2", fractionSize - wordSize + 2)));
//            upperLimit = currentPower;
//            for (int i = 1; i < wordSize - 1; i++) {
//                currentPower = currentPower.multiply(new BigDecimal("0.5"));
//                upperLimit = upperLimit.add(currentPower);
//            }
//
//        }
//        BigDecimal precision = new BigDecimal("1").divide(new BigDecimal(power("2", fractionSize), mathContext));
//        BigDecimal lowerLimit = upperLimit.add(precision).negate(mathContext);
//
////        p("Upper limit: " + upperLimit.toString());
////        p("Lower limit: " + lowerLimit.toString());
////        p("Precision: " + precision.toString());
//
//        String result;
//        BigDecimal inputNumber = new BigDecimal(input);
////        p("inputNumber: " + inputNumber.toString());
//
//        if(inputNumber.abs().compareTo(precision) < 0) {
////            p("Smaller than precision");
//            result = padCharacters((wordSize / 4), 0, '0');
//            return (base == 0) ? hexToBin(result) : result;
//        }
//        if(inputNumber.compareTo(lowerLimit) <= 0) {
////            p("Smaller than lowerLimit");
//            result = "8" + padCharacters((wordSize / 4) - 1, 0, '0');
//            return (base == 0) ? hexToBin(result) : result;
//        }
//        if(inputNumber.compareTo(upperLimit) > 0)   {
////            p("Smaller than upperLimit");
//            result = "7" + padCharacters((wordSize / 4) - 1, 0, 'F');
//            return (base == 0) ? hexToBin(result) : result;
//        }
//
//        Number number = new Number(anyToAny(input, 10, 2), 2);
//
////        p("integerPart: " + number.integerPart);
////        p("fractionalPart: " + number.fractionalPart);
//
//        String integerPart = number.getIntegralPart();
//        if (integerSize == 0) integerPart = "";
//        else if (integerPart.length() < integerSize)
//            integerPart = padCharacters(integerSize, integerPart.length() , '0') + integerPart;
//
//        String fractionalPart = number.getFractionalPart();
//        int fractionalSize = Math.max(0, fractionSize);
////        p("fractionalSize: " + fractionalSize);
////        p("wordSize: " + wordSize);
//        int shift = fractionalSize - wordSize + ((number.getIsNegative()) ? 0 : 1);
////        p("shift: " +  shift);
//        if (fractionalSize == 0)
//            fractionalPart = "";
//        else {
//            if (shift > 0)
//                fractionalPart = logicalShiftLeft(fractionalPart, shift);
//
//            if (fractionalPart.length() < fractionalSize)
//                fractionalPart = fractionalPart + padCharacters(fractionalSize, fractionalPart.length() , '0');
//            else if (fractionalPart.length() > fractionalSize)
//                fractionalPart = fractionalPart.substring(0, fractionalSize);
//        }
//
//        if (number.getIsNegative()) {
////            p(integerPart + fractionalPart);
////            p(invertBinaryString(integerPart + fractionalPart));;
//            result = addOne(invertBinaryString(integerPart + fractionalPart));
////            p(result);
//            result = padCharacters(wordSize, result.length(), '1') + result;
//       }
//        else {
//            result = "0" + integerPart + fractionalPart;
//            //result = padCharacters(wordSize, result.length(), '0') + result;
//        }
////        p(result);
//        result = result.substring(0, wordSize);
//        return (base == 0) ? result : binToHex(result);
//
//
//
//
////        int integerSize = Math.max(0, wordSize - 1 - fractionSize);
////        BigDecimal upperLimit;
////        BigDecimal precision = new BigDecimal("0");
////        if (integerSize > 0)
////            upperLimit = new BigDecimal(power("2", integerSize));
////        else  {
////            BigDecimal currentPower = new BigDecimal("1").divide(new BigDecimal(power("2", fractionSize - wordSize + 2)));
////            upperLimit = currentPower;
////            for (int i = 1; i < wordSize - 1; i++) {
////                currentPower = currentPower.multiply(new BigDecimal("0.5"));
////                upperLimit = upperLimit.add(currentPower);
////            }
////            precision = currentPower;
////        }
////        BigDecimal lowerLimit = upperLimit.add(precision).negate(mathContext);
////
////        if (debug == true) {
////            p("Upper limit: " + upperLimit.toString());
////            p("Lower limit: " + lowerLimit.toString());
////            p("Precision: " + precision.toString());
////        }
////
////
////        BigDecimal inputNumber = new BigDecimal(input);
////        if(inputNumber.compareTo(lowerLimit) <= 0) return "8" + padCharacters((wordSize / 4) - 1, 0, '0');
////        if(inputNumber.compareTo(upperLimit) > 0)  return "7" + padCharacters((wordSize / 4) - 1, 0, 'F');
////
////        String integerPart;
////        String fractionalPart;
////        if (debug) {
////            p("integerSize: " + integerSize);
////            p("number.integerPart.length(): " + number.integerPart.length());
////        }
////        // If integerSize is smaller than size of integerPart then cut the extra bits off
////        integerPart = integerSize <= number.integerPart.length()
////                ? number.integerPart.substring(number.integerPart.length() - integerSize, number.integerPart.length())
////                : number.integerPart;
////        if (debug) {
////            p("integerPart: " + integerPart);
////        }
////        // If integerSize is bigger than size of integerPart, pad more bits
////        integerPart = integerSize > 0 ? padCharacters(integerSize, integerPart.length(), '0') + integerPart : "";
////        if (debug) {
////            p("integerPart: " + integerPart);
////            p("fractionSize: " + fractionSize);
////            p("number.fractionalPart.length(): " + number.fractionalPart.length());
////        }
////        // If fractionSize is smaller than size of fractionalPart then cut the extra bits off
////        fractionalPart = fractionSize > number.fractionalPart.length()
////                ? number.fractionalPart
////                : number.fractionalPart.substring(0, fractionSize);
////        // If fractionSize is bigger than size of fractionalPart, pad more bits
////        fractionalPart = fractionalPart + padCharacters(Math.min(fractionSize, wordSize - 1), number.fractionalPart.length(), '0');
////        if (debug) p("fractionalPart: " + fractionalPart);
////
////        String binary = "0" + integerPart + fractionalPart;
////        if (debug) p("Binary: " + binary);
////
////        String result = decToSignedNum((number.isNegative ? "-" : "") + anyToAny(binary, 2, 10), wordSize, base);
//
////        return result;
//    }
    
   
//    public static String decToSignedBinary(String inputString, int wordSize) {
//
//        Number number = new Number(inputString, 10);
//
//        String result;
//        char signBit = number.getIsNegative() ? '1' : '0';
//        result = signBit + ((number.getIsNegative())
//                ? invertBinaryString(anyToAny(add(number.getString().substring(1), "-1"), 10, 2))
//                : anyToAny(number.getString(), 10, 2));
//
//        //p(result);
//        return result.length() < wordSize
//                ? padCharacters(wordSize, result.length(), signBit) + result
//                : result;
//    }
    
//    // base b is two's complement. h is two's complement in hex
//    public static String decToSignedNum(String input, int wordSize, int base) {
//
//        if (base == 0) return decToSignedBinary(input, wordSize);
//
//
//        if (input.equals("-0")) return "0";
//        String result = anyToAny(decToSignedBinary(input, wordSize), 2, 16);
//        result = padCharacters(wordSize / 4, result.length(), '0') + result;
//        return result;
//    }

    
    
    public static Number anyToDec(Number number, int base) {
        return new Number(number.getIsNegative(), IntegralPart.anyToDec(number.getIntegralPart(), base), 
            FractionalPart.anyToDec(number.getFractionalPart(), base, mathContext), number.getDotPosition());
    }

    public static Number decToAny(Number number, int base) {
        return new Number(number.getIsNegative(), IntegralPart.decToAny(number.getIntegralPart(), base), 
            FractionalPart.decToAny(number.getFractionalPart(), base, divisionPrecision), number.getDotPosition());
    }
    
    public static Number anyToAny(String input, int inputBase, int outputBase) {
        
        Number number = new Number(input, inputBase);

        if (number.hasError()) return number;

        if (outputBase == inputBase) return number;
        else if (outputBase == 10)   return anyToDec(number, inputBase);
        else if (inputBase  == 10)   return decToAny(number, outputBase);
        return decToAny(anyToDec(number, inputBase), outputBase);
    }

    public static void main(String argv[]) {
        System.out.println(Converter.anyToAny("01010", 2, 10));
    }
}

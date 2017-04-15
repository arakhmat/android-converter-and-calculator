package com.example.akhme_000.myapplication.converter;

import java.math.BigInteger;

public class IntegralPart {
 
    public static String decToAny(String input, int baseAsInt) {
        
        StringBuilder output = new StringBuilder("");
        
        BigInteger quotient = new BigInteger(input), remainder;
        BigInteger base = new BigInteger(baseAsInt + "");
        
        do {
            remainder = quotient.mod(base);
            output.append(HelperFunctions.intToChar(Integer.parseInt(remainder.toString())));
            quotient = quotient.divide(base);
        }
        while (!quotient.equals(new BigInteger("0")));
        
        return output.reverse().toString();
    }
    
    public static String anyToDec(String input, int base) {
        
        input = new StringBuilder(input).reverse().toString();
        
        BigInteger integer = new BigInteger("0");
        for (int i = 0; i < input.length(); i++){
            BigInteger currentPower = new BigInteger(base + "").pow(i);
            integer = integer.add(new BigInteger(HelperFunctions.charToInt(input.charAt(i)) + "").multiply(currentPower));
        }
        
        return integer.toString();
    }
}
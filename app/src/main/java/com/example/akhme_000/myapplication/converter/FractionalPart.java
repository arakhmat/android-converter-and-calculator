package com.example.akhme_000.myapplication.converter;

import java.math.BigDecimal;
import java.math.MathContext;

public class FractionalPart {
    
    public static String decToAny(String input, int baseAsInt, int divisionPrecision) {
    
        String output = "";    
    
        BigDecimal base = new BigDecimal(baseAsInt + "");
     
        BigDecimal product = new BigDecimal("0." + input);

//        for (int i = 0; i < divisionPrecision && !productString.matches("[0\\.]*"); i++) {
        for (int i = 0; i < divisionPrecision; i++) {
            product = product.multiply(base);
            String productString = product.toPlainString();
            output += HelperFunctions.intToChar(Integer.parseInt(productString.substring(0, productString.indexOf("."))));
            product = new BigDecimal("0." + productString.substring(productString.indexOf(".") + 1));
        }
        if (output.equals("")) output = "0";
        
        return output;
    }
    
    public static String anyToDec(String input, int base, MathContext mathContext) {
        
        BigDecimal fraction = new BigDecimal("0.0");
        
        BigDecimal one = new BigDecimal("1");
        BigDecimal currentPowerDec;
        for (int i = 0; i < input.length(); i++){
            
            BigDecimal base_to_power = new BigDecimal(base + "").pow(i + 1);
            try {
                currentPowerDec = one.divide(base_to_power, mathContext);
            } catch (ArithmeticException e){
                currentPowerDec = one.divide(base_to_power, MathContext.DECIMAL128);
            };
            fraction = fraction.add(new BigDecimal(HelperFunctions.charToInt(input.charAt(i)) + "").multiply(currentPowerDec));
        }

        return fraction.stripTrailingZeros().toString().substring(1);
    }
    
}

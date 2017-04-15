package com.example.akhme_000.myapplication.converter;

import java.math.MathContext;
/*
*  Number is used to simplify the data flow between functions.
*  It is also used for checking the inputs for correctness and breaking them down into components
*/
public class Number {

    final private static NumeralSystem[] numeralSystem;
    final private static String errorMessagePattern;
    final private static MathContext mathContext;
    final private static int divisionPrecision;

    private String string;
    private boolean isNegative;
    private int dotPosition;        
    private String integerPart;
    private String fractionalPart;
    private boolean hasError;
    private boolean isInteger;
    
    static {
        numeralSystem = new NumeralSystem[37];
        for (int i = 0; i < 37; i++) numeralSystem[i] = new NumeralSystem(i);
        errorMessagePattern= "^(Not a base [0-9]+ number)$";
        mathContext = MathContext.UNLIMITED;
        divisionPrecision = 100;
    }
    
    
    Number (String input, int base) {
        string = input;
        hasError = false;

        // pass the error through
        if (input.matches(errorMessagePattern)) {
            string = input;
            hasError = true;
            return;
        }

        // check for an error
        if (!input.matches(numeralSystem[base].getAllowedFormat())) {
            string = numeralSystem[base].getErrorMessage();
            hasError = true;
            return;
        }

        isNegative = input.contains("-");
        dotPosition = input.indexOf(".");

        fractionalPart = "0";
        if (dotPosition == -1) {
            isInteger = true;
            integerPart = isNegative ? input.substring(1) : input;
        }
        else {
            isInteger = false;
            integerPart = isNegative ? input.substring(1, dotPosition) : input.substring(0, dotPosition);
            if (dotPosition != input.length() - 1)
                fractionalPart = input.substring(dotPosition + 1);
        }
        buildString();
    }

    Number (boolean isNegative, String integerPart, String fractionalPart, int dotPosition) {
        this.isNegative = isNegative;
        this.integerPart = integerPart;
        this.fractionalPart = fractionalPart;
        if (isInteger)
            this.fractionalPart.equals("0");
        buildString();
    }
    
    private void buildString() {
        string = (isNegative ? "-" : "") + integerPart +(isInteger ? "" : "." + fractionalPart);
    }
    
    public boolean getIsNegative() {
        return isNegative;
    }
    
    public int getDotPosition() {
        return dotPosition;
    }
    
    public String getIntegralPart() {
        return integerPart;
    }
    
    public String getFractionalPart() {
        return fractionalPart;
    }

    public boolean hasError() { return hasError; }
    
    @Override
    public String toString() {
        return string;
    }
}
package com.example.akhme_000.myapplication.converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;

public class Calculator {
    
    
    private Converter converter;
    
    private final static HashMap<String, Integer> precedence;
    private final static HashMap<String, Boolean> associativity;
    static String operatorSymbols;
    
    static {
        precedence = new HashMap();
        associativity = new HashMap();
        operatorSymbols = "[\\(\\)\\+\\-\\*/^]";
        
        precedence.put("",  -1);
        precedence.put("+", 0);
        precedence.put("-", 0);
        precedence.put("*", 1);
        precedence.put("/", 1);
        precedence.put("^", 2);
        
        associativity.put("+", true);
        associativity.put("-", true);
        associativity.put("*", true);
        associativity.put("/", true);
        associativity.put("^", false);
    }
    
    
    Calculator() {
        converter = new Converter();
    }
    
    public static void printArrayList(ArrayList<String> list) {
        for (String s : list)
            System.out.print(s + " ");
        System.out.println();
    }
    
    private static void updateStackAndQueue(String element,
            ArrayList<String> outputQueue, ArrayList<String> operatorStack) {
        if (element.equals(")")) {
            while (!operatorStack.isEmpty()) {                
                String topElement = operatorStack.remove(operatorStack.size()-1);
                if (topElement.equals("(")) return;
                outputQueue.add(topElement);
            }
        }
        
        else {
            if (!element.equals("(")) {
                while (!operatorStack.isEmpty()) {
                    String topElement = operatorStack.get(operatorStack.size()-1);
                    if (topElement.equals("(")) break;
                    if (associativity.get(topElement)) {
                        if (precedence.get(topElement) >= precedence.get(element))
                            outputQueue.add(operatorStack.remove(operatorStack.size()-1));
                        else break;
                    }
                    else {
                        if (precedence.get(topElement) > precedence.get(element))
                            outputQueue.add(operatorStack.remove(operatorStack.size()-1));
                        else break;
                    }
                }
            }
            operatorStack.add(element);
        }
        
    }
    
    private static ArrayList<String> parse(String expression) {
    
        ArrayList<String> outputQueue = new ArrayList();
        ArrayList<String> operatorStack = new ArrayList();
        
        boolean isNumber = false;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            String c = Character.toString(expression.charAt(i));
            
            if (c.equals(" ")) continue;
    
            if (!c.matches(operatorSymbols)) { // Check if the symbol belong to the alphabet of the numeral system
                number.append(c);
                isNumber = true;
            }
            else {
                if (isNumber) {
                    isNumber = false;
                    outputQueue.add(number.toString());
                    number = new StringBuilder();
                }
                updateStackAndQueue(c, outputQueue, operatorStack);
            } 
        }
        if (isNumber) outputQueue.add(number.toString());
        updateStackAndQueue("", outputQueue, operatorStack); // Make sure that operatorStack is emptied to outputQueue
        
        printArrayList(outputQueue); 
        
        return outputQueue;
    }



    public static String evaluate(String expression, int inputBase, int outputBase) {

        if (expression == "") return "0";


        if (expression.replaceAll("\\(","").length() != expression.replaceAll("\\)","").length()) return "Brackets do not match";

        ArrayList<String> numberStack = new ArrayList();

        ArrayList<String> inputQueue = parse(expression);
        for (String element : inputQueue) {
            if (!element.matches(operatorSymbols)) {
                Number number = Converter.anyToAny(element, inputBase, 10);
                if (number.hasError()) {
                    if (inputQueue.size() == 1) return number.getString(); // If only a simple conversion was performed
                    return "One of the numbers is not base " + inputBase + "\n";
                }
                numberStack.add(number.getString());
            }
            else  {
                if (numberStack.size() < 2) return "Not enough arguments for " + element + " operation";

                BigDecimal b = new BigDecimal(numberStack.remove(numberStack.size()-1));
                BigDecimal a = new BigDecimal(numberStack.remove(numberStack.size()-1));
               
                String result = "";
                switch (element) {
                    case "+":
                        result = a.add(b).toPlainString();
                        break;
                    case "-":
                        result = a.subtract(b).toPlainString();
                        break;
                    case "*":
                        result = a.multiply(b).toPlainString();
                        break;
                    case "/":
                        result = a.divide(b, MathContext.DECIMAL128).toPlainString();
                        break;
                    case "^":
                        result = a.pow(b.intValue()).toPlainString();
                        break;
                }
                numberStack.add(result);
            }
        }
        if (numberStack.size() > 1) return "The user input has too many values";
        return Converter.anyToAny(numberStack.get(0), 10, outputBase).getString();
        
    }

    
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.evaluate("1.234+1.567-1.4234", 10, 10));

//        System.out.println(calculator.evaluate("3.14 + 4.54 * 2.1 / ( 1.7 - 5.1 ) ^ 2.4 ^ 3.1", 10, 10));
//        System.out.println(calculator.evaluate("5 + ((1 + 2)) * 4 - 3", 10, 10));
        System.out.println(calculator.evaluate("A+B.s8", 16, 10));
        
        
    }
}

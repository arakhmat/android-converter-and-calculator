package com.example.akhme_000.myapplication.converter;

public class HelperFunctions {
    // Using ASCII to convert from char to integer
    public static int charToInt(char character) {
        int num = (int) character;

        if (num > 47 && num < 58)        num -= 48;
        else if (num > 64 && num < 91)   num -= 55;
        else if (num > 96 && num < 123)  num -= 87;
        return num;
    }

    // Using ASCII to convert from integer to char
    public static char intToChar(int num) {
        char character = '0';
        if (num < 10)      character = (char) (num + 48);
        else if (num < 36) character = (char) (num + 55);
        return character;
    }
}

package com.example.akhme_000.myapplication.converter;

public class NumeralSystem
{

    private String allowedChars;
    private String allowedFormat;
    final private String errorMessage;
    final private int base;

    NumeralSystem(int system)
    {
        // base 0 is two's complement. 1 is two's complement in hex
        if(system < 0 || system > 37) system = 10;
        this.base = system;
        setAllowedChars();
        setAllowedFormat();
        errorMessage = "Not a base " + (system > 1 ? base  : (system == 0 ? "2's complement" : "hex 2's complement")) + " number";
    }

    // Creates the regex with the allowed chars
    private void setAllowedChars()
    {
        allowedChars = base == 0 ? "[01]+$" : (base == 1 ? "[0-9A-Fa-f]+" : "[0-" + (base < 11 ? base - 1 + "]" : 
                "9"+"A"+(base==11?"":"-"+(char)(base+54))+"a"+(base==11?"":"-"+(char)(base+86))+"]")); 
    }

    // Creates regex for checking whether the number has the coorect format or not
    private void setAllowedFormat()
    {
        allowedFormat = (base > 1 ? "-?" + getAllowedChars() + "+\\.?" + getAllowedChars() + "*" 
                : getAllowedChars());
    }
    
    public String getAllowedChars()
    {
        return this.allowedChars;
    }

    public String getAllowedFormat()
    {
        return this.allowedFormat;
    }

    public String getErrorMessage()
    {
        return this.errorMessage;
    }
}

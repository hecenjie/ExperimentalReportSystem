package com.nanwulife.util;

public class Sci2con {
    public static long sci2con(String str){
        double result = Double.parseDouble(str.split("e")[0]) * Math.pow(10, Double.parseDouble(str.split("e")[1]));
        System.out.println(result);
        return (long)result;
    }
}

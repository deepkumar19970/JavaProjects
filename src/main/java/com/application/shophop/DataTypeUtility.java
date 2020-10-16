package com.application.shophop;

public class DataTypeUtility {

    public static String stringValue(Object o){
        String value="";
        try{
            if(o!=null && o.toString().trim().length()>0){
                value= o.toString().trim();
            }
        }catch (Exception e){
            return value;
        }
        return  value;
    }
}

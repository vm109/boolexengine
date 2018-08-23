package com.brandbargain.boolex;

public class BoolexExceptions extends   Exception{
    private String message;
    public BoolexExceptions(String message){
        this.message = message;
    }

    public BoolexExceptions(){

    }

    public static  BoolexExceptions throwWOrdNotFoundINDictionary(){
        return new BoolexExceptions(BoolexConstants.Exceptions.Word_Not_Found_In_Dictionary_Exception);
    }
}

package com.example;

public class IllegalNameException extends RuntimeException{
    public IllegalNameException(){
        super("Nome invaido!");
    }
}

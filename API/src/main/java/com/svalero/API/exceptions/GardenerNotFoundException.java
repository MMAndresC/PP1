package com.svalero.API.exceptions;

public class GardenerNotFoundException extends Exception{
    public GardenerNotFoundException(){
        super("Gardener not exist");
    }

    public GardenerNotFoundException(String message){
        super(message);
    }
}

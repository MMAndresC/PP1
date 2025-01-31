package com.svalero.API.exceptions;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(){
        super("Client not exist");
    }

   /* public ClientNotFoundException(String message){
        super(message);
    }*/
}

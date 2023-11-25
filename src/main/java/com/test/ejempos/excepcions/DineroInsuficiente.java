package com.test.ejempos.excepcions;

public class DineroInsuficiente extends RuntimeException{
    public DineroInsuficiente(String message) {
        super(message);

    }
}

package com.isp.after;

public class Laptop implements Switchable, Chargeable {
    @Override
    public void turnOn() {
        System.out.println("Laptop is turning on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Laptop is turning off.");
    }

    @Override
    public void charge() {
        System.out.println("Laptop is charging.");
    }
}

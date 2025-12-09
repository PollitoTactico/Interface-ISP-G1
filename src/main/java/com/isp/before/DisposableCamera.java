package com.isp.before;

public class DisposableCamera implements Device {
    @Override
    public void turnOn() {
        System.out.println("Disposable camera is turning on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Disposable camera is turning off.");
    }

    @Override
    public void charge() {
        // Las cámaras desechables no se pueden cargar, pero estamos forzados a implementar este método.
        throw new UnsupportedOperationException("Disposable cameras cannot be charged.");
    }
}

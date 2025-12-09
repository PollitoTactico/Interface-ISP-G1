package com.isp.after;

public class FlashlightDisposable implements Switchable {
    @Override
    public void turnOn() {
        System.out.println("Disposable flashlight is turning on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Disposable flashlight is turning off.");
    }
}

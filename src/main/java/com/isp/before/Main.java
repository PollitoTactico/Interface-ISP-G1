package com.isp.before;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CÓDIGO CON VIOLACIÓN DEL ISP ===\n");
        
        // Dispositivo recargable
        Device phone = new Phone();
        System.out.println("--- Phone ---");
        phone.turnOn();
        phone.charge();
        phone.turnOff();
        
        System.out.println("\n--- Disposable Camera ---");
        // Dispositivo no recargable
        Device camera = new DisposableCamera();
        camera.turnOn();
  
        try {
            camera.charge(); 
        } catch (UnsupportedOperationException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("¡Este es el problema! La interfaz Device fuerza a DisposableCamera");
            System.out.println("a implementar un método que no tiene sentido para esta clase.");
        }
        
        camera.turnOff();
    }
}

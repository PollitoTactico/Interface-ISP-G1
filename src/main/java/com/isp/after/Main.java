package com.isp.after;

/**
 * Clase principal que demuestra la solución aplicando el ISP.
 * 
 * Ahora las interfaces están segregadas y cada clase implementa solo los métodos
 * que realmente necesita, sin código inútil ni excepciones en tiempo de ejecución.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== CÓDIGO REFACTORIZADO APLICANDO ISP ===\n");
        
        // Usando interfaces - Demostración de polimorfismo
        System.out.println("--- DISPOSITIVOS RECARGABLES ---");
        System.out.println("(Implementan Switchable + Chargeable)\n");
        
        // Declaramos con la interfaz que queremos demostrar
        Switchable deviceRecargable1 = new Phone();
        System.out.println("> Dispositivo Recargable 1 (Phone):");
        deviceRecargable1.turnOn();
        // Para cargar, necesitamos usar Chargeable
        if (deviceRecargable1 instanceof Chargeable) {
            ((Chargeable) deviceRecargable1).charge();
        }
        deviceRecargable1.turnOff();
        
        Switchable deviceRecargable2 = new Laptop();
        System.out.println("\n> Dispositivo Recargable 2 (Laptop):");
        deviceRecargable2.turnOn();
        if (deviceRecargable2 instanceof Chargeable) {
            ((Chargeable) deviceRecargable2).charge();
        }
        deviceRecargable2.turnOff();
        
        // Dispositivos no recargables - Solo Switchable
        System.out.println("\n\n--- DISPOSITIVOS NO RECARGABLES ---");
        System.out.println("(Solo implementan Switchable)\n");
        
        Switchable deviceNoRecargable1 = new DisposableCamera();
        System.out.println("> Dispositivo No Recargable 1 (DisposableCamera):");
        deviceNoRecargable1.turnOn();
        // deviceNoRecargable1.charge(); // Este método NO EXISTE - ¡Correcto por ISP!
        System.out.println("  (No se puede cargar - no implementa Chargeable)");
        deviceNoRecargable1.turnOff();
        
        Switchable deviceNoRecargable2 = new FlashlightDisposable();
        System.out.println("\n> Dispositivo No Recargable 2 (FlashlightDisposable):");
        deviceNoRecargable2.turnOn();
        System.out.println("  (No se puede cargar - no implementa Chargeable)");
        deviceNoRecargable2.turnOff();
        
        // Demostración de polimorfismo con interfaces segregadas
        System.out.println("\n\n--- DEMOSTRACIÓN DE POLIMORFISMO ---");
        System.out.println("\n> Método que solo acepta dispositivos Chargeable:");
        // Ahora los declaramos como Chargeable para pasarlos al método
        Chargeable chargeablePhone = new Phone();
        Chargeable chargeableLaptop = new Laptop();
        demonstrateChargeable(chargeablePhone);
        demonstrateChargeable(chargeableLaptop);
        // demonstrateChargeable(deviceNoRecargable1); // ¡Esto NO compila! - Correcto por ISP
        
        System.out.println("\n> Método que acepta cualquier dispositivo Switchable:");
        System.out.println("  (Todos los dispositivos pueden encenderse)");
        demonstrateSwitchable(deviceRecargable1);
        demonstrateSwitchable(deviceRecargable2);
        demonstrateSwitchable(deviceNoRecargable1);
        demonstrateSwitchable(deviceNoRecargable2);
        
        System.out.println("\n\n=== CONCLUSIÓN ===");
        System.out.println("✓ Las interfaces están segregadas correctamente");
        System.out.println("✓ Cada clase implementa solo los métodos que necesita");
        System.out.println("✓ No hay excepciones en tiempo de ejecución");
        System.out.println("✓ El código es más flexible y mantenible");
        System.out.println("✓ El polimorfismo funciona con interfaces específicas");
        System.out.println("✓ Los dispositivos no recargables NO tienen acceso a charge()");
    }
    
   
    private static void demonstrateChargeable(Chargeable device) {
        System.out.println("  - Cargando dispositivo recargable...");
        device.charge();
    }
    
   
    private static void demonstrateSwitchable(Switchable device) {
        device.turnOn();
    }
}

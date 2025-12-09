# Proyecto: Interface Segregation Principle (ISP)

## 📋 Descripción del Proyecto

Este proyecto demuestra el **Principio de Segregación de Interfaces (ISP)**, uno de los cinco principios SOLID de diseño orientado a objetos. El ISP establece que "una clase no debe ser forzada a implementar interfaces que no utiliza".

### Sistema Implementado

Sistema de gestión de dispositivos electrónicos que incluye:
- Dispositivos recargables (Phone, Laptop)
- Dispositivos no recargables (DisposableCamera, FlashlightDisposable)

---

## 🚨 Problema Identificado

### Violación del ISP (Paquete `com.isp.before`)

El código inicial presenta los siguientes problemas:

1. **Interfaz Monolítica**: La interfaz `Device` incluye tres métodos:
   ```java
   interface Device {
       void turnOn();
       void turnOff();
       void charge();  // ← Problema: No todos los dispositivos se pueden cargar
   }
   ```

2. **Implementación Forzada**: La clase `DisposableCamera` está obligada a implementar el método `charge()` aunque no tiene lógica para cargarse:
   ```java
   public void charge() {
       throw new UnsupportedOperationException("Disposable cameras cannot be charged.");
   }
   ```

3. **Consecuencias**:
   - ❌ Código inútil y confuso
   - ❌ Excepciones en tiempo de ejecución
   - ❌ Violación del ISP
   - ❌ Bajo cohesión
   - ❌ Acoplamiento innecesario

---

## ✅ Solución Implementada

### Refactorización Aplicando ISP (Paquete `com.isp.after`)

Se dividió la interfaz monolítica en **interfaces segregadas y específicas**:

#### 1. Interfaz `Switchable`
```java
public interface Switchable {
    void turnOn();
    void turnOff();
}
```
**Propósito**: Contiene métodos comunes a todos los dispositivos.

#### 2. Interfaz `Chargeable`
```java
public interface Chargeable {
    void charge();
}
```
**Propósito**: Específica para dispositivos recargables.

#### 3. Implementaciones

**Dispositivos Recargables** (implementan ambas interfaces):
```java
public class Phone implements Switchable, Chargeable {
    // Implementa turnOn(), turnOff() y charge()
}

public class Laptop implements Switchable, Chargeable {
    // Implementa turnOn(), turnOff() y charge()
}
```

**Dispositivos No Recargables** (solo implementan Switchable):
```java
public class DisposableCamera implements Switchable {
    // Solo implementa turnOn() y turnOff()
    // ¡Ya no necesita charge()!
}

public class FlashlightDisposable implements Switchable {
    // Solo implementa turnOn() y turnOff()
}
```

---

## 🎯 Beneficios de la Refactorización

| Antes (Violando ISP) | Después (Aplicando ISP) |
|----------------------|-------------------------|
| ❌ Interfaz grande y genérica | ✅ Interfaces pequeñas y específicas |
| ❌ Métodos innecesarios | ✅ Solo métodos necesarios |
| ❌ Excepciones en tiempo de ejecución | ✅ Código seguro y predecible |
| ❌ Bajo cohesión | ✅ Alta cohesión |
| ❌ Difícil de mantener | ✅ Fácil de mantener y extender |

### Ventajas Específicas

1. **Mayor Flexibilidad**: Podemos agregar nuevos tipos de dispositivos fácilmente
2. **Código Más Limpio**: Sin implementaciones vacías o con excepciones
3. **Mejor Polimorfismo**: Los métodos pueden aceptar interfaces específicas
4. **Compilación Segura**: Los errores se detectan en tiempo de compilación, no de ejecución
5. **Cohesión Mejorada**: Cada interfaz tiene una responsabilidad clara

---

## 🏗️ Estructura del Proyecto

```
Interface-ISP-G1/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── isp/
│                   ├── before/          # Código con violación del ISP
│                   │   ├── Device.java
│                   │   ├── Phone.java
│                   │   ├── DisposableCamera.java
│                   │   └── Main.java
│                   │
│                   └── after/           # Código refactorizado aplicando ISP
│                       ├── Switchable.java
│                       ├── Chargeable.java
│                       ├── Phone.java
│                       ├── Laptop.java
│                       ├── DisposableCamera.java
│                       ├── FlashlightDisposable.java
│                       └── Main.java
├── pom.xml
└── README.md
```

---

## 🚀 Cómo Ejecutar el Proyecto

### Prerrequisitos
- Java 11 o superior
- Maven 3.6 o superior

### Compilar el proyecto
```bash
mvn clean compile
```

### Ejecutar el código CON violación del ISP
```bash
mvn exec:java -Dexec.mainClass="com.isp.before.Main"
```

**Resultado esperado**: Se mostrará una excepción cuando se intente cargar la cámara desechable.

### Ejecutar el código refactorizado (aplicando ISP)
```bash
mvn exec:java -Dexec.mainClass="com.isp.after.Main"
```

**Resultado esperado**: Todos los dispositivos funcionan correctamente sin excepciones.

---

## 📸 Capturas de Pantalla

### Ejecución del Código CON Violación del ISP
![Antes - Con Violación](screenshots/before-isp-violation.png)

*Se observa la excepción `UnsupportedOperationException` al intentar cargar un dispositivo no recargable.*

### Ejecución del Código Refactorizado (Aplicando ISP)
![Después - Con ISP](screenshots/after-isp-applied.png)

*Todos los dispositivos funcionan correctamente, cada uno con los métodos que realmente necesita.*

---

## 💡 Reflexión Personal

### ¿Cómo se aplicó el ISP?

El ISP se aplicó mediante la **segregación de interfaces**:

1. **Identificación del problema**: La interfaz `Device` obligaba a todas las clases a implementar `charge()`, incluso cuando no era aplicable.

2. **Análisis de responsabilidades**: Separamos las responsabilidades en dos categorías:
   - Capacidad de encender/apagar (común a todos)
   - Capacidad de cargar (solo algunos dispositivos)

3. **Creación de interfaces específicas**: 
   - `Switchable` para funcionalidad básica
   - `Chargeable` para dispositivos recargables

4. **Implementación selectiva**: Cada clase implementa solo las interfaces que necesita.

### ¿Qué problemas resolvió?

1. **Eliminación de código inútil**: Ya no hay métodos que lanzan excepciones por no aplicar a la clase.

2. **Seguridad en tiempo de compilación**: Si intentamos llamar a `charge()` en un `DisposableCamera`, el compilador nos lo impedirá.

3. **Extensibilidad**: Agregar nuevos tipos de dispositivos es más fácil y seguro.

4. **Claridad del código**: Las interfaces pequeñas son más fáciles de entender y documentar.

5. **Mejor diseño orientado a objetos**: El código refleja correctamente el dominio del problema.

### Lecciones Aprendidas

- **Interfaces pequeñas son mejores**: Es preferible tener múltiples interfaces específicas que una interfaz grande y genérica.
- **Composición de interfaces**: Java permite implementar múltiples interfaces, lo que nos da flexibilidad para combinar capacidades.
- **Diseño basado en capacidades**: Las interfaces deben representar capacidades específicas, no tipos de objetos.
- **Principio de mínimo conocimiento**: Las clases deben depender solo de lo que realmente necesitan.

---

## 🔗 Relación con Otros Principios SOLID

El ISP trabaja en conjunto con otros principios SOLID:

- **SRP (Single Responsibility)**: Interfaces pequeñas tienen una única responsabilidad
- **OCP (Open/Closed)**: Más fácil extender sin modificar código existente
- **LSP (Liskov Substitution)**: Las implementaciones son sustituibles sin sorpresas
- **DIP (Dependency Inversion)**: Dependemos de abstracciones pequeñas y específicas

---

## 📚 Referencias

- Martin, R. C. (2017). *Clean Architecture: A Craftsman's Guide to Software Structure and Design*
- Martin, R. C. (2008). *Clean Code: A Handbook of Agile Software Craftsmanship*
- [SOLID Principles - Wikipedia](https://en.wikipedia.org/wiki/SOLID)

---

## 👥 Autor

**Grupo 1 - Proyecto ISP**

Fecha: Diciembre 2025

---

## 📝 Licencia

Este proyecto es con fines educativos para demostrar el Principio de Segregación de Interfaces.

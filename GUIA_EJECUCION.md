# Guía Rápida de Ejecución

## 📋 Comandos para Ejecutar el Proyecto

### 1. Compilar el Proyecto
```bash
mvn clean compile
```

### 2. Ejecutar el Código CON Violación del ISP (Versión "Before")
```bash
mvn exec:java '-Dexec.mainClass=com.isp.before.Main'
```

**Resultado esperado:**
- ✅ El teléfono funciona correctamente (se enciende, carga y apaga)
- ❌ La cámara desechable lanza una excepción al intentar cargar
- 🔴 **Problema:** La interfaz `Device` obliga a implementar `charge()` en dispositivos no recargables

### 3. Ejecutar el Código Refactorizado (Versión "After")
```bash
mvn exec:java '-Dexec.mainClass=com.isp.after.Main'
```

**Resultado esperado:**
- ✅ Todos los dispositivos recargables (Phone, Laptop) funcionan correctamente
- ✅ Todos los dispositivos no recargables (DisposableCamera, FlashlightDisposable) funcionan sin errores
- ✅ **No hay excepciones**: Cada clase implementa solo los métodos que necesita
- ✅ Demostración de polimorfismo con interfaces segregadas

---

## 🎯 Verificación de la Salida Esperada

### Código Refactorizado (ISP Aplicado)

#### ✅ Dispositivos Recargables
- **Phone**:
  - `turnOn()` ✓
  - `charge()` ✓
  - `turnOff()` ✓

- **Laptop**:
  - `turnOn()` ✓
  - `charge()` ✓
  - `turnOff()` ✓

#### ✅ Dispositivos No Recargables
- **DisposableCamera**:
  - `turnOn()` ✓
  - `turnOff()` ✓
  - ❌ NO tiene método `charge()` (correcto según ISP)

- **FlashlightDisposable**:
  - `turnOn()` ✓
  - `turnOff()` ✓
  - ❌ NO tiene método `charge()` (correcto según ISP)

#### ✅ Sin Excepciones
No se genera ninguna `UnsupportedOperationException` durante la ejecución.

---

## 📊 Comparación de Resultados

| Aspecto | Before (Violación ISP) | After (ISP Aplicado) |
|---------|------------------------|----------------------|
| **Excepciones** | ❌ Sí (UnsupportedOperationException) | ✅ No |
| **Código inútil** | ❌ Sí (métodos que lanzan excepciones) | ✅ No |
| **Flexibilidad** | ❌ Baja | ✅ Alta |
| **Mantenibilidad** | ❌ Difícil | ✅ Fácil |
| **Interfaces específicas** | ❌ No (1 interfaz grande) | ✅ Sí (2 interfaces pequeñas) |
| **Cumple ISP** | ❌ No | ✅ Sí |

---

## 🔧 Estructura de Interfaces

### Before (Violación)
```
Device
├── turnOn()
├── turnOff()
└── charge()  ← Todos deben implementar esto
```

### After (ISP Aplicado)
```
Switchable         Chargeable
├── turnOn()       └── charge()
└── turnOff()

Implementaciones:
- Phone: Switchable + Chargeable
- Laptop: Switchable + Chargeable
- DisposableCamera: Switchable
- FlashlightDisposable: Switchable
```

---

## 📸 Capturas de Pantalla

Para documentar tu proyecto, toma capturas de:

1. **Terminal con ejecución "Before"** - Mostrando la excepción
2. **Terminal con ejecución "After"** - Mostrando el funcionamiento correcto
3. **Código de las interfaces segregadas** - Switchable.java y Chargeable.java

Guárdalas en la carpeta `screenshots/`.

---

## 🚀 Subir a GitHub

```bash
# Agregar todos los archivos
git add .

# Commit con mensaje descriptivo
git commit -m "Implementación del Principio de Segregación de Interfaces (ISP)"

# Push al repositorio
git push origin main
```

---

## 📝 Checklist del Proyecto

- [x] Código "before" con violación del ISP
- [x] Código "after" aplicando ISP
- [x] README.md con reflexión detallada
- [x] Proyecto compila sin errores
- [x] Ejecución "before" muestra el problema
- [x] Ejecución "after" funciona correctamente
- [ ] Capturas de pantalla agregadas
- [ ] Código subido a GitHub

---

## 💡 Conceptos Clave Demostrados

1. **Segregación de Interfaces**: División de `Device` en `Switchable` y `Chargeable`
2. **Implementación Múltiple**: Las clases pueden implementar varias interfaces
3. **Polimorfismo**: Métodos que aceptan tipos específicos de interfaces
4. **Diseño Flexible**: Fácil agregar nuevos tipos de dispositivos
5. **Sin Código Inútil**: Cada clase solo tiene lo que necesita

---

## ❓ Preguntas de Reflexión

1. ¿Por qué es malo que `DisposableCamera` implemente un método `charge()` que lanza excepciones?
2. ¿Cómo facilita el ISP la adición de nuevos tipos de dispositivos?
3. ¿Qué ventajas tiene separar `Switchable` y `Chargeable`?
4. ¿Cómo se relaciona el ISP con el principio de responsabilidad única (SRP)?
5. ¿Qué problemas de mantenimiento resuelve el ISP?

---

¡Proyecto completado! 🎉

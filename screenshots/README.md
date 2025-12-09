# Screenshots Folder

Aquí debes colocar las capturas de pantalla de la ejecución del proyecto:

1. **before-isp-violation.png**: Captura mostrando la excepción del código que viola el ISP
2. **after-isp-applied.png**: Captura mostrando el código refactorizado funcionando correctamente

## Cómo tomar las capturas:

### Para la versión "before" (con violación):
```bash
mvn exec:java -Dexec.mainClass="com.isp.before.Main"
```
Toma una captura cuando aparezca la excepción.

### Para la versión "after" (refactorizado):
```bash
mvn exec:java -Dexec.mainClass="com.isp.after.Main"
```
Toma una captura mostrando la ejecución exitosa de todos los dispositivos.

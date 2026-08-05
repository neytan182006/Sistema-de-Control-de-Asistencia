# Sistema de Control de Asistencia

Sistema de control de asistencia de empleados para el curso de **Programación II** (Java + MySQL). Registra entrada/salida diaria por empleado y genera un reporte del día.

## Funcionalidades

- Listar empleados: se cargan desde MySQL hacia un **arreglo de objetos** `Empleado[]` de tamaño exacto (se cuenta primero con `COUNT(*)`, se crea el arreglo de ese tamaño y se llena con un ciclo) — mismo patrón de arreglos de Programación II, aplicado sobre datos persistentes.
- Registrar entrada / salida del día actual (con `ON DUPLICATE KEY` para no duplicar la fila del día).
- Reporte de asistencia del día.

## Estructura

```
src/
├── modelo/Empleado.java
├── dao/ConexionBD.java, EmpleadoDAO.java, AsistenciaDAO.java
└── app/Main.java
```

## Base de datos

Script en [`database/asistencia.sql`](database/asistencia.sql): tablas `EMPLEADOS` y `ASISTENCIAS` (con `UNIQUE(IdEmpleado, Fecha)` para garantizar un solo registro de asistencia por empleado por día).

## Requisitos

- JDK 17+, servidor MySQL local. Conector incluido en `lib/`.

## Cómo ejecutarlo

```bash
mysql -u root -p < database/asistencia.sql
javac -d bin -cp "lib/mysql-connector-j-9.5.0.jar" src/modelo/*.java src/dao/*.java src/app/*.java
java -cp "bin;lib/mysql-connector-j-9.5.0.jar" app.Main
```

> Compilado y verificado con `javac` sin errores; la conexión real a MySQL no se probó en este entorno (sin servidor corriendo, como acordamos) — verifica usuario/clave en `ConexionBD.java`.

## Capturas

_Pendiente: agregar capturas del menú en ejecución en `capturas/`._

## Licencia

MIT — ver [LICENSE](LICENSE).

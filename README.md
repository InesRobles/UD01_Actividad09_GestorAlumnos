# UD01_Actividad09_GestorAlumnos
 
Aplicación de consola desarrollada en **Kotlin** para gestionar la información básica del alumnado de un grupo: registrar estudiantes, almacenar sus notas, calcular la media del grupo y filtrar al alumnado aprobado y suspenso.
 
Proyecto de la asignatura **PMDM – UD01. Introducción**.
 
---
 
## Características
 
- Menú interactivo que se repite hasta que el usuario elige salir.
- Registro de alumnos con nombre, apellidos y nota final.
- Listado de todos los alumnos registrados.
- Cálculo de la media del grupo.
- Filtrado de alumnado **aprobado** (nota ≥ 5) y **suspenso** (nota < 5).
- Búsqueda por nombre o apellidos, con coincidencias parciales.
- Eliminación de alumnos por nombre (si hay varios con el mismo nombre, se piden los apellidos).
- Validaciones de entrada en todas las opciones.
## Tecnologías
 
| Herramienta | Uso |
|---|---|
| Kotlin | Lenguaje de programación |
| IntelliJ IDEA | Entorno de desarrollo |
| JDK | Entorno de ejecución |
 
## Estructura del proyecto
 
```
UD01_Actividad09_GestorAlumnos/
├── src/
│   ├── Main.kt              # Punto de entrada del programa
│   ├── Funciones.kt         # Menú y lógica de la aplicación
│   └── modelos/
│       └── Alumno.kt        # Data class Alumno
├── .gitignore
└── UD01_Actividad09_GestorAlumnos.iml
```
 
### Modelo de datos
 
Cada alumno se representa con una `data class`:
 
```kotlin
data class Alumno(
    val nombre: String,
    val apellidos: String,
    val nota: Double
)
```
 
Los alumnos se almacenan en una lista mutable dentro de la clase `Funciones`:
 
```kotlin
var alumnos = mutableListOf<Alumno>()
```
 
## Cómo ejecutar el proyecto
 
1. Clona el repositorio o descomprime el ZIP del proyecto.
2. Abre la carpeta en **IntelliJ IDEA** (`File > Open`).
3. Comprueba que el proyecto tiene un JDK configurado (`File > Project Structure > Project`).
4. Abre `src/Main.kt` y pulsa el botón **Run** junto a la función `main`.
## Menú principal
 
```
===== GESTOR DE ALUMNOS =====
0. Salir
1. Registrar alumno
2. Listar alumnos
3. Calcular media del grupo
4. Mostrar aprobados
5. Mostrar suspensos
6. Buscar alumno
7. Eliminar alumno
Seleccione una opción:
```
 
## Funcionalidades
 
### 1. Registrar alumno
Solicita nombre, apellidos y nota final. El alumno solo se añade a la lista si todos los datos son válidos.
 
### 2. Listar alumnos
Muestra todos los alumnos registrados. Si no hay ninguno, informa de que no hay alumnos registrados.
 
### 3. Calcular media del grupo
Calcula la media de todas las notas y la muestra con dos decimales. Si la lista está vacía, no se calcula y se muestra un aviso.
 
```
La media del grupo es: 6,75
```
 
### 4. Mostrar aprobados
Lista los alumnos con nota igual o superior a 5. Si no hay ninguno, muestra un mensaje adecuado.
 
```
===== ALUMNOS APROBADOS =====
Ana García López - Nota: 8.5
Carlos Ruiz Díaz - Nota: 6.2
```
 
### 5. Mostrar suspensos
Lista los alumnos con nota inferior a 5. Si no hay ninguno, muestra un mensaje adecuado.
 
```
===== ALUMNOS SUSPENSOS =====
Luis Pérez Martín - Nota: 4.7
```
 
### 6. Buscar alumno
Pide un texto y muestra los alumnos cuyo nombre o apellidos lo contengan, sin distinguir mayúsculas de minúsculas.
 
```
Introduce el texto a buscar: garc
Alumnos encontrados:
Ana García López - Nota: 8.5
```
 
Si no hay resultados:
 
```
No se han encontrado alumnos con ese criterio.
```
 
### 7. Eliminar alumno
Pide el nombre del alumno a eliminar:
 
- Si solo hay un alumno con ese nombre, se elimina directamente.
- Si hay varios con el mismo nombre, se muestran y se piden los apellidos para identificar a cuál eliminar.
- Si no existe, se muestra un mensaje de error.
```
Alumno eliminado correctamente.
```
 
### 0. Salir
Finaliza el programa.
 
## Validaciones
 
| Situación | Comportamiento |
|---|---|
| Opción de menú no numérica o fuera de rango | Muestra un mensaje de opción no válida |
| Nombre o apellidos vacíos | Muestra un error y vuelve a pedir el dato |
| Nota no numérica | Muestra un error y vuelve a pedir la nota |
| Nota menor que 0 o mayor que 10 | Muestra un error y vuelve a pedir la nota |
| Media con lista vacía | No se calcula y se muestra un aviso |
| Búsqueda sin resultados | Muestra "No se han encontrado alumnos con ese criterio." |
| Eliminación de un alumno inexistente | Muestra un mensaje de error |
 
 

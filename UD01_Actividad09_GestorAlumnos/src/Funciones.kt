import modelos.*
import java.util.Scanner

class Funciones {
    private val scan = Scanner(System.`in`)
    var alumnos = mutableListOf<Alumno>()
    fun iniciar() {
        var opcion: Int
        do {
            mostrarMenu()
            // Depuración
            if (!scan.hasNextInt()) {
                println("Opción no válida, introduce un número.")
                scan.next()
                opcion = 0
                continue
            }
            opcion = scan.nextInt()
            when (opcion) {
                1 -> {
                    registraeAlumno()
                }


                2 -> {
                    listarAlumnos(alumnos)
                }

                3 -> {
                    mediaNotas()

                }

                4 -> {
                    filtroAprobados()
                }

                5 -> {
                    filtroSuspensos()

                }

                6 -> {
                    busqueda()
                }

                7 -> {
                    eliminar()
                }

                0 -> {
                    println("Saliendo del programa. ¡Hasta pronto!")

                }

                else -> {
                    println("Opción no válida")
                }
            }
        } while (opcion != 0)
    }

    //FASE 3. Menú principal
    private fun mostrarMenu() {
        println("\n===== GESTOR DE ALUMNOS =====")
        println("0. Salir")
        println("1. Registrar alumno")
        println("2. Listar alumnos")
        println("3. Calcular media del grupo")
        println("4. Mostrar aprobados")
        println("5. Mostrar suspensos")
        println("6. Buscar alumno")
        println("7. Eliminar alumno")
        print("Seleccione una opción: ")
    }

    //FASE 4. Registro de alumnos
    private fun registraeAlumno() {
        //Añadimos el nombre
        var nombre: String
        while (true) {
            println("Introduce el nombre del alumno: ")
            nombre = readln().trim()
            if (nombre.isBlank()) {
                println("Error: El nombre no puede estar vacío.")
            } else {
                break
            }
        }
        //Añadimos los apellidos
        var apellidos: String
        while (true) {
            println("Introduce los apellidos del alumno: ")
            apellidos = readln().trim()
            if (apellidos.isBlank()) {
                println("Error: los apellidos no pueden estar vacíos.")
            } else {
                break
            }
        }
        //Añadimos la nota final
        var notaFinal: Double
        while (true) {
            println("Introduce la nota final del alumno: ")
            val entrada = readln().trim().toDoubleOrNull()
            if (entrada == null) {
                println("Opción no válida, introduce un valor numérico.")
                continue
            }
            if (entrada < 0 || entrada > 10) {
                println("Error: la nota debe estar comprendida entre 0 y 10.")
                continue
            }
            notaFinal = entrada
            break
        }
            val nuevoAlumno = Alumno(nombre = nombre, apellidos = apellidos, nota = notaFinal)
            alumnos.add(nuevoAlumno)
            println("Alumno añadido con exito: ${alumnos.last()}")

    }
    //FASE 5. Listar alumnos
    private fun listarAlumnos(alumnos: List<Alumno>){
        var valido=false
        while(!valido) {
            if (alumnos.isEmpty()) {
                println("Primero tienes que introducir alumnos.")
                valido= false
            }
            valido=true
        }
        println("Lista de alumnos:")
        alumnos.chunked(10).forEach {
            println(it.joinToString(" "))
        }
    }
    //Fase 6. Media
    private fun mediaNotas(){
        if (alumnos.isEmpty()) {
            println("No hay alumnos registrados, no se puede calcular la media.")
            return
        }
        val media = calcularMedia(alumnos)
        println("La media del grupo es: ${"%.2f".format(media)}")
    }
private fun calcularMedia(alumnos: List<Alumno>): Double {
    return alumnos.sumOf { it.nota } / alumnos.size
}
    //FASE 7. Filtrado de aprobados y suspensos
    private fun filtroAprobados(){
            println("\n===== ALUMNOS APROBADOS =====")
            val aprobados = alumnos.filter { it.nota >= 5 }
            if (aprobados.isEmpty()) {
                println("No hay alumnos aprobados.")
                return
            }
            aprobados.forEach {
                println("${it.nombre} ${it.apellidos} - Nota: ${it.nota}")
            }
    }
    private fun filtroSuspensos(){
        println("\n===== ALUMNOS SUSPENSOS =====")
        val suspensos = alumnos.filter { it.nota < 5 }
        if (suspensos.isEmpty()) {
            println("No hay alumnos suspensos.")
            return
        }
        suspensos.forEach {
            println("${it.nombre} ${it.apellidos} - Nota: ${it.nota}")
        }
    }
    //fase 8. Búsqueda alumno
    private fun busqueda(){
        if (alumnos.isEmpty()) {
            println("No hay alumnos registrados.")
            return
        }
        println("Introduce el texto a buscar: ")
        val texto = readln().trim()
        if (texto.isBlank()) {
            println("Error: el texto de búsqueda no puede estar vacío.")
            return
        }
        // Coincidencias parciales en nombre o apellidos, sin distinguir mayúsculas
        val encontrados = alumnos.filter {
            it.nombre.contains(texto, ignoreCase = true) ||
                    it.apellidos.contains(texto, ignoreCase = true)
        }
        if (encontrados.isEmpty()) {
            println("No se han encontrado alumnos con ese criterio.")
            return
        }
        println("Alumnos encontrados:")
        encontrados.forEach {
            println("${it.nombre} ${it.apellidos} - Nota: ${it.nota}")
        }
    }
    //FASE 9. Eliminación de alumnos
    private fun eliminar() {
        if (alumnos.isEmpty()) {
            println("No hay alumnos registrados.")
            return
        }
        println("Introduce el nombre del alumno a eliminar: ")
        val nombre = readln().trim()
        if (nombre.isBlank()) {
            println("Error: el nombre no puede estar vacío.")
            return
        }
        // Buscamos los alumnos que coinciden en nombre, sin distinguir mayúsculas
        val coincidencias = alumnos.filter { it.nombre.equals(nombre, ignoreCase = true) }
        if (coincidencias.isEmpty()) {
            println("Error: no existe ningún alumno con ese nombre.")
            return
        }
        // Si solo hay uno, se elimina directamente
        if (coincidencias.size == 1) {
            alumnos.remove(coincidencias[0])
            println("Alumno eliminado correctamente.")
            return
        }
        // Si hay varios con el mismo nombre, pedimos los apellidos
        println("Hay varios alumnos con ese nombre:")
        coincidencias.forEach {
            println("${it.nombre} ${it.apellidos} - Nota: ${it.nota}")
        }
        println("Introduce los apellidos del alumno a eliminar: ")
        val apellidos = readln().trim()
        val alumnoEliminar = coincidencias.find { it.apellidos.equals(apellidos, ignoreCase = true) }
        if (alumnoEliminar == null) {
            println("Error: no existe ningún alumno con ese nombre y apellidos.")
            return
        }
        alumnos.remove(alumnoEliminar)
        println("Alumno eliminado correctamente.")
    }
}
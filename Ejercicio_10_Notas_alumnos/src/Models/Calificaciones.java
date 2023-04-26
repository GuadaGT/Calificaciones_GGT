package Models;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Calificaciones {
    private static final String NOMBRE_ARCHIVO = "calificaciones.txt";
    static Scanner sc = new Scanner(System.in);

    // Función para insertar un alumno y sus calificaciones en el archivo
    public static void insertarAlumno() {
        try {
            FileWriter fw = new FileWriter(NOMBRE_ARCHIVO, true); // FileWriter para escribir en el archivo, con opción de añadir al final
            BufferedWriter bw = new BufferedWriter(fw); // BufferedWriter para mejorar la eficiencia en la escritura
            PrintWriter pw = new PrintWriter(bw); // PrintWriter para escribir en el archivo de manera más conveniente

            System.out.println("Ingrese los datos del alumno:");
            System.out.print("Apellidos: ");
            String apellidos = sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Nota 1: ");
            double nota1 = sc.nextDouble();
            System.out.print("Nota 2: ");
            double nota2 = sc.nextDouble();
            System.out.print("Nota 3: ");
            double nota3 = sc.nextDouble();
            double notaFinal = (nota1 + nota2 + nota3) / 3.0; // Cálculo de la nota final

            pw.println(apellidos + "," + nombre + "," + nota1 + "," + nota2 + "," + nota3 + "," + notaFinal); // Escribir los datos en el archivo
            pw.close(); // Cerrar PrintWriter para asegurar que los datos se escriban en el archivo

            System.out.println("Alumno insertado correctamente en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage()); // Manejo de errores de escritura en el archivo
        }
    }

    // Función para consultar las calificaciones de un alumno por su nombre
    public static void consultarCalificaciones() {
        try {
            FileReader fr = new FileReader(NOMBRE_ARCHIVO); // FileReader para leer el archivo de calificaciones
            BufferedReader br = new BufferedReader(fr); // BufferedReader para mejorar la eficiencia en la lectura

            System.out.print("Ingrese el nombre del alumno: ");
            String nombre = sc.nextLine(); // Leer el nombre del alumno ingresado por el usuario

            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null) { // Leer el archivo línea por línea
                String[] datos = linea.split(","); // Separar los datos de la línea por comas en un arreglo
                if (datos[1].equalsIgnoreCase(nombre)) { // Comparar el nombre ingresado con el nombre en el archivo (ignorando mayúsculas y minúsculas)
                    System.out.println("Calificaciones del alumno " + datos[1] + " " + datos[0] + ":");
                    System.out.println("Nota 1: " + datos[2]); // Mostrar la nota 1 del alumno
                    System.out.println("Nota 2: " + datos[3]); // Mostrar la nota 2 del alumno
                    System.out.println("Nota 3: " + datos[4]); // Mostrar la nota 3 del alumno
                    System.out.println("Nota Final: " + datos[5]); // Mostrar la nota final del alumno
                    encontrado = true; // Marcar como encontrado
                    break; // Salir del bucle while
                }
            }

            if (!encontrado) {
                System.out.println("No se encontraron calificaciones para el alumno con nombre: " + nombre); // Mostrar mensaje si el alumno no fue encontrado en el archivo
            }

            br.close(); // Cerrar BufferedReader para liberar recursos
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage()); // Manejo de errores de lectura del archivo
        }
    }

    public static void modificarCalificacionesAlumno() {
        System.out.println("Introduce el nombre del alumno a modificar: ");
        String nombre = sc.nextLine();
        boolean encontrado = false;

        try {
            // Leer el archivo y guardar los datos en una lista temporal
            File archivo = new File("calificaciones.txt");
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            ArrayList<String> listaAlumnos = new ArrayList<String>();

            while ((linea = br.readLine()) != null) {
                listaAlumnos.add(linea);
            }
            br.close();

            // Buscar el alumno en la lista y modificar sus calificaciones
            for (int i = 0; i < listaAlumnos.size(); i++) {
                String[] datosAlumno = listaAlumnos.get(i).split(",");
                if (datosAlumno[1].trim().equalsIgnoreCase(nombre)) { // Compara el nombre del alumno con el nombre proporcionado por el usuario
                    encontrado = true;
                    System.out.println("Introduce la nueva nota 1: ");
                    int nota1 = sc.nextInt();
                    System.out.println("Introduce la nueva nota 2: ");
                    int nota2 = sc.nextInt();
                    System.out.println("Introduce la nueva nota 3: ");
                    int nota3 = sc.nextInt();
                    sc.nextLine(); // Consumir el salto de línea después de leer el entero

                    double notaFinal = (nota1 + nota2 + nota3) / 3.0;
                    listaAlumnos.set(i, datosAlumno[0] + ", " + datosAlumno[1] + ", " + nota1 + ", " + nota2 + ", " + nota3 + ", " + notaFinal); // Actualiza la línea con las nuevas calificaciones
                    System.out.println("Calificaciones modificadas exitosamente.");
                    break;
                }
            }

            // Guardar la lista actualizada en el archivo
            if (encontrado) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                for (String alumno : listaAlumnos) {
                    bw.write(alumno);
                    bw.newLine();
                }
                bw.close();
            } else {
                System.out.println("No se encontró al alumno en el archivo.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer o escribir en el archivo.");
            e.printStackTrace();
        }
    }

    // Función para eliminar a un alumno
    public static void eliminarAlumno() {
        System.out.println("Introduce el nombre del alumno a eliminar: ");
        String nombre = sc.nextLine();
        boolean encontrado = false;

        try {
            // Leer el archivo y guardar los datos en una lista temporal
            File archivo = new File("calificaciones.txt");
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            ArrayList<String> listaAlumnos = new ArrayList<String>();

            while ((linea = br.readLine()) != null) {
                listaAlumnos.add(linea);
            }
            br.close();

            // Buscar el alumno en la lista y eliminarlo
            for (int i = 0; i < listaAlumnos.size(); i++) {
                String[] datosAlumno = listaAlumnos.get(i).split(",");
                if (datosAlumno[1].trim().equalsIgnoreCase(nombre)) { // Compara el nombre del alumno con el nombre proporcionado por el usuario
                    encontrado = true;
                    listaAlumnos.remove(i); // Elimina la línea correspondiente al alumno
                    System.out.println("Alumno eliminado exitosamente.");
                    break;
                }
            }

            // Guardar la lista actualizada en el archivo
            if (encontrado) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                for (String alumno : listaAlumnos) {
                    bw.write(alumno);
                    bw.newLine();
                }
                bw.close();
            } else {
                System.out.println("No se encontró al alumno en el archivo.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer o escribir en el archivo.");
            e.printStackTrace();
        }
    }
    // Función para mostrar información de todos los alumnos ordenados por apellidos
    public static void mostrarAlumnosOrdenadosPorApellidos() {
        try {
            File archivo = new File(NOMBRE_ARCHIVO);
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            ArrayList<String> alumnos = new ArrayList<>();

            // Leer el archivo y guardar los datos en una lista temporal
            while ((linea = br.readLine()) != null) {
                alumnos.add(linea);
            }
            br.close();

            if (!alumnos.isEmpty()) {
                System.out.println("Información de todos los alumnos ordenados por apellidos:");
                System.out.println("Apellidos | Nombre | Nota1 | Nota2 | Nota3 | NotaFinal");

                // Ordenar la lista de alumnos por apellidos utilizando un comparador
                Collections.sort(alumnos, new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        String[] datos1 = o1.split(",");
                        String[] datos2 = o2.split(",");
                        return datos1[0].compareToIgnoreCase(datos2[0]);
                    }
                });

                // Mostrar la información de los alumnos ordenada por apellidos
                for (String alumno : alumnos) {
                    System.out.println(alumno);
                }
            } else {
                System.out.println("No hay alumnos registrados.");
            }
        } catch (IOException e) {
            System.out.println("Error al mostrar la información de los alumnos: " + e.getMessage());
        }
    }

    // Función para mostrar alumnos aprobados
    public static void mostrarAlumnosAprobados() {
        try {
            File archivo = new File(NOMBRE_ARCHIVO);
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            ArrayList<String> alumnosAprobados = new ArrayList<>();

            // Leer el archivo y guardar los datos de los alumnos aprobados en una lista temporal
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                double notaFinal = Double.parseDouble(datos[5]);
                if (notaFinal >= 5.0) {
                    alumnosAprobados.add(linea);
                }
            }
            br.close();

            if (!alumnosAprobados.isEmpty()) {
                System.out.println("Información de alumnos aprobados:");
                System.out.println("Apellidos | Nombre | Nota1 | Nota2 | Nota3 | NotaFinal");

                // Mostrar la información de los alumnos aprobados
                for (String alumno : alumnosAprobados) {
                    System.out.println(alumno);
                }
            } else {
                System.out.println("No hay alumnos aprobados.");
            }
        } catch (IOException e) {
            System.out.println("Error al mostrar la información de los alumnos aprobados: " + e.getMessage());
        }
    }

    // Función para mostrar el número total de suspensos
    public static void mostrarNumeroTotalSuspensos() {
        try {
            File archivo = new File(NOMBRE_ARCHIVO);
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            int numSuspensos = 0;

            // Leer el archivo y contar el número de alumnos con nota final menor a 5.0
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                double notaFinal = Double.parseDouble(datos[5]);
                if (notaFinal < 5.0) {
                    numSuspensos++;
                }
            }
            br.close();

            System.out.println("Número total de suspensos: " + numSuspensos);
        } catch (IOException e) {
            System.out.println("Error al mostrar el número total de suspensos: " + e.getMessage());
        }
    }
}

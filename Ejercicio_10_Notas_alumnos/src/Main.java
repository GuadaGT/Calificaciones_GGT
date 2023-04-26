import Models.Calificaciones;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int opcion = 0;
        do {
            menu();
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    Calificaciones.insertarAlumno();
                    break;
                case 2:
                    Calificaciones.consultarCalificaciones();
                    break;
                case 3:
                    Calificaciones.modificarCalificacionesAlumno();
                    break;
                case 4:
                    Calificaciones.eliminarAlumno();
                    break;
                case 5:
                    Calificaciones.mostrarAlumnosOrdenadosPorApellidos();
                    break;
                case 6:
                    Calificaciones.mostrarAlumnosAprobados();
                    break;
                case 7:
                    Calificaciones.mostrarNumeroTotalSuspensos();
                    break;
                case 8:
                    System.out.println("Cerrandi programa. Hasta la proxima.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 8);
        sc.close();
            }


        public static void menu ()
        {
            System.out.println("========== GESTIÓN DE CALIFICACIONES ==========");
            System.out.println("1. Insertar alumno");
            System.out.println("2. Consultar calificaciones de un alumno");
            System.out.println("3. Modificar calificaciones de un alumno");
            System.out.println("4. Eliminar a un alumno");
            System.out.println("5. Mostrar información de todos los alumnos ordenados por apellidos");
            System.out.println("6. Mostrar alumnos aprobados");
            System.out.println("7. Mostrar número total de suspensos");
            System.out.println("8. Salir");
            System.out.print("Ingrese la opción deseada: ");
        }
    }


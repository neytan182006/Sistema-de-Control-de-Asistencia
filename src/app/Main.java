package app;

import dao.AsistenciaDAO;
import dao.EmpleadoDAO;
import modelo.Empleado;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final Scanner TECLADO = new Scanner(System.in);
    private static final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private static final AsistenciaDAO asistenciaDAO = new AsistenciaDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            try {
                switch (opcion) {
                    case 1 -> listarEmpleados();
                    case 2 -> registrarEntrada();
                    case 3 -> registrarSalida();
                    case 4 -> asistenciaDAO.imprimirReporteDelDia();
                    case 0 -> System.out.println("Hasta luego.");
                    default -> System.out.println("Opcion invalida.");
                }
            } catch (SQLException e) {
                System.out.println("Error de base de datos: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== CONTROL DE ASISTENCIA ===");
        System.out.println("1. Listar empleados");
        System.out.println("2. Registrar entrada");
        System.out.println("3. Registrar salida");
        System.out.println("4. Reporte de asistencia de hoy");
        System.out.println("0. Salir");
    }

    private static void listarEmpleados() throws SQLException {
        Empleado[] empleados = empleadoDAO.listarComoArreglo();
        if (empleados.length == 0) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }

    private static void registrarEntrada() throws SQLException {
        int idEmpleado = leerEntero("Id del empleado (ver opcion 1): ");
        asistenciaDAO.registrarEntrada(idEmpleado);
        System.out.println("Entrada registrada.");
    }

    private static void registrarSalida() throws SQLException {
        int idEmpleado = leerEntero("Id del empleado (ver opcion 1): ");
        asistenciaDAO.registrarSalida(idEmpleado);
        System.out.println("Salida registrada.");
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!TECLADO.hasNextInt()) {
            System.out.print("Ingrese un numero valido: ");
            TECLADO.next();
        }
        int valor = TECLADO.nextInt();
        TECLADO.nextLine();
        return valor;
    }
}

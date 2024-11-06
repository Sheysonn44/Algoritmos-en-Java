/**
 * Clase principal para el sistema de segmentación de procesos.
 * Permite al usuario añadir, ejecutar, terminar y visualizar procesos.
 */
package segmentacion;

import java.util.Scanner;

/**
 * Clase Segmentacion contiene el menú principal y maneja la interacción con el usuario.
 * @author Adrián
 * @author Jeison
 * @author Jocelyn
 */
public class Segmentacion {

    /**
     * Escáner para la entrada de datos por parte del usuario.
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Instancia de la clase Procesos para manejar las operaciones de procesos.
     */
    static Procesos pr = new Procesos();

    /**
     * Método principal que inicia el programa.
     *
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
        imprimirMenu();
        pr.añadirProceso();
        pr.mostrarListaProcesos();
    }

    /**
     * Imprime el menú y gestiona las opciones elegidas por el usuario.
     */
    public static void imprimirMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------");
        System.out.println("          MENU");
        System.out.println("1. Ingresar proceso");
        System.out.println("2. Ejecutar proceso");
        System.out.println("3. Mostrar lista de solicitudes de procesos");
        System.out.println("4. Terminar proceso");
        System.out.println("5. Mostrar procesos finalizados");
        System.out.println("00. Cerrar programa");
        System.out.println("-------------------------------");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                pr.añadirProceso();
                pr.mostrarListaProcesos();
                imprimirMenu();
                break;

            case 2:
                pr.validacion();               
                imprimirMenu();
                break;

            case 3:
                pr.mostrarListaProcesos();
                imprimirMenu();
                break;

            case 4:
                pr.terminarProceso();
                imprimirMenu();
                break;

            case 5:
                pr.mostrarProcesosTerminados();
                imprimirMenu();
                break;

            case 00:
                System.exit(opcion);

            default:
                System.out.println("Opcion invalida");
                imprimirMenu();
        }
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algoritmos_ubicacion;

import java.util.Scanner;

/**
 * Clase main donde llama 4 diferentes menus con el fin de que se cumpla la 
 * implementacion manual y automatica
 * @author Adrián
 * @author Jeison
 * @author Jocelyn
 */
public class ALgoritmos_Ubicacion {
    /**
     * Instancia de la clase Ubicacion
     */
    private static Ubicacion ub = new Ubicacion();

    /**
     * Metodo Main
     * @param args
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Ubicacion ubi = new Ubicacion();
       imprimirMenu();

    }

    public static void imprimirMenu() {
        Scanner scanner = new Scanner(System.in);     
        System.out.println("-------------------------------");
        System.out.println("          MENU");
        System.out.println("1. Manera Manual");
        System.out.println("2. Manera Automatizada");
        System.out.println("3. Mostrar memoria");
        System.out.println("4. Mostrar procesos");
        System.out.println("00. Cerrar programa");
        System.out.println("-------------------------------");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                imprimirMenuManual();
                imprimirMenu();
                break;

            case 2:
                imprimirMenuAjustes();
                imprimirMenu();
                break;

            case 3:
                ub.crearMemoria();
                ub.imprimirRAM();
                ub.reiniciarLista();
                ub.reiniciarMemoria();
                imprimirMenu();
                break;

            case 4:
                ub.listaProcesos();
                ub.imprimirProcesos();
                ub.reiniciarLista();
                ub.reiniciarMemoria();
                imprimirMenu();
                break;
            case 00:
                System.exit(opcion);

            default:
                System.out.println("Opcion invalida");
                imprimirMenu();
        }
    }

    public static void imprimirMenuManual() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("          MENU");
        System.out.println("1. Ingresar proceso");
        System.out.println("2. Ejecutar proceso");
        System.out.println("3. Mostrar lista de solicitudes de procesos");
        System.out.println("4. Terminar proceso");
        System.out.println("5. Volver atras");
        System.out.println("00. Cerrar programa");
        System.out.println("-------------------------------");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:              
                ub.añadirProceso();
                imprimirMenuManual();
                break;

            case 2:
                imprimirMenuAjustesManual();
                imprimirMenu();
                break;

            case 3:
                ub.mostrarListaProcesos();
                imprimirMenuManual();
                break;

            case 4:
                ub.terminarProceso();
                imprimirMenuManual();
                break;
            case 5:
                imprimirMenu();
                break;

            case 00:
                System.exit(opcion);

            default:
                System.out.println("Opcion invalida");
                imprimirMenu();
        }
    }

    public static void imprimirMenuAjustes() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-------------------------------");
        System.out.println("    Algoritmos de ubicacion");
        System.out.println("1. Primer Ajuste");
        System.out.println("2  Mejor Ajuste");
        System.out.println("3. Siguiente Ajuste");
        System.out.println("4. Peor Ajuste");
        System.out.println("5. Volver al menu");
        System.out.println("00. Cerrar programa");
        System.out.println("-------------------------------");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                ub.listaProcesos();
                ub.crearMemoria();
                ub.PrimerAjuste();
                ub.imprimirRAM();
                ub.mostrarAsignacionFinal();
                ub.reiniciarLista();
                ub.reiniciarMemoria();               
                imprimirMenu();
                break;

            case 2:
                ub.crearMemoria();
                ub.listaProcesos();
                ub.imprimirRAM();              
                System.out.println("------------------------------");
                for (Proceso object : ub.procesos) {
                   ub.MejorAjuste(object);
                }
                ub.imprimirRAM();
                ub.mostrarAsignacionFinal(); 
                ub.reiniciarMemoria();
                ub.reiniciarLista();
                imprimirMenu();
                break;

            case 3:
                ub.listaProcesos();
                ub.crearMemoria();
                ub.SiguienteAjuste();
                ub.imprimirRAM();
                ub.mostrarAsignacionFinal();
                ub.reiniciarMemoria();
                ub.reiniciarLista();
                imprimirMenu();
                break;

            case 4:    
                ub.crearMemoria();
                ub.listaProcesos();
                ub.imprimirRAM();
                ub.imprimirProcesos();
                System.out.println("------------------------------");
                for (Proceso object : ub.procesos) {
                    ub.peorAjuste(object);
                }
                ub.imprimirRAM();
                ub.mostrarAsignacionFinal();
                ub.reiniciarMemoria();
                ub.reiniciarLista();
                imprimirMenu();
                break;

            case 5:
                imprimirMenu();
                break;

            case 00:
                System.exit(opcion);

            default:
                System.out.println("Opcion invalida");
                imprimirMenu();
        }
    }
    public static void imprimirMenuAjustesManual() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-------------------------------");
        System.out.println("    Algoritmos de ubicacion");
        System.out.println("1. Primer Ajuste");
        System.out.println("2  Mejor Ajuste");
        System.out.println("3. Siguiente Ajuste");
        System.out.println("4. Peor Ajuste");
        System.out.println("5. Volver al menu");
        System.out.println("00. Cerrar programa");
        System.out.println("-------------------------------");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        if(ub.memoria.isEmpty()){
            ub.crearMemoria();
        }
        switch (opcion) {
            case 1:
         
                ub.PrimerAjuste();
                ub.imprimirRAM();
                ub.mostrarAsignacionFinal();
                           
                imprimirMenuManual();
                break;

            case 2:
                System.out.println("------------------------------");
                ub.imprimirRAM();
                for (Proceso object : ub.procesos) {
                   ub.MejorAjuste(object);
                }
                ub.mostrarAsignacionFinal();
                ub.imprimirRAM();
                imprimirMenuManual();
                break;

            case 3:
               
                ub.SiguienteAjuste();
                ub.imprimirRAM();              
                            
                imprimirMenuManual();
                break;

            case 4:    
                System.out.println("------------------------------");
                for (Proceso object : ub.procesos) {
                    ub.peorAjuste(object);
                }               
                ub.mostrarAsignacionFinal();    
                ub.imprimirRAM();
                imprimirMenuManual();
                break;

            case 5:
                ub.reiniciarMemoria();
                ub.reiniciarLista();
                imprimirMenu();
                break;

            case 00:
                System.exit(opcion);

            default:
                System.out.println("Opcion invalida");
                ub.reiniciarLista();
                ub.reiniciarMemoria();
                imprimirMenu();
        }
    }
}

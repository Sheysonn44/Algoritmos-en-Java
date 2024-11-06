/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paginacion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Clase que presenta las caracteristicas de los procesos en un sistema de
 * paginacion y proporciona metodos para la asignacion de la memoria y su
 * estado, disponible, en ejecución en memoria principal, en memoria
 * secundaria, finalizado.
 *
 * @author Jeison Alvarez
 * @author Adrian Chavarria
 * @author Jocelyn Abarca
 *
 */
public class Calculos {

    private String estado;
  
    /**
     * Atributos para la gestion de procesos y memoria
     */
    ArrayList<Proceso> memoriaProcesos = new ArrayList<>();
    String[] memoriaPrincipal = new String[8];
    String[] memoriaSecundaria = new String[16];
    Scanner scanner = new Scanner(System.in);
     /**
     * Metodo para agregar un nuevo proceso a la lista de procesos.
     */
    public void addProcess() {
        System.out.println("Ingrese el nombre del proceso:");
        String NombreProceso = scanner.nextLine();
        System.out.println("Ingrese el numero de paginacion:");
        int NumPaginacion = scanner.nextInt();
        scanner.nextLine();
        estado = "DISPONIBLE";

        Boolean[] estadoPagina = asignarEstadoPaginas(NumPaginacion);
        Proceso nuevoProceso = new Proceso(NumPaginacion, NombreProceso, estado, estadoPagina);
        memoriaProcesos.add(nuevoProceso);
        System.out.println("Proceso ingresado correctamente");
    }
/**
 * Metodo que asigna estados true o false aleatoriamente
 * @param numPaginacion Recibe el numero de paginas de un proceso
 * @return estadoPagina 
 */
    private Boolean[] asignarEstadoPaginas(int numPaginacion) {
        Random random = new Random();
        Boolean[] estadoPagina = new Boolean[numPaginacion];
        for (int i = 0; i < numPaginacion; i++) {
            estadoPagina[i] = random.nextBoolean();
        }
        return estadoPagina;
    }
    /**
     * Metodo asignar memoria que evalua el estado de las paginas 
     * y asi decide en donde los va a almacenar
     * @param procesito recibe el arraylist
     */
    public void asignarMemoria(Proceso procesito) {
        int marcosLibresPrincipal = contarEspaciosLibres(memoriaPrincipal);
        int marcosLibresSecundaria = contarEspaciosLibres(memoriaSecundaria);

        int paginasActivas = contarPaginasPorEstado(procesito, true);
        int paginasBloqueadas = contarPaginasPorEstado(procesito, false);

        if (marcosLibresPrincipal >= paginasActivas && marcosLibresSecundaria >= paginasBloqueadas) {
            procesito.setEstado("En_Ejecucion");
            asignarPaginas(memoriaPrincipal, procesito, true);
            asignarPaginas(memoriaSecundaria, procesito, false);
        } else {
            System.out.println("No hay suficiente espacio en ambas memorias para asignar el proceso.");
        }

        imprimirRAM();
        imprimirDiscoDuro();
    }

    /**
     * Método para contar las páginas activas o bloqueadas en un proceso.
     */
    private int contarPaginasPorEstado(Proceso procesito, boolean estadoPagina) {
        int contador = 0;
        for (Boolean estado : procesito.getEstadoPaginacion()) {
            if (estado == estadoPagina) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Asigna páginas a una memoria específica, dependiendo del estado de las
     * páginas.
     */
    private void asignarPaginas(String[] memoria, Proceso procesito, boolean estadoPaginaDeseado) {
        int paginasAsignadas = 0;
        Boolean[] estadoPaginacion = procesito.getEstadoPaginacion();

        for (int i = 0; i < estadoPaginacion.length; i++) {

            if (estadoPaginacion[i] == estadoPaginaDeseado) {
                for (int j = 0; j < memoria.length; j++) {
                    if (memoria[j] == null) {
                        memoria[j] = procesito.getNombreProceso();
                        paginasAsignadas++;
                        break;
                    }
                }
            }
        }
    }
    /**
     * Cuenta los espacios libres de las memorias
     * @param memoria recibe un arreglo que contiene a la memoria a analizar
     * @return libres devuelve la cantidad de marcos libres
     */
    private int contarEspaciosLibres(String[] memoria) {
        int libres = 0;
        for (String marco : memoria) {
            if (marco == null) {
                libres++;
            }
        }
        return libres;
    }
    /**
     * Libera los marcos de la memoria que recibe
     * @param memoria
     * @param nombreProceso 
     */
    public void liberarMarcos(String[] memoria, String nombreProceso) {
        for (int i = 0; i < memoria.length; i++) {
            if (nombreProceso.equals(memoria[i])) {
                memoria[i] = null;
            }
        }
        System.out.println("Los marcos del proceso '" + nombreProceso + "' fueron liberados");
    }

    /**
     * Metodo para mostrar la lista de procesos disponibles para ejecutar.
     */
    public void mostrarProcesosDisponibles() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getEstado().equals("DISPONIBLE")) {
                System.out.println(proceso.toString());
            }
        }
    }

    /**
     * Metodo para mostrar los procesos que estan en ejecucion.
     */
    public void mostrarProcesosEj() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getEstado().equals("En_Ejecucion") ) {     
                System.out.println(proceso.toString());

            }
        }
    }

    /**
     * Metodo que muestra la lista de procesos almacenados.
     */
    public void mostrarProcesos() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : memoriaProcesos) {
            for (int i = 0; i < proceso.getNumPaginacion(); i++) {
                if (proceso.getEstadoPaginacion()[i]) {
                    System.out.println(proceso.toString() + "-- Estado pagina: " + "Activo");
                } else {
                    System.out.println(proceso.toString() + "-- Estado pagina: " + "Bloqueado");
                }
            }
        }
    }

    /**
     * Metodo que muestra la lista de los procesos finalizados.
     */
    public void mostrarProcesosFinalizados() {
        System.out.println("Lista de procesos finalizados:\n");
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getEstado().equals("Finalizado")) {
                System.out.println(proceso.toString());
            }
        }
    }
    /**
     * Metodo para mover un proceso, ya sea a la principal
     * secundaria o finalizarlo
     * y genera nuevos estados a las paginas
     */
    public void moverProceso() {
        mostrarProcesosEj();
        System.out.println("¿Cuál proceso quiere mover?");
        String resp = scanner.next();
        Proceso procesito = encontrarProcesoPorNombre(resp);

        if (procesito != null) {
            procesito.setEstado("Finalizado");
            System.out.println("Seleccione destino:\n1. Principal\n2. Secundaria\n3. Finalizar");
            int op = scanner.nextInt();

            switch (op) {
                case 1 -> {
                    liberarMarcos(memoriaPrincipal, procesito.getNombreProceso());
                    liberarMarcos(memoriaSecundaria, procesito.getNombreProceso());
                    generarNuevosEstadosDePagina(procesito); 
                    asignarMemoria(procesito);
                    break;
                }
                case 2 -> {
                    liberarMarcos(memoriaSecundaria, procesito.getNombreProceso());
                    liberarMarcos(memoriaPrincipal, procesito.getNombreProceso());
                    generarNuevosEstadosDePagina(procesito); 
                    asignarMemoria(procesito);
                    break;
                }
                case 3 ->{
                    procesito.setEstado("Finalizado");
                    liberarMarcos(memoriaSecundaria, procesito.getNombreProceso());
                    liberarMarcos(memoriaPrincipal, procesito.getNombreProceso());
                    
                }
                default ->
                    System.out.println("Opción no válida");
            }
        } else {
            System.out.println("Ese proceso no existe...");
        }
    }

    /**
     * Genera nuevos estados de página (activo/bloqueado) para el proceso.
     */
    private void generarNuevosEstadosDePagina(Proceso procesito) {
        Random random = new Random();
        Boolean[] nuevoEstadoPagina = new Boolean[procesito.getNumPaginacion()];

        for (int i = 0; i < nuevoEstadoPagina.length; i++) {
            nuevoEstadoPagina[i] = random.nextBoolean(); // Activo (true) o Bloqueado (false)
        }

        procesito.setEstadoPaginacion(nuevoEstadoPagina); // Actualiza el estado de paginación en el proceso
    }

    /**
     * Valida la existencia de un proceso y lo ejecuta si está disponible.
     */
    public void validacion() {
        boolean validacion = false;
        Proceso procesito = null;
        mostrarProcesosDisponibles();
        System.out.println("-------------------------------");
        System.out.println("Cual proceso quiere ejecutar? \n");
        String respuesta = scanner.next().trim();
        for (Proceso m : memoriaProcesos) {
            if (m.getNombreProceso().equals(respuesta)) {
                procesito = m;
                validacion = true;
                break;
            }
        }
        if (validacion) {
            asignarMemoria(procesito);
        } else {
            System.out.println("Proceso inexistente");
        }
    }
    /**
     * 
     * @param nombre Recibe el nombre del proceso y lo busca
     * @return 
     */
    private Proceso encontrarProcesoPorNombre(String nombre) {
        for (Proceso proceso : memoriaProcesos) {
            if (proceso.getNombreProceso().equals(nombre)) {
                return proceso;
            }
        }
        return null;
    }
    /**
     * Imprime la memoria principal
     */
    public void imprimirRAM() {
        System.out.println("Memoria Principal:");
        for (int i = 0; i < memoriaPrincipal.length; i++) {
            System.out.println(" [" + i + "]: " + memoriaPrincipal[i]);
        }
    }
    /**
     * Imprime la memoria secundaria
     */
    public void imprimirDiscoDuro() {
        System.out.println("Memoria Secundaria:");
        for (int i = 0; i < memoriaSecundaria.length; i++) {
            System.out.println("[" + i + "]: " + memoriaSecundaria[i]);
        }
    }
}

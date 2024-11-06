/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package segmentacion;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa un proceso con su nombre, tamaño y estado. Permite la
 * gestión de procesos en una lista y en bloques de memoria. Además, realiza
 * operaciones de asignación, liberación y compactación de memoria.
 *
 * @author Adrián
 * @author Jeison
 * @author Jocelyn
 */
public class Procesos {

    private String nombre_Proceso;
    private int tamaño_Proceso;
    private int estado;
    ArrayList<Procesos> procesosTerminados = new ArrayList<>();

    /**
     * Constructor que inicializa un proceso con el nombre, tamaño y estado
     * especificados.
     *
     * @param nombre_Proceso Nombre del proceso
     * @param tamaño_Proceso Tamaño del proceso en MB
     * @param estado Estado del proceso
     */
    public Procesos(String nombre_Proceso, int tamaño_Proceso, int estado) {
        this.nombre_Proceso = nombre_Proceso;
        this.tamaño_Proceso = tamaño_Proceso;
        this.estado = estado;

    }

    public Procesos() {
    }

    public String getNombre_Proceso() {
        return nombre_Proceso;
    }

    public void setNombre_Proceso(String nombre_Proceso) {
        this.nombre_Proceso = nombre_Proceso;
    }

    public int getTamaño_Proceso() {
        return tamaño_Proceso;
    }

    public void setTamaño_Proceso(int tamaño_Proceso) {
        this.tamaño_Proceso = tamaño_Proceso;
    }

    public int getestado() {
        return estado;
    }

    public void setestado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Procesos{" + "nombre_Proceso: " + nombre_Proceso + ", tama\u00f1o_Proceso: " + tamaño_Proceso + '}';
    }

    ArrayList<Procesos> listaProcesos = new ArrayList<>();
    ArrayList<Procesos> procesosEnEjecucion = new ArrayList<>();
    String[] bloqueMemoria = new String[15];
    Scanner scanner = new Scanner(System.in);

    /**
     * Añade un proceso a la lista de procesos pendientes.
     */
    public void añadirProceso() {
        System.out.println("Ingrese el nombre del proceso:");
        nombre_Proceso = scanner.nextLine();
        System.out.println("Ingrese el tamaño del proceso en Mb :");
        tamaño_Proceso = scanner.nextInt();
        scanner.nextLine();
        estado = 0;

        Procesos nuevoProceso = new Procesos(nombre_Proceso, tamaño_Proceso, estado);
        listaProcesos.add(nuevoProceso);
        System.out.println("Proceso insertado correctamente en la lista de solicitudes");
    }

    /**
     * Muestra la lista de procesos almacenados.
     */
    public void mostrarListaProcesos() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Procesos proceso : listaProcesos) {
            System.out.println(proceso.toString());
        }
    }

    /**
     * Asigna un bloque de memoria al proceso especificado.
     *
     * @param proceso Proceso al que se le asignará un bloque de memoria
     */
    public void asignarBloqueMemoria(Procesos proceso) {
        int bloquesAsignados = 0;
        int bloquesLibres = 0;

        System.out.println("-------------------------------");
        for (String bloque : bloqueMemoria) {
            if (bloque == null) {
                bloquesLibres++;
            }
        }

        if (procesosEnEjecucion.contains(proceso)) {
            System.out.println("El proceso ya se está ejecutando");
            return;
        }

        if (bloquesLibres < proceso.getTamaño_Proceso()) {
            System.out.println("No hay suficiente espacio en el bloque de memoria para almacenar este proceso");

            return;
        }

        int espacioContinuo = verificarEspacioContinuo(proceso.getTamaño_Proceso());

        if (espacioContinuo == -1) {
            System.out.println("No hay espacios continuos disponibles para el proceso.");
            for (String bloque : bloqueMemoria) {
                if (bloque == null) {
                    bloquesLibres++;

                }
            }
            if (bloquesLibres < proceso.getTamaño_Proceso()) {
                System.out.println(" Y no se puede compactar");

            } else {
                System.out.println("Desea compactar la memoria, elija 1.siquiero compactar/2.No deseo compactar");
                Scanner scanner = new Scanner(System.in);
                int respuesta = scanner.nextInt();
                if (respuesta == 1) {
                    compactacion();
                    System.out.println("Memoria compactada. Intentando asignar el proceso nuevamente...");
                    // Recalcular los bloques libres
                    bloquesLibres = 0;
                    for (String bloque : bloqueMemoria) {
                        if (bloque == null) {
                            bloquesLibres++;

                        }
                    }
                    asignarBloqueMemoria(proceso);
                } else {
                    System.out.println("El proceso no puede ser asignado. No se realizará la compactación.");
                    return;
                }

            }

            return;
        }

        for (int i = espacioContinuo; i < espacioContinuo + proceso.getTamaño_Proceso(); i++) {
            bloqueMemoria[i] = proceso.getNombre_Proceso();
            bloquesAsignados++;
        }

        proceso.setestado(1);
        listaProcesos.remove(proceso);
        procesosEnEjecucion.add(proceso);
        mostrarBloqueMemoria();

        int memoriaDisponible = bloquesLibres - bloquesAsignados;
        System.out.println("Proceso: " + proceso.getNombre_Proceso() + " de tamaño:" + proceso.getTamaño_Proceso() + "MB, ejecutado exitosamente");
        System.out.println("Memoria disponible después de la asignación: " + memoriaDisponible + "MB");
    }

    /**
     * Verifica si hay espacio continuo disponible en memoria para un proceso de
     * tamaño dado.
     *
     * @param tamañoProceso Tamaño del proceso en MB
     * @return Índice inicial de espacio continuo o -1 si no hay suficiente
     * espacio continuo
     */
    private int verificarEspacioContinuo(int tamañoProceso) {
        int contadorEspacio = 0;
        int inicioEspacio = -1;

        for (int i = 0; i < bloqueMemoria.length; i++) {
            if (bloqueMemoria[i] == null) {

                if (contadorEspacio == 0) {
                    inicioEspacio = i;

                }
                contadorEspacio++;
                if (contadorEspacio == tamañoProceso) {
                    return inicioEspacio;

                }
            } else {
                contadorEspacio = 0;
            }

        }
        return -1;
    }

    /**
     * Compacta la memoria moviendo los procesos activos hacia el inicio del
     * bloque de memoria.
     */
    public void compactacion() {
        int indiceCompactacion = 0;

        for (int i = 0; i < bloqueMemoria.length; i++) {
            if (bloqueMemoria[i] != null) {
                bloqueMemoria[indiceCompactacion] = bloqueMemoria[i];

                if (i != indiceCompactacion) {
                    bloqueMemoria[i] = null;
                }
                indiceCompactacion++;

            }

        }
        System.out.println("Memoria Compactada");
        mostrarBloqueMemoria();
    }

    /**
     * Muestra el estado actual del bloque de memoria.
     */
    public void mostrarBloqueMemoria() {
        System.out.println("-------------------------------");
        System.out.println("Estado del bloque de memoria:");
        for (String bloque : bloqueMemoria) {
            System.out.print("[" + (bloque != null ? bloque : "libre") + "] ");
        }
        System.out.println();
    }

    /**
     * Valida si el proceso especificado está en la lista de procesos pendientes
     * para ser asignado a memoria.
     */
    public void validacion() {
        boolean validacion = false;
        Procesos proceso = null;
        mostrarListaProcesos();
        System.out.println("-------------------------------");
        System.out.println("Cual proceso quiere almacenar en el bloque de memoria? \n");
        String respuesta = scanner.next().trim();
        for (Procesos m : listaProcesos) {
            if (m.getNombre_Proceso().equals(respuesta)) {
                proceso = m;
                validacion = true;
                break;
            }
        }
        if (validacion) {
            asignarBloqueMemoria(proceso);
        } else {
            System.out.println("Proceso inexistente");
        }
    }

    /**
     * Libera los marcos de memoria ocupados por un proceso específico.
     *
     * @param nombreProceso Nombre del proceso a liberar
     */
    public void liberarMarcos(String nombreProceso) {
        for (int i = 0; i < bloqueMemoria.length; i++) {
            if (bloqueMemoria[i] != null && bloqueMemoria[i].equals(nombreProceso)) {
                bloqueMemoria[i] = null;
            }
        }
        System.out.println("Los MB del proceso '" + nombreProceso + "' fueron liberados");
    }

    /**
     * Muestra la lista de procesos en ejecución en el bloque de memoria.
     */
    public void mostrarProcesosEj() {
        System.out.println("Lista de procesos almacenados en el bloque de memoria:\n");
        if (procesosEnEjecucion.isEmpty()) {
            System.out.println("No hay procesos almacenados en el bloque de memoria");
            return;
        }
        for (Procesos proceso : procesosEnEjecucion) {
            System.out.println(proceso.toString());
        }

    }

    /**
     * Termina un proceso en ejecución y libera su espacio en memoria.
     */
    public void terminarProceso() {
        if (procesosEnEjecucion.isEmpty()) {
            System.out.println("No hay procesos en ejecución");
            return;
        }
        mostrarProcesosEj();
        System.out.println("-------------------------------");
        System.out.println("¿Cuál proceso quiere finalizar?");
        String resp = scanner.next();
        Procesos proceso = null;
        for (Procesos m : procesosEnEjecucion) {
            if (m.getNombre_Proceso().equals(resp)) {
                proceso = m;
                break;
            }
        }

        if (proceso != null) {
            liberarMarcos(proceso.getNombre_Proceso());
            proceso.setestado(-1);
            procesosEnEjecucion.remove(proceso);
            procesosTerminados.add(proceso);
            System.out.println("Proceso " + proceso.getNombre_Proceso() + " finalizado y memoria liberada.");
        } else {
            System.out.println("Proceso no encontrado en ejecución.");
        }

        System.out.println("Bloque de Memoria\n");
        mostrarBloqueMemoria();
    }

    /**
     * Muestra la lista de procesos que han terminado.
     */
    public void mostrarProcesosTerminados() {
        System.out.println("Lista de procesos que han terminado:");
        if (procesosTerminados.isEmpty()) {
            System.out.println("No hay procesos que hayan terminado.");
        } else {
            for (Procesos proceso : procesosTerminados) {
                System.out.println(proceso.toString());
            }
        }
    }

}

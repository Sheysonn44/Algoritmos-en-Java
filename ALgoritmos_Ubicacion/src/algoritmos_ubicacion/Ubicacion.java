/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos_ubicacion;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase ubicacion donde tenemos todos los calculos para los diferentes algoritmos
 * 
 * @author Adrián
 * @author Jeison
 * @author Jocelyn
 */
public class Ubicacion {

    ArrayList<Proceso> procesos = new ArrayList<>();
    ArrayList<Memoria> memoria = new ArrayList<>();
    ArrayList<Proceso> procesosNoAsignados = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    /**
     * Metodo que establece los valores y tamaños de los bloques de la memoria
     */
    public void crearMemoria() {
        memoria.add(new Memoria("B1", 1000, "", 1000));
        memoria.add(new Memoria("B2", 500, "", 500));
        memoria.add(new Memoria("B3", 800, "", 800));
        memoria.add(new Memoria("B4", 200, "", 200));
        memoria.add(new Memoria("B5", 600, "", 600));
        memoria.add(new Memoria("B6", 400, "", 400));
    }

    public void reiniciarMemoria() {
        memoria.clear();
    }

    /**
     * Añade un proceso a la lista de procesos pendientes.
     */
    public void añadirProceso() {
        System.out.println("Ingrese el nombre del proceso:");
        String nombre_Proceso = scanner.nextLine();
        System.out.println("Ingrese el tamaño del proceso en Mb :");
        int tamaño_Proceso = scanner.nextInt();
        scanner.nextLine();
        String estado = "En cola";
        Proceso nuevoProceso = new Proceso(nombre_Proceso, tamaño_Proceso, estado);
        procesos.add(nuevoProceso);
        System.out.println("Proceso insertado correctamente en la lista de solicitudes");
    }

    /**
     * Muestra la lista de procesos almacenados.
     */
    public void mostrarListaProcesos() {
        System.out.println("Lista de procesos almacenados:\n");
        for (Proceso proceso : procesos) {
            System.out.println(proceso.toString());
        }
    }

    /**
     * Termina un proceso en ejecución y libera su espacio en memoria.
     */
    public void terminarProceso() {
        if (procesos.isEmpty()) {
            System.out.println("No hay procesos en ejecución");
            return;
        }
        mostrarProcesosEj();
        System.out.println("-------------------------------");
        System.out.println("¿Cuál proceso quiere finalizar?");
        String resp = scanner.next();
        Proceso proceso = null;
        for (Proceso m : procesos) {
            if (m.getNombre_Proceso().equals(resp)) {
                proceso = m;
                break;
            }
        }

        if (proceso != null) {
            liberarMarcos(proceso.getNombre_Proceso(), proceso.getTamaño_Proceso());
            proceso.setEstado("Finalizado");
            procesos.remove(proceso);
            System.out.println("Proceso " + proceso.getNombre_Proceso() + " finalizado y memoria liberada.");
        } else {
            System.out.println("Proceso no encontrado en ejecución.");
        }

        System.out.println("Bloque de Memoria\n");
        imprimirRAM();
    }

    /**
     * Muestra la lista de procesos en ejecución en el bloque de memoria.
     */
    public void mostrarProcesosEj() {
        System.out.println("Lista de procesos almacenados en el bloque de memoria:\n");
        if (procesos.isEmpty()) {
            System.out.println("No hay procesos almacenados en el bloque de memoria");
            return;
        }
        for (Proceso proceso : procesos) {
            System.out.println(proceso.toString());
        }

    }

    /**
     * Libera los marcos de memoria ocupados por un proceso específico.
     *
     * @param nombreProceso Nombre del proceso a liberar
     */
    public void liberarMarcos(String nombreProceso, int tama) {
        for (Memoria memo : memoria) {
            if (memo != null && memoria.equals(nombreProceso)) {
                memo.setEstado(String.valueOf(nombreProceso));
                memo.setEspacio_Libre(memo.getEspacio_Libre() + tama);
            }
        }
        System.out.println("Los MB del proceso '" + nombreProceso + "' fueron liberados");
    }

    /**
     * Metodo asignar memoria que evalua el estado de las paginas y asi decide
     * en donde los va a almacenar
     */
    public void PrimerAjuste() {
        System.out.println("--------------Siguiente ajuste--------------");
        for (Proceso proceso : procesos) {
            if (proceso.getEstado().equals("En cola")) { // Verifica si el proceso está en cola
                for (Memoria memo : memoria) {
                    if (memo.getEspacio_Libre() >= proceso.getTamaño_Proceso()) {
                        // Asigna el proceso al bloque de memoria encontrado
                        memo.setEspacio_Libre(memo.getEspacio_Libre() - proceso.getTamaño_Proceso());
                        memo.setEstado(memo.getEstado() + proceso.getNombre_Proceso() + ",");
                        proceso.setEstado("En ejecucion"); // Cambia el estado del proceso a "En ejecucion"
                        procesosNoAsignados.remove(proceso);
                        break; // Salir del ciclo una vez que se ha asignado
                    } else {
                        proceso.setEstado("No_Asignado");
                    }
                }
            }
            if (proceso.getEstado().equals("No_Asignado")) {
                procesosNoAsignados.add(proceso);
            }
        }
    }
    /**
     * Metodo para agregar procesos a la lista
     * @param proceso
     */
    public void agregarProceso(Proceso proceso) {
        procesos.add(proceso);
        System.out.println("Proceso " + proceso.getNombre_Proceso() + " agregado a la lista.");
    }

    /**
     * Metodo que encuentra el bloque de memoria mas
     * pequeño
     * @param proceso
     * @return
     */
    private Memoria encontrarBloquePequeño(Proceso proceso) {

        Memoria bloqueMasPequeño = null;

        for (Memoria bloque : memoria) {

            if (bloque.getEspacio_Libre() >= proceso.getTamaño_Proceso()) {
                if (bloqueMasPequeño == null || bloque.getEspacio_Libre() < bloqueMasPequeño.getEspacio_Libre()) {

                    bloqueMasPequeño = bloque;

                }

            }
        }

        return bloqueMasPequeño;
    }
    /**
     * Algoritmo de mejor ajuste que recibe la lista y realiza los
     * calculos para encontrar la asignacion de memoria
     * @param proceso
     */
    public void MejorAjuste(Proceso proceso) {

        // aqui se hace la segunda vuelta donde se asigna ya el proceso en la memoria
        Memoria bloqueMasPequeño = encontrarBloquePequeño(proceso);

        if (bloqueMasPequeño != null && proceso.getEstado().equals("En cola")) {

            int espacioRestante = bloqueMasPequeño.getEspacio_Libre() - proceso.getTamaño_Proceso();

            bloqueMasPequeño.setEspacio_Libre(espacioRestante);
            bloqueMasPequeño.setEstado(bloqueMasPequeño.getEstado()+ proceso.getNombre_Proceso() + ",");

            proceso.setEstado("En ejecucion");
            proceso.setBloqueAsignado(bloqueMasPequeño);

            System.out.println("Proceso: " + proceso.getNombre_Proceso() + " asignado al bloque " + bloqueMasPequeño.getID_Memoria());
            System.out.println("Espacio restante disponible para el bloque: " + bloqueMasPequeño + " : " + espacioRestante + "MB");
            proceso.setEstado("Asignado");
        } else {
            if(proceso.getEstado().equals("En ejecucion")){
                System.out.println("PROCESO YA EN EJECUCION");
            }else{
                 System.out.println("NO HAY ESPACIO PARA: " + proceso.getNombre_Proceso());
                procesosNoAsignados.add(proceso);
            }
           
        }

    }
    /**
     * Metodo que imprime los procesos asignados
     */
    public void imprimirProcesosAsignados() {
       System.out.println("\nLista de procesos asignados:");
       for (Proceso proceso : procesos) {
           if (proceso.getEstado().equals("En ejecucion")) { // Solo imprimir procesos asignados
               Memoria bloque = proceso.getBloqueAsignado();
               System.out.println("Proceso: " + proceso.getNombre_Proceso() +
                                  ", asignado a: " + (bloque != null ? bloque.getID_Memoria() : "Ninguno") +
                                  ", tamaño: " + proceso.getTamaño_Proceso());
           }
       }
   }



    private int ultimaPosicion = 0; // Variable para rastrear la última posición usada

    /**
     * Metodo que realiza el siguiente ajuste a los procesos
     */
    public void SiguienteAjuste() {
        int n = memoria.size();
        System.out.println("--------------Siguiente ajuste--------------");
        for (Proceso proceso : procesos) {
            if (proceso.getEstado().equals("En cola")) {
                // Busca desde la última posición usada hasta el final de la lista
                for (int i = ultimaPosicion; i < n; i++) {
                    if (memoria.get(i).getEspacio_Libre() >= proceso.getTamaño_Proceso()) {
                        memoria.get(i).setEspacio_Libre(memoria.get(i).getEspacio_Libre() - proceso.getTamaño_Proceso());
                        memoria.get(i).setEstado(memoria.get(i).getEstado() + proceso.getNombre_Proceso() + ",");
                        proceso.setEstado("En ejecucion");
                        ultimaPosicion = i;
                        break;
                    }
                }
                if (proceso.getEstado().equals("En cola")) {
                    for (int i = 0; i < ultimaPosicion; i++) {
                        if (memoria.get(i).getEspacio_Libre() >= proceso.getTamaño_Proceso()) {
                            memoria.get(i).setEspacio_Libre(memoria.get(i).getEspacio_Libre() - proceso.getTamaño_Proceso());
                            memoria.get(i).setEstado(memoria.get(i).getEstado() + proceso.getNombre_Proceso() + ",");
                            proceso.setEstado("En ejecucion");
                            procesosNoAsignados.remove(proceso);
                            ultimaPosicion = i;
                            break;
                        } else {
                           proceso.setEstado("No_Asignado");
                        }
                    }
                }else{
                    System.out.println("PROCESO EN EJECUCION ");
                }
            }
            if(proceso.getEstado().equals("No_Asignado") ){
                procesosNoAsignados.add(proceso);
            }
        }
    }
    /**
     * Metodo que muestra la asignacion final
     */
    public void mostrarAsignacionFinal() {

        if (!procesosNoAsignados.isEmpty()) {
            System.out.println("\nProcesos que no pudieron ser asignados:");
            for (Proceso p : procesosNoAsignados) {

                System.out.println("Proceso: " + p.getNombre_Proceso() + " - Tamaño: " + p.getTamaño_Proceso() + " KB - Estado: No asignado");
            }
        }
    }
    /**
     * Metodo algoritmico que calcula el peor ajuste y recibe la lista
     * @param proceso
     */
    public void peorAjuste(Proceso proceso) {

        int peorPos = -1;
        int peorDiferencia = 0;
        boolean encontroPos = false;
        int tam = proceso.getTamaño_Proceso();

        /**
         * Recorremos la lista de bloques de memoria para encontrar el peor
         * ajuste
         */
        for (int i = 0; i < memoria.size(); i++) {
            Memoria bloque = memoria.get(i);

            if (proceso.getEstado().equals("En cola") && bloque.getEspacio_Libre() >= tam) {
                int diferencia = bloque.getEspacio_Libre() - tam;

                if (diferencia >= peorDiferencia) {
                    peorDiferencia = diferencia;
                    peorPos = i;
                    encontroPos = true;
                }
            }
        }

        if (encontroPos && proceso.getEstado().equals("En cola")) {
            Memoria bloqueSeleccionado = memoria.get(peorPos);

            bloqueSeleccionado.setEspacio_Libre(bloqueSeleccionado.getEspacio_Libre() - proceso.getTamaño_Proceso());
            bloqueSeleccionado.setEstado(bloqueSeleccionado.getEstado() + proceso.getNombre_Proceso() + ",");
            proceso.setEstado("En ejecucion"); // Cambia el estado del proceso a "E
        } else {
            if(proceso.getEstado().equals("En ejecucion")){
                  System.out.println("PROCESO EN EJECUCION");
            }else{
            System.out.println("Proceso " + proceso.getNombre_Proceso() + " no pudo ser asignado. Memoria insuficiente.");
            procesosNoAsignados.add(proceso);}
            
        }
    }

    /**
     * Metodo para inicializar el arraylist de los procesos
     *
     * @return
     */
    public ArrayList<Proceso> listaProcesos() {
        procesos.add(new Proceso("P1", 300, "En cola"));
        procesos.add(new Proceso("P2", 500, "En cola"));
        procesos.add(new Proceso("P3", 150, "En cola"));
        procesos.add(new Proceso("P4", 700, "En cola"));
        procesos.add(new Proceso("P5", 200, "En cola"));
        procesos.add(new Proceso("P6", 600, "En cola"));
        procesos.add(new Proceso("P7", 100, "En cola"));
        procesos.add(new Proceso("P8", 400, "En cola"));
        procesos.add(new Proceso("P9", 350, "En cola"));
        procesos.add(new Proceso("P10", 250, "En cola"));

        return null;

    }
    /**
     * Metodo que reinicia las listas
     * @return
     */
    public ArrayList<Proceso> reiniciarLista() {
        procesos.clear();
        procesosNoAsignados.clear();

        return null;
    }

    /**
     * Imprime la memoria principal
     */
    public void imprimirRAM() {
        System.out.println("Memoria Principal: \n");
        for (Memoria memo : memoria) {
            System.out.println(memo.toString());
        }
    }

    /**
     * Imprime la lista de procesos
     */
    public void imprimirProcesos() {
        System.out.println("Lista de procesos: \n");
        for (Proceso lista : procesos) {
            System.out.println(lista.toString());
        }
    }
}

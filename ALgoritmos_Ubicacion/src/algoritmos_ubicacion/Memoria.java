/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos_ubicacion;

/**
 * @author Adrián
 * @author Jeison
 * @author Jocelyn
 */
public class Memoria {
    private String ID_Memoria;
    private int tamaño_Bloque;
    private String estado;
    private int espacio_Libre;
    
    /**
     * Constructor que inicializa un proceso con el nombre, tamaño y estado
     * especificados.
     *
     * @param nombre_Proceso Nombre del proceso
     * @param tamaño_Proceso Tamaño del proceso en MB
     * @param estado Estado del proceso
     * @param espacio_Libre
     */
    public Memoria(String nombre_Proceso, int tamaño_Proceso, String estado, int espacio_Libre) {
        this.ID_Memoria = nombre_Proceso;
        this.tamaño_Bloque = tamaño_Proceso;
        this.estado = estado;
        this.espacio_Libre = espacio_Libre;

    }
    public Memoria(){}
    public String getID_Memoria() {
        return ID_Memoria;
    }

    public void setID_Memoria(String ID_Memoria) {
        this.ID_Memoria = ID_Memoria;
    }

    public int getTamaño_Bloque() {
        return tamaño_Bloque;
    }

    public void setTamaño_Bloque(int tamaño_Bloque) {
        this.tamaño_Bloque = tamaño_Bloque;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getEspacio_Libre() {
        return espacio_Libre;
    }

    public void setEspacio_Libre(int espacio_Libre) {
        this.espacio_Libre = espacio_Libre;
    }

  

    @Override
    public String toString() {
        return "ID: " + ID_Memoria + " --Tamaño Bloque: " + tamaño_Bloque + " Detalle: " + estado + " --Espacio_Libre: " + espacio_Libre;
    }
}

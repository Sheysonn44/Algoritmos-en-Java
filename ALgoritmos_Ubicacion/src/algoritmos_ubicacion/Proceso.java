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
public class Proceso {
    /**
     * Constructor que inicializa un proceso con el nombre, tamaño y estado
     * especificados.
     *
     * @param nombre_Proceso Nombre del proceso
     * @param tamaño_Proceso Tamaño del proceso en MB
     * @param estado Estado del proceso
     */
    private String Nombre_Proceso;
    private int Tamaño_Proceso;
    private String Estado;
    private Memoria bloqueAsignado;

    public Proceso(String Nombre_Proceso, int Tamaño_Proceso, String Estado) {
        this.Nombre_Proceso = Nombre_Proceso;
        this.Tamaño_Proceso = Tamaño_Proceso;
        this.Estado = Estado;
        this.bloqueAsignado = null;
    }

    public String getNombre_Proceso() {
        return Nombre_Proceso;
    }

    public void setNombre_Proceso(String Nombre_Proceso) {
        this.Nombre_Proceso = Nombre_Proceso;
    }

    public int getTamaño_Proceso() {
        return Tamaño_Proceso;
    }

    public void setTamaño_Proceso(int Tamaño_Proceso) {
        this.Tamaño_Proceso = Tamaño_Proceso;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Memoria getBloqueAsignado() {
        return bloqueAsignado;
    }

    public void setBloqueAsignado(Memoria bloqueAsignado) {
        this.bloqueAsignado = bloqueAsignado;
    }

    @Override
    public String toString() {
        return "Nombre_Proceso: " + Nombre_Proceso + " --Tamaño_Proceso: " + Tamaño_Proceso + " --Estado: " + Estado;
    }
}

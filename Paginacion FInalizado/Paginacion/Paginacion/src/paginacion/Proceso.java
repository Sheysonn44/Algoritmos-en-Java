/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paginacion;

/**
 * Clase usada para manejar los datos del los procesos
 * @author Jeison Alvarez
 * @author Adrian Chavarria
 * @author Jocelyn Abarca
 *
 */
public class Proceso {

    private int NumPaginacion;
    private String NombreProceso;
    private String estado;
    private Boolean[] estadoPaginacion;

    /**
     * Constructor con parámetros para crear un nuevo proceso.
     *
     * @param NumPaginacion número de paginación del proceso
     * @param NombreProceso nombre del proceso
     * @param estado estado del proceso disponible, en ejecución en
     * memoria principal, en memoria secundaria, finalizado)
     * @param estadoPaginacion Define activo o bloqueado
     */
    public Proceso(int NumPaginacion, String NombreProceso, String estado, Boolean[] estadoPaginacion) {
        this.NumPaginacion = NumPaginacion;
        this.NombreProceso = NombreProceso;
        this.estado = estado;
        this.estadoPaginacion = estadoPaginacion;
    }

    /**
     * Constructor vacío para la creación de procesos sin inicialización de
     * atributos.
     */
    public Proceso() {
    }

    /**
     * Obtiene el número de paginación del proceso.
     *
     * @return número de paginación del proceso
     */
    public int getNumPaginacion() {
        return NumPaginacion;
    }

    /**
     * Establece el número de paginación del proceso.
     *
     * @param NumPaginacion número de paginación del proceso
     */
    public void setNumPaginacion(int NumPaginacion) {
        this.NumPaginacion = NumPaginacion;
    }

    /**
     * Obtiene el nombre del proceso.
     *
     * @return nombre del proceso
     */
    public String getNombreProceso() {
        return NombreProceso;
    }

    /**
     * Establece el nombre del proceso.
     *
     * @param NombreProceso nombre del proceso
     */
    public void setNombreProceso(String NombreProceso) {
        this.NombreProceso = NombreProceso;
    }

    public String isEstado() {
        return estado;
    }

    /**
     * Establece el estado del proceso.
     *
     * @param estado estado del proceso
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el estado del proceso.
     *
     * @return estado del proceso
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Obtiene el estado de la pagina del proceso.
     *
     * @return estado del proceso
     */
    public Boolean[] getEstadoPaginacion() {
        return estadoPaginacion;
    }

    /**
     * Establece el estado por cada pagina del proceso;
     *
     * @param estadoPaginacion
     */
    public void setEstadoPaginacion(Boolean[] estadoPaginacion) {
        this.estadoPaginacion = estadoPaginacion;
    }

    /**
     * Devuelve una representación en cadena del proceso, que incluye el nombre,
     * número de paginación y estado.
     *
     * @return representación en cadena del proceso
     */

    @Override
    public String toString() {
        return "Nombre del proceso: " + NombreProceso + " -- Numero de paginacion: " + NumPaginacion + " -- Estado: " + estado;
    }
 
}


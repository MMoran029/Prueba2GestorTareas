/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2gestortareas;

/**
 *
 * @author mjosu
 */
public class Tarea {

    private String descripcion;
    private boolean completada;

    public Tarea(String descripcion){
        
        this.descripcion= descripcion;
        this.completada= false;
        
    }

    public Tarea(String descripcion,boolean completada){
        this.descripcion= descripcion;
        this.completada= completada;
        
    }

    public String getDescripcion(){
        return descripcion;
        
    }

    public boolean isCompletada(){
        return completada;
    }

    public void completar(){
        this.completada = true;
    }

    public String toLineaArchivo(){
        
        String estado= completada?"COMPLETADA":"PENDIENTE";
        return estado+"|"+descripcion;
    }

    @Override
    public String toString(){
        String simbolo= completada?"✓":" ";
        return "["+simbolo+"] "+descripcion;
    }
}
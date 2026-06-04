/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba2gestortareas;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author mjosu
 */
public class Prueba2GestorTareas {
 
    private static String ARCHIVO = "tareas.txt";
 
    public static void main(String[] args) {
        
        
        Scanner sc  =new Scanner(System.in);
        int opcion;
 
        do{
            
        System.out.println("\nGESTOR DE TAREAS");
        System.out.println("====================");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Mostrar tareas");
        System.out.println("3. Completar tarea");
        System.out.println("4. Salir");            
        System.out.print("Selecciona una opcion: ");
        
            opcion = sc.nextInt();
            sc.nextLine();
 
            switch (opcion) {
                
                
                case 1:
                    agregarTarea(sc);
                    break;
                    
                case 2:
                    mostrarTareas();
                    break;
                    
                case 3:
                    completarTarea(sc);
                    break;
                    
                case 4:
                    System.out.println("Saliste");
                    break;
                    
                default:
                    System.out.println("Opcion no valida. Intenta de nuevo.");
                    
            }
 
        }while(opcion!=4);
 
    }

 
    private static void agregarTarea(Scanner sc) {
        
        
        System.out.print("Ingresa la nueva tarea: ");
        String descripcion = sc.nextLine().trim();
 
        if (descripcion.isEmpty()) {
            System.out.println("La tarea no puede estar vacia.");
            return;
        }

        
        Tarea nuevaTarea = new Tarea(descripcion);
        
        
        try {
            
            File archivo = new File(ARCHIVO);
            FileWriter writer = new FileWriter(archivo,true);
            writer.write(nuevaTarea.toLineaArchivo()+"\n");
            writer.close();
            
            System.out.println("\n ✓ Tarea agregada: "+nuevaTarea.getDescripcion());
            
        } catch (IOException e) {
            System.out.println("Error al guardar la tarea: "+e.getMessage());
        }
    }
 
    private static void mostrarTareas() {
        ArrayList<Tarea> tareas = leerTareas();
 
        System.out.println("\nLISTA DE TAREAS");
        System.out.println("====================");
 
        if (tareas.isEmpty()) {
            
            System.out.println("No hay tareas registradas todavia.");
            return;
        }
 
        for (int i =0;i<tareas.size();i++){
            System.out.println((i+1)+"."+tareas.get(i));
        }
    }
 
    private static void completarTarea(Scanner scanner) {
        ArrayList<Tarea> tareas = leerTareas();
 
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas todavia.");
            return;
        }
 
        System.out.print("Numero de tarea a completar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
 
        if (numero<1 || numero>tareas.size()){
            System.out.println("Numero de tarea invalido.");
            return;
        }
 
        Tarea tarea = tareas.get(numero-1);
 
        if (tarea.isCompletada()){
            System.out.println("La tarea #"+numero+" ya estaba completada.");
            return;
        }
 
        tarea.completar();
        guardarTareas(tareas);
 
        System.out.println("\n ✓ Tarea #"+numero+" completada: "+tarea.getDescripcion());
    }
 
    private static ArrayList<Tarea> leerTareas(){
        ArrayList<Tarea> tareas= new ArrayList<>();
        File archivo = new File(ARCHIVO);
 
        if (!archivo.exists()){
            
            return tareas;
        }
 
        try {
            FileReader reader= new FileReader(archivo);
            StringBuilder sb = new StringBuilder();
            int c;
 
            while ((c=reader.read())!=-1){
                sb.append((char) c);
            }

            reader.close();
 
            String[] lineas= sb.toString().split("\n");
 
            for (String linea:lineas){
                String[] partes = linea.split("\\|", 2);
                if (partes.length==2){
                    
                    boolean completada = partes[0].equals("COMPLETADA");
                    tareas.add(new Tarea(partes[1],completada));
                    
                }
            }
 
        } catch (IOException e) {
            System.out.println("Error al leer las tareas: "+e.getMessage());
        }
 
        return tareas;
    }

    
    private static void guardarTareas(ArrayList<Tarea> tareas) {
        
        try {
            
            File archivo = new File(ARCHIVO);
            FileWriter writer = new FileWriter(archivo,false);
 
            for (Tarea t:tareas){
                
                writer.write(t.toLineaArchivo()+"\n");
            }

            
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas: "+e.getMessage());
        }
    }
}
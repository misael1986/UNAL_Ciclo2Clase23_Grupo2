/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unal.unal_ciclo2clase23_grupo2;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        Scanner tec = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("\n1. Agregar Libro"
                    + "\n2. Consultar Biblioteca"
                    + "\n3. Buscar Libro"
                    + "\n4. Modificar Libro"
                    + "\n5. Borrar Libro"
                    + "\n6. Salir\n");
            opcion = tec.nextInt();
            switch (opcion) {
                case 1:
                    uno();
                    break;
                case 2:
                    dos();
                    break;
                case 3:
                    tres();
                    break;
                case 4:
                    cuatro();
                    break;
                case 5:
                    cinco();
                    break;
                case 6:
                    seis();
                    break;
                default:
                    System.out.println("Opcion no existe en menu");
                    break;
            }

        } while (opcion > 0 && opcion < 6);
        System.out.println("Gracias!!!");

    }

    public static void uno() {
        BD bd = new BD();
        bd.conectar();
        Scanner tec = new Scanner(System.in);
        Integer id;
        String nombre;
        Integer anio;
        Integer editorial;
        Integer autor;
        Double precio;

        System.out.println("Digite la siguiente informacion:");
        System.out.println("ID del Libro:");
        id = tec.nextInt();
        System.out.println("Nombre:");
        nombre = tec.next();
        System.out.println("Año de Publicacion:");
        anio = tec.nextInt();
        System.out.println("ID de Editorial:");
        editorial = tec.nextInt();
        System.out.println("ID de Autor:");
        autor = tec.nextInt();
        System.out.println("Precio:");
        precio = tec.nextDouble();

        bd.insertar(id, nombre, anio, editorial, autor, precio);

    }

    public static void dos() {
        BD bd2 = new BD();//Objeto anonimo
        bd2.conectar();
        bd2.seleccionarTodos();
    }

    public static void tres() {
        Scanner te = new Scanner(System.in);
        System.out.println("Digite el ID del libro a buscar:");
        String id = te.next();
        BD bd3 = new BD();
        bd3.conectar();
        bd3.seleccionarUno(id);
    }

    public static void cuatro() {
        Scanner te = new Scanner(System.in);

        String nombre;
        int anio;
        double precio;
        int id;

        System.out.println("Digite el ID del libro a modificar:");
        id = te.nextInt();
        System.out.println("Nuevo nombre:");
        nombre = te.next();
        System.out.println("Nuevo año:");
        anio = te.nextInt();
        System.out.println("Nuevo precio:");
        precio = te.nextDouble();

        BD bd = new BD();
        bd.conectar();
        bd.actualizar(nombre, anio, precio, id);
    }

    public static void cinco() {
        Scanner te = new Scanner(System.in);
        System.out.println("Digite el ID del libro a BORRAR:");
        int id = te.nextInt();
        BD bd3 = new BD();
        bd3.conectar();
        bd3.borrar(id);
    }

    public static void seis() {
        System.out.println("Saliendo del programa...");
    }

}

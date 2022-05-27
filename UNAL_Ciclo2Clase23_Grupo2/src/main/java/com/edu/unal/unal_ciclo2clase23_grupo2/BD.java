/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unal.unal_ciclo2clase23_grupo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class BD {
    public Connection conn;

    public Connection conectar() {
        String dbURL = "jdbc:mysql://localhost:3306/libreria";
        String username = "admin";
        String password = "Admin123#";

        conn = null;
        // conectar
        try {
            conn = DriverManager.getConnection(
                    dbURL, username, password);
            if (conn != null) {
                //System.out.println(" Conectado ");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
//------------------------------------------------------------------

    public void insertar(int id, String nombre, int anio, 
            int editorial, int autor, double precio) {
        String sql = "INSERT INTO libro "
                + "(libId,libNombre,libPub ,ediId,"
                + "autId , libPrecio ) "
                + "VALUES (?,?,?,?,?,?)";

        int rowsInserted = 0;
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setInt(3, anio);
            statement.setInt(4, editorial);
            statement.setInt(5, autor);
            statement.setDouble(6, precio);
            rowsInserted = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        if (rowsInserted > 0) {
            System.out.println(" Insercion exitosa !");
        }

    }
//-------------------------------------------------------

    public void seleccionarTodos() {
        String sql = "SELECT * FROM libro";
        //System.out.println(sql);
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
            while (result.next()) {
                String titulo = result.getString(2);
                Integer pub = result.getInt(3);
                Double costo = result.getDouble(6);
                System.out.println("Titulo : "
                        + titulo + "\tAño publicacion: " + pub
                        + "\tCosto : " + costo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
    //-----------------------------------------------------
     public void seleccionarUno(String tx) {
        String sql = "SELECT * FROM libro WHERE libId=" + tx;
        //System.out.println(sql);
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
            while (result.next()) {
                String titulo = result.getString(2);
                Integer pub = result.getInt(3);
                Double costo = result.getDouble(6);
                System.out.println("LIBRO ENCONTRADO\nTitulo : "
                        + titulo + "\tAño publicacion: " + pub
                        + "\tCosto : " + costo);
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
    

    //-----------------------------------------------------------
    public void actualizar(String nombre, int anio, 
            double precio, int id) {
        String sql = " UPDATE libro SET libNombre =?,"
                + "libPub =?, libPrecio =? WHERE libId =?";

        try {

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, nombre);
            statement.setInt(2, anio);
            statement.setDouble(3, precio);
            statement.setInt(4, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El registro fue "
                        + " actualizado exitosamente !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //---------------------------------------
    public void borrar(int id) {
        String sql = "DELETE FROM libro WHERE libId =?";
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(" Borrado exitoso !");
            }
            else { System.out.println("Dato no está o ID no es correcto");}
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //------------------------
}
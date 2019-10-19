/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.Constantes;

/**
 *
 * @author ADL
 */
public class Conexion {
    private Connection connection = null;
    private String host;
    private String user;
    private String password;
    private static Conexion Conexion = null;

    public Conexion() {
        this.host = Constantes.DB_HOST;
        this.user = Constantes.DB_USER;
        this.password = Constantes.DB_PASSWORD;
    }

    public static Conexion getInstancia() {
        if (Conexion == null) {
            Conexion = new Conexion();
        }
        return Conexion;
    }

    public Connection getConexion() {
        return this.connection;
    }

    public void abrirConexion() {
        
        String db = Constantes.DB_NAME;
        String url_db = "jdbc:postgresql://" + this.host + ":5432/" + db;
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url_db, this.user, this.password);
            System.out.println("Conexion Exitosa: " + this.connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
  
}

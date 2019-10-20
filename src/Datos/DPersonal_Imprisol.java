/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class DPersonal_Imprisol {
    private int id;
    private String email;
    private String contraseña;
    private String codigo;
    private String nombre;
    private String telefono;
    private String direccion;
    private String cargo;
    private boolean estado;
   
    private Conexion conexion;
    
    public DPersonal_Imprisol() {
        this.id = 0;
        this.codigo = "";
        this.nombre = "";
        this.telefono = "";
        this.direccion = "";
        this.cargo = "";
        this.estado = true;
        conexion = Conexion.getInstancia();
    }

     
    public DPersonal_Imprisol(String codigo, String nombre, String telefono, String direccion, String cargo) {
        this.id = 0;
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cargo = cargo;
        this.estado = true;
        conexion = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }
 

    public DefaultTableModel getPersonales() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel adms = new DefaultTableModel();
        adms.setColumnIdentifiers(new Object[]{
            "id", "codigo","nombre", "telefono", "direccion", "cargo"
        });
        String sql = "SELECT * FROM personal WHERE estado = true ";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                adms.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getString("nombre"),
                    result.getString("telefono"),
                    result.getString("direccion"),
                    result.getString("cargo")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adms;
    }
    
    public int registrar() {
        
        /////PRIMERO INSERTAMOS EN LA TABLA USUARIO
        int idUsuario = insertarUsuarioDB();
        this.setId(idUsuario);
        //////////////////////////////////////////
        
         // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO personal(nit, nombre, telefono, estado, usuarioid) " +
                      "VALUES(?, ?, ?, ?, ?)";
        try {
            System.out.println("try catch de insertar personal");
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.nit);
            stmt.setString(2, this.nombre);
            stmt.setString(3, this.telefono);
            stmt.setBoolean(4, this.estado);
            stmt.setInt(5, this.id);
            int rows = stmt.executeUpdate();

            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = stmt.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int insertarUsuarioDB(){
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO usuario(username, contrasenia, estado) " +
                      "VALUES(?, ?, ?)";
        try {
            System.out.println("try catch de insertar usuario");
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.email);
            stmt.setString(2, this.contraseña);
            stmt.setBoolean(3, this.estado);
            int rows = stmt.executeUpdate();

            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = stmt.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);  
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int getIdPersonal() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        String sql = "SELECT * FROM cliente WHERE estado = true AND cliente.nit = ? LIMIT 1";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.nit);
            ResultSet result = stmt.executeQuery();
            id = 0;
             while (result.next()) {
                id = result.getInt("id");
            }
             
            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
    
    public DefaultTableModel getPersonal() {
        
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel cliente = new DefaultTableModel();
        String sql = "SELECT * FROM cliente WHERE estado = true AND cliente.nit = ? LIMIT 1";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.nit);
            ResultSet result = stmt.executeQuery();
            
             while (result.next()) {
                // Agrego las tuplas a mi tabla
                 System.out.println("NOMBRE --> " + result.getString("nombre"));
                cliente.addRow(new Object[] {
                    result.getInt("id"),
                    result.getString("nit"),
                    result.getString("nombre"),
                    result.getString("telefono"),
                });
            }
             
            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cliente;
    }
    
    public int modificar() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "UPDATE cliente SET nombre = ?, " +
                "telefono = ? WHERE cliente.nit = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            //stmt.setString(1, this.codigo);
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.telefono);
            stmt.setInt(3, this.nit);
            int rows = stmt.executeUpdate();
            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = stmt.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int eliminar() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "UPDATE cliente SET estado = false WHERE cliente.nit = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.nit);
            if (stmt.execute()) {
                this.conexion.cerrarConexion();
                return 1;
            } else {
                this.conexion.cerrarConexion();
                return 0;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
}

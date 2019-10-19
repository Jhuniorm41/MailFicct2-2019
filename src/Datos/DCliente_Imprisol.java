/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class DCliente_Imprisol {
    
    private int id;
    private String codigo;
    private int nit;
    private String nombre;
    private String telefono;
    private String estado;
    private Conexion conexion;

    public DCliente_Imprisol() {
        this.id = 0;
        this.codigo = "";
        this.nit = 0;
        this.nombre = "";
        this.telefono = "";
        this.estado = "";
        conexion = Conexion.getInstancia();
    }
    
    public DCliente_Imprisol(int id, String codigo, int nit, String nombre, String telefono) {
        this.id = id;
        this.codigo = codigo;
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.estado = "1";
        conexion = Conexion.getInstancia();
    }
    
    public DCliente_Imprisol(String codigo, int nit, String nombre, String telefono) {
        this.id = 0;
        this.codigo = codigo;
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.estado = "1";
        conexion = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public DefaultTableModel getClientes() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel clientes = new DefaultTableModel();
        clientes.setColumnIdentifiers(new Object[]{
            "id", "nit", "nombre", "telefono"
        });
        String sql = "SELECT * FROM cliente WHERE estado = '1'";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                //System.out.println(" RESULT ---> " + result.getString("nombre"));
                clientes.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("nit"),
                    result.getString("nombre"),
                    result.getString("telefono"),
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }
    
    public int registrar() {
         // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO cliente(codigo, nit, nombre, telefono, estado) " +
                      "VALUES(?, ?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setInt(2, this.nit);
            stmt.setString(3, this.nombre);
            stmt.setString(4, this.telefono);
            stmt.setString(5, this.estado);
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
    
    public int getIdCliente() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        String sql = "SELECT * FROM cliente WHERE estado = 'A' AND cliente.codigo = ? LIMIT 1";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
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
    
    public DefaultTableModel getCliente() {
        
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel cliente = new DefaultTableModel();
        String sql = "SELECT * FROM cliente WHERE estado = 'A' AND cliente.id = ? LIMIT 1";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.id);
            ResultSet result = stmt.executeQuery();
            
             while (result.next()) {
                // Agrego las tuplas a mi tabla
                 System.out.println("NOMBRE --> " + result.getString("nombre"));
                cliente.addRow(new Object[] {
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getString("nit"),
                    result.getString("nombre"),
                    result.getString("telefono"),
                    result.getString("estado")
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

        String sql = "UPDATE cliente SET codigo = ?, nit = ?, nombre = ?, " +
                "telefono = ?, estado = ? WHERE cliente.id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setInt(2, this.nit);
            stmt.setString(3, this.nombre);
            stmt.setString(4, this.telefono);
            stmt.setString(5, this.estado);
            stmt.setInt(6, this.id);
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

        String sql = "UPDATE cliente SET estado = 'D' WHERE cliente.id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.id);
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

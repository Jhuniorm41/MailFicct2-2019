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
public class DAdministrativo {
    private int id;
    private String codigo;
    private String nombre;
    private String telefono;
    private String estado;
    private String cargo;
    private Date fecha_ingreso;
    private Conexion conexion;
    
    public DAdministrativo() {
        this.id = 0;
        this.codigo = "";
        this.nombre = "";
        this.telefono = "";
        this.estado = "";
        this.cargo = "";
        this.fecha_ingreso = new Date(1000);
        conexion = Conexion.getInstancia();
    }

     
    public DAdministrativo(String codigo, String nombre, String telefono, String estado, String cargo, Date fecha_ingreso) {
        this.id = 0;
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.estado = estado;
        this.cargo = cargo;
        this.fecha_ingreso = fecha_ingreso;
        conexion = Conexion.getInstancia();
    }
    
    public DAdministrativo(int id, String codigo, String nombre, String telefono, String estado, String cargo, Date fecha_ingreso) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.estado = estado;
        this.cargo = cargo;
        this.fecha_ingreso = fecha_ingreso;
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
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    
    public DefaultTableModel getAdministrativos() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel adms = new DefaultTableModel();
        adms.setColumnIdentifiers(new Object[]{
            "id", "codigo","nombre", "telefono", "estado", "cargo", "fecha_ingreso"
        });
        String sql = "SELECT * FROM administrativo WHERE estado = 'A'";
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
                    result.getString("estado"),
                    result.getString("cargo"),
                    result.getDate("fecha_ingreso")
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
         // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO administrativo(codigo, nombre, telefono, estado, cargo, fecha_ingreso) " +
                      "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setString(2, this.nombre);
            stmt.setString(3, this.telefono);
            stmt.setString(4, "A");
            stmt.setString(5, this.cargo);
            stmt.setDate(6, this.fecha_ingreso);
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
    
    public int getIdAdm() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        String sql = "SELECT * FROM administrativo WHERE estado = 'A' AND codigo = ? LIMIT 1";
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
    
    public DefaultTableModel getAdministrativo() {
        
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel adm = new DefaultTableModel();
        String sql = "SELECT * FROM administrativo WHERE estado = 'A' AND administrativo.id = ? LIMIT 1";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.id);
            ResultSet result = stmt.executeQuery();
            
             while (result.next()) {
                // Agrego las tuplas a mi tabla
                adm.addRow(new Object[] {
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getString("nombre"),
                    result.getString("telefono"),
                    result.getString("estado"),
                    result.getString("cargo"),
                    result.getDate("fecha_ingreso")
                });
            }
             
            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adm;
    }
    
    public int modificar() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "UPDATE administrativo SET codigo = ?, nombre = ?, telefono = ?, " +
                "estado = ?, cargo = ?, fecha_ingreso = ? WHERE administrativo.id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setString(2, this.nombre);
            stmt.setString(3, this.telefono);
            stmt.setString(4, "A");
            stmt.setString(5, this.cargo);
            stmt.setDate(6, this.fecha_ingreso);
            stmt.setInt(7, this.id);
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

        String sql = "UPDATE administrativo SET estado = 'D' WHERE administrativo.id = ?";
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

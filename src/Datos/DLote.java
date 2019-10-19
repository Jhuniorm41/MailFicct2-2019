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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class DLote {

    private int id;
    private String codigo;
    private Date fecha_ingreso;
    private int cantidad;
    private String estado;
    private int almacenid;
    private int proveedorid;
    private Conexion conexion;

    public DLote() {
        id = 0;
        codigo = "";
        fecha_ingreso = new Date(1000);
        cantidad = -1;
        estado = "";
        almacenid = 0;
        proveedorid = 0;
        conexion = Conexion.getInstancia();
    }

    public DLote(String codigo, Date fecha_ingreso, int cantidad, String estado, int almacenid, int proveedorid) {
        this.id = 0;
        this.codigo = codigo;
        this.fecha_ingreso = fecha_ingreso;
        this.cantidad = cantidad;
        this.estado = estado;
        this.almacenid = almacenid;
        this.proveedorid = proveedorid;
        conexion = Conexion.getInstancia();
    }

    public DLote(int id, String codigo, Date fecha_ingreso, int cantidad, String estado, int almacenid, int proveedorid) {
        this.id = id;
        this.codigo = codigo;
        this.fecha_ingreso = fecha_ingreso;
        this.cantidad = cantidad;
        this.estado = estado;
        this.almacenid = almacenid;
        this.proveedorid = proveedorid;
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

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAlmacenid() {
        return almacenid;
    }

    public void setAlmacenid(int almacenid) {
        this.almacenid = almacenid;
    }

    public int getProveedorid() {
        return proveedorid;
    }

    public void setProveedorid(int proveedorid) {
        this.proveedorid = proveedorid;
    }

    public DefaultTableModel getLotes() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel lotes = new DefaultTableModel();
        lotes.setColumnIdentifiers(new Object[]{
            "id", "codigo", "fecha_ingreso", "cantidad", "estado", "almacenid", "proveedorid"
        });
        String sql = "SELECT * FROM lote WHERE estado = 'A'";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                lotes.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getDate("fecha_ingreso"),
                    result.getInt("cantidad"),
                    result.getString("estado"),
                    result.getInt("almacenid"),
                    result.getInt("proveedorid")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lotes;
    }

    public int registrar() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO lote(codigo, fecha_ingreso, cantidad, estado, almacenid, proveedorid) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setDate(2, this.fecha_ingreso);
            stmt.setInt(3, this.cantidad);
            stmt.setString(4, this.estado);
            stmt.setInt(5, this.almacenid);
            stmt.setInt(6, this.proveedorid);
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

    public DefaultTableModel getLote() {

        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel lote = new DefaultTableModel();
        String sql = "SELECT * FROM lote WHERE estado = 'A' AND lote.id = ? LIMIT 1";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.id);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                // Agrego las tuplas a mi tabla
                lote.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getDate("fecha_ingreso"),
                    result.getInt("cantidad"),
                    result.getString("estado"),
                    result.getInt("almacenid"),
                    result.getInt("proveedorid")
                });
            }

            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lote;
    }

    public int modificar() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "UPDATE lote SET codigo = ?, fecha_ingreso = ?, "
                + "cantidad = ?, estado = ?, almacenid = ?, proveedorid = ? WHERE lote.id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setDate(2, this.fecha_ingreso);
            stmt.setInt(3, this.cantidad);
            stmt.setString(4, this.estado);
            stmt.setInt(5, this.almacenid);
            stmt.setInt(6, this.proveedorid);
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

        String sql = "UPDATE lote SET estado = 'D' WHERE lote.id = ?";
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

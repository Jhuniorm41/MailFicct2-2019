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
public class DNotaVenta {

    private int id;
    private String codigo;
    private Date fecha_emision;
    private double monto_total;
    private String estado;
    private int pedidoid;
    private Conexion conexion;

    public DNotaVenta() {
        id = 0;
        codigo = "";
        fecha_emision = new Date(1000);
        monto_total = -1;
        estado = "";
        pedidoid = 0;
        conexion = Conexion.getInstancia();
    }

    public DNotaVenta(String codigo, Date fecha_emision, double monto_total, String estado, int pedidoid) {
        this.id = 0;
        this.codigo = codigo;
        this.fecha_emision = fecha_emision;
        this.monto_total = monto_total;
        this.estado = estado;
        this.pedidoid = pedidoid;
        conexion = Conexion.getInstancia();
    }

    public DNotaVenta(int id, String codigo, Date fecha_emision, double monto_total, String estado, int pedidoid) {
        this.id = id;
        this.codigo = codigo;
        this.fecha_emision = fecha_emision;
        this.monto_total = monto_total;
        this.estado = estado;
        this.pedidoid = pedidoid;
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

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPedidoid() {
        return pedidoid;
    }

    public void setPedidoid(int pedidoid) {
        this.pedidoid = pedidoid;
    }

    public DefaultTableModel getNotasVentas() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel notasVentas = new DefaultTableModel();
        notasVentas.setColumnIdentifiers(new Object[]{
            "id", "codigo", "fecha_emision", "monto_total", "estado", "pedidoid"
        });
        String sql = "SELECT * FROM notaventa WHERE estado = 'A'";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                notasVentas.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getDate("fecha_emision"),
                    result.getDouble("monto_total"),
                    result.getString("estado"),
                    result.getInt("pedidoid")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return notasVentas;
    }

    public int registrar() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO notaventa(codigo, fecha_emision, monto_total, estado, pedidoid) "
                + "VALUES(?, ?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setDate(2, this.fecha_emision);
            stmt.setDouble(3, this.monto_total);
            stmt.setString(4, this.estado);
            stmt.setInt(5, this.pedidoid);
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

    public DefaultTableModel getNotaVenta() {

        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel lote = new DefaultTableModel();
        String sql = "SELECT * FROM notaventa WHERE estado = 'A' AND notaventa.id = ? LIMIT 1";
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
                    result.getDate("fecha_emision"),
                    result.getDouble("monto_total"),
                    result.getString("estado"),
                    result.getInt("pedidoid")
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

        String sql = "UPDATE notaventa SET codigo = ?, fecha_emision = ?, monto_total = ?, "
                + "estado = ?, pedidoid = ? WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setDate(2, this.fecha_emision);
            stmt.setDouble(3, this.monto_total);
            stmt.setString(4, this.estado);
            stmt.setInt(5, this.pedidoid);
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

        String sql = "UPDATE notaventa SET estado = 'D' WHERE notavena.id = ?";
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

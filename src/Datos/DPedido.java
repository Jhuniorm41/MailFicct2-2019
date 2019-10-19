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
public class DPedido {

    private int id;
    private String codigo;
    private Date fecha_registro;
    private String descripcion;
    private String estado;
    private double monto_total;
    private int clienteid;
    private Conexion conexion;

    public DPedido() {
        id = 0;
        codigo = "";
        fecha_registro = new Date(1000);
        descripcion = "";
        estado = "";
        monto_total = -1;
        clienteid = 0;
        conexion = Conexion.getInstancia();
    }
                        
    public DPedido(String codigo, Date fecha_registro, String descripcion, String estado, double monto_total, int clienteid) {
        this.id = 0;
        this.codigo = codigo;
        this.fecha_registro = fecha_registro;
        this.descripcion = descripcion;
        this.estado = estado;
        this.monto_total = monto_total;
        this.clienteid = clienteid;
        conexion = Conexion.getInstancia();
    }

    public DPedido(int id, String codigo, Date fecha_registro, String descripcion, String estado, double monto_total, int clienteid) {
        this.id = id;
        this.codigo = codigo;
        this.fecha_registro = fecha_registro;
        this.descripcion = descripcion;
        this.estado = estado;
        this.monto_total = monto_total;
        this.clienteid = clienteid;
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

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public int getClienteid() {
        return clienteid;
    }

    public void setClienteid(int clienteid) {
        this.clienteid = clienteid;
    }

    public DefaultTableModel getPedidos() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel pedidos = new DefaultTableModel();
        pedidos.setColumnIdentifiers(new Object[]{
            "id", "codigo", "fecha_registro", "descripcion", "estado", "monto_total", "clienteid"
        });
        String sql = "SELECT * FROM pedido";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                //System.out.println(" === > " + result.getString("codigo"));
                pedidos.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getString("fecha_registro"),
                    result.getString("descripcion"),
                    result.getString("estado"),
                    result.getDouble("monto_total"),
                    result.getInt("clienteid")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pedidos;
    }

    public int registrar() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        String sql = "INSERT INTO pedido(codigo, fecha_registro, descripcion, estado, monto_total, clienteid) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setDate(2, this.fecha_registro);
            stmt.setString(3, this.descripcion);
            stmt.setString(4, this.estado);
            stmt.setDouble(5, this.monto_total);
            stmt.setInt(6, this.clienteid);
            int rows = stmt.executeUpdate();
            // Cierro Conexion
            if (rows != 0) {
                ResultSet generateKeys = stmt.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
            this.conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println("ocurrio un problema al regitrar 2");
            System.out.println(ex.getMessage());
        }
        System.out.println("ocurrio un problema al regitrar");
        return 0;
    }

    public DefaultTableModel getPedido() {

        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel pedido = new DefaultTableModel();
        String sql = "SELECT * FROM pedido WHERE id = ? LIMIT 1";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.id);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                // Agrego las tuplas a mi tabla
                pedido.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getString("fecha_registro"),
                    result.getString("descripcion"),
                    result.getString("estado"),
                    result.getDouble("monto_total"),
                    result.getInt("clienteid")
                });
            }

            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pedido;
    }

    public int modificar() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "UPDATE pedido SET codigo = ?, fecha_registro = ?, "
                + "descripcion = ?, estado = ?, monto_total = ?, clienteid = ? WHERE pedido.id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setDate(2, this.fecha_registro);
            stmt.setString(3, this.descripcion);
            stmt.setString(4, this.estado);
            stmt.setDouble(5, this.monto_total);
            stmt.setInt(6, this.clienteid);
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

        String sql = "UPDATE pedido SET estado = 'D' WHERE pedido.id = ?";
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

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
public class DEntrega {

    private int id;
    private String codigo;
    private Date fecha_registro;
    private Date fecha_entrega;
    private String destino;
    private String estado;
    private int administrativoid;
    private int notaventaid;
    private Conexion conexion;

    public DEntrega() {
        id = 0;
        codigo = "";
        fecha_registro = new Date(1000);
        fecha_entrega = new Date(1000);
        destino = "";
        estado = "";
        administrativoid = 0;
        notaventaid = 0;
        conexion = Conexion.getInstancia();
    }

    public DEntrega(String codigo, Date fecha_registro, Date fecha_entrega, String destino, String estado, int administrativoid, int notaventaid) {
        this.codigo = codigo;
        this.fecha_registro = fecha_registro;
        this.fecha_entrega = fecha_entrega;
        this.destino = destino;
        this.estado = estado;
        this.administrativoid = administrativoid;
        this.notaventaid = notaventaid;
        conexion = Conexion.getInstancia();
    }

    public DEntrega(int id, String codigo, Date fecha_registro, Date fecha_entrega, String destino, String estado, int administrativoid, int notaventaid) {
        this.id = id;
        this.codigo = codigo;
        this.fecha_registro = fecha_registro;
        this.fecha_entrega = fecha_entrega;
        this.destino = destino;
        this.estado = estado;
        this.administrativoid = administrativoid;
        this.notaventaid = notaventaid;
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

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAdministrativoid() {
        return administrativoid;
    }

    public void setAdministrativoid(int administrativoid) {
        this.administrativoid = administrativoid;
    }

    public int getNotaventaid() {
        return notaventaid;
    }

    public void setNotaventaid(int notaventaid) {
        this.notaventaid = notaventaid;
    }
    
    public DefaultTableModel getEntregas() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel entregas = new DefaultTableModel();
        entregas.setColumnIdentifiers(new Object[]{
            "id", "codigo", "fecha_registro", "fecha_entrega", "destino", "estado", "administrativoid", "notaventaid"
        });
        String sql = "SELECT * FROM entrega WHERE";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                entregas.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getDate("fecha_registro"),
                    result.getDate("fecha_entrega"),
                    result.getString("destino"),
                    result.getString("estado"),
                    result.getInt("administrativoid"),
                    result.getInt("notaventaid")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return entregas;
    }

    public int registrar() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO entrega(codigo, fecha_registro, fecha_entrega, destino, estado, administrativoid, notaventaid) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setDate(2, this.fecha_registro);
            stmt.setDate(3, this.fecha_entrega);
            stmt.setString(4, this.destino);
            stmt.setString(5, this.estado);
            stmt.setInt(6, this.administrativoid);
            stmt.setInt(7, this.notaventaid);
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

    public DefaultTableModel getEntrega() {

        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel lote = new DefaultTableModel();
        String sql = "SELECT * FROM entrega WHERE id = ? LIMIT 1";
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
                    result.getDate("fecha_registro"),
                    result.getDate("fecha_entrega"),
                    result.getString("destino"),
                    result.getString("estado"),
                    result.getInt("administrativoid"),
                    result.getInt("notaventaid")
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

        String sql = "UPDATE entrega SET codigo = ?, fecha_registro = ?, fecha_entrega = ?, "
                + "destino = ?, estado = ?, administrativoid = ?, notaventaid = ? WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setDate(2, this.fecha_registro);
            stmt.setDate(3, this.fecha_entrega);
            stmt.setString(4, this.destino);
            stmt.setString(5, this.estado);
            stmt.setInt(6, this.administrativoid);
            stmt.setInt(7, this.notaventaid);
            stmt.setInt(8, this.id);
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

        String sql = "UPDATE entrega SET estado = 'D' WHERE id = ?";
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

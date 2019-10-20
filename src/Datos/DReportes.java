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
public class DReportes {
    private Conexion conexion;

    public DReportes() {
        this.conexion = new Conexion();
    }

    public DefaultTableModel getProdsGarantiaVige() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel datos = new DefaultTableModel();
        datos.setColumnIdentifiers(new Object[]{
            "Nro", "Cliente", "CodProducto", "Tipo", "FechaFin"
        });
        String sql = "SELECT c.nombre, p.codigo as codproducto, tp.nombre as tipo, g.fecha_fin\n" +
                        "FROM producto p, garantia g, cliente c, tipo_producto tp\n" +
                        "WHERE g.productoid = p.id AND current_timestamp < g.fecha_fin AND\n" +
                        "	c.id = p.clienteid AND tp.id = p.tipoproductoid";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            int nro = 1;
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                datos.addRow(new Object[]{
                    nro,
                    result.getString("nombre"),
                    result.getString("codproducto"),
                    result.getString("tipo"),
                    result.getString("fecha_fin")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datos;
    }
    
    public DefaultTableModel getReservas() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel datos = new DefaultTableModel();
        datos.setColumnIdentifiers(new Object[]{
            "Nro", "Cliente", "FechaReserva"
        });
        String sql = "SELECT c.nombre as cliente, r.fecha_reservada\n" +
                    "FROM cliente c, reserva r\n" +
                    "WHERE c.id = r.clienteid AND r.fecha_reservada > current_timestamp";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            int nro = 1;
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                datos.addRow(new Object[]{
                    nro,
                    result.getString("cliente"),
                    result.getString("fecha_reservada")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datos;
    }
    
    public DefaultTableModel getCotizaciones() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel datos = new DefaultTableModel();
        datos.setColumnIdentifiers(new Object[]{
            "Nro", "personal", "Cliente", "TipoProducto", "TipoServicio", "Total"
        });
        String sql = "SELECT distinct(co.id),pe.nombre as personal, c.nombre as cliente, tp.nombre as tipoproducto,\n" +
                    " ts.nombre as tiposervicio, co.monto as total\n" +
                    "FROM cliente c, reserva r, cotizacion co, detalle_cotizacion dc,\n" +
                    "	tipo_producto tp, producto p, personal pe, tipo_servicio ts\n" +
                    "WHERE c.id = r.clienteid AND co.reservaid = r.id AND dc.cotizacionid = co.id AND\n" +
                    "	dc.tipoproductoid = tp.id AND tp.id = p.tipoproductoid AND pe.id = co.personalid AND\n" +
                    "	ts.id = dc.tiposervicioid";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            int nro = 1;
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                datos.addRow(new Object[]{
                    nro,
                    result.getString("personal"),
                    result.getString("cliente"),
                    result.getString("tipoproducto"),
                    result.getString("tiposervicio"),
                    result.getString("total")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datos;
    }
}

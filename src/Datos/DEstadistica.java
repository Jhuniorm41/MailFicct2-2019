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
public class DEstadistica {
    private Conexion conexion;

    public DEstadistica() {
        this.conexion = new Conexion();
    }

    public DefaultTableModel getEstadisticasVentasMes(int anio) {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel estadistica = new DefaultTableModel();
        estadistica.setColumnIdentifiers(new Object[]{
            "Cantidad_Ventas", "Promedio_Monto", "monto_menor", "monto_mayor", "Mes"
        });
        String sql = "SELECT COUNT (*) as cantidadVenta, avg(monto_total) as promedioMonto, min(monto_total) "
                + " as MontoMenor, max(monto_total) as MontoMayor,\n"
                + " extract(month from fecha_emision) as mes\n"
                + " FROM notaVenta\n"
                + " WHERE extract(years from fecha_emision) = ?\n"
                + " GROUP BY extract(month from fecha_emision)\n"
                + " ORDER BY extract(month from fecha_emision) ASC;";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, anio);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                estadistica.addRow(new Object[]{
                    result.getInt("cantidadVenta"),
                    result.getFloat("promedioMonto"),
                    result.getDouble("montoMenor"),
                    result.getDouble("montoMayor"),
                    result.getInt("Mes")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(sql);
        return estadistica;
    }
    
     public DefaultTableModel getEstadisticasVentasDia(int mes, int anio) {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel estadistica = new DefaultTableModel();
        estadistica.setColumnIdentifiers(new Object[]{
            "Cantidad_Ventas", "Promedio_Monto", "monto_menor", "monto_mayor", "Dia"
        });
        String sql = "SELECT COUNT (*) as cantidadVenta, avg(monto_total) as promedioMonto, min(monto_total) "
                + " as MontoMenor, max(monto_total) as MontoMayor,\n"
                + " extract(day from fecha_emision) as dia\n"
                + " FROM notaVenta\n"
                + " WHERE extract(month from fecha_emision) = ? and extract(year from fecha_emision) = ?\n"
                + " GROUP BY extract(day from fecha_emision)\n"
                + " ORDER BY extract(day from fecha_emision) ASC;";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, mes);
            stmt.setInt(2, anio);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                estadistica.addRow(new Object[]{
                    result.getInt("cantidadVenta"),
                    result.getFloat("promedioMonto"),
                    result.getDouble("montoMenor"),
                    result.getDouble("montoMayor"),
                    result.getInt("Dia")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estadistica;
    }
}

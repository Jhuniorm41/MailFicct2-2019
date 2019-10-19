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

    public DefaultTableModel getReportePedidosCliente(String codigo) {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel reporte = new DefaultTableModel();
        reporte.setColumnIdentifiers(new Object[]{
            "Codigo_cliente", "Nombre_cliente", "Codigo_pedido", "Descripcion_pedido", "Monto"
        });
        String sql = "select cl.codigo as ccliente, cl.nombre, p.codigo as cpedido, p.descripcion, p.monto_total "
                + "from cliente cl, pedido p where cl.codigo = ? and p.clienteID = cl.id";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, codigo);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                reporte.addRow(new Object[]{
                    result.getString("ccliente"),
                    result.getString("nombre"),
                    result.getString("cpedido"),
                    result.getString("descripcion"),
                    result.getDouble("monto_total"),});
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(sql);
        return reporte;
    }

    public DefaultTableModel getReporteNotaVentasCliente(String codigo) {
        System.out.println(codigo);
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel reporte = new DefaultTableModel();
        reporte.setColumnIdentifiers(new Object[]{
            "Codigo_cliente", "Nombre_cliente", "Codigo_nota_venta", "Fecha_emision", "Monto"
        });
        String sql = "select cl.codigo as ccliente, cl.nombre, n.codigo as ncodigo, n.fecha_emision, "
                + "n.monto_total from cliente cl, pedido p, notaventa n "
                + "where cl.codigo = ? and p.clienteID = cl.id and n.pedidoID = p.id";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, codigo);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                reporte.addRow(new Object[]{
                    result.getString("ccliente"),
                    result.getString("nombre"),
                    result.getString("ncodigo"),
                    result.getString("fecha_emision"),
                    result.getDouble("monto_total"),});
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(sql);
        return reporte;
    }

    public DefaultTableModel getReporteEntregasAdministradores(String codigo) {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel reporte = new DefaultTableModel();
        reporte.setColumnIdentifiers(new Object[]{
            "codigo", "nombre", "cargo", "codigoEntrega", "fecha_registro", "fecha_entrega", "destino", "estado"
        });
        String sql = "select administrativo.codigo, administrativo.nombre, administrativo.cargo, entrega.codigo as centrega, "
                + "entrega.fecha_registro, entrega.fecha_entrega, entrega.destino, entrega.estado "
                + "from entrega, administrativo "
                + "where administrativo.codigo = ? and entrega.administrativoID = administrativo.id";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, codigo);
            ResultSet result = stmt.executeQuery();
          //  System.out.println("res" + result.getString("codigo"));
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                reporte.addRow(new Object[]{
                    result.getString("codigo"),
                    result.getString("nombre"),
                    result.getString("cargo"),
                    result.getString("centrega"),
                    result.getString("fecha_registro"),
                    result.getString("fecha_entrega"),
                    result.getString("destino"),
                    result.getString("estado")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(sql);
        return reporte;
    }
}

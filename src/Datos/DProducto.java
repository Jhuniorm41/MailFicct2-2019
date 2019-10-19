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
public class DProducto {

    private int id;
    private String codigo;
    private String marca;
    private String modelo;
    private double precio;
    private double costo;
    private String estado;
    private int loteid;
    private int tipoproductoid;
    private Conexion conexion;

    public DProducto() {
        id = 0;
        codigo = "";
        marca = "";
        modelo = "";
        precio = -1;
        costo = -1;
        estado = "";
        loteid = 0;
        tipoproductoid = 0;
        conexion = Conexion.getInstancia();
    }

    public DProducto(String codigo, String marca, String modelo, double precio, double costo, String estado, int loteid, int tipoproductoid) {
        this.id = 0;
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.costo = costo;
        this.estado = estado;
        this.loteid = loteid;
        this.tipoproductoid = tipoproductoid;
        conexion = Conexion.getInstancia();
    }

    public DProducto(int id, String codigo, String marca, String modelo, double precio, double costo, String estado, int loteid, int tipoproductoid) {
        this.id = id;
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.costo = costo;
        this.estado = estado;
        this.loteid = loteid;
        this.tipoproductoid = tipoproductoid;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getLoteid() {
        return loteid;
    }

    public void setLoteid(int loteid) {
        this.loteid = loteid;
    }

    public int getTipoproductoid() {
        return tipoproductoid;
    }

    public void setTipoproductoid(int tipoproductoid) {
        this.tipoproductoid = tipoproductoid;
    }

    public DefaultTableModel getProductos() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel productos = new DefaultTableModel();
        productos.setColumnIdentifiers(new Object[]{
            "id", "codigo", "marca", "modelo", "precio", "costo", "estado", "loteid", "tipoproductoid"
        });
        String sql = "SELECT * FROM producto WHERE estado = 'A'";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                productos.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("codigo"),
                    result.getString("marca"),
                    result.getString("modelo"),
                    result.getDouble("precio"),
                    result.getDouble("costo"),
                    result.getString("estado"),
                    result.getInt("loteid"),
                    result.getInt("tipoproductoid")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;
    }

    public int registrar() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO producto(codigo, marca, modelo, precio, costo, estado, loteid, tipoproductoid) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setString(2, this.marca);
            stmt.setString(3, this.modelo);
            stmt.setDouble(4, this.precio);
            stmt.setDouble(5, this.costo);
            stmt.setString(6, this.estado);
            stmt.setInt(7, this.loteid);
            stmt.setInt(8, this.tipoproductoid);
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

    public DefaultTableModel getProducto() {

        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel lote = new DefaultTableModel();
        String sql = "SELECT * FROM producto WHERE estado = 'A' AND producto.id = ? LIMIT 1";
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
                    result.getString("marca"),
                    result.getString("modelo"),
                    result.getDouble("precio"),
                    result.getDouble("costo"),
                    result.getString("estado"),
                    result.getInt("loteid"),
                    result.getInt("tipoproductoid")
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

        String sql = "UPDATE producto SET codigo = ?, marca = ?, modelo = ?, precio = ?, costo = ?, "
                + "estado = ?, loteid = ?, tipoproductoid = ? WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.codigo);
            stmt.setString(2, this.marca);
            stmt.setString(3, this.modelo);
            stmt.setDouble(4, this.precio);
            stmt.setDouble(5, this.costo);
            stmt.setString(6, this.estado);
            stmt.setInt(7, this.loteid);
            stmt.setInt(8, this.tipoproductoid);
            stmt.setInt(9, this.id);
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

        String sql = "UPDATE producto SET estado = 'D' WHERE id = ?";
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
    
    public int getIdProducto() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        String sql = "SELECT * FROM producto WHERE codigo = ?";
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
}

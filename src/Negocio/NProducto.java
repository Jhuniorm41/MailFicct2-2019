/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DProducto;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NProducto {
    
    private DProducto producto;
    
    public NProducto() {
        producto =  new DProducto();
    }
    
    public DefaultTableModel getProductos() {
        return producto.getProductos();
    }
    
    public DefaultTableModel getProducto(int id) {
        producto.setId(id);
        return producto.getProducto();
    }
    
    public String registrar(String codigo, String marca, String modelo, double precio,
            double costo, int loteid, int tipoproductoid) {
        String msg = "";
        if (codigo.length() > 15) {
            msg += "codigo demasiado largo (maximo 15 caracteres)\n";
        }
        if (marca.length() > 15) {
            msg += "marca demasiado largo (maximo 15 caracteres\n";
        }
        if (modelo.length() > 15) {
             msg += "modelo demasiado largo (maximo 15 caracteres\n";
        }
    
        if (msg.length() > 0) {
            return msg;
        }
        producto = new DProducto(codigo, marca, modelo, precio, costo, "A", loteid, tipoproductoid);
        if (producto.registrar() > 0) {
            return "Se guardo correctamente";
        } else {
            return "No se guardo correctamente";
        }
    }
    
    public int modificar(String codigo, String marca, String modelo, double precio,
        double costo, int codigoLote, int tipoProducto) {
        producto.setCodigo(codigo);
        int idc = producto.getIdProducto();
        System.out.println("CODIGO === > " + codigo);
        System.out.println("marca === > " + marca);
        System.out.println("modelo === > " + modelo);
        System.out.println("precio === > " + precio);
        System.out.println("costo === > " + costo);
        System.out.println("codigoLote === > " + codigoLote);
        System.out.println("tipoProducto === > " + tipoProducto);
        System.out.println("id === > " + producto.getId());
        
        producto = new DProducto(idc, codigo, marca, modelo, precio, costo, "A", codigoLote, tipoProducto);
        return producto.modificar();
    }
    
    public int eliminar(int id) {
        producto.setId(id);
        return producto.eliminar();
    }
}

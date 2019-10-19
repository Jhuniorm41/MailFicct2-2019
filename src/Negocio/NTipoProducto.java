/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DTipoProducto;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Junior Guzman
 */
public class NTipoProducto {
     private DTipoProducto tipoProducto;
   
    public NTipoProducto() {
        tipoProducto = new DTipoProducto();
    }
    
    public DefaultTableModel getTiposProductos() {
        return tipoProducto.getTiposProductos();
    }
    
    public int registrar(String descripcion) {
        tipoProducto = new DTipoProducto(descripcion);
        return tipoProducto.registrar();
    }
    
    public DefaultTableModel getTipoProducto(int id) {
        tipoProducto.setId(id);
        return tipoProducto.getTipoProducto();
    }
    
    public int modificar(int id, String descripcion) {
        tipoProducto = new DTipoProducto(id, descripcion);
        return tipoProducto.modificar();
    }
    
    public int eliminar(int id) {
        tipoProducto.setId(id);
        return tipoProducto.eliminar();
    }
}

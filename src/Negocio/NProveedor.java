/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DProveedor;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NProveedor {
    private DProveedor prov;
   
    public NProveedor() {
        prov = new DProveedor();
    }
    
    public DefaultTableModel getProveedores() {
        return prov.getProveedores();
    }
    
    public String registrar(String codigo, String nombre, String telefono, String direccion) {
        String msg = "";
        if (codigo.length() > 15) {
            msg += "codigo demasiado largo (maximo 15 caracteres)";
        }
        if (nombre.length() > 50) {
            msg += "nombre demasiado largo (maximo 50 caracteres)";
        }
        if (telefono.length() > 11) {
            msg += "telefono demasiado largo (maximo 11 caracteres)";
        }
        if (direccion.length() > 20) {
            msg += "direccion demasiado larga (maximo 20 caracteres)";
        }
        if (msg.length() > 0) {
            return msg;
        }
        prov = new DProveedor(codigo, nombre,telefono, direccion, "A");
        if (prov.registrar() > 0) {
            return "Se registro correctamente";
        } else {
            return "No se registro correctamente";
        }
        
    }
    
    public DefaultTableModel getProveedor(int id) {
        prov.setId(id);
        return prov.getProveedor();
    }
    
    public int modificar(String codigo, String nombre, String telefono, String direccion) {
        prov.setCodigo(codigo);
        int id = prov.getIdProveedor();
        prov = new DProveedor(id, codigo, nombre,telefono, direccion, "A");
        return prov.modificar();
    }
    
   public int eliminar(String codigo) {
        prov.setCodigo(codigo);
        int id = prov.getIdProveedor();
        prov.setId(id);
        return prov.eliminar();
    }
}

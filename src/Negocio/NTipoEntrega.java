/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DTipoEntrega;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Junior Guzman
 */
public class NTipoEntrega {
       private DTipoEntrega tipoEntrega;
   
    public NTipoEntrega() {
        tipoEntrega = new DTipoEntrega();
    }
    
    public DefaultTableModel getTipoEntrega() {
        return tipoEntrega.getTipoEntrega();
    }
    
    public int registrar(String descripcion) {
        tipoEntrega = new DTipoEntrega(descripcion);
        return tipoEntrega.registrar();
    }
    
    public DefaultTableModel getTipoEntrega(int id) {
        tipoEntrega.setId(id);
        return tipoEntrega.getTipoEntrega();
    }
    
    public int modificar(int id, String descripcion) {
        tipoEntrega = new DTipoEntrega(id, descripcion);
        return tipoEntrega.modificar();
    }
    
    public int eliminar(int id) {
        tipoEntrega.setId(id);
        return tipoEntrega.eliminar();
    }
}

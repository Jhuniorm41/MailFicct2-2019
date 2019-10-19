/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DAlmacen;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NAlmacen {
    private DAlmacen almacen;
    
    public NAlmacen() {
        almacen = new DAlmacen();
    }
    
    public DefaultTableModel getAlmacenes() {
        return almacen.getAlmacenes();
    }
    
    public DefaultTableModel getAlmacen(int id) {
        almacen.setId(id);
        return almacen.getAlmacen();
    }
        public int registrar(String codigo) {
        almacen = new DAlmacen(codigo);
        return almacen.registrar();
    }
    
    public int modificar(int id, String codigo) {
        almacen = new DAlmacen(id, codigo);
        return almacen.modificar();
    }
    
    public int eliminar(int id) {
        almacen.setId(id);
        return almacen.eliminar();
    }
    
}

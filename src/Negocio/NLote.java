/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DLote;
import Datos.DPedido;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NLote {
    
    private DLote lote;
    
    public NLote() {
        lote = new DLote();
    }
    
    public DefaultTableModel getLotes() {
        return lote.getLotes();
    }
    
    public int registrar(String codigo, Date fecha_ingreso, int cantidad,int almacenid, int proveedorid) {
        lote = new DLote(codigo, fecha_ingreso, cantidad, "A", almacenid, proveedorid);
        return lote.registrar();
    }    
    
    public DefaultTableModel getLote(int id) {
        lote.setId(id);
        return lote.getLote();
    }
    
    public int modificar(int id, String codigo, Date fecha_ingreso, int cantidad, int clienteid, int proveedorid) {
        lote = new DLote(id, codigo, fecha_ingreso, cantidad, "A", clienteid, proveedorid);
        return lote.modificar();
    }
    
    public int eliminar(int id) {
        lote.setId(id);
        return lote.eliminar();
    }
}

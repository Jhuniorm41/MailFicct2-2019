/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DNotaVenta;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NNotaVenta {
    private DNotaVenta notaventa;
    
    public NNotaVenta() {
        notaventa = new DNotaVenta();
    }
    
    public DefaultTableModel getNotasVentas() {
        return notaventa.getNotasVentas();
    }
    
    public DefaultTableModel getNotaVenta(int id) {
        notaventa.setId(id);
        return notaventa.getNotaVenta();
    }
    
    public int registrar(String codigo, Date fecha_emision, double monto_total, int pedidoid) {
        notaventa = new DNotaVenta(codigo, fecha_emision, monto_total, "A", pedidoid);
        return notaventa.registrar();
    }
    
    public int modificar(int id, String codigo, Date fecha_emision, double monto_total, String estado, int pedidoid) {
        notaventa = new DNotaVenta(id, codigo, fecha_emision, monto_total, estado, pedidoid);
        return notaventa.modificar();
    }
    
    public int eliminar(int id) {
        notaventa.setId(id);
        return notaventa.eliminar();
    }
    
}

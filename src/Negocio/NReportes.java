/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DReportes;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NReportes {
    private DReportes reporte;

    public NReportes() throws Exception{
        this.reporte = new DReportes();
    }

   public DefaultTableModel getReporteProdsGarantiaVige() {
        return reporte.getProdsGarantiaVige();
    }
    
    public DefaultTableModel getReporteReservas() {
        return reporte.getReservas();
    }
    
    public DefaultTableModel getCotizaciones() {
        return reporte.getCotizaciones();
    }
}

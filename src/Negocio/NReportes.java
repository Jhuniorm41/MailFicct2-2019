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

    public DefaultTableModel getReportePedidosCodigoCliente(String codigo) {
        return reporte.getReportePedidosCliente(codigo);
    }

    public DefaultTableModel getReporteNotaVentaCodigoCliente(String codigo) {
        return reporte.getReporteNotaVentasCliente(codigo);
    }
    
    public DefaultTableModel getReporteEntregasCodigoAdministrativo(String codigo) {
        return reporte.getReporteEntregasAdministradores(codigo);
    }
}

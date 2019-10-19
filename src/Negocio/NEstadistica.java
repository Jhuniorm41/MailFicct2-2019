/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DEstadistica;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NEstadistica {
     private DEstadistica estadistica;

    public NEstadistica() {
        this.estadistica = new DEstadistica();
    }

    public DefaultTableModel getEstadisticaVentasMes(int anio) {
        return estadistica.getEstadisticasVentasMes(anio);
    }

    public DefaultTableModel getEstadisticaVentasDia(String mes, int anio) {
        mes = mes.trim();
        switch (mes.toLowerCase()) {
            case "enero":
                return estadistica.getEstadisticasVentasDia(1, anio);
            case "febrero":
                return estadistica.getEstadisticasVentasDia(2, anio);
            case "marzo":
                return estadistica.getEstadisticasVentasDia(3, anio);
            case "abril":
                return estadistica.getEstadisticasVentasDia(4, anio);
            case "mayo":
                return estadistica.getEstadisticasVentasDia(5, anio);
            case "junio":
                return estadistica.getEstadisticasVentasDia(6, anio);
            case "julio":
                return estadistica.getEstadisticasVentasDia(7, anio);
            case "agosto":
                return estadistica.getEstadisticasVentasDia(8, anio);
            case "septiembre":
                return estadistica.getEstadisticasVentasDia(9, anio);
            case "octubre":
                return estadistica.getEstadisticasVentasDia(10, anio);
            case "noviembre":
                return estadistica.getEstadisticasVentasDia(11, anio);
            case "diciembre": 
                return estadistica.getEstadisticasVentasDia(12, anio);
            default:
               System.out.println("No ingreso un mes correcto");
        } return estadistica.getEstadisticasVentasMes(1);
   }
}

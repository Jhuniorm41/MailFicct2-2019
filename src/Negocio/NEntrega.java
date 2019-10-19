/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DEntrega;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NEntrega {
    private DEntrega entrega;
    
    public NEntrega() {
        entrega = new DEntrega();
    }
    
    public DefaultTableModel getEntregas() {
        return entrega.getEntregas();
    }
    
    public DefaultTableModel getEntrega(int id) {
        entrega.setId(id);
        return entrega.getEntrega();
    }
    
    public String registrar(String codigo, String fecha_registro, String fecha_entrega, 
            String destino, int administrativoid, int notaventaid) {
        String msg = "";
        if (codigo.length() > 20) {
            msg += "codigo demasiado largo (maximo 20 caracteres)";
        }
        if (fecha_registro.length() > 10) {
            msg += "fecha registro no valida (maximo 10 caracteres)\n";
        }
        if (fecha_entrega.length() > 10) {
            msg += "fecha entrega no valida (maximo 10 caracteres)\n";
        }
        if (destino.length() > 50) {
            msg += "destino demasiado largo (maximo 50 caracteres)\n";
        }
        String arr[] = fecha_registro.split("-");
        if (arr.length != 3) {
            msg += "fecha registro no valida, utilice como separador -\n";
        }
        int y = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]) - 1;
        int d = Integer.parseInt(arr[2]);
        String arr2[] = fecha_entrega.split("-");
        if (arr2.length != 3) {
            msg += "fecha entrega no valida, utilice como separador -\n";
        }
        int y1 = Integer.parseInt(arr2[0]);
        int m1 = Integer.parseInt(arr2[1]) - 1;
        int d1 = Integer.parseInt(arr2[2]);
        if (msg.length() > 0) {
            return msg;
        }
        entrega = new DEntrega(codigo, new Date(y, m, d), new Date(y1, m1, d1), destino, "A", administrativoid, notaventaid);
        if (entrega.registrar() > 0) {
            return "Se guardo correctamente";
        } else {
            return "No se guardo correctamente";
        }
    }
    
    public int modificar(int id, String codigo, Date fecha_registro, Date fecha_entrega, 
            String destino, String estado, int administrativoid, int notaventaid, int tipoentregaid) {
        entrega = new DEntrega(id, codigo, fecha_registro, fecha_entrega, destino, estado, administrativoid, notaventaid);
        return entrega.modificar();
    }
    
    public int eliminar(int id) {
        entrega.setId(id);
        return entrega.eliminar();
    }
}

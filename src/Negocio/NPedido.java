/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DDetallePedido;
import Datos.DPedido;
import Datos.DProveedor;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NPedido {
    
    private DPedido pedido;
    private DDetallePedido detalle;
    
    public NPedido() {
        pedido = new DPedido();
    }
    
    public DefaultTableModel getPedidos() {
        return pedido.getPedidos();
    }
    
    public String registrar(String codigo, String fecha_registro, String descripcion, double monto_total, int clienteid, ArrayList<Integer> ids) {
        
        String msg = "";
        if (codigo.length() > 15) {
            msg += "codigo demasiado largo (maximo 15 caracteres)\n";
        }
        if (fecha_registro.length() > 10) {
            msg += "fecha no valida (maximo 10 caracteres)\n";
        }
        if (descripcion.length() > 50) {
            msg += "descripcion demasiado largo";
        }
        String arr[] = fecha_registro.split("-");
        if (arr.length != 3) {
            msg += "fecha no valida, utilice como separador -\n";
        }
        if (msg.length() > 0) {
            return msg;
        }
        int year = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]) - 1;
        int day = Integer.parseInt(arr[2]);
        pedido = new DPedido(codigo, new Date(year, month, day), descripcion,"A", monto_total, clienteid);
        int result = pedido.registrar();
        if (result > 0) {
            for (int i = 0; i < ids.size(); i++) {
                detalle = new DDetallePedido(result, ids.get(i));
                 if (detalle.registrar() < 1)
                     return "No se guardo correctamente, fallo en detallepedido";
            }
            return "Se guardo correctamente";
        } else {
            return "No se guardo correctamente";
        }        
        
    }
    
    public DefaultTableModel getPedido(int id) {
        pedido.setId(id);
        return pedido.getPedido();
    }
    
    public int modificar(int id, String codigo, Date fecha_registro, String descripcion, double monto_total, int clienteid) {
        pedido = new DPedido(id, codigo, fecha_registro, descripcion, "A", monto_total, clienteid);
        return pedido.modificar();
    }
    
    public int eliminar(int id) {
        pedido.setId(id);
        return pedido.eliminar();
    }
}

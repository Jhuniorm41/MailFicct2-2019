/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DCliente_Imprisol;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NCliente {
    private DCliente_Imprisol cliente;
   
    public NCliente() {
        cliente = new DCliente_Imprisol();
    }
    
    public DefaultTableModel getClientes() {
        return cliente.getClientes();
    }
    
    public String registrar(String codigo, int nit, String nombre, String telefono) {
        String msg = "No se pudo registrar el cliente\n";
        if (codigo.length() > 15) {
            msg += "Codigo demasiado largo (Maximo 15 caracteres)\n";
        }
        if (nit > 10000) {
            msg += "nit demasiado largo\n";
        }
        if (nombre.length() > 50) {
            msg += "nombre demasiado largo (maximo 50 caracteres)\n";
        }
        if (telefono.length() > 11) {
            return msg += "telefono demasiado largo (maximo 11 caracteres)\n";
        }
        cliente = new DCliente_Imprisol(codigo, nit, nombre,telefono);
        if (cliente.registrar() > 0) {
            return "Guardo correctamente";
        } else {
            return "No se guardo correctamente";
        }
    }
    
    public DefaultTableModel getCliente(int id) {
        cliente.setId(id);
        return cliente.getCliente();
    }
    
    public int modificar(String codigo, int nit, String nombre, String telefono) {
        cliente.setCodigo(codigo);
        int id = cliente.getIdCliente();
        cliente = new DCliente_Imprisol(id, codigo, nit, nombre,telefono);
        return cliente.modificar();
    }
    
    public int eliminar(String codigo) {
        cliente.setCodigo(codigo);
        cliente.getIdCliente();
        return cliente.eliminar();
    }
}

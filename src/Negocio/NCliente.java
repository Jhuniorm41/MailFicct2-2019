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
    
    public String registrar(String email, String contraseña, int nit, String nombre, String telefono) {
        String msg = "No se pudo registrar el cliente\n";
        if (email.length() > 50) {
            msg += "Codigo demasiado largo (Maximo 25 caracteres)\n";
        }
        if ((contraseña.length() < 6) || (contraseña.length() > 20)) {
            msg += "Contraseña(debe tener mas de 6 caracteres y menos de 25 caracteres))\n";
        }
        if (nit > 1000000000) {
            msg += "nit no coincide con los patrones de Impuestos Nacionales\n";
        }
        if (nombre.length() > 50) {
            msg += "nombre demasiado largo (maximo 50 caracteres)\n";
        }
        if (telefono.length() > 11) {
            return msg += "telefono demasiado largo (maximo 11 numeros)\n";
        }
        cliente = new DCliente_Imprisol(email, contraseña, nit, nombre,telefono);
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
    
    public int modificar(int nit, String nombre, String telefono) {
        int id = 0;
        cliente.setNit(nit);
        id = cliente.getIdCliente();
        if (id == 0) {
            return 0;
        }
        cliente = new DCliente_Imprisol();
        cliente.setNit(nit);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        return cliente.modificar();
        
    }
    
    public int eliminar(int nit) {
        cliente.setNit(nit);
        cliente.getIdCliente();
        return cliente.eliminar();
    }
}

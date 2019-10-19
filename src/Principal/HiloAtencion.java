/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADL
 */
public class HiloAtencion extends Thread {
    public volatile String mensaje;

    public HiloAtencion(String mensaje) {
        this.mensaje = mensaje;
    }
    
    @Override
    public void run() {
        try {
            new Impresol().procesarMensaje(mensaje);
        } catch (Exception ex) {
            System.out.println("Error al levantar el servidor");
            Logger.getLogger(HiloAtencion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

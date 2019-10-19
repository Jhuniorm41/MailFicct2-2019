/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Protocolos.POP;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Constantes;

/**
 *
 * @author ADL
 */
public class HiloMail extends Thread {
    
    public volatile boolean estado = true;

    @Override
    public void run() {
        System.out.println("Iniciar Escucha!!!");
            while (estado) {
                // Preguntar si hay mail
                String content = POP.readMail();
                if (content != null) {
                    System.out.println("Llego Correo!!!");
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println(content);
                    System.out.println("---------------------------------------------------------------------------");
                    new HiloAtencion(content).start();
                }
                waitCiclo();
            }
            System.out.println("Terminar Escucha!!!");
    }
    
    public void waitCiclo() {
            try {
                sleep(Constantes.TIME_THREAD * 1000); // Esperar 2 seg.
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloMail.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void finalizar() {
        if (isAlive()) {
            interrupt();
        }
        estado = false;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Negocio.NEstadistica;
import Procesador.Analex;
import Procesador.Token;
import Protocolos.SMTP;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import utils.Tools;

/**
 *
 * @author ADL
 */
public class MailEstadistica_Imprisol {
    
    private NEstadistica estad;
    
    public MailEstadistica_Imprisol() {
        estad = new NEstadistica();
    }
    
    public void porMes(Analex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //clienteNegocio clienteNegocio = new clienteNegocio();
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());
            //SMTP.sendMail(correoDest, Constante.msgAyudaPropietario+"\n\n",Comandos_Ayuda.AYUDA_REGISTRARCLIENTE);
            return;
        }
        try {
            // Sino, ejecutar el comando
            analex.Avanzar();
            // Atributos      
            int anio = analex.Preanalisis().getAtributo();
            
            DefaultTableModel tabla =  estad.getEstadisticaVentasMes(anio);
          
            SMTP.sendMail(destinatario, "VENTAMES", "VENTAMES" + "\n" + Tools.dibujarDatos(tabla));
            //pedido.modificar(codigo, d, descripcion, total, clienteid, ids);
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            System.out.println("ERROR -->" + e.getLocalizedMessage());
            System.out.println("ERROR -->" + e.getCause());
            System.out.println("ERROR ------> " + e.getMessage());
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(destinatario, "VENTAMES", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);
            
        }
    }
    
    public void porDia(Analex analex, String destinatario) {
        
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //clienteNegocio clienteNegocio = new clienteNegocio();
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());
            //SMTP.sendMail(correoDest, Constante.msgAyudaPropietario+"\n\n",Comandos_Ayuda.AYUDA_REGISTRARCLIENTE);
            return;
        }
        try {
            // Sino, ejecutar el comando
            analex.Avanzar();
            // Atributos      
            String mes = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int anio = analex.Preanalisis().getAtributo();
            DefaultTableModel tabla =  estad.getEstadisticaVentasDia(mes, anio);
          
            SMTP.sendMail(destinatario, "VENTADIA", "VENTADIA" + "\n" + Tools.dibujarDatos(tabla));
            //pedido.modificar(codigo, d, descripcion, total, clienteid, ids);
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            System.out.println("ERROR -->" + e.getLocalizedMessage());
            System.out.println("ERROR -->" + e.getCause());
            System.out.println("ERROR ------> " + e.getMessage());
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(destinatario, "VENTADIA", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);
            
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Negocio.NReportes;
import Procesador.Analex;
import Procesador.Token;
import Protocolos.MimeMail;
import Protocolos.SMTP;
import javax.swing.table.DefaultTableModel;
import utils.Tools;

/**
 *
 * @author ADL
 */
public class MailReportes {
    NReportes reportes;
    
    public MailReportes() throws Exception {
        reportes = new NReportes();
    }
    
    public void reporteProdsGarantia(Analex analex, String destinatario) {
         // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //SMTP.sendMail(correoDest, Constantes.MsgAyuda, Constantes.AYUDA_MOSTRARCLIENTES);
            return;
        }
         analex.Avanzar();
        // Atributos      
       // String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
        //DefaultTableModel tabla = reportes.getReportePedidosCodigoCliente(codigo);
        try {
            MimeMail mimemailer = new MimeMail();
            
            mimemailer.sendHtmlEmail(destinatario, "PRODUCTOS CON GARANTIA VIGENTE",Tools.dibujarTablawithHTMLwithoutOpciones(reportes.getReporteProdsGarantiaVige()));            
            //SMTP.sendMail(destinatario, "REPORTE DE VENTAS DEL CLIENTE", Tools.dibujarDatos(tabla));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
    
    public void reporteReservas(Analex analex, String destinatario) {
         // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //SMTP.sendMail(correoDest, Constantes.MsgAyuda, Constantes.AYUDA_MOSTRARCLIENTES);
            return;
        }
         analex.Avanzar();
        // Atributos      
       // String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
        //DefaultTableModel tabla = reportes.getReportePedidosCodigoCliente(codigo);
        try {
            MimeMail mimemailer = new MimeMail();
            
            mimemailer.sendHtmlEmail(destinatario, "RESERVAS VIGENTES",Tools.dibujarTablawithHTMLwithoutOpciones(reportes.getReporteReservas()));            
            //SMTP.sendMail(destinatario, "REPORTE DE VENTAS DEL CLIENTE", Tools.dibujarDatos(tabla));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
    
    public void reporteCotizaciones(Analex analex, String destinatario) {
         // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //SMTP.sendMail(correoDest, Constantes.MsgAyuda, Constantes.AYUDA_MOSTRARCLIENTES);
            return;
        }
         analex.Avanzar();
        // Atributos      
       // String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
        //DefaultTableModel tabla = reportes.getReportePedidosCodigoCliente(codigo);
        try {
            MimeMail mimemailer = new MimeMail();
            
            mimemailer.sendHtmlEmail(destinatario, "LISTA DE COTIZACIONES", Tools.dibujarTablawithHTMLwithoutOpciones(reportes.getCotizaciones()));            
            //SMTP.sendMail(destinatario, "REPORTE DE VENTAS DEL CLIENTE", Tools.dibujarDatos(tabla));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
}

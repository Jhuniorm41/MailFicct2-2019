/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Email.MailPersonal_Imprisol;
import Email.MailCliente_Imprisol;
import Email.MailEstadistica_Imprisol;
import Email.MailReportes;

import Procesador.Analex;
import Procesador.Cinta;
import Procesador.Parser;
import Procesador.Token;
import Protocolos.MimeMail;
import Protocolos.SMTP;
import utils.Constantes;
import utils.Help;
import utils.Tools;

/**
 *
 * @author ADL
 */
public class Impresol {

    
    /*public void procesarMensaje(String Mensaje){
        String destinatario = Tools.getDestinatario(Mensaje);        */

    public Impresol() throws Exception {
    }

    private MailReportes mail_reporte = new MailReportes();
    private MailEstadistica_Imprisol mail_es = new MailEstadistica_Imprisol();
     ///TECNO WEB GESTION 2-2019
    private MailCliente_Imprisol mail_cliente = new MailCliente_Imprisol();
    private MailPersonal_Imprisol mail_personal = new MailPersonal_Imprisol();
    
    
    

    public void procesarMensaje(String Mensaje) throws Exception {
        String destinatario = Tools.getDestinatario(Mensaje);
        System.out.println("Destinatario: " + destinatario);
        String content = Tools.getSubjectOrden(Mensaje);
        System.out.println("ProcesarMensaje:Contenido<<<<<<<<>>>>>>>>>>>>>> " + content);

        //Usuario user = new Usuario();
        //Verificamos si el usuario esta registrado en el sistema
        //Quitamos formato extra en caso de reenvio o de respuesta de mensajes
        content = Tools.quitar_formato_mail(content);
        Cinta cinta = new Cinta(content);
        Analex analex = new Analex(cinta);
        Parser parser = new Parser(analex);
        // Verificar Orden
        parser.Expresion(); //verifica la estructura

        if (parser.errorFlag) {
            // Enviar Correo de Error
            SMTP.sendMail(destinatario, "Error de Comando", "El comando:  " + analex.M.texto + "\n es incorrecto!, trate consultando los comandos de ayuda con el comando HELP");
            return;
        }

        // Si todo va bien, procesar el Comando
        analex.Init();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            // Mostrar Ayudas
            System.out.println("HELP-------");
            SMTP.sendMail(destinatario, "AYUDA PRINCIPAL", Help.PRINCIPAL);
            return;
        }

        switch (token.getAtributo()) {
            //////////////TECNO WEB 2-2019
            //CASO DE USO NUMERO 1
                //CLIENTES
             case Token.OBTENERCLIENTES:
                this.mail_cliente.listar(analex, destinatario);
                break;
            case Token.REGISTRARCLIENTE:
                this.mail_cliente.registrar(analex, destinatario);
                break;
            case Token.MODIFICARCLIENTE:
                this.mail_cliente.modificar(analex, destinatario);
                break;
            case Token.ELIMINARCLIENTE:
                this.mail_cliente.eliminar(analex, destinatario);
                break;  
                
            //PERSONAL
             case Token.OBTENERPERSONALES:
                this.mail_personal.listar(analex, destinatario);
                break;
            case Token.REGISTRARPERSONAL:
                this.mail_personal.registrar(analex, destinatario);
                break;
            case Token.MODIFICARPERSONAL:
                this.mail_personal.modificar(analex, destinatario);
                break;
            case Token.ELIMINARPERSONAL:
                this.mail_personal.eliminar(analex, destinatario);
                break;  
                
            /// REPORTES
            case Token.REPORTEPRODSGARANTIA:
                this.mail_reporte.reporteProdsGarantia(analex, destinatario);
                break;
            case Token.REPORTERESERVASVIG:
                this.mail_reporte.reporteReservas(analex, destinatario);
                break;
            case Token.REPORTECOTIZACIONES:
                this.mail_reporte.reporteCotizaciones(analex, destinatario);
                break;      
                
                
            ///////////////////////////////////
        }

        //Para registrar a un usuario
//        if(token.getAtributo()== Token.REGISTRARME)
//        {
//        if(user.existeUsuario(destinatario)){
//         clienteSMTP.sendMail(destinatario, "Error de Registro!", "El usuario ya se encuentra registrado");
//        }else{
//        registrarme(analex, destinatario);    
//        }                           
//        }        
//        if (user.existeUsuario(destinatario)){
//          // Sino es HELP, es una funcionalidad
//
//            switch (token.getAtributo()) {
//
//            }         
//
//        } else{
//           //clienteSMTP.sendMail(destinatario, "No se encuentra registrado!", "El sistema no puede atender sus peticiones dado que no se encuentra registrado, favor comuniquese con el administrador, Gracias... :)");   
//        }   
    }
}

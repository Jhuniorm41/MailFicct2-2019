/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Negocio.NAlmacen;
import Procesador.Analex;
import Procesador.Token;
import Protocolos.SMTP;
import utils.Tools;

/**
 *
 * @author Junior Guzman
 */
public class MailAlmacen extends TemplateMail{
    
    private NAlmacen almacen;
    
    public MailAlmacen() throws Exception {
        this.almacen = new NAlmacen();
    }

    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {
         // Obtengo el Siguiente token
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
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            almacen.registrar(codigo);
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            System.out.println("SUPUESTAMENTE REGISTRO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(destinatario, "Registrar Almacen", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
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
            int id = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            almacen.modificar(id, codigo);
            System.out.println("SUPUESTAMENTE ACTUALIZDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(destinatario, "Actualizar almacen", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //SMTP.sendMail(correoDest, Constantes.MsgAyuda, Constantes.AYUDA_MOSTRARCLIENTES);
            return;
        }

        try {
            // Sino, ejecutar el comando
            analex.Avanzar();
            // Atributos      
            int id = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            almacen.eliminar(id);
            System.out.println("ELIMINO");
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            //SMTP.sendMail(correoDest,"OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Eliminar almacen", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
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
        // Sino, ejecutar el comando
        try {
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            SMTP.sendMail(destinatario, "OBTENERALMACENES", "Lista de TIPO PRODUCTO\n" + Tools.dibujarDatos(almacen.getAlmacenes()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar almacenes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }

        //  String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());        
        //  clienteSMTP.sendMail(correoDest, "Mostrar Clientes\n\n", mensaje);   
    }  
}

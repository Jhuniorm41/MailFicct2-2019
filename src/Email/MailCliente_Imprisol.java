/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Negocio.NCliente;
import Procesador.Analex;
import Procesador.Token;
import Protocolos.MimeMail;
import Protocolos.SMTP;
import utils.Tools;

/**
 *
 * @author Junior Guzman
 */
public class MailCliente_Imprisol extends TemplateMail {

    private NCliente cliente;

    public MailCliente_Imprisol() throws Exception {
        this.cliente = new NCliente();
    }

    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        System.out.println("ENTRO AL GUARDAR >>>>>>>><<<<<<<<<<<");
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
            String email = Tools.quitarComillas(analex.Preanalisis().getToStr());
            System.out.println("EL EMAIL ES =====>>>>>>: "+email);
            analex.Avanzar();
            analex.Avanzar();
            String contraseña = Tools.quitarComillas(analex.Preanalisis().getToStr());
            System.out.println("CONTRASEÑA ES =====>>>>>>: "+contraseña);
            analex.Avanzar();
            analex.Avanzar();
            int nit = analex.Preanalisis().getAtributo();
            System.out.println("NIT ES =====>>>>>>: "+nit);
            analex.Avanzar();
            analex.Avanzar();
            String nombre = Tools.quitarComillas(analex.Preanalisis().getToStr());
            System.out.println("NOMBRE ES =====>>>>>>: "+nombre);
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Tools.quitarComillas(analex.Preanalisis().getToStr());
            System.out.println("TELEFONO ES =====>>>>>>: "+telefono);
            String respuesta = cliente.registrar(email,contraseña, nit, nombre, telefono);
            SMTP.sendMail(destinatario, "Registrar Cliente", respuesta);
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(destinatario, "Registrar Cliente", "ERROR CATCH" + "\n" + "Mensaje enviado: " + analex.M.texto);
            
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
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int nit = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            String nombre = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Tools.quitarComillas(analex.Preanalisis().getToStr());

            cliente.modificar(codigo, nit, nombre, telefono);
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(destinatario, "Actualizar Cliente", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {
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

        try {
            // Sino, ejecutar el comando
            analex.Avanzar();
            // Atributos      
            int nit = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            cliente.eliminar(nit);
            System.out.println("ELIMINO");
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            //SMTP.sendMail(correoDest,"OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

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
            MimeMail mail = new MimeMail();
            mail.sendHtmlEmail(destinatario, "Listado de ClienteS", Tools.dibujarTablawithHTML(cliente.getClientes()));
            //SMTP.sendMail(destinatario, "OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }

        //  String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());        
        //  clienteSMTP.sendMail(correoDest, "Mostrar Clientes\n\n", mensaje);   
    }

}

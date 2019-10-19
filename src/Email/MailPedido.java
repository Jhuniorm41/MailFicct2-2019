/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Negocio.NPedido;
import Procesador.Analex;
import Procesador.Token;
import Protocolos.SMTP;
import java.sql.Date;
import java.util.ArrayList;
import utils.Constantes;
import utils.Help;
import utils.Tools;

/**
 *
 * @author ADL
 */
public class MailPedido extends TemplateMail{

    private NPedido pedido;
    
    public MailPedido() {
        pedido = new NPedido();
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
            analex.Avanzar();
            analex.Avanzar();
            String fecha = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String descripcion = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String monto_total = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int clienteid = analex.Preanalisis().getAtributo();
            double total = Double.parseDouble(monto_total);
            Date d = new Date(1000);
            analex.Avanzar();
            analex.Avanzar();
            token = analex.Preanalisis();
            System.out.println("ATRIBUTO " + token.getAtributo());
            ArrayList<Integer> ids = new ArrayList<>();
            if (token.getAtributo() == Token.PROD) {
                System.out.println("PUSO PARAMETROS");
                analex.Avanzar();
                analex.Avanzar();
                int p0;
                while(!analex.Preanalisis().getToStr().equals("EOF")) {
                    System.out.println("====" + analex.Preanalisis().getToStr());
                    
                    p0 = analex.Preanalisis().getAtributo();
                    analex.Avanzar();
                    analex.Avanzar();
                    ids.add(p0);
                }
            }
            System.out.println("EPARA  =>" + ids);
            pedido.registrar(codigo, fecha, descripcion, total, clienteid, ids);
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            System.out.println("ERROR -->" + e.getLocalizedMessage());
            System.out.println("ERROR -->" + e.getCause());
            System.out.println("ERROR ------> " + e.getMessage());
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            //SMTP.sendMail(destinatario, "Registrar PROVEEDOR", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

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
            String fecha = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String descripcion = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String monto_total = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int clienteid = analex.Preanalisis().getAtributo();
            double total = Double.parseDouble(monto_total);
            Date d = new Date(1000);
            analex.Avanzar();
            analex.Avanzar();
            token = analex.Preanalisis();
            System.out.println("ATRIBUTO " + token.getAtributo());
            ArrayList<Integer> ids = new ArrayList<>();
            if (token.getAtributo() == Token.PROD) {
                System.out.println("PUSO PARAMETROS");
                analex.Avanzar();
                analex.Avanzar();
                int p0;
                int c = 0;
                while(!analex.Preanalisis().getToStr().equals("EOF") && c<= 5) {
                    System.out.println("====" + analex.Preanalisis().getToStr());
                    
                    p0 = analex.Preanalisis().getAtributo();
                    analex.Avanzar();
                    analex.Avanzar();
                    ids.add(p0);
                    c++;
                }
            }
            System.out.println("EPARA  =>" + ids);
            //pedido.modificar(codigo, d, descripcion, total, clienteid, ids);
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            System.out.println("ERROR -->" + e.getLocalizedMessage());
            System.out.println("ERROR -->" + e.getCause());
            System.out.println("ERROR ------> " + e.getMessage());
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            //SMTP.sendMail(destinatario, "Registrar PROVEEDOR", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //SMTP.sendMail(destinatario, Constantes.MsgAyuda, Help.AYUDA_MOSTRARCLIENTES);
            return;
        }
        // Sino, ejecutar el comando
        try {
            SMTP.sendMail(destinatario, "OBENTENER PEDIDOS", "Lista de PEDIDOS\n" + Tools.dibujarDatos(pedido.getPedidos()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "OBTENER PEDIDOS", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
    
    
}

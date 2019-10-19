/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Negocio.NLote;
import Procesador.Analex;
import Procesador.Token;
import Protocolos.SMTP;
import java.sql.Date;
import utils.Tools;

/**
 *
 * @author Estudiante
 */
public class MailInventario extends TemplateMail {

    NLote inventario;
    
    public MailInventario () {
        inventario = new NLote();
    }
    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {
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
            String fecha_ingreso = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int cantidad = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            int almacenid = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            int proveedorid = analex.Preanalisis().getAtributo();
            Date fecha_reg = new Date(1000);
            inventario.registrar(codigo, fecha_reg, cantidad, almacenid, proveedorid);
            
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            //SMTP.sendMail(destinatario, "Registrar Cliente", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            SMTP.sendMail(destinatario, "OBENTENERINVENTARIOS", "Lista de INVENTARIOS\n" + Tools.dibujarDatos(inventario.getLotes()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "OBENTENERINVENTARIOS", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
    
}

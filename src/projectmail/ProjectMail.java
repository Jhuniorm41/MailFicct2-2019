/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmail;

import Datos.Conexion;
import Datos.DAdministrativo;
import Datos.DCliente_Imprisol;
import Datos.DPedido;
import Datos.DProveedor;
import Datos.DUsuario_Imprisol;
import Negocio.NCliente;
import Negocio.NPedido;
import Negocio.NProducto;
import Negocio.NReportes;
import Protocolos.MimeMail;
import Protocolos.POP;
import Protocolos.SMTP;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import utils.Help;
import utils.Tools;

/**
 *
 * @author ADL
 */
public class ProjectMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.abrirConexion();
    }
    
}

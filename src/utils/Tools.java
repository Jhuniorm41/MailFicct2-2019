/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class Tools {
    public static Date convertirFechas(String fecha) {
        // Formato de fecha a ingresar dd-MM-yyyy
        Date fechaNueva = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date fechaJava = formato.parse(fecha);
            fechaNueva = new Date(fechaJava.getTime());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return fechaNueva;
    }
    public static String getDestinatario(String contenido) {
        String destinatario = "";
        // Dividir en lineas
        String[] lines = contenido.split("\n");
        int index = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 5
                    && lines[i].substring(0, 5).toUpperCase().equals("FROM:")) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            // Quitar la palabra 'From: '
            destinatario = lines[index].substring(6);
            lines = destinatario.split(" ");
            if (lines.length == 1) { // Correo del Server
                destinatario = lines[0];
            } else { // Desde otro Servidor de Correo
                destinatario = lines[lines.length - 1];
                destinatario = destinatario.split("<")[1].split(">")[0];
            }
        }
        return destinatario;
    }

    public static String getSubjectOrden(String contenido) {
        String orden = "";
        String aux ="";
        // Dividir en lineas
        String[] lines = contenido.split("\n");
        int index = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 8
                    && lines[i].substring(0, 8).toUpperCase().equals("SUBJECT:")) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            // Quitar la palabra 'Subject: '
            orden = lines[index].substring(8);
            aux = lines[index+1].substring(0,3);
            while(!(aux.equals("To:")) && !(aux.equals("MIM")) && !(aux.equals("Thr"))){
             orden = orden + lines[index +1].substring(0);   
             index ++;
             aux= lines[index+1].substring(0, 3);
            }
        }
        
        return orden;
    }

    public static String dibujarTabla(DefaultTableModel tabla) {
        String tableString = "";
        ArrayList<String> headers = new ArrayList<>();
        ArrayList<List<String>> rowList = new ArrayList<>();

        // Agregando Los Headers
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            headers.add(tabla.getColumnName(i));
        }

        // Agregando Content
        for (int i = 0; i < tabla.getRowCount(); i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                row.add(String.valueOf(tabla.getValueAt(i, j)));
            }
            rowList.add(row.subList(0, row.size()));
        }

        if (rowList.size() < 1) {
            return "(Tabla Vacia)";
        }
        
        // Creando Tabla para mostrar
//        Board board = new Board(125);
//        Table table = new Table(board, 125, headers, rowList);
//        Block tableBlock = table.tableToBlocks();
//        board.setInitialBlock(tableBlock);
//        board.build();
//        tableString = board.getPreview();

        return tableString;
    }

    public static String quitarComillas(String texto) {
        int len = texto.length() - 1;
        return texto.substring(1, len);
    }
    
    public static String quitar_formato_mail(String c) {
        int j = 0;
        while(j< c.length()){
            if ( c.charAt(j) == ':'){
             String cad = c.substring(j + 1);
             return cad;
            }else{
               j++; 
            }
        }
        return c;
    }
    
     public static String dibujarTablawithHTML(DefaultTableModel tabla) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + ".button {\n"
                + "    background-color: #355bad; /* Blue */\n"
                + "    border: none;\n"
                + "    color: white;\n"
                + "    padding: 5px 15px;\n"
                + "    text-align: center;\n"
                + "    text-decoration: none;\n"
                + "    display: inline-block;\n"
                + "    font-size: 16px;\n"
                + "    margin: 4px 2px;\n"
                + "    cursor: pointer;\n"
                + "}\n"
                + "\n"
                + ".button2 {background-color: #008CBA;} /* Blue */\n"
                + ".button3 {background-color: #f44336;} /* Red */ \n"
                + ".button4 {background-color: #e7e7e7; color: black;} /* Gray */ \n"
                + ".button5 {background-color: #355bad;} /* Blue */"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    text-align: left;\n"
                + "    padding: 8px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #f2f2f2}\n"
                + "\n"
                + "th {\n"
                + "    background-color: #355bad;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"w3-container\">\n"
                + "\n"
                + "  <table class=\"w3-table-all\">\n"
                + "    <thead>\n"
                + "<tr class=\"w3-red\">\n";
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tableString += "<th>" + (tabla.getColumnName(i)) + "</th> \n";
        }
        tableString += "</tr> \n"
                + "</thead> \n";

        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                tableString += "<td>"
                        + (String.valueOf(tabla.getValueAt(i, j)))
                        + "</td> \n";
            }
            //<a href=\"mailto:" + Constants.MAIL_USERMAIL + "?subject=ELIMINAR\"><button class=\"button button3\">ELIMINAR</button></a>
//            tableString += "<td><a href=\"mailto:" + Variables.MAIL_USERMAIL + "?subject=MODIFICAR\"> <button class=\"button button5\">MODIFICAR</button>  "
//                    + "<a href=\"mailto:" + Variables.MAIL_USERMAIL + "?subject=ELIMINAR\"> <button class=\"button button3\">ELIMINAR</button> </td> \n";

            tableString += "</tr> \n";

        }

        if (tabla.getRowCount() < 1) {
            return "(Tabla Vacia)";
        }
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html> ";

        return tableString;
    }
     
     public static String dibujarTablawithHTMLwithoutOpciones(DefaultTableModel tabla) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    text-align: left;\n"
                + "    padding: 8px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #f2f2f2}\n"
                + "\n"
                + "th {\n"
                + "    background-color: #355bad;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"w3-container\">\n"
                + "\n"
                + "  <table class=\"w3-table-all\">\n"
                + "    <thead>\n"
                + "<tr class=\"w3-red\">\n";
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tableString += "<th>" + (tabla.getColumnName(i)) + "</th> \n";
        }
//        tableString += "<th> Opciones </th> \n";
        tableString += "</tr> \n"
                + "</thead> \n";

        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                tableString += "<td>"
                        + (String.valueOf(tabla.getValueAt(i, j)))
                        + "</td> \n";
            }
            //<a href=\"mailto:" + Constants.MAIL_USERMAIL + "?subject=ELIMINAR\"><button class=\"button button3\">ELIMINAR</button></a>

            //tableString += "<td><a href=\"mailto:" + Constants.MAIL_USERMAIL + "?subject=MODIFICAR\"> <button class=\"button button5\">MODIFICAR</button>  </td> \n";
            tableString += "</tr> \n";

        }

        if (tabla.getRowCount() < 1) {
            return "(Esta tabla no contiene datos)";
        }
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html> ";

        return tableString;
    }
     
        public static String dibujarDatos(DefaultTableModel data) {
         //System.out.println("COUNT " + data.getRowCount());
         //System.out.println("COLUM " + data.getColumnCount());
        String result = ""; 
        ArrayList<Integer> len = new ArrayList();
        for (int i = 0; i < data.getColumnCount(); i++) {
            System.out.println("COLUM NAME --> " + data.getColumnName(i));
            result += data.getColumnName(i) + "            ";
            len.add(data.getColumnName(i).length());
        }
        result += "\n";
        for (int i = 0; i < data.getRowCount(); i++) {
            for (int j = 0; j < data.getColumnCount(); j++) {
                //System.out.println("ELEM -- >" + data.getValueAt(i, j));
                result +=  espacio(result, len.get(j));
                result += String.valueOf(data.getValueAt(i, j)) + "            ";
            }
            result += "\n";
        }
        result += "\n";
        System.out.println("TABLA -----");
        System.out.println(result);
        return result;
     }
        
    public static String espacio(String pal, int cant) {
        
        String pa = "";
        for(int i = 0; i < cant; i++) {
            pa += " ";
        }
        return pa;
    }
}

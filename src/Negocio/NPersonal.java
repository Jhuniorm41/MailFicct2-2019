/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DPersonal_Imprisol;
import java.sql.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NPersonal {

    private DPersonal_Imprisol adm;

    public NPersonal() {
        adm = new DPersonal_Imprisol();
    }

    public DefaultTableModel getAdministrativos() {
        return adm.getAdministrativos();
    }

    public String registrar(String codigo, String nombre, String telefono, String cargo, String fecha_ingreso) {
        String msg = "No se pudo guardar\n";
        if (codigo.length() > 15) {
            msg += "codigo demasiado largo (maximo 15 caracteres)\n";
        }
        if (nombre.length() > 50) {
            msg += "nombre demasiado largo (maximo 50 caracteres)\n";
        }
        if (telefono.length() > 11) {
            msg += "telefono demasiado largo (maximo 11 caracteres)\n";
        }
        if (cargo.length() > 15) {
            msg += "cargo demasiado largo (maximo 15 caracteres\n)";
        }
        if (fecha_ingreso.length() > 10) {
            return msg += "Fecha no valida\n";
        }
        String arr[] = fecha_ingreso.split("-");
        if (arr.length != 3) {
            return msg += "Fecha no valida por favor utilice - ";
        }
        int year = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]) - 1;
        int day = Integer.parseInt(arr[2]);
        
        adm = new DPersonal_Imprisol(codigo, nombre, telefono, "A", cargo, new Date(year, month, day));
        if (adm.registrar() > 0) {
            return "Se guardo correctamente";
        } else {
            return "No se guardo correctamente";
        }
    }

    public DefaultTableModel getAdministrativo(int id) {
        adm.setId(id);
        return adm.getAdministrativo();
    }

    public int modificar(String codigo, String nombre, String telefono, String cargo, Date fecha_ingreso) {
        adm.setCodigo(codigo);
        int id = adm.getIdAdm();
        adm = new DPersonal_Imprisol(id , codigo, nombre, telefono, "A", cargo, fecha_ingreso);
        return adm.modificar();
    }

    public int eliminar(String codigo) {
        adm.setCodigo(codigo);
        int id = adm.getIdAdm();
        adm.setId(id);
        return adm.eliminar();
    }

    public String Mostrar() throws Exception {
          String rx = "";
        try {
            List<DPersonal_Imprisol> lObj = (List<DPersonal_Imprisol>) this.getAdministrativos();

            rx = "<center><h2>LISTA DE ADMINISTRATIVOS DE LA EMPRESA</h2></center><br>";
            rx += " <table style=\"width:100%; border-style: outset; text-align: left;\" >" +
                    "             <thead>\n" +
                    "                   <tr >\n" +
                    "                         <th>#</th>\n" +
                    "                         <th>Codigo</th>\n" +
                    "                         <th>Nombre</th>\n" +
                    "                         <th>Telefono</th>\n" +
                    "                         <th>Cargo</th>\n" +
                    "                   </tr>\n" +
                    "             </thead>\n" +
                    "                  <tbody> ";
            for (DPersonal_Imprisol obj : lObj) {
                rx = rx +
                        "<tr style=\"\">\n" +
                        "   <td>"+ obj.getId() + "</td>\n" +
                        "   <td>"+ obj.getCodigo()+ "</td>\n" +
                        "   <td>"+ obj.getNombre()+ "</td>\n" +
                        "   <td>"+ obj.getTelefono()+ "</td>\n" +
                        "   <td>"+ obj.getCargo()+ "</td>\n" +
                        "</tr>\n"
                ;
            }
            rx  +=   "  </tbody>\n" +
                    "</table>\n"
            ;
        } catch (Exception e){
            throw e;
        }
        return rx;
    }
}

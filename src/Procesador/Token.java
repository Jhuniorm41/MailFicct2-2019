/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesador;

/**
 *
 * @author ADL
 */
public class Token {

    // Constantes
    public static final int NUM = 0; // Numero Valor
    public static final int STRING = 1; // String Constante
    public static final int FUNC = 2; // Alguna Funcion
    public static final int GB = 3; //Guion Bajo
    public static final int LLA = 4; // Llave Abierta
    public static final int LLC = 5; // Llave Cerrada
    public static final int SEPARADOR = 6; // Coma ,
    public static final int FIN = 7;
    public static final int ERROR = 8;
    public static final int TRUE = 9;
    public static final int FALSE = 10;
    public static final int HELP = 11;
    public static final int BARRA = 12;// |

//Funciones
    //public static final int REGISTRARCLIENTE = 100;
    //public static final int MODIFICARCLIENTE = 101;
    //public static final int ELIMINARCLIENTE = 102;
    //public static final int OBTENERCLIENTE = 103;
    //public static final int OBTENERCLIENTES = 104;

    public static final int OBTENERADMINISTRATIVOS = 105;
    public static final int REGISTRARADMINISTRATIVO = 106;
    public static final int MODIFICARADMINISTRATIVO = 107;
    public static final int ELIMINARADMINISTRATIVO = 108;

    public static final int OBTENERPROVEEDORES = 109;
    public static final int REGISTRARPROVEEDOR = 110;
    public static final int MODIFICARPROVEEDOR = 111;
    public static final int ELIMINARPROVEEDOR = 112;

    public static final int OBTENERTIPOSPRODUCTOS = 113;
    public static final int REGISTRARTIPOPRODUCTO = 114;
    public static final int MODIFICARTIPOPRODUCTO = 115;
    public static final int ELIMINARTIPOPRODUCTO = 116;
    
    public static final int OBTENERPRODUCTOS = 117;
    public static final int REGISTRARPRODUCTO = 118;
    public static final int MODIFICARPRODUCTO = 119;
    public static final int ELIMINARPRODUCTO = 120;
    
    public static final int OBTENERTIPOSENTREGAS = 121;
    public static final int REGISTRARTIPOENTREGA = 122;
    public static final int MODIFICARTIPOENTREGA = 123;
    public static final int ELIMINARPTIPOENTREGA = 124;
    
    public static final int OBTENERALMACENES = 125;
    public static final int REGISTRARALMACEN = 126;
    public static final int MODIFICARALMACEN = 127;
    public static final int ELIMINARALMACEN = 128;
    
    public static final int OBTENERPEDIDOS = 129;
    public static final int REGISTRARPEDIDO = 130;
    public static final int MODIFICARPEDIDO = 131;
    public static final int ELIMINARPEDIDO = 132;
    public static final int PROD = 133;
    
    public static final int OBTENERVENTAS = 134;
    public static final int REGISTRARVENTA = 135;
    public static final int MODIFICARVENTA = 136;
    public static final int ELIMINARVENTA = 137;
    
    public static final int OBTENERENTREGAS = 138;
    public static final int REGISTRARENTREGA = 139;
    public static final int MODIFICARENTREGA = 140;
    public static final int ELIMINARENTREGA = 141;
    
    public static final int OBTENERINVENTARIOS = 142;
    public static final int REGISTRARINVENTARIO = 143;
    public static final int MODIFICARINVENTARIO = 144;
    public static final int ELIMINARINVENTARIO = 145;
    
    public static final int REPORTENTCLIENTE = 146;
    public static final int REPORTEENTREGASADM = 147;
    public static final int REPORTEPEDCLIENTES = 148;
    
    public static final int VENTAMES = 149;
    public static final int VENTADIA = 150;
    
    
    ///////////TECNO WEB 2-2019
    //CASO DE USO NUMERO 1 GESTIONAR USUARIO(CLIENTE)
    public static final int REGISTRARCLIENTE = 100;
    public static final int MODIFICARCLIENTE = 101;
    public static final int ELIMINARCLIENTE = 102;
    public static final int OBTENERCLIENTE = 103;
    public static final int OBTENERCLIENTES = 104;
    
    
    
    
    //public static final int M = 150;   

    private int nombre;
    private int atributo;
    private String toStr;

    public Token() {
    }

    public Token(int nombre, int atributo, String toStr) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.toStr = toStr;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getAtributo() {
        return atributo;
    }

    public void setAtributo(int atributo) {
        this.atributo = atributo;
    }

    public String getToStr() {
        return toStr;
    }

    public void setToStr(String toStr) {
        this.toStr = toStr;
    }
}

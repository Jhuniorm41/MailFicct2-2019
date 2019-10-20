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
    public static final int BARRA = 12;// 

    ///////////TECNO WEB 2-2019
    //CASO DE USO NUMERO 1 GESTIONAR USUARIO(CLIENTE Y PERSONAL)
   
    //Funciones
    public static final int REGISTRARCLIENTE = 100;
    public static final int MODIFICARCLIENTE = 101;
    public static final int ELIMINARCLIENTE = 102;
    public static final int OBTENERCLIENTE = 103;
    public static final int OBTENERCLIENTES = 104;
   
    public static final int REGISTRARPERSONAL = 105;
    public static final int MODIFICARPERSONAL = 106;
    public static final int ELIMINARPERSONAL = 107;
    public static final int OBTENERPERSONAL = 108;
    public static final int OBTENERPERSONALES = 109;
    
    //REPORTES
    public static final int REPORTEPRODSGARANTIA = 200;
    public static final int REPORTERESERVASVIG = 201;
    public static final int REPORTECOTIZACIONES = 202;
    
    
    
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesador;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author ADL
 */
public class TPC {

    private static final LinkedList<String> lexemas = new LinkedList<>(Arrays.asList(
            "HELP",
            "TRUE",
            "FALSE",
            // CU1
            //GESTION DE CLIENTES
            
            "REGISTRARCLIENTE",
            "MODIFICARCLIENTE",
            "ELIMINARCLIENTE",
            "OBTENERCLIENTE",
            "OBTENERCLIENTES",
            // GESTION DE PERSONAL
           "REGISTRARPERSONAL",
            "MODIFICARPERSONAL",
            "ELIMINARPERSONAL",
            "OBTENERPERSONAL",
            "OBTENERPERSONAL",
            //REPORTE
            "REPORTEPRODSGARANTIA",
            "REPORTERESERVASVIG",
            "REPORTECOTIZACIONES"
    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
            // CASO DE USO1
            //GESTIONAR CLIENTE
            new Token(Token.FUNC, Token.REGISTRARCLIENTE, "REGISTRARCLIENTE"),
            new Token(Token.FUNC, Token.MODIFICARCLIENTE, "MODIFICARCLIENTE"),
            new Token(Token.FUNC, Token.ELIMINARCLIENTE, "ELIMINARCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTE, "OBTENERCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTES, "OBTENERCLIENTES"),
            // GESTIONAR PERSONAL
            new Token(Token.FUNC, Token.REGISTRARPERSONAL, "REGISTRARPERSONAL"),
            new Token(Token.FUNC, Token.MODIFICARPERSONAL, "MODIFICARPERSONAL"),
            new Token(Token.FUNC, Token.ELIMINARPERSONAL, "ELIMINARPERSONAL"),
            new Token(Token.FUNC, Token.OBTENERPERSONAL, "OBTENERPERSONAL"),
            new Token(Token.FUNC, Token.OBTENERPERSONALES, "OBTENERPERSONALES"),
            
            //REPORTES
            new Token(Token.FUNC, Token.REPORTEPRODSGARANTIA, "REPORTEPRODSGARANTIA"),
            new Token(Token.FUNC, Token.REPORTERESERVASVIG, "REPORTERESERVASVIG"),
            new Token(Token.FUNC, Token.REPORTECOTIZACIONES, "REPORTECOTIZACIONES")

    ));

    public static Token estaEnTPC(String lexema) {
        lexema = lexema.toUpperCase();
        for (int i = 0; i < lexemas.size(); i++) {
            if (lexemas.get(i).toUpperCase().equals(lexema)) {
                Token token = new Token();
                token.setNombre(tokens.get(i).getNombre());
                token.setAtributo(tokens.get(i).getAtributo());
                token.setToStr(tokens.get(i).getToStr());
                return token;
            }
        }
        return null;
    }
}

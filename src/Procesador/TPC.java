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
            // CU GESTION DE USUARIO
            "REGISTRARCLIENTE",
            "MODIFICARCLIENTE",
            "ELIMINARCLIENTE",
            "OBTENERCLIENTE",
            "OBTENERCLIENTES",
            "OBTENERADMINISTRATIVOS",
            "REGISTRARADMINISTRATIVO",
            "MODIFICARADMINISTRATIVO",
            "ELIMINARADMINISTRATIVO",
            "OBTENERPROVEEDORES",
            "REGISTRARPROVEEDOR",
            "MODIFICARPROVEEDOR",
            "ELIMINARPROVEEDOR",
            // CU GESTIONAR PRODUCTO
            "OBTENERTIPOSPRODUCTOS",
            "REGISTRARTIPOPRODUCTO",
            "MODIFICARTIPOPRODUCTO",
            "ELIMINARTIPOPRODUCTO",
            "OBTENERPRODUCTOS",
            "REGISTRARPRODUCTO",
            "MODIFICARPRODUCTO",
            "ELIMINARPRODUCTO",
            
            "OBTENERTIPOSENTREGAS",
            "REGISTRARTIPOENTREGA",
            "MODIFICARTIPOENTREGA",
            "ELIMINARTIPOENTREGA",
            
            "OBTENERALMACENES",
            "REGISTRARALMACEN",
            "MODIFICARALMACEN",
            "ELIMINARALMACEN",
            
            "OBTENERPEDIDOS",
            "REGISTRARPEDIDO",
            "MODIFICARPEDIDO",
            "ELIMINARPEDIDO",
            "PROD",
            
            "OBTENERVENTAS",
            "REGISTRARVENTA",
            "MODIFICARVENTA",
            "ELIMINARVENTA",
            
            "OBTENERENTREGAS",
            "REGISTRARENTREGA",
            "MODIFICARENTREGA",
            "ELIMINARENTREGA",
            
            "OBTENERINVENTARIOS",
            "REGISTRARINVENTARIO",
            "MODIFICARINVENTARIO",
            "ELIMINARINVENTARIO",
            
            "REPORTENTCLIENTE",
            "REPORTEENTREGASADM",
            "REPORTEPEDCLIENTES",
            "VENTAMES",
            "VENTADIA"
            
    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
            // CASO DE USO GESTIONAR CLIENTE
            new Token(Token.FUNC, Token.REGISTRARCLIENTE, "REGISTRARCLIENTE"),
            new Token(Token.FUNC, Token.MODIFICARCLIENTE, "MODIFICARCLIENTE"),
            new Token(Token.FUNC, Token.ELIMINARCLIENTE, "ELIMINARCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTE, "OBTENERCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTES, "OBTENERCLIENTES"),
            
            
            new Token(Token.FUNC, Token.OBTENERADMINISTRATIVOS, "OBTENERADMINISTRATIVOS"),
            new Token(Token.FUNC, Token.REGISTRARADMINISTRATIVO, "REGISTRARADMINISTRATIVO"),
            new Token(Token.FUNC, Token.MODIFICARADMINISTRATIVO, "MODIFICARADMINISTRATIVO"),
            new Token(Token.FUNC, Token.ELIMINARADMINISTRATIVO, "ELIMINARADMINISTRATIVO"),
            
            new Token(Token.FUNC, Token.OBTENERPROVEEDORES, "OBTENERPROVEEDORES"),
            new Token(Token.FUNC, Token.REGISTRARPROVEEDOR, "REGISTRARPROVEEDOR"),
            new Token(Token.FUNC, Token.MODIFICARPROVEEDOR, "MODIFICARPROVEEDOR"),
            new Token(Token.FUNC, Token.ELIMINARPROVEEDOR, "ELIMINARPROVEEDOR"),
            // CASO DE USO GESTIONAR PRODUCTO
            new Token(Token.FUNC, Token.OBTENERTIPOSPRODUCTOS, "OBTENERTIPOSPRODUCTOS"),
            new Token(Token.FUNC, Token.REGISTRARTIPOPRODUCTO, "REGISTRARTIPOPRODUCTO"),
            new Token(Token.FUNC, Token.MODIFICARTIPOPRODUCTO, "MODIFICARTIPOPRODUCTO"),
            new Token(Token.FUNC, Token.ELIMINARTIPOPRODUCTO, "ELIMINARTIPOPRODUCTO"),
            
            new Token(Token.FUNC, Token.OBTENERPRODUCTOS, "OBTENERPRODUCTOS"),
            new Token(Token.FUNC, Token.REGISTRARPRODUCTO, "REGISTRARPRODUCTO"),
            new Token(Token.FUNC, Token.MODIFICARPRODUCTO, "MODIFICARPRODUCTO"),
            new Token(Token.FUNC, Token.ELIMINARPRODUCTO, "ELIMINARPRODUCTO"),
            
            new Token(Token.FUNC, Token.OBTENERTIPOSENTREGAS, "OBTENERTIPOSENTREGAS"),
            new Token(Token.FUNC, Token.REGISTRARTIPOPRODUCTO, "REGISTRARTIPOENTREGA"),
            new Token(Token.FUNC, Token.MODIFICARTIPOPRODUCTO, "MODIFICARTIPOENTREGA"),
            new Token(Token.FUNC, Token.ELIMINARTIPOPRODUCTO, "ELIMINARTIPOENTREGA"),
            
            new Token(Token.FUNC, Token.OBTENERALMACENES, "OBTENERALMACENES"),
            new Token(Token.FUNC, Token.REGISTRARALMACEN, "REGISTRARALMACEN"),
            new Token(Token.FUNC, Token.MODIFICARALMACEN, "MODIFICARALMACEN"),
            new Token(Token.FUNC, Token.ELIMINARALMACEN, "ELIMINARALMACEN"),
            
            new Token(Token.FUNC, Token.OBTENERPEDIDOS, "OBTENERPEDIDOS"),
            new Token(Token.FUNC, Token.REGISTRARPEDIDO, "REGISTRARPEDIDO"),
            new Token(Token.FUNC, Token.MODIFICARPEDIDO, "MODIFICARPEDIDO"),
            new Token(Token.FUNC, Token.ELIMINARPEDIDO, "ELIMINARPEDIDO"),
            new Token(Token.FUNC, Token.PROD, "PROD"),
            
            new Token(Token.FUNC, Token.OBTENERVENTAS, "OBTENERVENTAS"),
            new Token(Token.FUNC, Token.REGISTRARVENTA, "REGISTRARVENTA"),
            new Token(Token.FUNC, Token.MODIFICARVENTA, "MODIFICARVENTA"),
            new Token(Token.FUNC, Token.ELIMINARVENTA, "ELIMINARVENTA"),
            
            new Token(Token.FUNC, Token.OBTENERENTREGAS, "OBTENERENTREGAS"),
            new Token(Token.FUNC, Token.REGISTRARENTREGA, "REGISTRARENTREGA"),
            new Token(Token.FUNC, Token.MODIFICARENTREGA, "MODIFICARENTREGA"),
            new Token(Token.FUNC, Token.ELIMINARENTREGA, "ELIMINARENTREGA"),

            new Token(Token.FUNC, Token.OBTENERINVENTARIOS, "OBTENERINVENTARIOS"),
            new Token(Token.FUNC, Token.REGISTRARINVENTARIO, "REGISTRARINVENTARIO"),
            new Token(Token.FUNC, Token.MODIFICARINVENTARIO, "MODIFICARINVENTARIO"),
            new Token(Token.FUNC, Token.ELIMINARINVENTARIO, "ELIMINARINVENTARIO"),
            
            new Token(Token.FUNC, Token.REPORTENTCLIENTE, "REPORTENTCLIENTE"),
            new Token(Token.FUNC, Token.REPORTEENTREGASADM, "REPORTEENTREGASADM"),
            new Token(Token.FUNC, Token.REPORTEPEDCLIENTES, "REPORTEPEDCLIENTES"),
            
            new Token(Token.FUNC, Token.VENTAMES, "VENTAMES"),
            new Token(Token.FUNC, Token.VENTADIA, "VENTADIA")
     
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

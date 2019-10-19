/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Negocio.NCliente;
import Negocio.NProducto;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class Help {
    private static NCliente cliente;
    private static NProducto producto;
    
    public static final String PRINCIPAL = "Bienvenido!!!\n\n"
            + "A continuacion se listaran los comandos disponibles para interactuar con el Sistema\n"
            + "\"Ejemplo: REGISTRARCLIENTE HELP\"\n\n"            
            + "NOTA: TODAS LAS PETICIONES HACIA EL SISTEMA DEBEN SER ENVIADAS POR EL CAMPO \"ASUNTO\"\n\n"            
            + "------------------------- LISTA DE COMANDOS -----------------------\n\n"
            + "                      -CU1.GESTIONAR CLIENTES\n"
            + "                            REGISTRARCLIENTE\n"
            + "                            MODIFICARCLIENTE\n"
            + "                            ELIMINARCLIENTE\n"
            + "                            OBTENERCLIENTE\n"
            + "                            MOSTRARCLIENTES\n\n"
            +"                          Registrar Cliente\n\n"
            +"                          funcion:Permite registrar un cliente\n\n"  
            +"                          parametros:\n\n"
            +"                          - codigo:(String con comillas dobles)\n"
            +"                          - nit:(entero sin comillas)\n"
            +"                          - nombre:(String con comillas dobles)\n"
            +"                          - telefono:(String con comillas dobles)\n"
            +"                      ejemplo:\n\n"
            + "                     REGISTRARCLIENTE {\"cod1\",12345,\"joaquin chumacero\",\"7271731\"}\n\n"
            + ""
            + "                      -CU2.GESTIONAR ADMINISTRATIVOS*\n"
            + "                            REGISTRARADMINISTRATIVO\n"
            + "                            MODIFICARADMINISTRATIVO\n"
            + "                            ELIMINARADMNISTRATIVO\n"
            + "                            OBTENERADMINISTRATIVO\n"
            
            + "                       Registrar Administrativo\n\n"
            +"                          funcion:Permite registrar un administrativo\n\n"  
            +"                          parametros:\n\n"
            +"                          - codigo:(String con comillas dobles)\n"
            +"                          - nombre:(String con comillas dobles)\n"
            +"                          - telefono:(String con comillas dobles)\n"
            +"                          - cargo:(String con comillas dobles)\n"
            +"                          - fecha_ingreso:(String con comillas dobles)\n"
            +"                      ejemplo:\n\n"
            + "                     REGISTRARADMINISTRATIVO {\"cod1\",\"pepito\",\"123456\",\"7271731\",\"2019-01-02\"}\n\n"
            
            + "                      -CU3.GESTIONAR PROVEEDOR*\n"
            + "                            REGISTRARPROVEEDOR\n"
            + "                            MODIFICARPROVEEDOR\n"
            + "                            ELIMINARPROVEEDOR\n"
            + "                            OBTENERPROVEEDOR\n"
            
            + "                 Registrar Proveedor\n\n"
            +"                          funcion:Permite registrar un proveedor\n\n"  
            +"                          parametros:\n\n"
            +"                          - codigo:(String con comillas dobles)\n"
            +"                          - nombre:(String con comillas dobles)\n"
            +"                          - telefono:(String con comillas dobles)\n"
            +"                          - direccion:(String con comillas dobles)\n"
            +"                      ejemplo:\n\n"
            + "                     REGISTRARPROVEEDOR {\"cod111\",\"timiotul\",\"123456\",\"av paurito\"}\n\n"
            
            + "                      -CU4.GESTIONAR PRODUCTO*\n"
            + "                            OBTENERPRODUCTOS\n"
            + "                            REGISTRARPRODUCTOS\n"
            + "                            MODIFICARPRODUCTO\n"
            + "                            ELIMINARPRODUCTO\n"
            + "                     Registrar producto\n\n"
            +"                          funcion:Permite registrar un producto\n\n"  
            +"                          parametros:\n\n"
            +"                          - codigo:(String con comillas dobles)\n"
            +"                          - marca:(String con comillas dobles)\n"
            +"                          - modelo:(String con comillas dobles)\n"
            +"                          - precio:(String con comillas dobles)\n"
            +"                          - costo:(String con comillas dobles)\n"
            +"                          - loteid:(entero sin comillas)\n"
            +"                          - tipoproductoid:(entero sin comillas)\n"
            +"                      ejemplo:\n\n"
            + "                     REGISTRARPRODUCTO {\"pro111\",\"patito\",\"2019\",\"20\",\"15\",1,1}\n\n"
            
            + "                      -CU5.GESTIONAR PEDIDO*\n"            
            + "                            OBTENERPEDIDOS\n"
            + "                            REGISTRARPEDIDO\n"
            + "                            ELIMINARPEDIDO\n"
            
            + "                     Registrar Pedido\n\n"
            +"                          funcion:Permite registrar un Pedido\n\n"  
            +"                          parametros:\n\n"
            +"                          - codigo:(String con comillas dobles)\n"
            +"                          - fecha_registro:(String con comillas dobles)\n"
            +"                          - descripcion:(String con comillas dobles)\n"
            +"                          - monto_total:(String con comillas dobles)\n"
            +"                          - clienteid:(entero sin comillas)\n"
            +"                       funcion:PROD\n"
            +"                       recibe los ids de los productos seleccionados\n"
            +"                      ejemplo:\n\n"
            + "                     REGISTRARPEDIDO {\"ped111\",\"muchos productos\",20,1} PROD {1,2}\n\n"
            
            + "                      -CU6. GESTIONAR VENTAS*\n"
            + "                             OBTENERVENTAS\n"
            + "                             REGISTRARVENTA\n"
            + "                             ELIMINARVENTA\n"
            + "                     Registrar Venta\n\n"
            +"                          funcion:Permite registrar una venta\n\n"  
            +"                          parametros:\n\n"
            +"                          - codigo:(String con comillas dobles)\n"
            +"                          - fecha_emision:(String con comillas dobles)\n"
            +"                          - monto_total:(String con comillas dobles)\n"
            +"                          - pedidoid:(entero sin comillas)\n"
            + "                     REGISTRARPEDIDO {\"vent111\",\"2010-09-09\",\"20\",1}\n\n"
            
            + "                      -CU7. GESTIONAR ENTREGA*\n"
            + "                             OBTENERENTREGAS\n"
            + "                             REGISTRARENTREGA\n"
            + "                             ELIMINARENTREGA\n"
            + "                     Registrar Entrega\n\n"
            +"                          funcion:Permite registrar una entrega\n\n"  
            +"                          parametros:\n\n"
            +"                          - codigo:(String con comillas dobles)\n"
            +"                          - fecha_registro:(String con comillas dobles)\n"
            +"                          - fecha_entrega:(String con comillas dobles)\n"
            +"                          - destino:(String con comillas dobles)\n"
            +"                          - administrativoid:(entero sin comillas)\n"
            +"                          - notaventaidid:(entero sin comillas)\n"
            + "                     REGISTRARENTREGA {\"vent111\",\"2010-09-09\",\"20\",1}\n\n"
            
            + "                      -CU7. GESTIONAR INVENTARIO*\n"
            + "                             OBTENERINVENTARIOS\n"
            + "                             REGISTRARINVENTARIO\n"
            + "                             ELIMINARINVENTARIO\n"
            + "                     Registrar Inventario\n\n"
            +"                          funcion:Permite registrar un inventario\n\n"  
            +"                          parametros:\n\n"
            +"                          - codigo:(String con comillas dobles)\n"
            +"                          - fecha_ingreso:(String con comillas dobles)\n"
            +"                          - cantidad:(entero sin comillas)\n"
            +"                          - almacenid:(String con comillas dobles)\n"
            +"                          - proveedorid:(entero sin comillas)\n"
            + "                     REGISTRARINVENTARIO {\"inv111\",\"2010-09-09\",20,1,1}\n\n"
            + "                 -CU7. ESTADISTICA*\n"                   
            + "                       VENTAMES {2019}\n\n"
            + "                       VENTADIA {\"abril\", 2019}\n\n}"
            + "                   REPORTES \n\n"
            + "                       REPORTENTCLIENTE \n"
            + "                       todas las ventas de un cliente\n"
            + "                       REPORTENTCLIENTE {\"CL0001}\n"
            + "-----------------------------------------------------------------------------------------------\n\n";

  
    
}

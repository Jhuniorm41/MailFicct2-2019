/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Procesador.Analex;

/**
 *
 * @author Junior Guzman
 */
public abstract class TemplateMail {

    public abstract void registrar(Analex analex, String destinatario) throws Exception;

    public abstract void modificar(Analex analex, String destinatario) throws Exception;

    public abstract void eliminar(Analex analex, String destinatario) throws Exception;

    public abstract void listar(Analex analex, String destinatario) throws Exception;
}

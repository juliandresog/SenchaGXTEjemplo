/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.sencha.client;

import com.ejemplo.sencha.client.entidades.Usuario;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author josorio
 */
public interface GWTServiceAsync {

    /**
     * Ejemplo
     * @param s
     * @param callback 
     */
    public void myMethod(String s, AsyncCallback<String> callback);

    /**
     * Guardad entidad usuario en la base de datos.
     * @param usuario
     * @param crearCallBackGuardarCambios 
     */
    public void guardarEntidad(Usuario usuario, AsyncCallback<Integer> crearCallBackGuardarCambios);
}

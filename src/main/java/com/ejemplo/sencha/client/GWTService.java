/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.sencha.client;

import com.ejemplo.sencha.client.entidades.Usuario;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 *
 * @author josorio
 */
//@RemoteServiceRelativePath("gwtservice")
public interface GWTService extends RemoteService {

    /**
     * Ejemplo
     * @param s
     * @return 
     */
    public String myMethod(String s);
    
    /**
     * Guardo entidad en la BD
     * @param usuario
     * @return 
     */
    public Integer guardarEntidad(Usuario usuario);
}

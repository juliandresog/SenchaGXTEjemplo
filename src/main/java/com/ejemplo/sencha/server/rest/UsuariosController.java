/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.sencha.server.rest;

import com.ejemplo.sencha.server.dao.DaoUsuario;
import com.ejemplo.sencha.client.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author josorio
 */
@Controller
public class UsuariosController {
    
    @Autowired
    private DaoUsuario daoUsuario;
    
    /**
     * Lista todos los usuarios de la BD
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping( method = {RequestMethod.GET}, value = "/listarusuarios" )
    public ModelAndView obtener10Parqueaderos(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView retorno = new ModelAndView();
        List<Usuario> listaEntidades;
        try{            
            listaEntidades = daoUsuario.listar();
            if( listaEntidades == null )
            {
                listaEntidades = new ArrayList<Usuario>();
            }
        }catch(Exception e){
            System.out.println("Error "+e.getMessage());
            listaEntidades = new ArrayList<Usuario>();
        }
        retorno.addObject( "cantidad", listaEntidades.size() );
        retorno.addObject( "datos", listaEntidades );

        return retorno;
    }
    
}

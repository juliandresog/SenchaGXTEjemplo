/*

 * To change this template, choose Tools | Templates

 * and open the template in the editor.

 */
package com.ejemplo.sencha.server.servicios.impl;

import com.ejemplo.sencha.server.dao.DaoUsuario;
import com.ejemplo.sencha.client.entidades.Usuario;
import com.ejemplo.sencha.server.servicios.ServicioUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * 
 * @author josorio
 */
public class ServicioUsuariosImpl implements ServicioUsuarios, Serializable {

    @Autowired
    private DaoUsuario daoUsuarios;
    
    private static final Logger log = Logger.getLogger(ServicioUsuariosImpl.class);

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Usuario> usuarios() {

        List<Usuario> retorno = null;

        retorno = daoUsuarios.getActivos();

        if (retorno == null) {

            retorno = new ArrayList<Usuario>();

        }

        return retorno;

    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Integer guardarUsuario(Usuario usuario) {

        Integer retorno = 1;

        try {

            daoUsuarios.guardar(usuario);

            retorno = 0;

        } catch (ConstraintViolationException e) {
            log.error("Error", e);
            retorno = 4;

        } catch (DataIntegrityViolationException e) {
            log.error("Error", e);
            retorno = 4;

        } catch (Exception e) {
            log.error("Error", e);
            e.printStackTrace();

            retorno = 3;

        }

        return retorno;

    }

}

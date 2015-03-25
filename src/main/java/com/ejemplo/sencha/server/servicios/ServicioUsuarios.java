 /*

 * To change this template, choose Tools | Templates

 * and open the template in the editor.

 */
package com.ejemplo.sencha.server.servicios;

import com.ejemplo.sencha.client.entidades.Usuario;

import java.util.List;

/**
 *
 * Servicio con los metodos necesarios para gestionar los usuarios en la BD
 *
 * @author josorio
 *
 */
public interface ServicioUsuarios {

    /**
     *
     * Método para retornar una lista con todos los usuarios activos(no
     * eliminados)
     *
     * en la base de datos
     *
     * @return Una lista con todos los usuarios en la BD
     *
     */
    public List<Usuario> usuarios();

    /**
     *
     * Guarda el usuario entregado, si no existía lo crea
     *
     * @param usuario
     *
     * @return Alguno de los siguientes valores:
     *
     * <ul>
     *
     * <li>0: En caso de crear el usuario exitosamente</li>
     *
     * <li>1: En caso de el usuario no tener privilegios</li>
     *
     * <li>2: En caso de no haber sesión</li>
     *
     * <li>3: En caso de error</li>
     *
     * </ul>
     *
     */
    public Integer guardarUsuario(Usuario usuario);

}

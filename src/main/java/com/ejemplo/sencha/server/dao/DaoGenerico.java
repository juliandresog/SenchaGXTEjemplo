/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.sencha.server.dao;

import org.springframework.dao.DataAccessException;
import java.util.List;

/**
 * (DAO, Objeto de Acceso a Datos) Componente de acceso a datos reutilizable
 * para no reescribir las operaciones basicas sobre los objetos del dominio.
 *
 * @param <T>
 */
public interface DaoGenerico<T extends Object> {

    /**
     * Permite cargar una entidad de manera generica
     *
     * @param id el id de la entidad a cargar
     * @return La entidad cargada
     * @throws DataAccessException
     */
    public T getById(int id) throws DataAccessException;

    /**
     * Permite guardar una entidad de manera generica
     *
     * @param entidad la entidad a guardar
     * @throws DataAccessException
     */
    public void guardar(T entidad) throws DataAccessException;

    /**
     * Permite borrar una entidad de manera generica
     *
     * @param entidad La entidad a borrar
     * @throws DataAccessException
     */
    public void borrar(T entidad) throws DataAccessException;

    /**
     * Refresca el objeto desde la base de datos aplicando bloqueo para update
     *
     * @param entity
     */
    public void refrescarBloquear(T entity);

    /**
     * Lista todas las entidades en el repositorio del tipo definido
     *
     * @return El listado de instancias
     * @throws DataAccessException
     */
    public List<T> listar() throws DataAccessException;
}

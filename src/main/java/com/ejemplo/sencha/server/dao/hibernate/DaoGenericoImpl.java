/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.sencha.server.dao.hibernate;

import com.ejemplo.sencha.server.dao.DaoGenerico;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.LockMode;

/**
 * {@inheritDoc}
 */
public class DaoGenericoImpl<T> implements DaoGenerico<T> {

    protected Class<T> domainClass = extraerClaseDominio();
    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    protected Class<T> extraerClaseDominio() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public T getById(int id) {
        return (T) sessionFactory.getCurrentSession().get(extraerClaseDominio(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void guardar(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void borrar(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void refrescarBloquear(T entity) {
        sessionFactory.getCurrentSession().refresh(entity, LockMode.UPGRADE);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> listar() {
        StringBuilder sb = new StringBuilder("from ");
        sb.append(extraerClaseDominio().getName());
        sb.append(" entity");
        return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
    }
}

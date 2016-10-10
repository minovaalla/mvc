package by.pvt.minova.carrent.dao;

import by.pvt.minova.carrent.exceptions.DaoException;
import by.pvt.minova.carrent.entities.Entity;

import java.util.List;

/**
 * Describes the interface <b>Entity</b>
 * @author minova
 * @version 1.0
 */
public interface IDao<T extends Entity> {
    void add(T entity) throws DaoException;
    List<T> getAll() throws DaoException;
    T getById(int id) throws DaoException;
    void delete(int id) throws DaoException;
   }

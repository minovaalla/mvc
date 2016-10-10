package by.pvt.minova.carrent.services;

import by.pvt.minova.carrent.entities.Entity;

import java.sql.Connection;

public abstract class AbstractService<T extends Entity> implements IService<T>{
    protected Connection connection;
}

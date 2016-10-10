package by.pvt.minova.carrent.dao;

import by.pvt.minova.carrent.entities.Entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AbstractDao<T extends Entity> implements IDao <T>{
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet result;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fransergio-dev
 * @param <T>
 * @param <I>
 */
public interface DaoGeneric<T, I extends Serializable>{
    	public void save(Object T)throws SQLException, ClassNotFoundException;
	public void update(Object T)throws SQLException, ClassNotFoundException;
	public void delete(Object T)throws SQLException, ClassNotFoundException;
	public T getById(Object T)throws SQLException, ClassNotFoundException;
	public List<T> getAll()throws SQLException, ClassNotFoundException;
}

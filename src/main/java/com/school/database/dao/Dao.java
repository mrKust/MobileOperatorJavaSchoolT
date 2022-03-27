package com.school.database.dao;

import java.util.List;

public interface Dao<T> {

    T get(int id);

    T getByName(String name);

    List<T> getAll();

    void save(T t);

    void delete(int id);
}
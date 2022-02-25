package com.school.service;

import java.util.List;

public interface ServiceMVC<T> {

    List<T> getAll();

    void save(T t);

    T get(int id);

    void delete(int id);
}

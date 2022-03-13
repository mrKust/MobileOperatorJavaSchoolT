package com.school.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceMVC<T> {

    @Transactional(propagation = Propagation.NESTED)
    List<T> getAll();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void save(T t);

    @Transactional(propagation = Propagation.NESTED)
    T get(int id);

    @Transactional(propagation = Propagation.NESTED)
    T getByName(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void delete(int id);

}

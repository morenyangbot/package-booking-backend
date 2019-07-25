package com.oocl.packagebooking.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID extends Serializable> {
    T save(T t);

    List<T> findAll();

    void deleteById(ID id);

    Optional<T> findById(ID id);

}

package com.demo.core.controller;

import com.demo.core.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface ICrudController<E extends BaseEntity<PK>, PK extends Serializable> {

    List<E> read();
    E read(PK id);
    E create(E entity);
    E update(Long id, E entity);
    void delete(PK id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.core.service;

import com.demo.core.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cakmak
 */
public interface ICrudService<E extends BaseEntity<PK>, PK extends Serializable> {

    List<E> read();
    E read(PK id);
    E create(E entity);
    E update(Long id, E entity);
    void delete(PK id);
}

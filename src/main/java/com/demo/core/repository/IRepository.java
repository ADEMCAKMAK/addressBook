/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.core.repository;


import com.demo.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cakmak
 */
@NoRepositoryBean
public interface IRepository<E extends BaseEntity<PK>, PK extends Serializable>
        extends JpaRepository<E,PK> {

    List<E> read();
    E read(PK id);
    E create(E entity);
    E update(PK id, E entity);
    void delete(PK id);
}

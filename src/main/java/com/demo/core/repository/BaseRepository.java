/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.core.repository;


import com.demo.core.entity.BaseEntity;
import com.demo.core.exception.NotFoundException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cakmak
 */
@Transactional(readOnly=true)
public class BaseRepository<E extends BaseEntity<PK>, PK extends Serializable>
        extends SimpleJpaRepository<E, PK>
        implements IRepository<E, PK> {

    private final EntityManager entityManager;
    private final JpaEntityInformation<E, PK> jpaEntityInformation;

    public BaseRepository(JpaEntityInformation<E, PK> jpaEntityInformation, EntityManager entityManager) {
        super(jpaEntityInformation, entityManager);
        this.entityManager = entityManager;
        this.jpaEntityInformation = jpaEntityInformation;
    }

    @Override
    public List<E> read() {
        return this.findAll()
                .stream()
                .filter(item -> !item.getDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public E read(PK id) {
        return this.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public E create(E entity) {
        return this.save(entity);
    }

    @Override
    @Transactional
    public E update(PK id, E entity) {
        if( !id.equals(entity.getId()) )
            throw new RuntimeException("id does not match");
        return this.save(entity);
    }

    @Override
    @Transactional
    public void delete(PK id) {
        this.deleteById(id);
    }

}

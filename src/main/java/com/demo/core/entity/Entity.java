package com.demo.core.entity;


import java.io.Serializable;

/**
 *
 * @author cakmak
 */
public interface Entity<PK extends Serializable> {

    PK getId();

    void setId(PK id);

}
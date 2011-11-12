package org.experimenter.repository.entity;

import java.io.Serializable;

public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

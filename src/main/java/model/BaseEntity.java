package model;

import javax.persistence.Entity;
import javax.persistence.Table;

public class BaseEntity {

    private Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

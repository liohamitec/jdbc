package model;


import javax.persistence.Entity;
import javax.persistence.Table;

public class NamedEntity extends BaseEntity {

    private String name;

    public NamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public NamedEntity(String name) {
        super(null);
        this.name = name;
    }

    public NamedEntity() {
        super(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}

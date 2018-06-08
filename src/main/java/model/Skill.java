package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Skill extends NamedEntity {
    public Skill() {
    }

    private Set<Developer> developers = new HashSet<>();

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Skill(Long id, String name) {
        super(id, name);
    }

    public Skill(String name) {
        super(name);
    }
}
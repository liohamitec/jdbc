package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skills")
@AttributeOverride(name = "name", column = @Column(name = "skill"))
public class Skill extends NamedEntity {
    public Skill() {
    }

    @ManyToMany(mappedBy = "skills")
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
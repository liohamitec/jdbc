package model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer
        extends NamedEntity {

    @Column(name = "age")
    private Integer age;

    @Column(name = "salary")
    private Integer salary;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "developers_skills",
            joinColumns = { @JoinColumn(name = "developers_id") },
            inverseJoinColumns = { @JoinColumn(name = "skills_id") }
    )
    private Set<Skill> skills = new HashSet<>();

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Developer(Long id, String name) {
        super(id, name);
    }

    public Developer(String name) {
        super(name);
    }

    public Developer(Long id, String name, Integer age) {
        super(id, name);
        this.age = age;
    }

    public Developer(Long id, String name, Integer age, int salary) {
        super(id, name);
        this.age = age;
        this.salary = salary;
    }

    public Developer(String name, Integer age, Integer salary) {
        super(name);
        this.age = age;
        this.salary = salary;
    }

    public Developer() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return  "{" +
                "id='" + getId() + "\', " +
                "name='" + getName() + "\', " +
                "age='" + getAge() + "\', " +
                "salary='" + getSalary() + "\' " +
                '}';
    }
}

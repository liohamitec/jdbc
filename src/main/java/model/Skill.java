package model;

public class Skill extends NamedEntity {
    public Skill(Long id, String name) {
        super(id, name);
    }

    public Skill(String name) {
        super(name);
    }
}
package model;

public class Skill extends BaseEntity {
    private String skill;

    public Skill(Long id,String skill) {
        super.setId(id);
        this.skill = skill;
    }

    public Skill() {
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}

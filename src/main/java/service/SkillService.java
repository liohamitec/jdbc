package service;

import model.JdbcSkillRepositoryImpl;
import model.Skill;
import repository.SkillRepository;
import util.ConnectionUtil;

import java.sql.Connection;
import java.util.Collection;

public class SkillService {
    SkillRepository skillRepImpl;

    //public SkillService(int action, Skill skill)

    public SkillService(Connection conn) {
        skillRepImpl = new JdbcSkillRepositoryImpl(conn);
    }

    public void insert(Skill skill) {
        skillRepImpl.add(skill);
    }

    public int delete(Long id) {
        return skillRepImpl.remove(id);
    }

    public int update(Skill newSkill) {
        return skillRepImpl.update(newSkill);
    }

    public Skill getById(Long id) {
        return skillRepImpl.getById(id);
    }

    public Collection<Skill> getAll() {
        return skillRepImpl.getAll();
    }
}

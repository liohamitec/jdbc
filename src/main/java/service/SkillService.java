package service;

import repository.jdbc.JdbcSkillRepositoryImpl;
import model.Skill;
import repository.SkillRepository;

import java.sql.Connection;
import java.util.Collection;

public class SkillService {
    SkillRepository skillRepImpl;

    public SkillService(Connection conn) {
        skillRepImpl = new JdbcSkillRepositoryImpl(conn);
    }

    public int insert(Skill skill) {
        return skillRepImpl.add(skill);
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

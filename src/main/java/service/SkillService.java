package service;

import model.JdbcSkillRepositoryImpl;
import model.Skill;
import repository.SkillRepository;
import util.ConnectionUtil;

import java.sql.Connection;

public class SkillService {
    SkillRepository skillRepImpl;

    //public SkillService(int action, Skill skill)

    public SkillService(Connection conn) {
        skillRepImpl = new JdbcSkillRepositoryImpl(conn);
    }

    public void insert(Skill skill) {
        skillRepImpl.add(skill);
    }

    public void delete(Long id) {
        skillRepImpl.remove(id);
    }

    public void update(Skill newSkill) {
        skillRepImpl.update(newSkill);
    }

    public void getById(Long id) {
        skillRepImpl.getById(id);
    }

    public void getAll() {
        skillRepImpl.getAll();
    }
}

package repository;

import model.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class SkillRepository
    implements GenericRepository<Skill,Long> {
    private final static String SELECT_PREPARED = "SELECT * FROM skills WHERE id=?;";
    private final static String INSERT_PREPARED = "INSERT INTO skills VALUES ?;";
    private final static String REMOVE_PREPARED = "DELETE FROM skills WHERE id=?;";
    private final static String UPDATE_PREPARED = "UPDATE skills SET skill=? WHERE id=?;";

    private Connection connection;

    SkillRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Skill skill) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_PREPARED);
            ps.setString(1, skill.getSkill());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try {
            PreparedStatement ps = connection.prepareStatement(REMOVE_PREPARED);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean update(Skill skill) {
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_PREPARED);
            ps.setString(1, skill.getSkill());
            ps.setLong(2, skill.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Skill getById(Long id) {
        Skill skill = null;
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_PREPARED);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            String skillName = null;
            if (rs != null)
                skillName = rs.getString("skill");

            skill = new Skill(id,skillName);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return skill;
        }
    }

    @Override
    public Collection<Skill> getAll() {
        Collection<Skill> collection = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM skills;");
            ResultSet rs = ps.executeQuery();

            Long id = null;
            String skill = null;
            while (rs.next()) {
                id = rs.getLong("id");
                skill = rs.getString("skill");
                collection.add(new Skill(id,skill));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return collection;
        }
    }


}

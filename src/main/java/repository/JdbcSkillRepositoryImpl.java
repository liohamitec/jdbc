package repository;

import model.Skill;
import repository.SkillRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcSkillRepositoryImpl implements SkillRepository {
    private final static String SELECT_SKILL_QUERY = "SELECT * FROM skills WHERE id=?;";
    private final static String INSERT_SKILL_QUERY = "INSERT INTO skills VALUES ?;";
    private final static String REMOVE_SKILL_QUERY = "DELETE FROM skills WHERE id=?;";
    private final static String UPDATE_SKILL_QUERY = "UPDATE skills SET skill=? WHERE id=?;";

    private Connection connection;

    public JdbcSkillRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Skill skill) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_SKILL_QUERY)) {
            ps.setString(1, skill.getName());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int remove(Long id) {
        int removedAmount = 0;

        try (PreparedStatement ps = connection.prepareStatement(REMOVE_SKILL_QUERY)) {
            ps.setLong(1, id);
            removedAmount = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return removedAmount;
    }

    @Override
    public int update(Skill skill) {
        int updatedAmount = 0;

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_SKILL_QUERY)) {
            ps.setString(1, skill.getName());
            ps.setLong(2, skill.getId());
            updatedAmount = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return updatedAmount;
    }

    @Override
    public Skill getById(Long id) {
        Skill skill = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SELECT_SKILL_QUERY);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            String skillName = null;
            if (rs != null)
                skillName = rs.getString("skill");

            skill = new Skill(id,skillName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return skill;
    }

    @Override
    public Collection<Skill> getAll() {
        Collection<Skill> collection = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM skills;");
             ResultSet rs = ps.executeQuery()) {

            Long id;
            String skill;
            while (rs.next()) {
                id = rs.getLong("id");
                skill = rs.getString("skill");
                collection.add(new Skill(id,skill));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return collection;
    }
}

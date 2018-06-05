package repository;

import model.Developer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {
    private final static String SELECT_DEVELOPER_QUERY = "SELECT * FROM developers WHERE id=?;";
    private final static String INSERT_DEVELOPER_QUERY = "INSERT INTO developers(name,age,salary) VALUES (?,?,?);";
    private final static String REMOVE_DEVELOPER_QUERY = "DELETE FROM developers WHERE id=?;";
    private final static String UPDATE_DEVELOPER_QUERY =
            "UPDATE developers SET name=?,age=?,salary=? WHERE id=?;";
    private final static String REMOVE_DEVELOPERS_SKILLS_QUERY =
            "DELETE FROM developers_skills WHERE developers_id=?;";
    private final static String REMOVE_DEVELOPERS_COMPANIES_QUERY =
            "DELETE FROM developers_companies WHERE developers_id=?;";
    private final static String REMOVE_DEVELOPERS_PROJECTS_QUERY =
            "DELETE FROM developers_projects WHERE developers_id=?;";

    private Connection connection;

    public JdbcDeveloperRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(Developer developer) {
        int amount = 0;

        try (PreparedStatement ps = connection.prepareStatement(INSERT_DEVELOPER_QUERY)) {
            ps.setString(1, developer.getName());
            ps.setInt(2, developer.getAge());
            ps.setInt(3, developer.getSalary());
            amount = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return amount;
    }

    @Override
    public int remove(Long id) {
        int removedAmount = 0;

        try (PreparedStatement ps_developers = connection.prepareStatement(REMOVE_DEVELOPER_QUERY);
             PreparedStatement ps_dev_skills = connection.prepareStatement(REMOVE_DEVELOPERS_SKILLS_QUERY);
             PreparedStatement ps_dev_companies = connection.prepareStatement(REMOVE_DEVELOPERS_COMPANIES_QUERY);
             PreparedStatement ps_dev_projects = connection.prepareStatement(REMOVE_DEVELOPERS_PROJECTS_QUERY)) {

            connection.setAutoCommit(false);

            ps_dev_skills.setLong(1,id);
            ps_dev_skills.executeUpdate();

            ps_dev_companies.setLong(1,id);
            ps_dev_companies.executeUpdate();

            ps_dev_projects.setLong(1,id);
            ps_dev_projects.executeUpdate();

            ps_developers.setLong(1, id);
            removedAmount = ps_developers.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return removedAmount;
    }

    @Override
    public int update(Developer developer) {
        int updatedAmount = 0;

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_DEVELOPER_QUERY)) {
            ps.setString(1, developer.getName());
            ps.setInt(2, developer.getAge());
            ps.setInt(3, developer.getSalary());
            ps.setLong(4, developer.getId());
            updatedAmount = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return updatedAmount;
    }

    @Override
    public Developer getById(Long id) {
        Developer developer = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SELECT_DEVELOPER_QUERY);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            String devName = null;
            int age = 0;
            int salary = 0;

            if (rs.next()) {
                devName = rs.getString("name");
                age = rs.getInt("age");
                salary = rs.getInt("salary");
            }
            developer = new Developer(id,devName,age,salary);
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

        return developer;
    }

    @Override
    public Collection<Developer> getAll() {
        Collection<Developer> collection = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM developers;");
             ResultSet rs = ps.executeQuery()) {

            Long id;
            String name;
            int age;
            int salary;
            while (rs.next()) {
                id = rs.getLong("id");
                name = rs.getString("name");
                age = rs.getInt("age");
                salary = rs.getInt("salary");

                collection.add(new Developer(id,name,age,salary));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return collection;
    }
}

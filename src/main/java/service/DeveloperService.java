package service;

import model.Developer;
import repository.DeveloperRepository;
import repository.JdbcDeveloperRepositoryImpl;

import java.sql.Connection;
import java.util.Collection;

public class DeveloperService {
    DeveloperRepository developerRepoImpl;

    public DeveloperService(Connection conn) {
        developerRepoImpl = new JdbcDeveloperRepositoryImpl(conn);
    }

    public int insert(Developer dev) {
        return developerRepoImpl.add(dev);
    }

    public int delete(Long id) {
        return developerRepoImpl.remove(id);
    }

    public int update(Developer newDeveloper) {
        return developerRepoImpl.update(newDeveloper);
    }

    public Developer getById(Long id) {
        return developerRepoImpl.getById(id);
    }

    public Collection<Developer> getAll() {
        return developerRepoImpl.getAll();
    }
}

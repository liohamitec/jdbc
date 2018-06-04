package repository;

import java.util.Collection;

public interface GenericRepository<T,ID> {
    void add(T t);

    void remove(ID id);

    boolean update(T t);

    T getById(ID id);

    Collection<T> getAll();
}

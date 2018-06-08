package repository;

import java.util.Collection;

public interface GenericRepository<T,ID> {
    boolean add(T t);

    int remove(ID id);

    int update(T t);

    T getById(ID id);

    Collection<T> getAll();
}

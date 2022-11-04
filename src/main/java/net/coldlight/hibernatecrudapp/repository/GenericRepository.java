package net.coldlight.hibernatecrudapp.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
        List<T> getAll();
        T getByID(ID id);
        T save(T t);
        T update(T t);
        void delete(T t);
}


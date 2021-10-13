package persistence.dao;

import persistence.model.Model;

import java.util.List;

public interface Dao<T extends Model> {

    public List<T> findAll();

    public T findById(Integer id);

    public T saveOrUpdate(T t);

    public void delete(Integer id);
}

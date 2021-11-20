package nursingManagement.persistence.dao;

import nursingManagement.persistence.model.Model;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface Dao<T extends Model> {

    public List<T> findAll();

    public T findById(Integer id);

    public T saveOrUpdate(T t) throws SQLIntegrityConstraintViolationException;

    public void delete(Integer id);
}

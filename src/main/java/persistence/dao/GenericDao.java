package persistence.dao;

import persistence.model.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericDao<T extends Model> implements Dao<T> {

    protected Class<T> modelType;

    @PersistenceContext
    protected EntityManager em;

    public GenericDao(Class<T> modelType){
        this.modelType = modelType;
    }

    public void setEm(EntityManager em){
        this.em = em;
    }

    //Criteria queries are a programmatic and typesafe
    //way to express a query
    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(modelType);
        Root<T> root = criteriaQuery.from(modelType);

        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T findById(Integer id) {
        return em.find(modelType,id);
    }

    @Override
    public T saveOrUpdate(T t) {
        return em.merge(t);
    }

    @Override
    public void delete(Integer id) {
        em.remove(em.find(modelType,id));
    }
}

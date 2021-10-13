package persistence.dao;

import persistence.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class UserDao extends GenericDao<User> {

    public UserDao(){
        super(User.class);
    }

    public User findByEmail(String userEmail){

        //Get Criteria Builder object from entity manager
        CriteriaBuilder builder = em.getCriteriaBuilder();

        //Create a new criteria query instance for the user entity
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);

        //get the root of the query, from where all navigation starts
        Root<User> root = criteriaQuery.from(User.class);

        //specify the item that is to be returned in the query result
        criteriaQuery.select(root);

        // add query restrictions
        criteriaQuery.where(builder.equal(root.get("email"),userEmail));

        //create and execute a query using the criteria
        return em.createQuery(criteriaQuery).getSingleResult();
    }

}

package nursingManagement.persistence.dao;

import nursingManagement.persistence.model.Act;
import org.springframework.stereotype.Repository;

@Repository
public class ActDao extends GenericDao<Act> {

    public ActDao() {
        super(Act.class);
    }
}

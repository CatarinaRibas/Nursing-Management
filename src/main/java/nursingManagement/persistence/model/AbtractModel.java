package nursingManagement.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class AbtractModel implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public Integer getId(){
        return this.id;
    }

    @Override
    public void setId(Integer id){
        this.id = id;
    }




}

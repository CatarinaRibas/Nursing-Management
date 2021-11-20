package nursingManagement.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acts")
public class Act implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date nursing_date;

    private Integer minimum_blood_pressure;

    private Integer maximum_blood_pressure;

    private Integer heart_beat;

    private String comments;

    @ManyToOne
    private Patient patient;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNursing_date(){
        return this.nursing_date;
    }

    public void setNursing_date(Date nursing_date){
        this.nursing_date = nursing_date;
    }

    public Integer getMinimum_blood_pressure(){
        return this.minimum_blood_pressure;
    }

    public void setMinimum_blood_pressure(Integer minimum_blood_pressure){
        this.minimum_blood_pressure = minimum_blood_pressure;
    }

    public Integer getMaximum_blood_pressure(){
        return this.maximum_blood_pressure;
    }

    public void setMaximum_blood_pressure(Integer maximum_blood_pressure){
        this.maximum_blood_pressure = maximum_blood_pressure;
    }

    public Integer getHeart_beat(){
        return this.heart_beat;
    }

    public void setHeart_beat(Integer heart_beat){
        this.heart_beat = heart_beat;
    }

    public String getComments(){
        return this.comments;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public Patient getPatient(){
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

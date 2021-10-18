package nursingManagement.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patients")
public class Patient implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Date birthdate;

    private String address;

    private String village;

    private Integer phone;

    private String email;

    @Override
    public Integer getId(){
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Date getBirthdate(){
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate){
        this.birthdate = birthdate;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getVillage(){
        return this.village;
    }

    public void setVillage(String village){
        this.village = village;
    }

    public Integer getPhone(){
        return this.phone;
    }

    public void setPhone(Integer phone){
        this.phone = phone;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String toString(){
        return "Patient{" +
                "name=" + name + '\'' +
                "birthdate= " + birthdate + '\'' +
                "address= " + address + '\'' +
                "village= " + village + '\'' +
                "phone= " + phone + '\'' +
                "email= " + email + '\'' +
                "}";
    }

}

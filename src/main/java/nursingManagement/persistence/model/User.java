package nursingManagement.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Name should have at least 3 letters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have at least 6 letters")
    private String password;

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){

        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                "}";

    }



}

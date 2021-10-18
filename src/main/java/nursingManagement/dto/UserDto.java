package nursingManagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @NotNull(message = "First name is mandatory")
    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 64)
    private String name;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }


}

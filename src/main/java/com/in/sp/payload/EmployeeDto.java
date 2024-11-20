package com.in.sp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {


    private  long id;

    @NotBlank
    @Size(min=3,message = "At list 3 char requre")
    private String name;

    @Email
    private String email;


    @Size(min=10,max=10,message="Should be 10 digits")
    private String mobile;

}

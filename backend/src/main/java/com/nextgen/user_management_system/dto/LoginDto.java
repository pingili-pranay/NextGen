package com.nextgen.user_management_system.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid emailId format!!!")
    @NotNull
    private String emailId;

    @Size(max = 12, message = "Password must not exceed 12 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z]).{1,20}$",
            message = "Password must have at least one uppercase and one lowercase letter")
    @NotNull
    private String password;

}
